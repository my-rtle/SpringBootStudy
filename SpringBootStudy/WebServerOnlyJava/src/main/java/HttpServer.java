import java.io.*;
import java.net.*;
public class HttpServer {
    public static void main(String[] args) throws IOException{
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("서버 시작! http://localhost:"+port);

        while (true){
            Socket clientSocket = serverSocket.accept();
            new Thread(new ClientHandler(clientSocket)).start();
        }

    }
}


