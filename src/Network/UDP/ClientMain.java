package Network.UDP;

import Network.Constants;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.Scanner;

/**
 * Created by Astrid on 09-Oct-17.
 */
public class ClientMain
{
    public static void main(String args[])
    {
        Client client = new Client();
	    client.start();
	
	    Scanner scanner = new Scanner(System.in);
	    
		System.out.println("Started...");
	
	    System.out.println("Press enter to send data...");
	    scanner.nextLine();
	    
        client.send("127.0.0.1", 4800, "Hello, world!".getBytes());

        System.out.println("Data sent");
	
	    System.out.println("Press enter to receive data...");
	    scanner.nextLine();

		Thread t = new Thread(client);

		t.start();
	 
		byte[] data = client.receivePacket().getData();
		System.out.println("Data received...");
		System.out.println(new String(data, Constants.ENCODING));

        client.stop();
    }
}
