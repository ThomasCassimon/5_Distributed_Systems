package Files;

import java.io.IOException;

import static java.lang.Math.min;

public class FileTest
{
	public static void main (String[] args)
	{
		String filename1 = "giphy.gif";
		String filename2 = "copy1.gif";
		String filename3 = "giphy.gif";
		String filename4 = "copy3.gif";
		
		File file1 = new File (filename1);
		File file2 = new File (filename2);
		File file3 = new File (filename3);
		File file4 = new File (filename4);

		try
		{
			/*
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
			*/

			System.out.println(file3.toString());

			byte[][] segmentedData = new byte [(int) min(Math.ceil((float) file3.size() / (float) (1 << 12)), Integer.MAX_VALUE)][1<<12];

			for (int i = 0; file3.available() > 0; i++)
			{
				System.out.println("Read " + (1 << 12) + " bytes from file, segment no.: " + i + " Avail: " + Integer.toString(file3.available()));
				segmentedData[i] = file3.read(1 << 12);
			}
			
			for (int i = 0; i < segmentedData.length; i++)
			{
				System.out.println("Wrote " + (segmentedData[i].length) + " bytes from file, segment no.: " + i);
				file4.append(segmentedData[i]);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
