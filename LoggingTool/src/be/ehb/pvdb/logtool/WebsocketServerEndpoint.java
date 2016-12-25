package be.ehb.pvdb.logtool;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/ehb")
public class WebsocketServerEndpoint {
	 
    @OnOpen
    public void onOpen(Session session) {
    	System.out.println("Connected ..., session active " + session.getId());
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
    	System.out.println("WebsocketServerEndpoint>onClose");
    	System.out.println(String.format("Session %s closed because of %s", session.getId(), closeReason));
    }
 
}