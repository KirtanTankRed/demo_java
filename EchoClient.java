import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a message to send to the server: ");
        String message = scanner.nextLine();

        try {
            Socket clientSocket = new Socket("127.0.0.1", 8888);

            OutputStream outputStream = clientSocket.getOutputStream();
            outputStream.write(message.getBytes());
            outputStream.flush();

            InputStream inputStream = clientSocket.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);

            String response = new String(buffer, 0, bytesRead);
            System.out.println("Received from server: " + response);

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
