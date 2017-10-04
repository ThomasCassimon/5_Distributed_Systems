
package Network.UDP;

import Network.Constants;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;

public class Peer implements UDPPeer, Runnable
{
	private boolean isRunning;
	private int portNum;
	private DatagramSocket socket;
	private LinkedList<DatagramPacket> packetBuffer;

	public Peer(int port)
	{
		this.isRunning = false;
		this.portNum = portNum;
		this.socket = null;
		this.packetBuffer = new LinkedList<>();
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void start()
	{
		try
		{
			this.socket = new DatagramSocket(Constants.UDP_PORT);
		}
		catch(SocketException e)
		{
			System.err.println("Error when creating new datagramsocket");
			e.printStackTrace();
		}
		this.isRunning = true;
		this.run();
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
	public byte[] receiveData()
	{
		DatagramPacket packet = packetBuffer.getFirst();
		packetBuffer.removeFirst();
		return packet.getData();
	}

	@Override
	public DatagramPacket receivePacket()
	{
		DatagramPacket packet = packetBuffer.getFirst();
		packetBuffer.removeFirst();
		return packet;
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
		while(this.isRunning)
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
		}
	}
}