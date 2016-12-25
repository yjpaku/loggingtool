package be.ehb.pvdb.logtool;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class MessageJsonDecoder implements Decoder.Text<Message> {
	
	@Override
	public Message decode(String jsonMessage) throws DecodeException {
		JsonObject jsonObject = Json.createReader(new StringReader(jsonMessage)).readObject();
		String time=jsonObject.getString("time");
		String seqNr=jsonObject.getString("seqNr");
		Message message = new Message(jsonObject.getString("subject"),Long.parseLong(time),Long.parseLong(seqNr),jsonObject.getString("content"));
		return message;

	}

	@Override
	public boolean willDecode(String jsonMessage) {
		try {
			JsonObject jsonObject = Json.createReader(new StringReader(jsonMessage)).readObject();
			if (jsonMessage.startsWith("{\"subject:")) {
				if(jsonObject.getString("subject").equals("userinput")) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void init(EndpointConfig ec) {
	}

	@Override
	public void destroy() {
	}

}
