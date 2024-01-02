// EchoClient.java

import java.net.*;
import java.io.*;

public class EchoClient
{
  public static final int PORT = 1667;
  public static void main(String[] args)
  {
    
	Socket clientSocket = null;
    
    try
    { // create socket

      clientSocket = new Socket("localhost", PORT);
    }
    catch (Exception e)
    {
      System.err.println("Error Creating Socket");
    }

    
      try
      {

		/*OutputStreamWriter output =
            new OutputStreamWriter(clientSocket.getOutputStream());*/
			PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true); 
        BufferedReader input = new BufferedReader(
					new InputStreamReader(clientSocket.getInputStream()));
        BufferedReader inputData = new BufferedReader(new InputStreamReader(System.in));    
				
		while(true) {
			System.out.println("Enter your word: ");
			String inputWord = inputData.readLine();
			output.write(inputWord + "\r\n");
			output.flush();
         if (inputWord.equals(""))
			{
				break;
			}
			String outputWord = input.readLine();
			System.out.println(outputWord);
						
		}
		input.close();
		output.close();
		clientSocket.close();
				
	} catch (Exception e)
      	{
              System.err.println("Closing Socket connection");
              if (clientSocket != null)
              try {
               clientSocket.close();
               } catch (IOException ex) {}
      	}
    
  }
}

