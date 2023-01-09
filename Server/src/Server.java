import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("server started");
        int port = 8081;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ) {
                    System.out.println("New connection accepted");
                    final String name = in.readLine();
                    boolean a;
                    int i = 1;
                    int attempt;
                    int myNumber = (int) (Math.random() * 100);
                    System.out.println(myNumber);
                    out.println(String.format("Hi, %s, your port is %d", name, clientSocket.getPort()));
                    out.println(String.format("Отгадайте мое число от 1 до 100, У вас 7 попыток. Введите свой вариант"));
                    while (i < 8) {
                        // System.out.println("  i ====  " + i);
                        attempt = Integer.parseInt(in.readLine());
                        if (attempt == myNumber) {
                            break;
                        } else if ((attempt < myNumber) && !(i == 7))
                            out.println("Вы не угадали!  Мое число больше! Попробуйте еще!");
                        else if ((attempt > myNumber) && !(i == 7))
                            out.println("Вы не угадали!  Мое число меньше! Попробуйте снова!");
                        i++;
                    }
                    if (i == 8) {
                        out.println("Ваши попытки закончились! Вы проиграли! Было загадано число " + myNumber);
                    } else out.println("Поздравляю!!!" + "Вы угадали!  Было загадано число  " + myNumber);
                }
            }
        }
    }
}


