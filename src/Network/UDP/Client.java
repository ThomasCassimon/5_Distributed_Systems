package Network.UDP;

import java.io.IOException;
import java.net.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Astrid on 09-Oct-17.
 */
public class Client implements UDPPeer, Runnable
{
	private boolean isRunning;
	private int portNum;
	private DatagramSocket socket;
	private LinkedList<DatagramPacket> packetBuffer;

	public Client()
	{
		this.isRunning = false;
		this.socket = null;
		this.packetBuffer = new LinkedList<>();
		this.init();
	}

	public void init()
	{
		try
		{
			this.socket = new DatagramSocket();
			this.setPort(this.socket.getPort());
			System.out.println("Receive socket created");
		}
		catch(SocketException e)
		{
			System.err.println("Error when creating new datagramsocket");
			e.printStackTrace();
		}
		this.isRunning = true;
	}

	@Override
	public void start() throws IOException
	{
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
					 int port,
					 byte[] data)
	{
		InetAddress IP;
		DatagramPacket packet = null;
		try
		{
			IP = InetAddress.getByName(remoteHost);
			packet = new DatagramPacket(data,0,data.length,IP,port);
		}
		catch(UnknownHostException e)
		{
			System.err.println("Error host not found when trying to send packet");
			e.printStackTrace();
		}
		try
		{
			this.socket.send(packet);
		}
		catch(IOException e)
		{
			System.err.println("Error when sending packet");
			e.printStackTrace();
		}


	}

	@Override
	public void send(String remoteHost,
					 int port,
					 String data)
	{
		byte[] bytes = data.getBytes();
		this.send(remoteHost,port,bytes);

	}

	@Override
	public void send(String remoteHost,
					 int port,
					 List<Byte> data)
	{
		byte[] bytes = new byte[data.size()];
		int i=0;

		for(byte b: data)
		{
			bytes[i] = b;
			i++;
		}

		this.send(remoteHost,port,bytes);
	}

	@Override
	public byte[] receiveData()
	{
		DatagramPacket packet = receivePacket();

		if(packet != null)
		{
			return receivePacket().getData();
		}
		else
		{
			return null;
		}
	}

	@Override
	public DatagramPacket receivePacket()
	{
		if(!packetBuffer.isEmpty())
		{
			DatagramPacket packet = packetBuffer.getFirst();
			packetBuffer.removeFirst();
			return packet;
		}
		else
		{
			return null;
		}

	}

	@Override
	public void stop()
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
		System.out.println("Running: " + isRunning);
		System.out.println("Closed: " + socket.isClosed());
		while(this.isRunning && !socket.isClosed())
		{
			byte[] buffer = new byte[1500];
			DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
			try
			{
				socket.receive(packet);
				packetBuffer.add(packet);
			}
			catch(IOException e)
			{
				System.err.println("Error when trying to receive a packet");
				e.printStackTrace();
			}
			System.out.println("Packet received");
		}
	}
}
