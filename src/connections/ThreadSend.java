package connections;

import java.io.IOException;
import java.io.ObjectOutputStream;

import entity.EntityPlayer;

public class ThreadSend extends Thread{
	
	ObjectOutputStream out;
	
	EntityPlayer player = new EntityPlayer();
	
	public ThreadSend(ObjectOutputStream o) {
		out = o;
		start();
	}
	
	public void run() {
		while(true){
			try {
				out.writeObject(""+player.getX());
				out.writeObject(""+player.getY());
				out.writeObject(""+player.getZ());
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

}
