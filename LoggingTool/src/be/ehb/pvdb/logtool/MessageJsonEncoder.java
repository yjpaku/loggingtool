package be.ehb.pvdb.logtool;

import java.util.Arrays;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;
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
			if (message.getSubject() == "process") {
				List<String> processItem = Arrays.asList(message.getContent().split(";"));
				jsonObject = Json.createObjectBuilder()
						.add("subject", message.getSubject())
						.add("time", Long.toString(message.getTime()))
						.add("seqNr", Long.toString(Message.getSeqNr()))
						.add("pid", processItem.get(0))
						.add("processName", processItem.get(1))
						.add("processUsedTime", processItem.get(2))
						.add("user", processItem.get(3))
						.add("startTime", processItem.get(4))
						.build();
			} else {
				jsonObject = Json.createObjectBuilder()
						.add("subject", message.getSubject())
						.add("time", Long.toString(message.getTime()))
						.add("seqNr", Long.toString(Message.getSeqNr()))
						.add("content", message.getContent())
						.build();
			}
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