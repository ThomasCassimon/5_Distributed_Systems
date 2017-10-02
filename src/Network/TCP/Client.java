package Network.TCP;

import Interfaces.TCPClient;
import Network.NetworkConstants;

import java.net.*;
import java.io.*;
import java.util.List;

public class Client implements TCPClient
{
    private int serverPort;
    private Socket clientSocket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public Client()
    {

        this.serverPort = NetworkConstants.TCP_PORT;
    }

    @Override
    public void start()
    {
        try
        {
            this.clientSocket = new Socket(NetworkConstants.TCP_SERVER_IP, this.serverPort);
            inputStream = new DataInputStream(clientSocket.getInputStream());
        }
        catch(IOException e)
        {
            System.err.println("IO exception when starting client TCP socket");
            e.printStackTrace();
        }




    }

    @Override
    public int getPort()
    {
        return serverPort;
    }

    @Override
    public void setPort(int port)
    {
        this.serverPort = port;
    }

    @Override
    public void send(byte[] data)
    {

    }

    @Override
    public void send(List<Byte> data)
    {

    }

    @Override
    public byte[] receive()
    {
        return new byte[0];
    }

    @Override
    public byte[] receive(int numBytes)
    {
        return new byte[0];
    }

    @Override
    public void stop()
    {

    }
}
