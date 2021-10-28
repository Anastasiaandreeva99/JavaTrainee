import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer1 {
	
	private static ServerSocket server8082;
	private static int port2 = 8082;
	

	public static void main(String args[]) throws IOException, ClassNotFoundException{

        
        server8082 = new ServerSocket(port2);
        while(true){
            Socket socket = server8082.accept();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Message Received 8082: " + message);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject("Aloha Client 8082 "+message);
            ois.close();
            oos.close();
            socket.close();
            if(message.equalsIgnoreCase("bye bye server")) break;
        }
        server8082.close();

}
}
