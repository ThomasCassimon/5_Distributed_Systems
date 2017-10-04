package Network.TCP;

import Network.Constants;

import java.io.IOException;

/**
 * Created by Astrid on 02-Oct-17.
 */
public class ClientMain
{
	public static void main(String args[])
	{
		Network.TCP.Client tcpClient = new Network.TCP.Client(Constants.TCP_SERVER_IP);

		tcpClient.start();

		try
		{
			System.out.println("Press enter to send data...");
			System.in.read();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		tcpClient.send("Hello, world!".getBytes());

		tcpClient.stop();
	}
}
