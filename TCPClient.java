import java.io.*;
import java.net.*;
import java.util.*;

class TCPClient {
    public static void main(String argv[]) throws Exception {
        String sentence;
        Scanner inFromUser = new Scanner(System.in);
        Socket clientSocket = new Socket("localhost", 6789);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        while (true) {
            System.out.print("Please enter words: ");
            sentence = inFromUser.nextLine();

            if (sentence.isEmpty()) {
                break;
            }

            outToServer.writeBytes(sentence + '\n');
        }

        inFromUser.close();
        outToServer.close();
        clientSocket.close();
    }
}
