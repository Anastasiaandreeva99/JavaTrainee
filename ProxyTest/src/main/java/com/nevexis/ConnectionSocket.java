package com.nevexis;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectionSocket {
	
	public static void main(String[] args) throws UnknownHostException, IOException {  
    
		Socket s=new Socket("localhost",6666);  
		DataOutputStream dout=new DataOutputStream(s.getOutputStream());  
		dout.writeUTF("Hello Server");  
		dout.flush();  
		dout.close();  
		s.close();  
		 
		}  
	
//	private Socket clientSocket;
//    private PrintWriter out;
//    private BufferedReader in;
//
//    public void startConnection(String ip, int port) throws UnknownHostException, IOException {
//        clientSocket = new Socket(ip, port);
//        out = new PrintWriter(clientSocket.getOutputStream(), true);
//        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//    }
//
//    public String sendMessage(String msg) throws IOException {
//        out.println(msg);
//        String resp = in.readLine();
//        return resp;
//    }
//
//    public void stopConnection() throws IOException {
//        in.close();
//        out.close();
//        clientSocket.close();
//    }
}
