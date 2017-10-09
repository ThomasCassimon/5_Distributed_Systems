package Network.UDP;

import Network.Constants;

import java.io.IOException;
import java.net.DatagramPacket;

/**
 * Created by Astrid on 09-Oct-17.
 */
public class ClientMain
{
    public static void main(String args[])
    {
        Client client = new Client();
		DatagramPacket packet;
		byte[] data;

		System.out.println("Started...");

        try
        {
            System.out.println("Press enter to send data...");
            System.in.read();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        client.send(Constants.UDP_SERVER_IP,Constants.UDP_PORT_SERVER,"Hello, world!".getBytes());

        System.out.println("Data send");

		try
		{
			System.out.println("Press enter to receive data...");
			System.in.read();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		Thread t = new Thread(client);

		t.start();

		while((packet = client.receivePacket()) == null)
		{
		}
		data = packet.getData();
		System.out.println("Data received...");
		System.out.println(data);

        client.stop();
    }
}
