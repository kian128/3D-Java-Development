package connections;

import game.Main;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

import javax.swing.JOptionPane;

public class Client {
	
	public static Socket socket;
	
	public static ObjectOutputStream out;
	public static ObjectInputStream in;
	
	public static int port = 25566;
	//public static String connectIP = "25.119.196.130";
	
	public Client() throws IOException {
		String ip = JOptionPane.showInputDialog("Enter IP");
		
		socket = new Socket(ip, port);
		
		in = new ObjectInputStream(socket.getInputStream());
		out = new ObjectOutputStream(socket.getOutputStream());
		
		byte[] ping = new byte[256];
		new Random().nextBytes(ping);
		out.write(ping);
		out.flush();
		long latency = in.readLong();
		System.out.println("Latency: "+(System.currentTimeMillis()-latency));
		
		new ThreadReceive(in);
		new ThreadSend(out);
	}
	
	public static void main(String args[]) {
		try {
			new Client();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		new Main();
	}
}
