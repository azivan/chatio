import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author andreaszivanovic
 */
public class ClientHandler implements Runnable {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private ArrayList<ClientHandler> clients;

    public ClientHandler(Socket clientSocket, ArrayList<ClientHandler> clients) throws IOException {
        this.clients = clients;
        this.client = clientSocket;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            while (true) {
                String chatMsg = in.readLine();
                if ( chatMsg.startsWith("") ) {
                    int fs = chatMsg.indexOf("");
                    if ( fs != -1 ) {
                        broadcastMsg(chatMsg.substring(fs));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("IOException in ClientHandler");
            e.printStackTrace();
        } finally {
            out.close();
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void broadcastMsg(String msg) {
        for (ClientHandler aClient : clients) {
            aClient.out.println(msg);
        }
    }
}

