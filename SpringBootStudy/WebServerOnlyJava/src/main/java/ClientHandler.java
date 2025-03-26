import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable{
    private Socket clientSocket;

    public ClientHandler(Socket socket){
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try(BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))){

            String requestLine = in.readLine();
            System.out.println("클라이언트 요청: "+requestLine);

            boolean isPost = requestLine.startsWith("POST");

            int contentLength = 0;
            String line;
            while (!(line = in.readLine()).isEmpty()) {
                System.out.println("헤더: " + line);
                if (line.startsWith("Content-Length:")) {
                    contentLength = Integer.parseInt(line.split(": ")[1]);
                }
            }


            StringBuilder requestBody = new StringBuilder();
            if (isPost && contentLength > 0) {
                char[] bodyData = new char[contentLength];
                in.read(bodyData);
                requestBody.append(bodyData);
            }

            String response = "HTTP/1.1 200 OK\r\n" +
                                "Content-Type: text/html\r\n" +
                                "Connection: close\r\n\r\n" ;
            out.write(response);

            if (isPost) {
                out.write("<h1>POST 요청을 받았습니다!</h1>");
                out.write("<p>데이터: " + requestBody.toString() + "</p>");
            } else {
                out.write("<h1>GET 요청을 받았습니다!</h1>");
            }

            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
