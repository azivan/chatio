package com.zivanbre;

import java.io.*;
import java.net.*;
import java.util.*;

public class MainServer {
    public static void main(String[] args) throws UnknownHostException {
        int port = 1337;
        String hostName = Inet4Address.getLocalHost().getHostName();
        try {
            System.out.println("Waiting for connection on: " + hostName + ":" + port);
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSocket = serverSocket.accept();
            System.out.println("New terminal connected");
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            clientHandler(output, input);
        } catch (IOException e) {
            System.out.println("Error when trying to listen to port: " + port);
            e.printStackTrace();
        }
    }

    public static void clientHandler(PrintWriter output, BufferedReader input) throws IOException {
        String clientInput;
        while ((clientInput = input.readLine()) != null) {
            System.out.println("Client typed: " + clientInput);
            output.println("Server: " + clientInput);
            if ( clientInput.equals("quit") ) {
                System.out.println("Client has quit the server");

            }
        }
    }
}
