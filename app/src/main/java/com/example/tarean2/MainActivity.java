package com.example.tarean2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ProgressBar pb;
    Button bt;
    int counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pb = findViewById(R.id.progressBar);
        bt = findViewById(R.id.btningresar);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pb.setVisibility(View.VISIBLE);
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        counter++;
                        pb.setProgress(counter);
                        if (counter == 50){
                            timer.cancel();
                            Intent opcion = new Intent(MainActivity.this, Inventario.class);
                            startActivity(opcion);
                        }
                    }
                };
                timer.schedule(timerTask, 100, 100);
            }
        });



    }
    public void selec(View v){
        Intent selecc = new Intent(this, Inventario.class);
        startActivity(selecc);

    }
}