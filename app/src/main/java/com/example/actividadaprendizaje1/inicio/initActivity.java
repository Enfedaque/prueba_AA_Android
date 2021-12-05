package com.example.actividadaprendizaje1.inicio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.example.actividadaprendizaje1.R;
import com.example.actividadaprendizaje1.inicio.indexActivity;

public class initActivity extends AppCompatActivity {

    ProgressBar progressBar;
    private int i;
    Handler handler;
    Intent miIntent;
    VideoView videoLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initt);

        //Video del logo
        videoLogo=findViewById(R.id.video);
        //Ruta del video
        String videoURL="android.resource://" + getPackageName() + "/" + R.raw.logo;
        Uri videoUri=Uri.parse(videoURL);
        videoLogo.setVideoURI(videoUri);
        videoLogo.start();

        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE); //Para que no sea visible al principio

        handler=new Handler();
        miIntent= new Intent(this, indexActivity.class);

        Thread miHilo=new Thread(new Runnable() {
            @Override
            public void run() {
                while(i<=80){
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
                    if (i==80){
                        startActivity(miIntent);
                    }
                    i++;
                }
            }
        });
        miHilo.start();
    }
}