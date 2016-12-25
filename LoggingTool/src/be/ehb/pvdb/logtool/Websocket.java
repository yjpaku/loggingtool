package be.ehb.pvdb.logtool;

import java.io.IOException;

import javax.websocket.EncodeException;

@SuppressWarnings("unused")
public class Websocket implements Listener {
	private Message message;
	private Subject messageData;

	public Websocket(Subject messageData) {
		this.messageData = messageData;
		messageData.registerListener(this);
	}

	public void doSendMsg(Message message) {
		this.message = message;
		display();
	}
	public void display() {
		System.out.println("websocket: " + message.toString());
		try {
			WebsocketClient.getSession().getBasicRemote().sendObject(message);
		} catch (IOException | EncodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
