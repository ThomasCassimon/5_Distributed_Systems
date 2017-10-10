package Network.UDP;

import Network.Constants;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.Scanner;

/**
 * Created by Astrid on 09-Oct-17.
 */
public class ServerMain
{
    public static void main(String args[])
    {
        Server server = new Server(4800);
        server.start();
	    Scanner scanner = new Scanner(System.in);
	
	    System.out.println("Press enter to check for data...");
	    scanner.nextLine();
	    
	    DatagramPacket dgPacket = server.receivePacket();
	    byte[] data = dgPacket.getData();
        System.out.println("Data received...");

        System.out.println("Data: " + new String(data, Constants.ENCODING));
	    System.out.println("Press enter to send data...");
	    scanner.nextLine();
	    
        server.send("127.0.0.1", dgPacket.getPort(), "Hello, world!");
        
        System.out.println("Press enter to stop server...");
        scanner.nextLine();
	    
        server.stop();
    }
}
