package Network.UDP;

import Files.File;
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
	
	    System.out.println("Press enter to check for data...");
	    scanner.nextLine();

	 
		//byte[] data = client.receivePacket().getData();

		//System.out.println(new String(data, Constants.ENCODING));

		File file = new File("result.gif");

		while(!client.bufferEmpty())
		{
			try
			{
				file.append(client.receiveData());
			}
			catch(IOException e)
			{
				System.err.println("Exception when writing file");
				e.printStackTrace();
			}
		}

		System.out.println("Data received...");


        client.stop();
    }
}
