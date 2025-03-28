import java.io.*;
import java.net.*;
public class HttpServer {
    public static void main(String[] args) throws IOException{
        int port = 9090;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("서버 시작! http://localhost:"+port);

        while (true){
            System.out.println("클라이언트 요청 대기 중...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("클라이언트 연결됨!");
            new Thread(new ClientHandler(clientSocket)).start();
        }

    }
}


