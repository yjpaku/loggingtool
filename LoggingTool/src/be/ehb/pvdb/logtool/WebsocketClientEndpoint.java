package be.ehb.pvdb.logtool;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ClientEndpoint
public class WebsocketClientEndpoint {
	 @OnOpen
	    public void onOpen(Session session) {
	 
	 }
	 @OnMessage
	    public void onMessage(String message, Session session) {
		System.out.println(session.getId() + "|" + message);
	 }
	 @OnError
		public void onError(Session session, Throwable e) {
		 
	 }
	 @OnClose
	    public void onClose(Session session, CloseReason closeReason) {
		 
	 }
}
