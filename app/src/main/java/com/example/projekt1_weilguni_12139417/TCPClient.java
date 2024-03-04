package com.example.projekt1_weilguni_12139417;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class TCPClient {

    public String sendData(String data) {
        Socket socket = null;
        BufferedWriter writer = null;
        BufferedReader reader = null;
        String response = null;

        try {
            String SERVER_ADDRESS = "se2-submission.aau.at";
            int SERVER_PORT = 20080;
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            // Send data to server
            writer.write(data);
            writer.newLine();
            writer.flush();

            // Read response from server
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            response = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) socket.close();
                if (writer != null) writer.close();
                if (reader != null) reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return response;
    }
}



