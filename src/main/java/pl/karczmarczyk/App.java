package pl.karczmarczyk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.glassfish.tyrus.server.Server;
import pl.karczmarczyk.config.Config;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
	Config.readConfig();
	
        Server server = new Server(
		Config.getAsString("chat.server.host"), 
		Config.getAsInt("chat.server.port"),
		Config.getAsString("chat.server.contextPath"),
		null, ChatEndpoint.class) {};

	try {
	    server.start();
	    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	    System.out.print("Please press a key to stop the server.");
	    reader.readLine();
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    server.stop();
	}
    }
}
