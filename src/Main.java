import java.nio.charset.StandardCharsets;

public class Main {
// http://localhost:80/test.txt
    public static void main(String[] args) {
        int port = 80;
        Server server = new Server();
        try {
            server.monitor(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
