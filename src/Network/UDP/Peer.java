
	package Network.UDP;

import Network.Constants;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.List;

public class Peer implements UDPPeer, Runnable
{
	private boolean isRunning;
	private int portNum;
	private DatagramSocket socket;

	public Peer(int port)
	{
		this.isRunning = false;
		this.portNum = portNum;
		this.socket = null;
	}

	@Override
	public void start()
	{
		try
		{
			socket = new DatagramSocket(Constants.UDP_PORT);


		}
		catch(SocketException e)
		{
			System.err.println("Error when making new datagram socket");
			e.printStackTrace();
		}
		this.isRunning = true;
	}

	@Override
	public int getPort()
		{
			return this.portNum;
		}

	@Override
	public void setPort(int port)
		{
			this.portNum = port;
		}

	@Override
	public void send(String remoteHost,
					 String data)

	{

	}

	@Override
	public void send(String remoteHost,
					 byte[] data)

	{

	}

	@Override
	public void send(String remoteHost,
					 List<Byte> data)
	{

	}

	@Override
	public byte[] receive(String remoteHost)
		{
			return new byte[0];
		}

	@Override
	public byte[] receive(String remoteHost,
						  int numBytes)
	{
		return new byte[0];
	}

	@Override
	public void stop() throws
			IOException
	{
		if(this.socket != null)
		{
			socket.close();
		}
		this.isRunning = false;
	}

	@Override
	public void run()
	{

	}
}