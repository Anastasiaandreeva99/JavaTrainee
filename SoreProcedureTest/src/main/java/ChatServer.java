import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

public class ChatServer {
	private Scanner scanner = new Scanner(System.in);

	private Logger logger = Logger.getLogger(this.getClass().getName());

	@OnOpen
	public void onOpen(Session session) {

		logger.info("Connected ... " + session.getId());

	}

	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("CLIENT:" + message);
		System.out.println("Server send message onMessage");
		String msg = scanner.nextLine();
		try {
			session.getBasicRemote().sendText(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {

		logger.info(String.format("Session %s closed because of %s", session.getId(), closeReason));
		try {
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
