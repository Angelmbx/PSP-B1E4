import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/*
 Usando Sockets TCP realiza un programa cliente que introduzca cadenas por teclado hasta introducir un asterisco.
Las cadenas se enviarán a un programa servidor.
El programa servidor aceptará la conexión de un único cliente y por cada cadena recibida le devolverá al cliente
el número de caracteres de la misma.
El programa servidor finalizará cuando reciba un asterisco como cadena.
 */

public class Server {

    public static void main(String[] args) throws IOException {

        //create Server socket
        ServerSocket server = new ServerSocket(6008);
        System.out.println("SERVER set on PORT " + server.getLocalPort());

        // Server socket accepts client conection
        Socket client = server.accept();
        System.out.println("CLIENT connected to SERVER");

        //input stream
        DataInputStream inputStream = new DataInputStream(client.getInputStream());

        //output stream;
        DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());

        String clientText = inputStream.readUTF();

        while (!clientText.equals("*")) {
            int textLength = clientText.length();
            outputStream.writeInt(textLength);
        }

        //close streams and then sockets
        inputStream.close();
        outputStream.close();
        client.close();
        server.close();

    }

}
