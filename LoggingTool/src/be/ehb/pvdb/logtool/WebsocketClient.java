package be.ehb.pvdb.logtool;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;

import javax.websocket.DeploymentException;
import javax.websocket.Session;

import org.glassfish.tyrus.client.ClientManager;

public class WebsocketClient implements Runnable{
	
	private static CountDownLatch latch = null;
	private static Session session = null;
	
	public WebsocketClient(){
	}
	
	@Override
	public void run() {
		latch = new CountDownLatch(1);
		ClientManager client = ClientManager.createClient();
		try {
			session = client.connectToServer(WebsocketClientEndpoint.class, new URI("ws://localhost:8025/websockets/ehb"));
			latch.await();

		} catch (DeploymentException | URISyntaxException | InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println("Session started: " + session.getId());
	}

	public static Session getSession() {
		return session;
	}
	
	
}
