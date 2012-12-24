package game;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

public class Screen {

	public void initDisplay(int width, int height, String title) {
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(title);
			Display.setResizable(true);
			Display.create();
		}catch(LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	public void initDisplayOrthographic(int width, int height) {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, width, 0, height, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	}
	
	public void initDisplayGluPerspective(float fov, int width, int height, float renderNear, float renderFar) {
		glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(fov, (float) width / height, renderNear, renderFar);
        glMatrixMode(GL_MODELVIEW);
	}
	
	public static void initDisplayGluLookAt(float fov, int width, int height, float renderNear, float renderFar, float cameraX, float cameraY, float cameraZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
		glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(fov, (float) Display.getWidth() / Display.getHeight(), renderNear, renderFar);
    	gluLookAt(cameraX, cameraY, cameraZ, centerX, centerY, centerZ, upX, upY, upZ);
    	glMatrixMode(GL_MODELVIEW);
	}
}
