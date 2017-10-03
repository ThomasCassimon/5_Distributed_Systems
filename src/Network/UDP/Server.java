package Network.UDP;

import Interfaces.UDPServer;

import java.io.IOException;
import java.net.DatagramSocket;
import java.util.List;

public class Server implements UDPServer
{
	private boolean isRunning;
	private int portNum;
	private DatagramSocket socket;

	public Server (int port)
	{
		this.isRunning = false;
		this.portNum = portNum;
	}

	@Override
	public void start() throws
						IOException
	{

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

	}

	@Override
	public void run()
	{

	}
}
