package be.ehb.pvdb.logtool;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageJsonEncoder implements Encoder.Text<Message> {

	@Override
	public void init(EndpointConfig config) {
	}

	@Override
	public String encode(Message message) {
		JsonObject jsonObject=null;
		try {
		jsonObject = Json.createObjectBuilder()
				.add("subject", message.getSubject())
				.add("time", Long.toString(message.getTime()))
				.add("seqNr", Long.toString(Message.getSeqNr()))
				.add("content", message.getContent()).build();
		}
		//catch (EncodeException e) {
			catch (Exception e) {
		};
		return jsonObject.toString();
	}

	@Override
	public void destroy() {
	}
	

}