package Files;

import java.io.IOException;

public class FileTest
{
	public static void main (String[] args)
	{
		String file = "manjaro-xfce-17.0.4-stable-x86_64.iso";
		String copyFile = "copy-of-manjaro-xfce-17.0.4-stable-x86_64.iso";

		try
		{
			long startRead = System.nanoTime();
			byte[] fileData = File.read(file);
			long endRead = System.nanoTime();
			long startWrite = System.nanoTime();
			File.write(copyFile, fileData);
			long endWrite = System.nanoTime();
			System.out.println("Took " + Float.toString((endRead - startRead) / 1000000.0f) + "ms to read " + Float.toString(fileData.length / (1024.0f * 1024.0f * 1024.0f)) + "GB, took " + Float.toString((endWrite - startWrite) / 1000000.0f) + "ms to write it");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
