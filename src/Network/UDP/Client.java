package Network.UDP;

import Network.Constants;

import java.io.IOException;
import java.net.*;
import java.util.List;

public class Client implements UDPClient
{
	private boolean dataPresent;
	private int portNum;
	private String remoteHost;
	private DatagramSocket socket;

	public Client (String remoteHost)
	{
		this.dataPresent = false;
		this.remoteHost = remoteHost;
		this.portNum = Constants.UDP_PORT;
	}

	public Client (String remoteHost, int port)
	{
		this.dataPresent = false;
		this.remoteHost = remoteHost;
		this.portNum = port;
	}

	@Override
	public void start()
	{
		try
		{
			this.socket = new DatagramSocket();
		}
		catch (SocketException se)
		{
			System.err.println("Network.UDP.Client.start()\tA SocketException was thrown while creating a DatagramSocket.");
			se.printStackTrace();
		}
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
		this.start();
	}

	@Override
	public void send(byte[] data)
	{
		try
		{
			DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName(this.remoteHost), this.portNum);
			this.socket.send(packet);
		}
		catch (UnknownHostException uhe)
		{
			System.err.println("Network.UDP.Client.send()\tAn UnknownHostException was thrown while creating a DatagramPacket.");
			uhe.printStackTrace();
		}
		catch (IOException ioe)
		{
			System.err.println("Network.UDP.Client.send()\tAn IOException was thrown while trying to send the DatagramPacket.");
			ioe.printStackTrace();
		}
	}

	@Override
	public void send(List<Byte> data)
	{
		byte[] dataArray = new byte[data.size()];

		for (int i = 0; i < data.size(); i++)
		{
			dataArray[i] = data.get(i);
		}

		this.send(dataArray);
	}

	public boolean hasData ()
	{
		return this.dataPresent;
	}

	@Override
	public byte[] receive()
	{
		return new byte[0];
	}

	@Override
	public byte[] receive(int numBytes)
	{
		return new byte[0];
	}

	@Override
	public void stop()
	{

	}
}
