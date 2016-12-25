package be.ehb.pvdb.logtool;

import java.util.ArrayList;

public class MessageData implements Subject{
	private ArrayList<Listener> listeners;
	private Message message;
	
	public MessageData() {
		listeners = new ArrayList<Listener>();
	}

	@Override
	public void registerListener(Listener o) {
		listeners.add(o);
	}

	@Override
	public void notifyListeners() {
		for (int i = 0 ; i < listeners.size(); i++) {
			Listener o = (Listener)listeners.get(i);
			o.doSendMsg(message);
		}
	}
	public void sendMsg(){
		notifyListeners();
	}
	public void createMsg(Message message) {
		this.message = message;
		sendMsg();
	}
}
