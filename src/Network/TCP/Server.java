package Network.TCP;

import Interfaces.TCPServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server implements TCPServer
{
	private boolean isRunning;
	private int portNum;
	private ServerSocket socket;
	private ConnectionHandler ch;

	public Server (int port)
	{
		this.isRunning = false;
		this.portNum = port;
		this.socket = null;
		this.ch = null;
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
			throw new RuntimeException("TCPServer::setPort():\tCan't change TCP port when server is running.");
		}
		else
		{
			this.portNum = port;
		}
	}

	@Override
	public void send(String data)
	{
		try
		{
			this.ch.write(data);
		}
		catch (IOException ioe)
		{
			System.err.println("Networking.TCP.Server::send()\tAn Exception occurred while trying to send data");
			ioe.printStackTrace();
		}
	}

	@Override
	public void send(byte[] data)
	{
		try
		{
			this.ch.write(data);
		}
		catch (IOException ioe)
		{
			System.err.println("Networking.TCP.Server::send()\tAn Exception occurred while trying to send data");
			ioe.printStackTrace();
		}
	}

	@Override
	public void send(List<Byte> data)
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
			this.ch.write(dataArray);
		}
		catch (IOException ioe)
		{
			System.err.println("Networking.TCP.Server::send()\tAn Exception occurred while trying to send data");
			ioe.printStackTrace();
		}
	}

	@Override
	public byte[] receive()
	{
		return this.ch.readBytes();
	}

	@Override
	public byte[] receive(int numBytes)
	{
		return this.ch.readBytes(numBytes);
	}

	@Override
	public void stop() throws IOException
	{
		synchronized (this.socket)
		{
			this.socket.close();
		}

		this.isRunning = false;
	}

	@Override
	public void run()
	{
		try
		{
			while (this.isRunning && !this.socket.isClosed())
			{
				synchronized (this.socket)
				{
					Socket clientSocket = this.socket.accept();
					this.ch = new ConnectionHandler(clientSocket);
				}

				Thread t = new Thread(this.ch);
				t.start();
			}
		}
		catch (IOException ioe)
		{
			System.err.println("Network.TCP.Server::run()\tException occurred in call to accept()");
			ioe.printStackTrace();
		}
	}
}
