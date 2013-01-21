package game;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import render.VoxelRendering;

import entity.Entity;
import entity.EntityList;
import entity.EntityPlayer;

public class Input {
	
	public static EntityPlayer player = new EntityPlayer();
	public static Camera camera = new Camera();
	
	public boolean key_MoveForward, key_MoveBackward, key_MoveLeft, key_MoveRight;
	public boolean key_Jump, key_Crouch, key_PanFaster;
	public boolean key_LookUp, key_LookDown;
	public boolean key_Exit;
	public boolean key_LeftMouse, key_RightMouse;
	public boolean key_ToggleFlying;
	
	private boolean isFlying = false;
	
	public void setKeys() {
		key_MoveForward = Keyboard.isKeyDown(Keyboard.KEY_W);
		key_MoveBackward = Keyboard.isKeyDown(Keyboard.KEY_S);
		key_MoveLeft = Keyboard.isKeyDown(Keyboard.KEY_A);
		key_MoveRight = Keyboard.isKeyDown(Keyboard.KEY_D);
		
		key_Jump = Keyboard.isKeyDown(Keyboard.KEY_SPACE);
		key_Crouch = Keyboard.isKeyDown(Keyboard.KEY_LCONTROL);
		key_PanFaster = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT);
		
		key_LookUp = Keyboard.isKeyDown(Keyboard.KEY_UP);
		key_LookDown = Keyboard.isKeyDown(Keyboard.KEY_DOWN);
		
		key_Exit = Keyboard.getEventKey() == Keyboard.KEY_ESCAPE;
		
		key_ToggleFlying = Keyboard.getEventKey() == Keyboard.KEY_C;
		
		key_LeftMouse = Mouse.isButtonDown(0);
		key_RightMouse = Mouse.isButtonDown(1);
	}
	
	public void update() {
		if(key_PanFaster) player.multiplySpeed(2);
		
			/** MOVEMENT **/
			Vector3f newPosition = new Vector3f(player.getX(), player.getY(), player.getZ());
			
			if(key_MoveForward) Movement.changePosition(newPosition, player.getRotY(), player.getSpeed(), 0, -player.getSpeed());
			if(key_MoveBackward) Movement.changePosition(newPosition, player.getRotY(), -player.getSpeed(), 0, player.getSpeed());
			if(key_MoveLeft) Movement.changePosition(newPosition, player.getRotY() + 90, -player.getSpeed(), 0, player.getSpeed());
			if(key_MoveRight) Movement.changePosition(newPosition, player.getRotY() + 90, player.getSpeed(), 0, -player.getSpeed());
			
			if(isFlying) {
				Movement.gravity = 0;
				if(key_Jump) {
					Movement.changePosition(newPosition, 0, 0, player.getSpeed(), 0);
				}
				if(key_Crouch) {
					Movement.changePosition(newPosition, 0, 0, -player.getSpeed(), 0);
				}	
			}
			
			if(!isFlying) {
				if(Movement.gravity <= 15) Movement.gravity += (0.98 / 40) * Main.delta;
			}
			
			if(key_Jump && !isFlying && !Movement.isJumping) {
				Movement.gravity = -8;
				Movement.isJumping = true;
			}
			
			Movement.changePosition(newPosition, 0, 0, -Movement.gravity, 0);
			
			if(!Collision.checkPlayerCollision(newPosition.x, player.getY(), player.getZ())) {
	        	player.setX(newPosition.x);
	        }
	        if(!Collision.checkPlayerCollision(player.getX(), player.getY(), newPosition.z)) {
	        	player.setZ(newPosition.z);
	        }
	        if(!Collision.checkPlayerCollision(player.getX(), newPosition.y, player.getZ())) {
	        	player.setY(newPosition.y);
	        }
	        if(Collision.checkPlayerCollision(player.getX(), newPosition.y, player.getZ()) && newPosition.y < player.getY()) {
	        	Movement.gravity = 0;
	        	Movement.isJumping = false;
	        }
			/** END MOVEMENT **/
			
		if(key_PanFaster) player.multiplySpeed(1/2f);
		
		if(key_LookUp) player.setRotX(player.getRotX() - 0.5f);
		if(key_LookDown) player.setRotX(player.getRotX() + 0.5f);
		
		if(key_RightMouse) {
		}
		
		/*int dWheel = Mouse.getDWheel();
		if (dWheel < 0 && !Main.isZoomedMin) {
			player.movePlayer(player.getRotY(), -player.getSpeed() * camera.scrollSpeed, player.getSpeed() * camera.scrollSpeed);
			player.changeY(player.getSpeed() * 0.001f * Main.delta * camera.scrollSpeed);
	    } else if (dWheel > 0 && !Main.isZoomedMax){
			player.movePlayer(player.getRotY(), player.getSpeed() * camera.scrollSpeed, -player.getSpeed() * camera.scrollSpeed);
			player.changeY(-player.getSpeed() * 0.001f * Main.delta * camera.scrollSpeed);
	    }*/
		
		while(Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if(key_Exit) {
					Mouse.setGrabbed(false);
				} else if (key_ToggleFlying) {
					isFlying = !isFlying;
			    	if(isFlying) System.out.println("Flying ON");
			    	if(!isFlying) System.out.println("Flying OFF");
				}
			}
		}
		
		while(Mouse.next()) {
			if(key_LeftMouse) {
				Mouse.setGrabbed(true);
			}
			if(key_RightMouse) {
			}
		}
	}

}
