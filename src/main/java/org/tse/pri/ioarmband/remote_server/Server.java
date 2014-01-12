package org.tse.pri.ioarmband.remote_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server implements Runnable{

	private static String bindAddr = "0.0.0.0";
	private static int port = 80;
	private ServerSocket serverSocket;
	public Server() {
	}
	
	
	public void start(){
		try {
			serverSocket = new ServerSocket(port, 50, Inet4Address.getByName(bindAddr));
			new Thread(this);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run() {
		try {
			while(true){
				System.out.println("Waiting clients");
				Socket s = serverSocket.accept();
				System.out.println("Client connected");
				BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				PrintWriter out = new PrintWriter(s.getOutputStream(), true);
		        String inputLine;
				while ((inputLine = in.readLine()) != null) {
					System.out.println("Received: {" + inputLine + "}");
		             out.println(inputLine);
		             if (inputLine.equals("Bye."))
		                break;
		        }
				System.out.println("Client disconnected");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
