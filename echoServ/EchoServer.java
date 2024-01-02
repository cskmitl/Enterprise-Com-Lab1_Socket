// EchoServer.java

import java.net.*;
import java.io.*;

public class EchoServer
{
  public static final int PORT = 1667;
  public static void main(String[] args)
  {
    ServerSocket serverSocket = null;
    Socket serviceSocket = null;
    

    try
    { // create socket

      serverSocket = new ServerSocket(PORT);
    }
    catch (Exception e)
    {
      System.err.println("Error Creating Socket");
    }

    while (true)
    {
      try
      {

        // wait for connection then create streams
        System.out.println("Waiting for client connection");
        serviceSocket = serverSocket.accept();
        OutputStreamWriter output =
            new OutputStreamWriter(serviceSocket.getOutputStream());
        BufferedReader input = new BufferedReader(
					new InputStreamReader(serviceSocket.getInputStream()));
				
		while(true) {
			
			String inputWord = input.readLine();
			if (!inputWord.equals(""))
			{
				output.write(inputWord.toUpperCase() + "\r\n");
				output.flush();
			}
			else {
				output.close();
				input.close();
				serviceSocket.close();
				break;
			}
		}
				
	} catch (Exception e)
      	{
              System.err.println("Closing Socket connection");
              if (serviceSocket != null)
              try {
               serviceSocket.close();
               } catch (IOException ex) {}
      	}
    }
  }
}

