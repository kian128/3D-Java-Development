package connections;

import static org.lwjgl.opengl.GL11.*;

public class ConnectedPlayer {
	
	public static float x, y, z, width, height;
	
	public ConnectedPlayer(float x, float y, float z, float width, float height) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.width = width;
		this.height = height;
	}
	
	public static void render() {
		glBegin(GL_QUADS);		// Draw The Cube Using quads
		glColor3f(0, 0, 1);
		
		glNormal3f(0, 1, 0); //top
	    	glVertex3f(x + width, y + height, z);	// Top Right Of The Quad (Top)
	    	glVertex3f(x, y + height, z);	// Top Left Of The Quad (Top)
	    	glVertex3f(x, y + height, z + width);	// Bottom Left Of The Quad (Top)
	    	glVertex3f(x + width, y + height, z + width);	// Bottom Right Of The Quad (Top)

	    glNormal3f(0, -1, 0); //bottom
	    	glVertex3f(x + width, y, z + width);	// Top Right Of The Quad (Bottom)
	    	glVertex3f(x, y, z + width);	// Top Left Of The Quad (Bottom)
	    	glVertex3f(x, y, z);	// Bottom Left Of The Quad (Bottom)
	    	glVertex3f(x + width, y, z);	// Bottom Right Of The Quad (Bottom)
	    
	    glNormal3f(0, 0, 1); //front
	    	glVertex3f(x + width, y + height, z + width);	// Top Right Of The Quad (Front)
	    	glVertex3f(x, y + height, z + width);	// Top Left Of The Quad (Front)
	    	glVertex3f(x, y, z + width);	// Bottom Left Of The Quad (Front)
	    	glVertex3f(x + width, y, z + width);	// Bottom Right Of The Quad (Front)
	    
	    glNormal3f(0, 0, -1); //back
	    	glVertex3f(x + width, y, z);	// Top Right Of The Quad (Back)
	    	glVertex3f(x, y, z);	// Top Left Of The Quad (Back)
	    	glVertex3f(x, y + height, z);	// Bottom Left Of The Quad (Back)
	    	glVertex3f(x + width, y + height, z);	// Bottom Right Of The Quad (Back)
	    
	    glNormal3f(-1, 0, 0); //left
	    	glVertex3f(x, y + height, z + width);	// Top Right Of The Quad (Left)
	    	glVertex3f(x, y + height, z);	// Top Left Of The Quad (Left)
	    	glVertex3f(x, y, z);	// Bottom Left Of The Quad (Left)
	    	glVertex3f(x, y, z + width);	// Bottom Right Of The Quad (Left)
	    
	    glNormal3f(1, 0, 0); //right
	    	glVertex3f(x + width, y + height, z);	// Top Right Of The Quad (Right)
	    	glVertex3f(x + width, y + height, z + width);	// Top Left Of The Quad (Right)
	    	glVertex3f(x + width, y, z + width);	// Bottom Left Of The Quad (Right)
	    	glVertex3f(x + width, y, z);	// Bottom Right Of The Quad (Right)
	    glClearColor(0, 0, 1, 0);
	    
	    glEnd();
	}

}
