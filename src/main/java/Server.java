import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) throws IOException {
        Server.startServer(23444);
    }

    public static void startServer(int port) throws IOException {

        ServerSocket serverSocket = new ServerSocket(port);

        try (Socket socket = serverSocket.accept();
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Клиент подсоединился");
            String msgIn;

            while ((msgIn = in.readLine()) != null) {
                System.out.println(msgIn);
                if (msgIn.equals("end")) break;
                int clientNumber = Integer.parseInt(msgIn);
                int result = Server.countFibonacci(clientNumber);
                out.println("Число Фибоначии #" + clientNumber + ": " + result);
            }
        }
    }

    public static int countFibonacci(int clientNumber) {

        int beforeLast = 0;
        int last = 1;
        int result = 0;

        for (int i = 0; i < clientNumber - 1; i++) {

            if (clientNumber == 0) {
                result = beforeLast;
                return result;
            } else if (clientNumber == 1) {
                result = last;
                return result;
            }

            result = last + beforeLast;
            beforeLast = last;
            last = result;
        }

        return result;
    }
}
