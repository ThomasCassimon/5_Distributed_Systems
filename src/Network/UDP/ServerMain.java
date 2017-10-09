package Network.UDP;

import Network.Constants;

import java.io.IOException;
import java.net.DatagramPacket;

/**
 * Created by Astrid on 09-Oct-17.
 */
public class ServerMain
{
    public static void main(String args[])
    {
        Server server = new Server(Constants.UDP_PORT_SERVER);

		DatagramPacket packet;
        byte[] data;

		try
		{
			System.out.println("Press enter to check for data...");
			System.in.read();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

        packet = server.receivePacket();
        data = packet.getData();
        System.out.println("Data received...");

        System.out.println("Data: " + data.toString());


		try
		{
			System.out.println("Press enter to send data...");
			System.in.read();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}


        server.send(packet.getAddress().getHostAddress(),packet.getPort(),data);


        server.stop();
    }
}
