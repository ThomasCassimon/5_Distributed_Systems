package Network.TCP;

import Files.File;
import Network.Constants;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Astrid on 02-Oct-17.
 */
public class ClientMain
{
	public static void main(String args[])
	{
		Network.TCP.Client tcpClient = new Network.TCP.Client(Constants.TCP_SERVER_IP);
		Scanner scanner = new Scanner(System.in);

		tcpClient.start();
		
		System.out.println("Press enter to send data...");
		scanner.nextLine();

		tcpClient.send("Hello, world!".getBytes());
		
		System.out.println("Press enter to show received data...");
		scanner.nextLine();
		
		byte[] fileData = tcpClient.receive();
		
		System.out.println("Received: '" + new String(fileData, Constants.ENCODING) + "'");
		
		File fileObj = new File ("C:\\Users\\Thomas\\FuckYouVogel.gif");
		try
		{
			fileObj.write(fileData);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		tcpClient.stop();
	}
}
