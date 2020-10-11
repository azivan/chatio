import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author andreaszivanovic
 */
public class Server {

    private static final int PORT = 1337;
    private static final int threadCounter = 0;

    private static ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService numThreads = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(PORT);

        while (true) {
            System.out.println("[SERVER]: Waiting for client connections...");

            //Waits and listens for new connections
            Socket client = ss.accept();
            ClientHandler clientThread = new ClientHandler(client, clients);
            clients.add(clientThread);

            numThreads.execute(clientThread);

        }
    }
}
