package com.example.projekt1_weilguni_12139417;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    private TextView responseTextView;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText matriculationEditText = findViewById(R.id.matriculationEditText);
        responseTextView = findViewById(R.id.responseTextView);
        Button sendButton = findViewById(R.id.sendButton);
        Button calculateButton = findViewById(R.id.calculateButton);




        sendButton.setOnClickListener(v -> {
            String matriculationNumber = matriculationEditText.getText().toString();

            if (isValidMatriculationNumber(matriculationNumber)) {
                new Thread(() -> {
                    String response = new TCPClient().sendData(matriculationNumber);
                    runOnUiThread(() -> displayResponse(response));
                }).start();
            }
            else {
                displayErrorMessage();
            }

        });



        calculateButton.setOnClickListener(v -> {
            String matriculationNumber = matriculationEditText.getText().toString();
            if (isValidMatriculationNumber(matriculationNumber)) {
                String sortedMatriculation = MatriculationSorter.sortMatriculationNumber(matriculationNumber);
                runOnUiThread(() -> responseTextView.setText("Sortierte Matrikelnummer: " + sortedMatriculation));
            }
            else {
                displayErrorMessage();
            }
        });
    }



    @SuppressLint("SetTextI18n")
    private void displayResponse(final String response) {
        runOnUiThread(() -> {
            if (response != null) {
                responseTextView.setText("Antwort vom Server: " + response);
            } else {
                responseTextView.setText("Keine Antwort vom Server erhalten.");
            }
        });
    }


    private boolean isValidMatriculationNumber(String matriculationNumber) {
        int MATRICULATION_NUMBER_LENGTH = 8;
        return matriculationNumber.length() == MATRICULATION_NUMBER_LENGTH;
    }


    @SuppressLint("SetTextI18n")
    private void displayErrorMessage() {
        responseTextView.setText("Matrikelnummer ist ungültig.");
    }
}

