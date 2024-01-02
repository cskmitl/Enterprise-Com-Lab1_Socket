import java.io.*;
import java.net.*;
import java.util.*;

class TCPServer {
   public static void main(String argv[]) {
      ServerSocket welcomeSocket = null;

      try {
         welcomeSocket = new ServerSocket(6789);
      } catch (IOException e) {
         System.out.println("Cannot create a welcome socket");
         System.exit(1);
      }

      while (true) {
         try {
            System.out.println("The server is waiting ");
            Socket connectionSocket = welcomeSocket.accept();
            handleClient(connectionSocket);
         } catch (IOException e) {
            System.out.println("Error cannot create this connection");
         }
      }
   }

   private static void handleClient(Socket connectionSocket) {
      try (
            Scanner inFromClient = new Scanner(connectionSocket.getInputStream());
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());) {
         String clientSentence;

         while (true) {
            clientSentence = inFromClient.nextLine();

            if (clientSentence.isEmpty()) {
               break;
            }

            String capitalizedSentence = clientSentence.toUpperCase() + '\n';
            outToClient.writeBytes(capitalizedSentence);
         }
      } catch (IOException e) {
         System.out.println("Error handling client connection");
      } finally {
         try {
            connectionSocket.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }
}
