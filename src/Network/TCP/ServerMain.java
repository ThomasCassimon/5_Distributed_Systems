package Network.TCP;

import Files.File;
import Network.Constants;

import java.io.IOException;
import java.util.Scanner;

public class ServerMain
{
	public static void main (String[] args)
	{
		Network.TCP.Server tcpServer = new Network.TCP.Server(Network.Constants.TCP_PORT);
		Scanner scanner = new Scanner(System.in);
		
		File fileObj = new File ("giphy.gif");
		byte[] fileData = new byte [1];
		try
		{
			fileData = fileObj.read();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		try
		{
			tcpServer.start();
		}
		catch (IOException ioe)
		{
			System.err.println("Network.TCP.ServerMain.main()\tError when trying to start TCP server.");
			ioe.printStackTrace();
		}

		System.out.println("Server: " + tcpServer.toString());
		System.out.println("Press enter to check for data");
		scanner.nextLine();

		for (String host : tcpServer.getActiveConnections())
		{
			if (tcpServer.hasData(host))
			{
				System.out.println("Received the following data from " + host);
				System.out.println("'" + new String(tcpServer.receive(host), Constants.ENCODING) + "'");
			}
			else
			{
				System.out.println("No data for " + host);
			}
		}
		
		System.out.println("Press enter to send data");
		scanner.nextLine();
		
		byte[][] segmentedFile = File.segment(fileData, 1400);
		
		System.out.println("Segmented the file in " + segmentedFile.length + " segments.");
		
		for (String host : tcpServer.getActiveConnections())
		{
			for (byte[] segment : segmentedFile)
			{
				tcpServer.send(host, segment);
			}
		}
		
		System.out.println("Press enter to close server");
		scanner.nextLine();
		System.out.println("Calling server.stop");
		
		try
		{
			tcpServer.stop();
			System.out.println("Server stopped");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
