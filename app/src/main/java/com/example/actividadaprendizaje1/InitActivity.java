package com.example.actividadaprendizaje1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class InitActivity extends AppCompatActivity {

    ImageView ivLogo;
    ProgressBar progressBar;
    private int i;
    Handler handler;
    Intent miIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initt);

        ivLogo=findViewById(R.id.logo);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE); //Para que no sea visible al principio

        handler=new Handler();
        miIntent= new Intent(this, indexActivity.class);

        Thread miHilo=new Thread(new Runnable() {
            @Override
            public void run() {
                while(i<=100){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(i);
                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (i==100){
                        startActivity(miIntent);
                    }
                    i++;
                }
            }
        });
        miHilo.start();
    }
}