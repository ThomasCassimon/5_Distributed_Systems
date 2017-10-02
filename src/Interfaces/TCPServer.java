package Interfaces;

import java.io.IOException;
import java.util.List;

public interface TCPServer extends Runnable
{
	/**
	 * Starts the TCP server
	 */
	public void start () throws IOException;

	/**
	 * Returns the port that the server is running on
	 * @return
	 */
	public int getPort ();

	/**
	 * Sets the port the server is running on.
	 * @warning Before the port is changed, the server needs to be stopped
	 * @warning after the port is changed, the server needs to be restarted
	 * @param port
	 */
	public void setPort (int port);

	/**
	 * Sends a string
	 * @param data
	 */
	public void send (String data);

	/**
	 * Sends all bytes in the data array
	 * @param data
	 */
	public void send (byte[] data);

	/**
	 * Sends all bytes in the data list.
	 * @param data
	 */
	public void send (List<Byte> data);

	/**
	 * reads all bytes from the interal receive buffer
	 * @return
	 */
	public byte[] receive ();

	/**
	 * read numBytes bytes from the internal receive buffer.
	 * @param numBytes
	 * @return
	 */
	public byte[] receive (int numBytes);

	/**
	 * Stops the server
	 */
	public void stop() throws IOException;

	/**
	 * Called by the server's start() method.
	 * Implements Runnable
	 * @see Runnable
	 */
	public void run ();
}
