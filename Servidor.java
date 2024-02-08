package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor implements Runnable {
	
	private Socket clientSocket;
	
	public Servidor (Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	public static void main(String[] args) {

		try {
			
			ServerSocket serverSocket = new ServerSocket(5001);
			System.out.println("Servidor de Chat iniciado.");
			
			int i = 0;
			
			while (true) {
				
				Socket clientSocket2 = serverSocket.accept();
				System.out.println("Cliente conectado desde " + clientSocket2.getInetAddress());
				i++;
				
				new Thread(new Servidor(clientSocket2), "Cliente " + i).start();
				
				
				}
						
		} catch (IOException e){
			e.printStackTrace();
		}

	}

	public void run() {
		
		String inputLine;
		
		try {
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			out.println("Bienvenido al servidor, ¿Qué necesitas?");
			out.println("1. Hospital");
			out.println("2. Policía");
			out.println("3. Bomberos");
			out.println("4. Administración");
			out.println("Utilice q para salir");
			
			while ((inputLine = in.readLine()) != null) {
				
				System.out.println("LLego Esto: " + inputLine);
				
				if (inputLine.equals("1")) {
					out.println("Numero Hospital");
					
				} else if (inputLine.equals("2")) {
					out.println("Numero Policia");
					
				} else if (inputLine.equals("3")) {
					out.println("Numero Bomberos");
					
				} else if (inputLine.equals("4")) {
					out.println("Numero Administracion");
				
				} else if (inputLine.equals("q")) {
					clientSocket.close();
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
