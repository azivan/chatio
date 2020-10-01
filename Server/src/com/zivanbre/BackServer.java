package com.zivanbre;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class BackServer extends Thread {

    private final Socket clientSocket;

    public BackServer(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {

    }

    private void clientHandler(PrintWriter out, BufferedReader in) throws IOException {
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            out.println("Server: " + inputLine);
            System.out.println("Client typed: " + inputLine);
            if(inputLine.equals("quit")) {
                System.out.println("Client has quit the server");
                break;
            }
        }
    }
}
