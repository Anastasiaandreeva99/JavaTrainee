package com.nevexis;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerClass {
	
	
	
	public static void main(String[] args) throws IOException{  
	
		ServerSocket ss=new ServerSocket(6666);  
		Socket s=ss.accept();//establishes connection   
		DataInputStream dis=new DataInputStream(s.getInputStream());  
		String  str=(String)dis.readUTF();  
		System.out.println("message= "+str);  
		ss.close();  
	}
	
//	private ServerSocket serverSocket;
//    private Socket clientSocket;
//    private PrintWriter out;
//    private BufferedReader in;
//
//    public void start(int port) throws IOException {
//        serverSocket = new ServerSocket(port);
//        clientSocket = serverSocket.accept();
//        out = new PrintWriter(clientSocket.getOutputStream(), true);
//        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//        String greeting = in.readLine();
//            if ("hello server".equals(greeting)) {
//                out.println("hello client");
//            }
//            else {
//                out.println("unrecognised greeting");
//            }
//    }
//
//    public void stop() throws IOException {
//        in.close();
//        out.close();
//        clientSocket.close();
//        serverSocket.close();
//    }

}
