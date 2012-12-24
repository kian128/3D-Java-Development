package entity;

import org.lwjgl.util.vector.Vector3f;

public abstract class Entity {
	
	protected float width, height, speedX, speedY;
	protected Vector3f position, rotation;
	
	protected Entity(Vector3f position, Vector3f rotation, float width, float height) {
		this.position = position;
		this.rotation = rotation;
		this.width = width;
		this.height = height;
	}
	
	protected Entity() {}
	
	public float getX() {
		return position.x;
	}
	public float getY() {
		return position.y;
	}
	public float getZ() {
		return position.z;
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
	
	public void setX(float x) {
		position.x = x;
	}
	public void setY(float y) {
		position.y = y;
	}
	public void setZ(float z) {
		position.z = z;
	}
	
	public float getWidth() {
		return width;
	}
	public float getHeight() {
		return height;
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
	
	/**PLACEHOLDERS**/
	
	public float getSpeedX() {
		return speedX;
	}
	public float getSpeedY() {
		return speedY;
	}

}
