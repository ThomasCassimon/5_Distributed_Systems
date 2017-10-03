package Network.UDP;

import java.io.IOException;
import java.util.List;

public class Server implements UDPServer
{
	private boolean isRunning;
	private int portNum;

	public Server (int port)
	{

	}

	@Override
	public void start() throws
						IOException
	{

	}

	@Override
	public int getPort()
	{
		return 0;
	}

	@Override
	public void setPort(int port)
	{

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
