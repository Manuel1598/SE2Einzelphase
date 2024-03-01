package com.example.projekt1_weilguni_12139417;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TCPClient {

    private final String SERVER_ADDRESS = "se2-submission.aau.at";
    private final int SERVER_PORT = 20080;

    public void sendData(String data) {
        Socket socket = null;
        DataOutputStream outputStream = null;
        DataInputStream inputStream = null;

        try {
            // Verbindung zum Server herstellen
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);

            // Daten senden
            outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeUTF(data);

            // Antwort vom Server empfangen
            inputStream = new DataInputStream(socket.getInputStream());
            String response = inputStream.readUTF();
            System.out.println("Antwort vom Server: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Verbindung schließen
            try {
                if (socket != null) socket.close();
                if (outputStream != null) outputStream.close();
                if (inputStream != null) inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



