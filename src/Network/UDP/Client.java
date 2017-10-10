package Network.UDP;

import java.io.IOException;
import java.net.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Astrid on 09-Oct-17.
 */
public class Client implements UDPClient, Runnable
{
	private DatagramSocket socket;
	private LinkedList<DatagramPacket> packetBuffer;

	public Client()
	{
		this.socket = null;
		this.packetBuffer = new LinkedList<DatagramPacket>();
	}

	@Override
	public void start()
	{
		try
		{
			this.socket = new DatagramSocket();
			
			Thread t = new Thread (this);
			t.start();
		}
		catch (SocketException e)
		{
			System.err.println("An Exception was thrown while creating a new DatagramSocket.");
			e.printStackTrace();
		}
	}

	@Override
	public void send(String remoteHost,
					 int port,
					 byte[] data)
	{
		try
		{
			this.socket.send(new DatagramPacket(data,0, data.length, InetAddress.getByName(remoteHost), port));
		}
		catch(UnknownHostException e)
		{
			System.err.println("Error host not found when trying to send packet");
			e.printStackTrace();
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
			throw new RuntimeException("Packet received was null");
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
			throw new RuntimeException("Packet buffer was empty");
		}
	}

	@Override
	public void stop()
	{
		if(this.socket != null)
		{
			this.socket.close();
		}
	}

	@Override
	public void run()
	{
		while(!this.socket.isClosed())
		{
			byte[] buffer = new byte[1500];
			DatagramPacket incomingPacket = new DatagramPacket(buffer, buffer.length);
			try
			{
				this.socket.receive(incomingPacket);
				System.out.println("Received " + incomingPacket.getData().length + " Bytes");
				this.packetBuffer.add(incomingPacket);
				System.out.println("Packetbuffer size " + this.packetBuffer.size());
			}
			catch(IOException e)
			{
				System.err.println("Error when trying to receive a packet");
				e.printStackTrace();
			}
		}
	}

	public boolean bufferEmpty()
	{
		return packetBuffer.isEmpty();
	}
}
