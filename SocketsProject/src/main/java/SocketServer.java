


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class implements java Socket server
 * @author pankaj
 *
 */
public class SocketServer {
    
    private static ServerSocket server8081;
    private static int port = 8081;
 
    


    public static void main(String args[]) throws IOException, ClassNotFoundException{
        server8081 = new ServerSocket(port);
        while(true){
            Socket socket = server8081.accept();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Message Received 8081: " + message);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject("Aloha Client "+message);
            ois.close();
            oos.close();
            socket.close();
            if(message.equalsIgnoreCase("bye bye server")) break;
        }
            server8081.close();
     
      
 
    }
    
}

