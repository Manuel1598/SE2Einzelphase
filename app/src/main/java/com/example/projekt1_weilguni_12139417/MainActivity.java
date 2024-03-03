package com.example.projekt1_weilguni_12139417;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;


public class MainActivity extends AppCompatActivity {

    private TextView responseTextView;
    private DisposableObserver<String> disposableObserver;
    private Disposable disposable;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText matriculationEditText = findViewById(R.id.matriculationEditText);
        responseTextView = findViewById(R.id.responseTextView);
        Button sendButton = findViewById(R.id.sendButton);
        Button calculateButton = findViewById(R.id.calculateButton);


        disposableObserver = new DisposableObserver<String>() {
            @Override
            public void onNext(String response) {
                displayResponse(response);
            }

            @Override
            public void onError(Throwable e) {
                displayErrorMessage();
            }

            @Override
            public void onComplete() {
                Toast.makeText(MainActivity.this, "Serveranfrage abgeschlossen", Toast.LENGTH_SHORT).show();
            }
        };

        sendButton.setOnClickListener(v -> {
            String matriculationNumber = matriculationEditText.getText().toString();
            if (isValidMatriculationNumber(matriculationNumber)) {
                if (disposable != null && !disposable.isDisposed()) {
                    disposable.dispose(); // Dispose previous subscription
                }
                disposable = getImageNetworkCall(matriculationNumber)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(disposableObserver);
            } else {
                displayErrorMessage();
            }
        });


        calculateButton.setOnClickListener(v -> {
            String matriculationNumber = matriculationEditText.getText().toString();
            if (isValidMatriculationNumber(matriculationNumber)) {
                String sortedMatriculation = MatriculationSorter.sortMatriculationNumber(matriculationNumber);
                responseTextView.setText("Sortierte Matrikelnummer: " + sortedMatriculation);
            } else {
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

    private Observable<String> getImageNetworkCall(String matriculationNumber) {
        return Observable.create(subscriber -> {
            String response = new TCPClient().sendData(matriculationNumber);
            if (response != null) {
                subscriber.onNext(response);
                subscriber.onComplete();
            } else {
                subscriber.onError(new Throwable("Keine Antwort vom Server erhalten."));
            }
        });
    }
}

