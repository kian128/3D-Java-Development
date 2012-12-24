package connections;

import game.Main;

import java.io.*;
import java.net.*;

public class Server {
	
	public static ServerSocket server;
	public static Socket client;
	
	public static ObjectOutputStream out;
	public static ObjectInputStream in;
	
	static int port = 25566;
	//public static String serverIP = "25.119.196.130";

	public Server() throws IOException {
		server = new ServerSocket(port);
		client = server.accept();
		
		out = new ObjectOutputStream(client.getOutputStream());
		in = new ObjectInputStream(client.getInputStream());
		
		long start = System.currentTimeMillis();
		byte[] ping = new byte[256];
		in.read(ping);
		System.out.println("Latency: "+(System.currentTimeMillis()-start));
		out.writeLong(start);
		out.flush();
		
		new ThreadSend(out);
		new ThreadReceive(in);
	}
	
	public static void main(String args[]) {
		try {
			new Server();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		new Main();
	}
}
