import java.io.*;
import java.net.Socket;

public class Client {

    private static Socket clientSocket; //сокет для общения
    private static BufferedReader reader; // нам нужен ридер читающий с консоли, иначе как
    // мы узнаем что хочет сказать клиент?
    private static BufferedReader in; // поток чтения из сокета
    private static PrintWriter out; // поток записи в сокет

    static String host = "netology.homework";
    static int port = 8089;
    static int age;

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket(host, port)) {
            //читаем сообщения с сервера
            reader = new BufferedReader(new InputStreamReader(System.in));
            //поток для чтения из сокета
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // поток для записи
            out = new PrintWriter(
                    new OutputStreamWriter(clientSocket.getOutputStream()), true);

            System.out.println("Как вас зовут?");
            String name = reader.readLine(); // ждём пока клиент что-нибудь не напишет
            out.println(name); // отправляем сообщение на сервер
            out.flush();
            String serverName = in.readLine(); // ждём, что скажет сервер
            System.out.println(serverName); // получив - выводим на экран

            age = Integer.parseInt(reader.readLine()); // ждём пока клиент что-нибудь не напишет
            out.println(age); // отправляем сообщение на сервер
            out.flush();
            String serverAge = in.readLine(); // ждём, что скажет сервер
            System.out.println(serverAge); // получив - выводим на экран
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}