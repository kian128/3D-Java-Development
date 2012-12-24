package render;

import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.opengl.GL15.*;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector3f;

import entity.Entity;
import entity.EntityCube;
import entity.EntityList;
import entity.EntityPlayer;

public class VoxelRendering {
	
	public static float cubeSize = 1;
	public static int chunkSize = 16;
	public static int numberOfVertices = 24;
	public static int numberOfNormals = 6;
	public static double probability = 1;
	public static float noise = 0.55f;
	
	public static FloatBuffer vertexData, normalData;
	public static int vertexHandler, normalHandler;
	
    public static EntityPlayer player = new EntityPlayer();
	
	public static void initialiseChunk(int startX, int startY, int startZ) {
		vertexHandler = glGenBuffers();
		normalHandler = glGenBuffers();
		
		vertexData = BufferUtils.createFloatBuffer((int) (Math.pow(chunkSize, 2) * numberOfVertices * 3));
		normalData = BufferUtils.createFloatBuffer((int) (Math.pow(chunkSize, 2) * numberOfVertices * 3));
		
		Random random = new Random();
		
		for(int x = startX; x < chunkSize; x++) {
			for(int z = startZ; z < chunkSize; z++) {
				if(random.nextInt((int) (1 / probability)) <= 1) {
					vertexData.put(renderCubeVertices(x * cubeSize, (float) Math.sin(x * z) * noise * cubeSize, z * cubeSize));
					normalData.put(renderCubeNormals(x * cubeSize, (float) Math.sin(x * z) * noise * cubeSize, z * cubeSize));
					
					Entity cube = new EntityCube(new Vector3f(x * cubeSize, (float) Math.sin(x * z) * noise * cubeSize, z * cubeSize), new Vector3f(0, 0, 0), cubeSize, cubeSize);
					EntityList.cubes.add(cube);
				}
			}
		}
		
		vertexData.flip();
		normalData.flip();
	}
	
	public static void renderChunk() {
		glBindBuffer(GL_ARRAY_BUFFER, vertexHandler);
        glBufferData(GL_ARRAY_BUFFER, vertexData, GL_STATIC_DRAW);
        glVertexPointer(3, GL_FLOAT, 0, 0L);
        
       	glBindBuffer(GL_ARRAY_BUFFER, normalHandler);
       	glBufferData(GL_ARRAY_BUFFER, normalData, GL_STATIC_DRAW);
       	glNormalPointer(GL_FLOAT, 0, 0L);
        
       	glEnableClientState(GL_VERTEX_ARRAY);
       	glEnableClientState(GL_NORMAL_ARRAY);
       		glColor3f(1f, 0f, 0f);
       		//glMaterialf(GL_FRONT, GL_SHININESS, 10f);
       		glDrawArrays(GL_QUADS, 0, calculateNumberOfCubes() * 24);
       	glDisableClientState(GL_NORMAL_ARRAY); 
       	glDisableClientState(GL_VERTEX_ARRAY);
        
        glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	public static int calculateNumberOfCubes() {
		return (int) (Math.pow(chunkSize, 2) * probability);
	}
	
	public static float[] renderCubeVertices(float x, float y, float z) {
		return new float[] {
				//top
				x + cubeSize, y + cubeSize, z,
				x, y + cubeSize, z,
				x, y + cubeSize, z + cubeSize,
				x + cubeSize, y + cubeSize, z + cubeSize,
				//bottom
				x + cubeSize, y, z + cubeSize,
				x, y, z + cubeSize,
				x, y, z,
				x + cubeSize, y, z,
				//front
				x + cubeSize, y + cubeSize, z + cubeSize,
				x, y + cubeSize, z + cubeSize, 
				x, y, z + cubeSize, 
				x + cubeSize, y, z + cubeSize,
				//back
				x + cubeSize, y, z, 
				x, y, z,
				x, y + cubeSize, z,
				x + cubeSize, y + cubeSize, z,
				//left
				x, y + cubeSize, z + cubeSize,
				x, y + cubeSize, z,
				x, y, z,
				x, y, z + cubeSize, 
				//right
				x + cubeSize, y + cubeSize, z,
				x + cubeSize, y + cubeSize, z + cubeSize,
				x + cubeSize, y, z + cubeSize, 
				x + cubeSize, y, z 
		};
	}
	
	public static float[] renderCubeNormals(float x, float y, float z) {
		return new float[] {
				//top
				0, 1, 0, 
				0, 1, 0, 
				0, 1, 0, 
				0, 1, 0,
				//bottom
				0, -1, 0, 
				0, -1, 0, 
				0, -1, 0, 
				0, -1, 0, 
				//front
				0, 0, 1, 
				0, 0, 1, 
				0, 0, 1, 
				0, 0, 1, 
				//back
				0, 0, -1, 
				0, 0, -1, 
				0, 0, -1, 
				0, 0, -1, 
				//left
				-1, 0, 0, 
				-1, 0, 0, 
				-1, 0, 0, 
				-1, 0, 0, 
				//right
				1, 0, 0, 
				1, 0, 0, 
				1, 0, 0, 
				1, 0, 0 
		};
	}
}
