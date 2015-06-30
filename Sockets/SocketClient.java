package test;

import java.io.*;
import java.net.*;

public class SocketClient {
    public static void main(String[] args) throws IOException {
        
        if (args.length != 2) {
            System.err.println("Usage: java Client <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (
            Socket clientSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;

            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("ACK")){
                    System.out.println("Initiating file transfer...");
                    byte [] mybytearray  = new byte [6022386];
                    InputStream is = clientSocket.getInputStream();
                    FileOutputStream fos = new FileOutputStream("/home/zemoso06/rachana.txt"); 
                    BufferedOutputStream bos = new BufferedOutputStream(fos);
                    int bytesRead = is.read(mybytearray,0,mybytearray.length);
                    int current = bytesRead;
                    do {
                       bytesRead = is.read(mybytearray, current, (mybytearray.length-current));
                       if(bytesRead >= 0) current += bytesRead;
                    } while(bytesRead > -1);
                    bos.write(mybytearray, 0 , current);
                    bos.flush();
                    System.out.println("File "+ "downloaded (" + current + " bytes read)");
                    System.out.println("Done.");
                    break;
                }
                
                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    out.println(fromUser);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }
}