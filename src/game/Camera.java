package game;

import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import entity.EntityPlayer;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class Camera {
	
	EntityPlayer player = new EntityPlayer();
	
	public static float cameraDistance = 5;
	public float mouseSensitivity = 20;
	public static float scrollSpeed = 3;
	
	public void moveCamera() {
		if(Mouse.isGrabbed()) {
			float mouseDY = Mouse.getDY() * mouseSensitivity * 0.01f;
			float mouseDX = Mouse.getDX() * mouseSensitivity * 0.01f;
			if (player.getRotX() - mouseDY >= player.maxViewNeg && player.getRotX() - mouseDY <= player.maxViewPos) {
                player.changeRotX(-mouseDY);
            } else if (player.getRotX() - mouseDY < player.maxViewNeg) {
                player.setRotX(player.maxViewNeg);
            } else if (player.getRotX() - mouseDY > player.maxViewPos) {
                player.setRotX(player.maxViewPos);
            }
			player.changeRotY(mouseDX);
		}
	}
	
	public void translate() {
		glLoadIdentity();
		glRotatef(player.getRotX(), 1, 0, 0);
        glRotatef(player.getRotY(), 0, 1, 0);
        glRotatef(player.getRotZ(), 0, 0, 1);
        glTranslatef(-player.getX() - player.getWidth() / 2, -player.getY() - player.getHeight(), -player.getZ() - player.getWidth() / 2);
	}
}
