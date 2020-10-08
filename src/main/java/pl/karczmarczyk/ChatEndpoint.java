package pl.karczmarczyk;

import dao.MessageDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author mateusz
 */
@ServerEndpoint( 
  value="/chat/{username}", 
  decoders = MessageDecoder.class, 
  encoders = MessageEncoder.class )
public class ChatEndpoint {
 
    private Session session;
    private static Set<ChatEndpoint> chatEndpoints 
      = new CopyOnWriteArraySet<>();
    private static HashMap<String, String> users = new HashMap<>();
 
    @OnOpen
    public void onOpen(
      Session session, 
      @PathParam("username") String username) throws IOException {
 
        this.session = session;
        chatEndpoints.add(this);
        users.put(session.getId(), username);
 
        Message message = new Message();
        message.setFrom(username);
        message.setTo(username);
        message.setContent("Connected! Welcome!");
	message.setDateCreated(LocalDateTime.now());
	message.setIsTechnicalMessage(Boolean.TRUE);
        broadcast(message);
    }
 
    @OnMessage
    public void onMessage(Session session, Message message) 
      throws IOException {
 
        message.setFrom(users.get(session.getId()));
	message.setDateCreated(LocalDateTime.now());
        broadcast(message);
    }
 
    @OnClose
    public void onClose(Session session) throws IOException {
 
        chatEndpoints.remove(this);
        Message message = new Message();
        message.setFrom(users.get(session.getId()));
        message.setContent("Disconnected!");
        broadcast(message);
    }
 
    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }
 
    private static void broadcast(Message message) 
      throws IOException {
        chatEndpoints.forEach(endpoint -> {
            synchronized (endpoint) {
               try {
		    String id = endpoint.session.getId();
		    System.out.println(id + "=" + users.get(id));
		    if (users.get(id).equals(message.getTo())
			    || users.get(id).equals(message.getFrom())) {
			endpoint.session.getBasicRemote().
			
			// send by WS
			sendObject(message);
			
			// persist
			try {
			    if (!message.getIsTechnicalMessage() && users.get(id).equals(message.getFrom())) {
				MessageDAO.insert(message);
			    }
			} catch (SQLException ex) {
			    Logger.getLogger(ChatEndpoint.class.getName()).log(Level.SEVERE, null, ex);
			} catch (NamingException ex) {
			    Logger.getLogger(ChatEndpoint.class.getName()).log(Level.SEVERE, null, ex);
			} catch (ClassNotFoundException ex) {
			    Logger.getLogger(ChatEndpoint.class.getName()).log(Level.SEVERE, null, ex);
			}
		    }
		} catch (IOException | EncodeException e) {
		    e.printStackTrace();
		}
            }
        });
    }
}

