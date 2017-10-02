import java.io.IOException;

public class ServerMain
{
	public static void main (String[] args)
	{
		Network.TCP.Server tcpServer = new Network.TCP.Server(Network.Constants.TCP_PORT);
		try
		{
			tcpServer.start();
		}
		catch (IOException ioe)
		{
			System.err.println("ServerMain::main()\tError when trying to start TCP server.");
			ioe.printStackTrace();
		}

		System.out.println("Press enter to close server");

		try
		{
			System.in.read();
			tcpServer.stop();
		}
		catch (IOException ioe)
		{
			System.err.println("ServerMain::main()\tError when trying to close TCP serever.");
			ioe.printStackTrace();
		}
	}
}
