package Network.UDP;

import Files.File;
import Network.Constants;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Astrid on 09-Oct-17.
 */
public class ClientMain
{
    public static void main(String args[])
    {
        Client client = new Client();
	    client.start();
	
	    Scanner scanner = new Scanner(System.in);
	    
		System.out.println("Started...");
	
	    System.out.println("Press enter to send data...");
	    scanner.nextLine();
	    
        client.send("127.0.0.1", 4800, "Hello, world!".getBytes());

        System.out.println("Data sent");
	
	    System.out.println("Press enter to check for data...");
	    scanner.nextLine();

	 
		//byte[] data = client.receivePacket().getData();

		//System.out.println(new String(data, Constants.ENCODING));

		File file = new File("result.gif");

		byte[][] data = new byte[client.getBufferLength()][500];
		int i = 0;
		while(!client.bufferEmpty())
		{


			data[i] = client.receiveData();
			//file.append(client.receiveData());
			i++;
		}

		byte[] dataA = new byte[data.length * 500];
		int k = 0;

		for(byte[] bytes: data)
		{
			for(i = 0;i<bytes.length; i++)
			{
				dataA[k] = bytes[i];
				k++;
			}
		}
		try
		{
			file.write(dataA);
		}
		catch(IOException e)
		{
			System.err.println("Exception when writing file");
			e.printStackTrace();
		}


		System.out.println("Data received...");


        client.stop();
    }
}
