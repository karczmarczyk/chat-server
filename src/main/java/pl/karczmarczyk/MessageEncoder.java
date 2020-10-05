package pl.karczmarczyk;

import com.google.gson.Gson;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author mateusz
 */
public class MessageEncoder implements Encoder.Text<Message> {
 
    private static Gson gson = new Gson();
 
    @Override
    public String encode(Message message) throws EncodeException {
	System.out.println("encode = "+message.toString());
        return gson.toJson(message);
    }
 
    @Override
    public void init(EndpointConfig endpointConfig) {
        // Custom initialization logic
    }
 
    @Override
    public void destroy() {
        // Close resources
    }
}
