import java.io.*;
import java.net.*;
import java.util.*;

public class TCPConcurrentServer {
   public static void main(String argv[]) {
      ServerSocket welcomeSocket = null;
      try {
         welcomeSocket = new ServerSocket(1667);
      } catch (IOException e) {
         System.out.println("Cannot create a welcome socket");
         System.exit(1);
      }

      while (true) {
         try {
            System.out.println("Waiting for client connection at port number 1667");
            Socket connectionSocket = welcomeSocket.accept();
            EchoThread echoThread = new EchoThread(connectionSocket);
            echoThread.start();
         } catch (IOException e) {
            System.out.println("Cannot create this connection");
         }
      }
   }
}

class EchoThread extends Thread {
   private Socket serviceSocket;

   public EchoThread(Socket serviceSocket) {
      this.serviceSocket = serviceSocket;
   }

   public void run() {
      try (
            Scanner inFromClient = new Scanner(serviceSocket.getInputStream());
            DataOutputStream outToClient = new DataOutputStream(serviceSocket.getOutputStream());) {
         String clientInput;

         while (true) {
            System.out.print("enter number 1 (to end just press enter): ");
            clientInput = inFromClient.nextLine();

            if (clientInput.isEmpty()) {
               break;
            }

            try {
               int number1 = Integer.parseInt(clientInput);

               System.out.print("enter number 2 (to end just press enter): ");
               clientInput = inFromClient.nextLine();

               if (clientInput.isEmpty()) {
                  break;
               }

               int number2 = Integer.parseInt(clientInput);

               int result = number1 + number2;

               outToClient.writeBytes("The result is " + result + "\r\n");
            } catch (NumberFormatException e) {
               outToClient.writeBytes("Invalid input. Please enter numbers.\r\n");
            }
         }
      } catch (IOException e) {
         System.out.println("Error handling client connection");
      } finally {
         try {
            serviceSocket.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }
}
