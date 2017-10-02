package Network.TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

public class ConnectionHandler implements Runnable
{
	private static final int BUFFER_SIZE = 1 << 14; // Buffer is 16K in size

	private DataInputStream in;
	private DataOutputStream out;
	private Socket socket;
	private byte[] data;

	public ConnectionHandler (Socket socket)
	{
		this.socket = socket;
		this.data = new byte [BUFFER_SIZE];
	}

	public byte[] readBytes (int numBytes)
	{
		byte[] data = new byte [numBytes];
		System.arraycopy(this.data, 0, data, 0, numBytes);
		System.arraycopy(this.data, numBytes, this.data, 0, this.data.length - numBytes - 1);
		return data;
	}

	public byte[] readBytes()
	{
		return this.data;
	}

	public String readString()
	{
		return Arrays.toString(this.data);
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
			this.in.readFully(this.data);
		}
		catch (IOException ioe)
		{
			System.err.println("Network.TCP.ConnectionHandler::run()\tAn exception was thrown when creating Data*Streams for the ConnectionHandler.");
			ioe.printStackTrace();
		}
	}
}
