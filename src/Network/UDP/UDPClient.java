package Network.UDP;

import java.util.List;

public interface UDPClient
{
	/**
	 * Starts the UDP client
	 */
	public void start ();

	/**
	 * Returns the port that the client is running on
	 * @return
	 */
	public int getPort ();

	/**
	 * Sets the port the client is running on.
	 * WARNING: Before the port is changed, the client needs to be stopped
	 * WARNING: after the port is changed, the client needs to be restarted
	 * @param port
	 */
	public void setPort (int port);

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
	 * Stops the client
	 */
	public void stop();
}
