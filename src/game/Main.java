package game;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import connections.ConnectedPlayer;

import entity.EntityCube;
import entity.EntityPlayer;

import render.VoxelRendering;

public class Main {
	
	public static int width = 1280, height = 720;
	public static String title = "3D Java Development";
	public static int fov = 90;
	public static float renderNear = 0.001f, renderFar = 100;
	public static int maxFps = 60;
	
	public static boolean running = true;
	public static int delta;
	
	public static boolean isCameraMovable = false;
	public static boolean isZoomedMax = false;
	public static boolean isZoomedMin = false;
	
	public static ConnectedPlayer playerConnected = new ConnectedPlayer(0, 1, 0, 0.5f, 2.0f);
	
	Screen screen = new Screen();
	Camera camera = new Camera();
	Timing timing = new Timing();
	EntityPlayer player = new EntityPlayer();
	
	public Main() {
		screen.initDisplay(width, height, title);
		screen.initDisplayGluPerspective(fov, width, height, renderNear, renderFar);
		
		Lighting.initLighting();
		//hello
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        glColorMaterial(GL_FRONT, GL_DIFFUSE);
        
		VoxelRendering.initialiseChunk(0, 0, 0);
		
		initPlayerPosition();
		Mouse.setGrabbed(true);
		
		while(running) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			delta = (int) timing.getDelta();
			
			Lighting.enableLightPosition(GL_LIGHT0, player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, player.getZ() + player.getWidth() / 2);
			
			//setPlayerBounds();
			camera.translate();
			camera.moveCamera();
			new Input();
			timing.updateFPS();
			
			VoxelRendering.renderChunk();
			
			ConnectedPlayer.render();
			
			if(Display.isCloseRequested()) {
				running = false;
			}
			Display.update();
			Display.sync(maxFps);
		}
		
		Display.destroy();
		System.exit(0);
	}
	
	public void initPlayerPosition() {
		player.setY(1);
        player.setZ(5);
        //player.setRotX(45);
	}
	
	public void setPlayerBounds() {
		if(player.getRotX() <= 0) player.setRotX(0);
		if(player.getRotX() >= 90) player.setRotX(90);
		if(player.getY() <= 2) isZoomedMax = true;
			else isZoomedMax = false;
		if(player.getY() >= 8) isZoomedMin = true;
			else isZoomedMin = false;
	}
	
	public static void main(String args[]) {
		new Main();
	}

}
