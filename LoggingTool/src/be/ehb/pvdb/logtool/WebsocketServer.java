package be.ehb.pvdb.logtool;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.BindException;

import org.glassfish.tyrus.server.Server;

public class WebsocketServer {

    public static void main(String[] args) {
        runServer();
    }
 
    public static void runServer() {
    	System.out.println("Server version v001.035");
        Server server = new Server("localhost", 8025, "/websockets", WebsocketServerEndpoint.class);
 
        try {
            server.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please press a key to stop the server.");
            reader.readLine();
        } catch (BindException e) {
        	System.out.println("Server already running.");
        	return;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            server.stop();
        }
    }
}
