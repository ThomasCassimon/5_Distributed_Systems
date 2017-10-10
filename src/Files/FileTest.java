package Files;

import java.io.IOException;

public class FileTest
{
	public static void main (String[] args)
	{
		String filename1 = "file1.bin";
		String filename2 = "file2.bin";
		String filename3 = "file3.bin";
		String filename4 = "file4.bin";
		
		File file1 = new File (filename1);
		File file2 = new File (filename2);
		File file3 = new File (filename3);
		File file4 = new File (filename4);

		try
		{
			long startRead = System.nanoTime();
			byte[] fileData = file1.read();
			long endRead = System.nanoTime();
			
			long startWrite = System.nanoTime();
			file2.write(fileData);
			long endWrite = System.nanoTime();
			
			System.out.println("Took " + Float.toString((endRead - startRead) / 1000000.0f) + "ms to read " + Float.toString(fileData.length / (1024.0f * 1024.0f * 1024.0f)) + "GB, took " + Float.toString((endWrite - startWrite) / 1000000.0f) + "ms to write it");
			System.out.println("Average Throughput: Read: " + Float.toString((fileData.length / (1024 * 1024)) / ((endRead - startRead) / 1000000000.0f)) + "MB/s, Write: " + Float.toString((fileData.length / (1024 * 1024)) / ((endWrite - startWrite) / 1000000000.0f)) + "MB/s");
			
			byte[][] segmented = File.segment(fileData, 1 << 12);
			System.out.println("Number of segments: " + Integer.toString(segmented.length));
			System.out.println("Segment Size: " + Integer.toString(segmented[0].length));
			
			/*
			byte[][] segmentedData = new byte [segmented.length][segmented[0].length];
			
			for (int i = 0; i < segmentedData.length; i++)
			{
				System.out.println("Read " + (1 << 12) + " bytes from file, segment no.: " + i);
				segmentedData[i] = file3.read(1 << 12);
			}
			
			for (int i = 0; i < segmentedData.length; i++)
			{
				System.out.println("Wrote " + (segmentedData[i].length) + " bytes from file, segment no.: " + i);
				file4.write(segmentedData[i]);
			}
			*/
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
