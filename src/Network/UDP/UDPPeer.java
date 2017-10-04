package Network.UDP;

import java.io.IOException;
import java.util.List;

public interface UDPPeer
{
	/**
	 * Starts the UDP server.
	 * @throws IOException
	 */
	public void start () throws
						 IOException;

	/**
	 * Returns the port that the server is running on.
	 * @return	The port that the server runs on.
	 */
	public int getPort ();

	/**
	 * Sets the port the server is running on.
	 * @param	port	The new port the server should listen on.
	 * WARNING: Before the port is changed, the server needs to be stopped.
	 * WARNING: After the port is changed, the server needs to be restarted.
	 */
	public void setPort (int port);

	/**
	 * Sends a string
	 * @param remoteHost	The remote host that the data should be sent to.
	 * @param data			The data to be sent.
	 */
	public void send (String remoteHost, String data);

	/**
	 * Sends all bytes in the data array.
	 * @param remoteHost	The remote host that the data should be sent to.
	 * @param data			The data to be sent.
	 */
	public void send (String remoteHost, byte[] data);

	/**
	 * Sends all bytes in the data list.
	 * @param remoteHost	The remote host that the data should be sent to.
	 * @param data			The data to be sent.
	 */
	public void send (String remoteHost, List<Byte> data);

	/**
	 * reads all bytes from the interal receive buffer
	 * @param	remoteHost	The remote host from whose buffer we want to read.	//todo: Check Grammar
	 * @return	All data in the buffer for the specified host.
	 */
	public byte[] receive (String remoteHost);

	/**
	 * read numBytes bytes from the internal receive buffer.
	 * @param remoteHost	The remote host from whose buffer we want to read. //todo: Check Grammar
	 * @param numBytes		The number of bytes to read.
	 * @return				The first numBytes bytes in the inputStream for the requested host.
	 */
	public byte[] receive (String remoteHost, int numBytes);

	/**
	 * Stops the server
	 * @throws IOException
	 */
	public void stop() throws IOException;

	/**
	 * Called by the server's start() method.
	 * Implements Runnable
	 * @see Runnable
	 */
	public void run ();

	public String toString();
}
