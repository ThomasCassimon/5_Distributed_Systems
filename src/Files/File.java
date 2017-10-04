package Files;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static java.lang.Long.min;

public class File
{
	/**
	 * Uses the new java NIO API to read files as fast as possible
	 * The maximum filesize is Integer.MAX_VALUE bytes (So about 4GB).
	 * @param file  The filename of the file we wish to read.
	 * @return      A byte array containing all bytes of the file we read.
	 * @throws IOException  An IOException can be thrown by the FileChannel.read() method, or because the file is too large.
	 */
	public static byte[] read (String file) throws
	                                        IOException
	{
		FileInputStream inputStream = new FileInputStream(file);

		FileChannel channel = inputStream.getChannel();

		if (channel.size() > Integer.MAX_VALUE)
		{
			throw new IOException("File is to large( " + Long.toString(channel.size()) + " bytes), maximum file size is " + Integer.toString(Integer.MAX_VALUE) + " bytes ( " + Integer.toString(Integer.MAX_VALUE / (1024 * 1024 * 1024)) + " Gigabytes)");
		}

		ByteBuffer buffer =  ByteBuffer.allocate((int) min(channel.size(), Integer.MAX_VALUE));

		while (channel.position() < channel.size())
		{
			channel.read(buffer);
		}

		channel.close();

		return buffer.array();
	}

	/**
	 * Uses the new java NIO API to write files as fast as possible
	 * @param file  The filename of the file we wish to write.
	 * @throws IOException An IOException can be thrown by the FileChannel.read() method, or because the file is too large.
	 */
	public static void write (String file, byte[] data) throws
	                                                    IOException
	{
		FileOutputStream outputStream = new FileOutputStream(file);

		FileChannel channel = outputStream.getChannel();

		channel.write(ByteBuffer.wrap(data));

		channel.close();
	}
}
