package Network.TCP;

import Interfaces.TCPClient;
import Network.Constants;

import java.net.*;
import java.io.*;
import java.util.List;

public class Client implements TCPClient
{
	private static final int BUFFER_SIZE = 1500;
	private int serverPort;
	private String IP;
	private Socket clientSocket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;

	public Client(String IP)
	{
		this.serverPort = Constants.TCP_PORT;
		this.IP = IP;
	}

	public Client( String IP, int port)
    {
        this.serverPort = port;
        this.IP = IP;
    }

	@Override
	public void start()
	{
		try
		{
			this.clientSocket = new Socket(this.IP, this.serverPort);
			inputStream = new DataInputStream(clientSocket.getInputStream());
			outputStream = new DataOutputStream(clientSocket.getOutputStream());
		}
		catch(IOException e)
		{
			System.err.println("IO exception when starting client TCP socket");
			e.printStackTrace();
		}
	}

	@Override
	public int getPort()
	{
		return serverPort;
	}

	@Override
	public void setPort(int port)
	{
		this.serverPort = port;
	}

	@Override
	public void send(byte[] data)
	{
		try
		{
			outputStream.write(data);
		}
		catch(IOException e)
		{
			System.err.println("Error when writing data to outputstream");
			e.printStackTrace();
		}
	}

	@Override
	public void send(List<Byte> data)
	{
		byte[] arrayData = new byte[data.size()];

		for (int i = 0; i<data.size();i++)
		{
			arrayData[i] = data.get(i);
		}

		try
		{
			outputStream.write(arrayData);
		}
		catch(IOException e)
		{
			System.err.println("Error when writing data to outputstream");
			e.printStackTrace();
		}
	}

	@Override
	public byte[] receive()
	{
		int i;
		try
		{
			i = inputStream.available();
		}
		catch (IOException e)
		{
			System.err.println("Error when estimating length of inputstream");
			e.printStackTrace();
		}

		byte[] data = new byte[BUFFER_SIZE];


		try
		{
			 inputStream.read(data);
		}
		catch (IOException e)
		{
			System.err.println("Error when reading byte from input stream");
			e.printStackTrace();
		}

		return data;
	}

	@Override
	public byte[] receive(int numBytes)
	{
		byte[] data = new byte[numBytes];

		try
		{
			inputStream.read(data);
		}
		catch(IOException e)
		{
			System.err.println("Error when reading certain amount of bytes from inputstream");
			e.printStackTrace();
		}

		return data;
	}

	@Override
	public void stop()
	{
		try
		{
			clientSocket.close();
		}
		catch(IOException e)
		{
			System.err.println("Error when closing client socket");
			e.printStackTrace();
		}
	}
}
