package entity;

import game.*;

import org.lwjgl.util.vector.Vector3f;

public class EntityPlayer { 
	
	Timing timing = new Timing();
	
	private static Vector3f rotation = new Vector3f(0, 0, 0);
	private static Vector3f position = new Vector3f(0, 0, 0);
	
	private static Vector3f newPosition = new Vector3f();
	
	public float maxViewPos = 90;
	public float maxViewNeg = -90;
	
	private float width = 0.5f;
	private float height = 1;
	
	private final float constantSpeed = 5;
	private float variableSpeed = constantSpeed;
	
	public static boolean isFirstPerson = true;
	
	public float getSpeed() {
		return variableSpeed;
	}
	public void setSpeed(float speed) {
		this.variableSpeed = speed;
	}
	public void addSpeed(float speed) {
		this.variableSpeed += speed;
	}
	public void multiplySpeed(float speed) {
		this.variableSpeed *= speed;
	}
	public void resetSpeed() {
		this.variableSpeed = constantSpeed;
	}
	
	public float getWidth() {
		return width;
	}
	public float getHeight() {
		return height;
	}
	
	public float getX() {
		return position.x;
	}
	public float getY() {
		return position.y;
	}
	public float getZ() {
		return position.z;
	}
	
	public void setX(float x) {
		position.x = x;
	}
	public void setY(float y) {
		position.y = y;
	}
	public void setZ(float z) {
		position.z = z;
	}
	
	public void changeX(float x) {
		position.x += x;
	}
	public void changeY(float y) {
		position.y += y;
	}
	public void changeZ(float z) {
		position.z += z;
	}
	
	public float getRotX() {
		return rotation.x;
	}
	public float getRotY() {
		return rotation.y;
	}
	public float getRotZ() {
		return rotation.z;
	}
	
	public void setRotX(float x) {
		rotation.x = x;
	}
	public void setRotY(float y) {
		rotation.y = y;
	}
	public void setRotZ(float z) {
		rotation.z = z;
	}
	
	public void changeRotX(float x) {
		rotation.x += x;
	}
	public void changeRotY(float y) {
		rotation.y += y;
	}
	public void changeRotZ(float z) {
		rotation.z += z;
	}
	
}
