// DayServer.java

import java.net.*;
import java.io.*;
import java.util.Date;

public class DayServer {
  public static final int PORT = 1666;

  public static void main(String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(PORT)) {
      while (true) {
        System.out.println("Waiting for client connection");
        try (Socket serviceSocket = serverSocket.accept();
            OutputStreamWriter output = new OutputStreamWriter(serviceSocket.getOutputStream())) {

          Date now = new Date();
          output.write(now.toString() + "\r\n");
          output.flush();
        } catch (IOException e) {
          System.err.println("Error handling client connection");
        }
      }
    } catch (IOException e) {
      System.err.println("Error creating ServerSocket");
    }
  }
}
