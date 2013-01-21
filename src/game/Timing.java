package game;

import org.lwjgl.Sys;

public class Timing {
	
	private long lastTime = getTime();
	private long lastFrame = getTime();
	private int fps;
	
	public static int delta;
	
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	public long getDelta() {
		long currentTime = getTime();
		long delta = currentTime - lastTime;
		lastTime = currentTime;
		
		return delta;
	}
	
	public void updateFPS() {
		if(getTime() - lastFrame > 100) {
			System.out.println("FPS: " + fps);
			fps = 0;
			lastFrame += 1000;
		}
		fps++;
	}

}
