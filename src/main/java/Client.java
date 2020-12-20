import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

    public static void main(String[] args) throws IOException {
        Client.startClient();
    }

    public static void startClient() throws IOException {

        Socket socket = new Socket("localhost", 23444);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {

            String msgOut;

            while (true) {
                System.out.println("Введите порядковый номер числа Фибонначи");
                msgOut = scanner.nextLine();
                out.println(msgOut);
                if (msgOut.equals("end")) break;
                System.out.println("Сервер: " + in.readLine());
            }
        }
    }
}
