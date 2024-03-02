package com.example.projekt1_weilguni_12139417;

import android.os.AsyncTask;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;



public class MainActivity extends AppCompatActivity {

    private EditText matriculationEditText;
    private TextView responseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matriculationEditText = findViewById(R.id.matriculationEditText);
        responseTextView = findViewById(R.id.responseTextView);
        Button sendButton = findViewById(R.id.sendButton);
        Button calculateButton = findViewById(R.id.calculateButton);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String matriculationNumber = matriculationEditText.getText().toString();
                sendDataToServer(matriculationNumber);
            }
        });


        calculateButton.setOnClickListener(new View.OnClickListener() { // Klicklistener für den "Calculate" Button
            @Override
            public void onClick(View v) {
                String matriculationNumber = matriculationEditText.getText().toString();
                String sortedMatriculation = MatriculationSorter.sortMatriculationNumber(matriculationNumber);
                responseTextView.setText("Sortierte Matrikelnummer: " + sortedMatriculation);
            }
        });


    }


    private void sendDataToServer(final String matriculationNumber) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String response = new TCPClient().sendData(matriculationNumber);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        displayResponse(response);
                    }
                });
            }
        }).start();
    }





    private void displayResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (response != null) {
                    responseTextView.setText("Antwort vom Server: " + response);
                } else {
                    responseTextView.setText("Keine Antwort vom Server erhalten.");
                }
            }
        });
    }
}