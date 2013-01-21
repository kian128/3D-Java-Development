package game;

import org.lwjgl.util.vector.Vector3f;

import entity.EntityPlayer;

public class Movement {
	
	private static float movCoeff = 0.001f;
	public static float gravity = 0;
	public static boolean isJumping = true;
	
	public static EntityPlayer player = new EntityPlayer();
	
	public static void changePosition(Vector3f position, float rotation, float xDirection, float yDirection, float zDirection) {
		position.x += (float) Math.sin(Math.toRadians(rotation)) * xDirection * movCoeff * Main.delta;
		position.z += (float) Math.cos(Math.toRadians(rotation)) * zDirection * movCoeff * Main.delta;
		position.y += yDirection * movCoeff * Main.delta;
	}
	
}
