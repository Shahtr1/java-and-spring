import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public void run() {
        int port = 8010;
        ServerSocket socket;
        try {
            socket = new ServerSocket(port);
            socket.setSoTimeout(10000); // wait for 10 seconds
            while (true) {
                System.out.println("Server is listening on port " + port);
                Socket acceptedConnection = socket.accept();
                System.out.println("Connection accepted from client " + acceptedConnection.getRemoteSocketAddress());

                /**
                 * 1. BufferedReader: It is used to wrap an InputStream, providing a convenient
                 * method to handle input character streams. By buffering the input, it provides
                 * efficient reading of characters, arrays, and lines.
                 * This is particularly useful when reading data from a network stream, as it
                 * combines the received bytes into one complete character stream.
                 * 
                 * 2. PrintWriter: It is used to wrap an OutputStream, converting characters
                 * into bytes.
                 * It provides methods to print data in a variety of formats to a text-output
                 * stream.
                 * PrintWriter makes it easy to write formatted text data to the stream, and it
                 * handles the character-to-byte transformation for sending data over the
                 * network.
                 */
                PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream());
                BufferedReader fromClient = new BufferedReader(
                        new InputStreamReader(acceptedConnection.getInputStream()));

                toClient.println("Hello from the server");
                toClient.close();
                fromClient.close();
                acceptedConnection.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
