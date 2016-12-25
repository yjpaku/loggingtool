package be.ehb.pvdb.logtool;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;

import javax.websocket.DeploymentException;

import org.glassfish.tyrus.client.ClientManager;

@SuppressWarnings("unused")
public class LoggingToolMain {
	public static void main (String [] args) throws InterruptedException {
		WebsocketClient webSocketClient = new WebsocketClient();
		Thread websocketThread = new Thread(webSocketClient);
		websocketThread.start();
		
		while (WebsocketClient.getSession() == null){
			System.out.println("Waiting for session ...");
			Thread.sleep(1000);
		}
		
		MessageData messageData = new MessageData();
		
		Websocket ws01 = new Websocket(messageData);
		FileSave fs01 = new FileSave(messageData);
		
		messageData.createMsg(new Message("subject1","content1"));
		messageData.createMsg(new Message("subject10","content10"));
		messageData.createMsg(new Message("subject100","content100"));
		
		MessageData messageData02 = new MessageData();
		Websocket ws02 = new Websocket(messageData02);
		FileSave fs02 = new FileSave(messageData02);
		
		messageData02.createMsg(new Message("02subject1","02content1"));
		messageData02.createMsg(new Message("02subject10","02content10"));
		messageData02.createMsg(new Message("02subject100","02content100"));
		
		
		//Pause for 2 seconds
		ProcessList pl = new ProcessList(2000);
		Thread plThread = new Thread(pl);
		plThread.start();
		
		for(int i=0; i<100; i++){
			messageData.createMsg(new Message("subject"+i,"content"+i));
			messageData02.createMsg(new Message("02subject"+i,"02content"+i));
			//Pause for 3 seconds
            Thread.sleep(3000);
		}
		plThread.interrupt();
		websocketThread.interrupt();
	}
}
