package game;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import connections.ConnectedPlayer;

import entity.EntityCube;
import entity.EntityPlayer;

import render.VoxelRendering;

public class Main {
	
	public static int width = 1024, height = 640;
	public static String title = "3D Java Development";
	public static int fov = 90;
	public static float renderNear = 0.001f, renderFar = 100;
	public static int maxFps = 60;
	
	public static boolean running = true;
	public static float delta;
	
	public static boolean isCameraMovable = false;
	public static boolean isZoomedMax = false;
	public static boolean isZoomedMin = false;
	
	private static int shaderProgram;
	public static final String VERTEX_SHADER_LOCATION = "src/render/shader.vs";
	public static final String FRAGMENT_SHADER_LOCATION = "res/render/shader.fs";
	
	public static ConnectedPlayer playerConnected = new ConnectedPlayer(0, 1, 0, 0.5f, 2.0f);
	
	Screen screen = new Screen();
	Camera camera = new Camera();
	Timing timing = new Timing();
	EntityPlayer player = new EntityPlayer();
	Input input = new Input();
	
	public Main() {
		screen.initDisplay(width, height, title);
		screen.initDisplayGluPerspective(fov, width, height, renderNear, renderFar);
		
		Lighting.initLighting();
		
		glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);
        
		VoxelRendering.initialiseChunk(0, 0, 0);
		
		initPlayerPosition();
		Mouse.setGrabbed(true);
		
		while(running) {
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			
			delta = (int) timing.getDelta();
			
			//Lighting.enableLightPosition(GL_LIGHT0, player.getX() + player.getWidth() / 2, player.getY() + player.getHeight(), player.getZ() + player.getWidth() / 2);
			
			//setPlayerBounds();
			camera.translate();
			camera.moveCamera();
			input.setKeys();
			input.update();
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
		player.setY(10);
		player.setX(5);
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
