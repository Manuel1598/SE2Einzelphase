package com.example.projekt1_weilguni_12139417;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TCPClient {

    private final String SERVER_ADDRESS = "se2-submission.aau.at";
    private final int SERVER_PORT = 20080;

    public String sendData(String data) {
        Socket socket = null;
        DataOutputStream outputStream = null;
        DataInputStream inputStream = null;
        String response = null;

        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            outputStream = new DataOutputStream(socket.getOutputStream());

            //Übergabe der Matrikelnumemer zum Server
            byte[] matriculationBytes = data.getBytes("UTF-8");
            outputStream.writeInt(matriculationBytes.length);
            outputStream.write(matriculationBytes);

            //Lesen der Antwort vom Server
            inputStream = new DataInputStream(socket.getInputStream());
            int responseLength = inputStream.readInt();
            byte[] responseBytes = new byte[responseLength];
            inputStream.readFully(responseBytes);
            response = new String(responseBytes, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) socket.close();
                if (outputStream != null) outputStream.close();
                if (inputStream != null) inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return response;
    }
}



