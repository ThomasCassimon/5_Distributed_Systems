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

		System.out.println("BUFFERLENGTH: " + client.getBufferLength());

		byte[][] dataSeg = new byte[client.getBufferLength()][500];

		int length = client.getBufferLength();
		for(int i = 0; i<length;i++)
		{
			dataSeg[i] = client.receiveData();
			System.out.println("SEGMENT " + i);
			//file.append(client.receiveData());
			//Client.printByteArray(dataSeg[i]);
		}


		byte[] dataArr = new byte[dataSeg.length * 500];
		int k = 0;
		for(int i = 0; i<dataSeg.length;i++)
		{
			for(int j = 0;j<dataSeg[i].length; j++)
			{
				dataArr[(i * 500) + j] = dataSeg[i][j];
				//System.out.println("ARRAY INDEX " + ((i*500)+j) + "	" + dataSeg[i][j]);
				//Client.printByteArray(dataSeg[i]);
			}
		}
		try
		{
			//Client.printByteArray(dataArr);
			file.write(dataArr);
		}
		catch(IOException e)
		{
			System.err.println("Exception when writing file");
			e.printStackTrace();
		}


		System.out.println("Data received...");

		System.out.println("Press any key to close server");
		scanner.nextLine();
        client.stop();
    }
}
