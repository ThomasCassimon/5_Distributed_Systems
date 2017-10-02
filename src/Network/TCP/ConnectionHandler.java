package Network.TCP;

import Network.Constants;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectionHandler implements Runnable
{
	private static final int BUFFER_SIZE = 1 << 14; // Buffer is 16K in size

	private DataInputStream in;
	private DataOutputStream out;
	private Socket socket;

	public ConnectionHandler (Socket socket)
	{
		this.socket = socket;
	}

	public byte[] readBytes (int numBytes)
	{
		byte[] tmpBuffer = new byte [numBytes];

		try
		{
			this.in.read(tmpBuffer, 0, numBytes);
		}
		catch (IOException ioe)
		{
			System.err.println("Networking.TCP.ConnectionHandler.readBytes()\tAn exception was thrown while trying to read from DataInputStream");
			ioe.printStackTrace();
		}

		return tmpBuffer;
	}

	public byte[] readBytes()
	{
		ArrayList<Byte> tmpBuffer = new ArrayList<Byte> (BUFFER_SIZE);

		try
		{
			byte[] bytes = new byte [BUFFER_SIZE];

			while (this.in.read(bytes) > 0)
			{
				for (byte b : bytes)
				{
					tmpBuffer.add(b);
				}
			}
		}
		catch (IOException ioe)
		{
			System.err.println("Networking.TCP.ConnectionHandler.readBytes()\tAn exception was thrown while trying to read from DataInputStream");
			ioe.printStackTrace();
		}

		byte[] bytes = new byte [tmpBuffer.size()];

		for (int i = 0; i < tmpBuffer.size(); i++)
		{
			bytes[i] = tmpBuffer.get(i);
		}

		return bytes;
	}

	public String readString()
	{
		return new String(this.readBytes(), Constants.ENCODING);
	}

	public void write (byte[] data) throws IOException
	{
		this.out.write(data);
	}

	public void write (String data) throws IOException
	{
		this.out.write(data.getBytes());
	}

	@Override
	public void run()
	{
		try
		{
			this.in = new DataInputStream(this.socket.getInputStream());
			this.out = new DataOutputStream(this.socket.getOutputStream());
		}
		catch (IOException ioe)
		{
			System.err.println("Network.TCP.ConnectionHandler.run()\tAn exception was thrown when creating Data*Streams for the ConnectionHandler.");
			ioe.printStackTrace();
		}
	}
}
