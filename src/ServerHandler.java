import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author andreaszivanovic
 */
public class ServerHandler implements Runnable {
    private Socket server;
    private BufferedReader in;
    private String serverResponse;

    public ServerHandler(Socket s) throws IOException {
        server = s;
        in = new BufferedReader(new InputStreamReader(server.getInputStream()));
    }

    @Override
    public void run() {
        try {
            while (true) {
                serverResponse = in.readLine();
                System.out.println("User said: " + serverResponse);
                if ( serverResponse == null ) break;
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
