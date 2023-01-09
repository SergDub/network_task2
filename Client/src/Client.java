import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String answer;

        String host = "netology.homework";
        int port = 8081;
        try (Socket clientSocket = new Socket(host, port);
             PrintWriter out = new
                     PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            out.println("Serg");

            String resp = in.readLine();
            System.out.println(resp);
            String resp1 = in.readLine();
            System.out.println(resp1);

            Scanner scanner = new Scanner(System.in);
            int i = 1;
            while (true) {
                System.out.println("Попытка номер " + i);
                answer = scanner.nextLine();
                out.println(answer);
                String resp2 = in.readLine();
                System.out.println(resp2);
                if (!(resp2.equals("Вы не угадали!  Мое число больше! Попробуйте еще!")) && !(resp2.equals( "Вы не угадали!  Мое число меньше! Попробуйте снова!")))
                    break;
                i++;
            }
            if (i == 8) System.out.println(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

