import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 * @author andreaszivanovic
 */
public class Client {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 1337;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_IP, SERVER_PORT);

        ServerHandler serverConnection = new ServerHandler(socket);

        BufferedReader kb = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        String name = getName(kb);

        new Thread(serverConnection).start();


        while (true) {
            String command = kb.readLine();
            if ( command.toLowerCase().startsWith("/quit") ) break;
            out.println(command);
        }

        socket.close();
        System.exit(0);

    }

    private static String getName(BufferedReader kb) throws IOException {
        System.out.println("Enter your name: ");
        return kb.readLine();
    }
}
