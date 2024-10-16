import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  public void run() throws IOException {
    int port = 8010;
    try {
      ServerSocket socket = new ServerSocket(port);
      socket.setSoTimeout(10000);
      while(true) {
        System.out.println("Server is Listening of port "+port);
        Socket acceptedConnection = socket.accept();
        System.out.println("Connection Accepted from client"+ acceptedConnection.getRemoteSocketAddress());
        
        PrintWriter toCLient = new PrintWriter(acceptedConnection.getOutputStream());
        BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
        toCLient.println("Hello from Server");
        
        toCLient.close();
        fromClient.close();
        acceptedConnection.close();
        socket.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public static void main(String[] args) {
    Server server = new Server();
    try {
      server.run();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
}