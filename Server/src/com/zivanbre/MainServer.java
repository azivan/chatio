package com.zivanbre;

import java.io.*;
import java.net.*;
import java.util.*;

public class MainServer {
    public static void main(String[] args) throws UnknownHostException {
        // Init socket on port 1337
        int port = 1337;
        String hostName = Inet4Address.getLocalHost().getHostName();
        try {
            System.out.println("Waiting for connection on: " + hostName + ":" + port);
            // New socket
            ServerSocket serverSocket = new ServerSocket(port);
            // Accepts connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("New terminal connected on port: " + port);
            // Create client input w/ server echo output
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // Call method
            clientHandler(output, input);
        } catch (IOException e) {
            System.out.println("Error when trying to listen to port: " + port);
            e.printStackTrace();
        }
    }

    public static void clientHandler(PrintWriter output, BufferedReader input) throws IOException {
        String clientInput;
        // Open socket while terminal clientInput is not null
        while ((clientInput = input.readLine()) != null) {
            System.out.println("Client typed: " + clientInput);
            output.println("You typed: " + clientInput);
            output.println("Server responded: " + clientInput);
            // Use "quit" to close
            if ( clientInput.equals("quit") ) {
                System.out.println("Client has quit the server");
                break;
            }
        }
    }
}
