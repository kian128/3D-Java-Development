package connections;

import game.Main;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ThreadReceive extends Thread{
	
	ObjectInputStream in;
	
	public ThreadReceive(ObjectInputStream i) {
		in = i;
		start();
	}
	
	public void run() {
		while(true){
			try {
				Main.playerConnected.x = Float.parseFloat((String)in.readObject());
				Main.playerConnected.y = Float.parseFloat((String)in.readObject());
				Main.playerConnected.z = Float.parseFloat((String)in.readObject());
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				System.exit(1);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

}
