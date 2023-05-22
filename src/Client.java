import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;

/*
 Usando Sockets TCP realiza un programa cliente que introduzca cadenas por teclado hasta introducir un asterisco.
Las cadenas se enviarán a un programa servidor.
El programa servidor aceptará la conexión de un único cliente y por cada cadena recibida le devolverá al cliente
el número de caracteres de la misma.
El programa servidor finalizará cuando reciba un asterisco como cadena.
 */

public class Client {

    public static void main(String[] args) throws IOException {
        //create client socket with connection paramaters
        String host = "localhost";
        int port = 6008;

        Socket client  = new Socket(host,port);

        //output stream to Server
        String inputText="";
        Scanner sc = new Scanner(System.in);
        DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());

        //input stream
        DataInputStream inputStream = new DataInputStream(client.getInputStream());


        while (!inputText.equals("*")){
            System.out.println("Introduzca cadenas de texto. * para salir");
            inputText = sc.nextLine();
            //sending data to SERVER
            outputStream.writeUTF(inputText);

            //recieving SERVER data
            System.out.println("Datos recibidos del SERVER. Número de caracteres: " + inputStream.readInt());
        }

        //Program stops once inputText = *
        if (inputText.equals("*")){
            System.exit(0);
        }

        //close streams and socket
        outputStream.close();
        inputStream.close();
        client.close();

    }


}
