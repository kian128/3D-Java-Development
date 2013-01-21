package game;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;


public class Lighting {
	
	public static void initLighting() {
		//glShadeModel(GL_SMOOTH);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_COLOR_MATERIAL);
				
		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0);
			
		glEnable(GL_NORMALIZE);
		//Lighting.enableLightType(GL_LIGHT0, GL_AMBIENT, 0.2f, 0.2f, 0.2f, 1);
		//Lighting.enableLightType(GL_LIGHT0, GL_SPECULAR, 0.5f, 0.5f, 0.5f, 1);
				
		Lighting.enableModelLighting(GL_LIGHT_MODEL_AMBIENT, 0.0f, 0.0f, 0.0f, 1);
		Lighting.enableLightType(GL_LIGHT0, GL_DIFFUSE, 0.5f, 0.5f, 0.5f, 1);
		Lighting.enableLightPosition(GL_LIGHT0, 200, 32, 200);
	}
	
	public static void enableLightType(int lightNumber, int type, float red, float green, float blue, float alpha) {
		FloatBuffer color = BufferUtils.createFloatBuffer(4);
		color.put(green).put(red).put(blue).put(alpha);
		color.flip();
		glLight(lightNumber, type, color);
	}
	
	public static void enableLightPosition(int lightNumber, float x, float y, float z) {
		FloatBuffer position = BufferUtils.createFloatBuffer(4);
		position.put(x).put(y).put(z).put(1.0f);
		position.flip();
		glLight(lightNumber, GL_POSITION, position);
	}
	
	public static void enableModelLighting(int type, float red, float green, float blue, float alpha) {
		FloatBuffer color = BufferUtils.createFloatBuffer(4);
		color.put(green).put(red).put(blue).put(alpha);
		color.flip();
		glLightModel(type, color);
	}

}
