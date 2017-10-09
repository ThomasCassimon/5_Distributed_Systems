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
	private DatagramSocket socket;
	private LinkedList<DatagramPacket> packetBuffer;

	public Client()
	{
		this.isRunning = false;
		this.socket = null;
		this.packetBuffer = new LinkedList<DatagramPacket>();
	}

	@Override
	public void start() throws IOException
	{
		this.socket = new DatagramSocket();
		System.out.println("Receive socket created");
		this.isRunning = true;
		Thread t = new Thread (this);
		t.start();
	}

	@Override
	public void send(String remoteHost,
					 int port,
					 byte[] data)
	{
		DatagramPacket packet = null;
		try
		{
			packet = new DatagramPacket(data,0,data.length,InetAddress.getByName(remoteHost),port);
		}
		catch(UnknownHostException e)
		{
			System.err.println("Error host not found when trying to send packet");
			e.printStackTrace();
		}

		try
		{
			if (packet != null)
			{
				this.socket.send(packet);
			}
			else
			{
				throw new RuntimeException("Tried sending a NULL packet");
			}
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
		this.send(remoteHost,port,data.getBytes());
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
		DatagramPacket packet = this.receivePacket();

		if(packet != null)
		{
			return this.receivePacket().getData();
		}
		else
		{
			return null;
		}
	}

	@Override
	public DatagramPacket receivePacket()
	{
		if(!this.packetBuffer.isEmpty())
		{
			DatagramPacket packet = this.packetBuffer.getFirst();
			this.packetBuffer.removeFirst();
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
			this.socket.close();
		}
		this.isRunning = false;
	}

	@Override
	public void run()
	{
		System.out.println("Running: " + this.isRunning);
		System.out.println("Closed: " + this.socket.isClosed());
		while(this.isRunning && !this.socket.isClosed())
		{
			byte[] buffer = new byte[1500];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			try
			{
				this.socket.receive(packet);
				this.packetBuffer.add(packet);
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
