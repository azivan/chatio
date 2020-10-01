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
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BackServer server = new BackServer(clientSocket);
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        clientHandler(out, in);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        } catch (IOException e) {
            System.out.println("Error when trying to listen to port: " + port);
            e.printStackTrace();
        }
    }

    private static void clientHandler(PrintWriter out, BufferedReader in) throws IOException {
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            out.println("Server: " + inputLine);
            System.out.println("Client typed: " + inputLine);
            if ( inputLine.toLowerCase().equals("quit") ) {
                System.out.println("Client has quit the server");
                break;
            }
        }
    }
}
