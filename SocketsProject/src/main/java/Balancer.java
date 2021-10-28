import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Balancer {
    
    
    private static ServerSocket server8080;
    private static int port3 = 8080;
    private static int br =0;
    public static void main(String args[]) throws IOException, ClassNotFoundException{
   
        server8080 = new ServerSocket(port3);
        while(true){
        	br++;
            Socket socket = server8080.accept();          
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Message Received: " + message);
             InetAddress host = InetAddress.getLocalHost();
                 Socket socketBalance = null;
                 ObjectOutputStream oos2 = null;
                 ObjectInputStream ois2 = null;      
            	int portBalance=br%2==0?8081:8082;    
                 socketBalance = new Socket(host.getHostName(), portBalance);
                     oos2 = new ObjectOutputStream(socketBalance.getOutputStream());
                     System.out.println("Sending to Server");
                      oos2.writeObject("pikli");
                     ois2 = new ObjectInputStream(socketBalance.getInputStream());
                     String messageBalance = (String) ois2.readObject();
                     System.out.println("Message: " + messageBalance);
                     ois2.close();
                     oos2.close();
            
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject("Hi Client "+message+messageBalance);
            ois.close();
            oos.close();
            socket.close();
            if(message.equalsIgnoreCase("bye bye server")) break;
        }
        server8080.close();
        System.out.println("Shutting down Socket server!!");
      
 
    }
    
}


