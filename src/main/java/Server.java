import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static BufferedReader in; // поток чтения из сокета
    private static PrintWriter out; // поток записи в сокет
    static int port = 8089;

    public static void main(String[] args) throws IOException {
        System.out.println("Сервер запущен!");
        try (ServerSocket server = new ServerSocket(port))// серверсокет прослушивает указанный порт
        {
            while (true) {
                Socket clientSocket = server.accept(); // ждем пока кто нибудь не подключиться
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(
                        new OutputStreamWriter(clientSocket.getOutputStream()));
                String name = in.readLine(); // ждём пока клиент что-нибудь нам напишет
                System.out.println(name);
                out.println(name + " " + "Привет, это Сервер! Подскажи пожалуйста, сколько тебе лет?");
                out.flush();
                int age = Integer.parseInt(in.readLine()); // ждём пока клиент что-нибудь нам напишет
                System.out.println(age);
                out.println(fer(age));
                out.flush();
            }
        }
    }
    public static String fer(int age) {
        String result;
        if (age > 18) {
            result = "Добро пожаловать в зону для взрослых";
        } else {
            result = "Добро пожаловать в зону для детей";
        }
        return result;
    }
}


