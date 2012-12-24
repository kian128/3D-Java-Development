package game;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import entity.Entity;
import entity.EntityList;
import entity.EntityPlayer;

public class Input {
	
	public static EntityPlayer player = new EntityPlayer();
	public static Camera camera = new Camera();
	
	public boolean key_MoveForward = Keyboard.isKeyDown(Keyboard.KEY_W);
	public boolean key_MoveBackward = Keyboard.isKeyDown(Keyboard.KEY_S);
	public boolean key_MoveLeft = Keyboard.isKeyDown(Keyboard.KEY_A);
	public boolean key_MoveRight = Keyboard.isKeyDown(Keyboard.KEY_D);
	
	public boolean key_Jump = Keyboard.isKeyDown(Keyboard.KEY_SPACE);
	public boolean key_Crouch = Keyboard.isKeyDown(Keyboard.KEY_LCONTROL);
	public boolean key_PanFaster = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT);
	
	public boolean key_LookUp = Keyboard.isKeyDown(Keyboard.KEY_UP);
	public boolean key_LookDown = Keyboard.isKeyDown(Keyboard.KEY_DOWN);
	
	public boolean key_Exit = Keyboard.isKeyDown(Keyboard.KEY_ESCAPE);
	
	public boolean key_LeftMouse = Mouse.isButtonDown(0);
	public boolean key_RightMouse = Mouse.isButtonDown(1);
	
	public Input() {
		if(key_PanFaster) player.multiplySpeed(2);
		
			/** MOVEMENT **/
			Vector3f newPosition = new Vector3f(player.getX(), player.getY(), player.getZ());
			/*if(key_MoveForward) player.calculateNewPlayerPositionHorizontal(player.getRotY(), player.getSpeed(), -player.getSpeed());
			if(key_MoveBackward) player.calculateNewPlayerPositionHorizontal(player.getRotY(), -player.getSpeed(), player.getSpeed());
			if(key_MoveLeft) player.calculateNewPlayerPositionHorizontal(player.getRotY() + 90, -player.getSpeed(), player.getSpeed());
			if(key_MoveRight) player.calculateNewPlayerPositionHorizontal(player.getRotY() + 90, player.getSpeed(), -player.getSpeed());
			
			if(key_Jump) player.calculateNewPlayerPositionVertical(player.getSpeed());
			if(key_Crouch) player.calculateNewPlayerPositionVertical(-player.getSpeed());*/
			
			if(key_MoveForward) {
				newPosition.x += (float) Math.sin(Math.toRadians(player.getRotY())) * player.getSpeed() * 0.001f * Main.delta;
				newPosition.z += (float) Math.cos(Math.toRadians(player.getRotY())) * -player.getSpeed() * 0.001f * Main.delta;
			}
			if(key_MoveBackward) {
				newPosition.x += (float) Math.sin(Math.toRadians(player.getRotY())) * -player.getSpeed() * 0.001f * Main.delta;
				newPosition.z += (float) Math.cos(Math.toRadians(player.getRotY())) * player.getSpeed() * 0.001f * Main.delta;
			}
			if(key_MoveLeft) {
				newPosition.x += (float) Math.sin(Math.toRadians(player.getRotY() + 90)) * -player.getSpeed() * 0.001f * Main.delta;
				newPosition.z += (float) Math.cos(Math.toRadians(player.getRotY() + 90)) * player.getSpeed() * 0.001f * Main.delta;
			}
			if(key_MoveRight) {
				newPosition.x += (float) Math.sin(Math.toRadians(player.getRotY() + 90)) * player.getSpeed() * 0.001f * Main.delta;
				newPosition.z += (float) Math.cos(Math.toRadians(player.getRotY() + 90)) * -player.getSpeed() * 0.001f * Main.delta;
			}
			
			if(key_Jump) {
				newPosition.y += player.getSpeed() * 0.001f * Main.delta;
			}
			if(key_Crouch) {
				newPosition.y += -player.getSpeed() * 0.001f * Main.delta;
			}
			
			if(!Collision.checkPlayerCollision(newPosition.x, player.getY(), player.getZ())) {
	        	player.setX(newPosition.x);
	        }
	        if(!Collision.checkPlayerCollision(player.getX(), player.getY(), newPosition.z)) {
	        	player.setZ(newPosition.z);
	        }
	        if(!Collision.checkPlayerCollision(player.getX(), newPosition.y, newPosition.z)) {
	        	player.setY(newPosition.y);
	        }
			/** END MOVEMENT **/
			
		if(key_PanFaster) player.multiplySpeed(1/2f);
		
		if(key_LookUp) player.setRotX(player.getRotX() - 0.5f);
		if(key_LookDown) player.setRotX(player.getRotX() + 0.5f);
		
		/*int dWheel = Mouse.getDWheel();
		if (dWheel < 0 && !Main.isZoomedMin) {
			player.movePlayer(player.getRotY(), -player.getSpeed() * camera.scrollSpeed, player.getSpeed() * camera.scrollSpeed);
			player.changeY(player.getSpeed() * 0.001f * Main.delta * camera.scrollSpeed);
	    } else if (dWheel > 0 && !Main.isZoomedMax){
			player.movePlayer(player.getRotY(), player.getSpeed() * camera.scrollSpeed, -player.getSpeed() * camera.scrollSpeed);
			player.changeY(-player.getSpeed() * 0.001f * Main.delta * camera.scrollSpeed);
	    }*/
		
		while(Keyboard.next()) {
			if(key_Exit) Mouse.setGrabbed(false);
		}
		
		while(Mouse.next()) {
			if(key_LeftMouse) Mouse.setGrabbed(true);
		}
	}

}
