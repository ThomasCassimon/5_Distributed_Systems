package Network.TCP;

import Interfaces.TCPServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

public class Server implements TCPServer
{
	private boolean isRunning;
	private int portNum;
	private ServerSocket socket;
	private HashMap<String, ConnectionHandler> incomingConnections;

	public Server (int port)
	{
		this.isRunning = false;
		this.portNum = port;
		this.socket = null;
		this.incomingConnections = new HashMap<String, ConnectionHandler> ();
	}

	@Override
	public void start() throws
	                    IOException
	{
		this.socket = new ServerSocket(this.portNum);

		Thread t = new Thread(this);
		t.start();
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
		if (this.isRunning)
		{
			throw new RuntimeException("TCPServer.setPort()\tCan't change TCP port when server is running.");
		}
		else
		{
			this.portNum = port;
		}
	}

	@Override
	public void send(String remoteHost, String data)
	{
		try
		{
			this.incomingConnections.get(remoteHost).write(data);
		}
		catch (IOException ioe)
		{
			System.err.println("Networking.TCP.Server.send()\tAn Exception was thrown while trying to send data");
			ioe.printStackTrace();
		}
	}

	@Override
	public void send(String remoteHost, byte[] data)
	{
		try
		{
			this.incomingConnections.get(remoteHost).write(data);
		}
		catch (IOException ioe)
		{
			System.err.println("Networking.TCP.Server.send()\tAn Exception was thrown while trying to send data");
			ioe.printStackTrace();
		}
	}

	@Override
	public void send(String remoteHost, List<Byte> data)
	{
		byte[] dataArray = new byte [data.size()];
		int i = 0;

		for (byte b : data)
		{
			dataArray[i] = b;
			i++;
		}

		try
		{
			this.incomingConnections.get(remoteHost).write(dataArray);
		}
		catch (IOException ioe)
		{
			System.err.println("Networking.TCP.Server.send()\tAn Exception was thrown while trying to send data");
			ioe.printStackTrace();
		}
	}

	@Override
	public byte[] receive(String remoteHost)
	{
		return this.incomingConnections.get(remoteHost).readBytes();
	}

	@Override
	public byte[] receive(String remoteHost, int numBytes)
	{
		return this.incomingConnections.get(remoteHost).readBytes(numBytes);
	}

	@Override
	public void stop() throws IOException
	{
		this.isRunning = false;
		this.socket.close();
	}

	@Override
	public void run()
	{
		try
		{
			while (this.isRunning && !this.socket.isClosed())
			{
				Socket clientSocket = this.socket.accept();
				ConnectionHandler ch = new ConnectionHandler(clientSocket);

				Thread t = new Thread(ch);
				t.start();

				this.incomingConnections.put(clientSocket.getRemoteSocketAddress().toString(), ch);
			}
		}
		catch (IOException ioe)
		{
			System.err.println("Network.TCP.Server.run()\tException was thrown in call to accept()");
			ioe.printStackTrace();
		}
	}

	public String toString()
	{
		StringBuilder resBuilder = new StringBuilder();
		resBuilder.append("Server listening on port ");
		resBuilder.append(this.portNum);
		resBuilder.append(" (TCP)");
		resBuilder.append('\n');
		resBuilder.append("Running: ");
		resBuilder.append(this.isRunning);
		resBuilder.append('\n');
		resBuilder.append("Connected Clients:");
		resBuilder.append('\n');

		if (this.incomingConnections.size() > 0)
		{
			for (String remoteHost : this.incomingConnections.keySet())
			{
				resBuilder.append(remoteHost);
				resBuilder.append('\n');
			}
		}
		else
		{
			resBuilder.append("None");
		}

		return resBuilder.toString();
	}
}
