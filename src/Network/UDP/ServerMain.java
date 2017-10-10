package Network.UDP;

import Files.File;
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
        File file = new File("giphy.gif");
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

		try
		{
			byte[] dataA = file.read();

			byte[][] dataS = File.segment(dataA,500);
			System.out.println("NUMBER SEGMENTS: " + dataS.length);

			int i = 0;
			for(byte[] bytes: dataS)
			{
				server.send(dgPacket.getAddress().getHostAddress(),dgPacket.getPort(),bytes);
				System.out.println("SEGMENT " + i);
				i++;

			}
		}
		catch(IOException e)
		{
			System.err.println("Exception when reading file");
			e.printStackTrace();
		}


	    
        //server.send("127.0.0.1", dgPacket.getPort(), "Hello, world!");
        
        System.out.println("Press enter to stop server...");
        scanner.nextLine();
	    
        server.stop();
    }
}
