import java.io.*;
import java.net.*;

public class ArkBotClient implements Runnable{

	@Override
	public void run() {
    	String hostName = ArkBot.global.HOST;
    	int portNumber = ArkBot.global.PORT;
	    try (	    	
	        Socket sSocket = new Socket(hostName, portNumber);
	        PrintWriter out = new PrintWriter(sSocket.getOutputStream(), true);
	        BufferedReader in = new BufferedReader(new InputStreamReader(sSocket.getInputStream()));
	    ) {
	    	ArkBot.connection = true;
	    	ArkBotGUI.GUIText("CLIENT: Connection Established with ArkBotServer.");
	        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	        String fromServer;
	        String fromUser;

	        while ((fromServer = in.readLine()) != null) {
	            System.out.println("Server: " + fromServer);
	            if (fromServer.equals("Bye."))
	                break;
	             
	            fromUser = stdIn.readLine();
	            if (fromUser != null) {
	                System.out.println("Client: " + fromUser);
	                out.println(fromUser);
	            }
	        }
	    	ArkBot.connection = false;
	    	ArkBotGUI.GUIText("CLIENT: Disconnected from ArkBotServer.");
	    } catch (UnknownHostException e) {
	        ArkBotGUI.GUIText("CLIENT: Unkown Host " + hostName);
	    } catch (IOException e) {
	        ArkBotGUI.GUIText("CLIENT: Unable to create I/O Connection " +
	            hostName);
	    }
		
	}
}