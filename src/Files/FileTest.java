package Files;

import java.io.IOException;

public class FileTest
{
	public static void main (String[] args)
	{
		String file = "C:\\Users\\Thomas\\Dropbox\\file.bin";
		String copyFile = "C:\\Users\\Thomas\\Dropbox\\copy-of-file.bin";
		
		File fileObj = new File (file);
		File copyObj = new File (copyFile);

		try
		{
			long startRead = System.nanoTime();
			byte[] fileData = fileObj.read();
			long endRead = System.nanoTime();
			
			long startWrite = System.nanoTime();
			copyObj.write(fileData);
			long endWrite = System.nanoTime();
			System.out.println("Took " + Float.toString((endRead - startRead) / 1000000.0f) + "ms to read " + Float.toString(fileData.length / (1024.0f * 1024.0f * 1024.0f)) + "GB, took " + Float.toString((endWrite - startWrite) / 1000000.0f) + "ms to write it");
			System.out.println("Average Throughput: Read: " + Float.toString((fileData.length / (1024 * 1024)) / ((endRead - startRead) / 1000000000.0f)) + "MB/s, Write: " + Float.toString((fileData.length / (1024 * 1024)) / ((endWrite - startWrite) / 1000000000.0f)) + "MB/s");
			byte[][] segmented = File.segment(fileData, 1 << 12);
			System.out.println("Number of segments: " + Integer.toString(segmented.length));
			System.out.println("Segment Size: " + Integer.toString(segmented[0].length));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
