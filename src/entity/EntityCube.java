package entity;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.vector.Vector3f;

public class EntityCube extends Entity {
	
	public EntityCube(Vector3f position, Vector3f rotation, float width, float height) {
		super(position, rotation, width, height);
	}
}
