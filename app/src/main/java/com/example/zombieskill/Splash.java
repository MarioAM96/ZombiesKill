package com.example.zombieskill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        int Duracion_Splash = 3000;

        //Handler EJECUTA UN DETERMINADO CODIGO EN UN TIEMPO DETERMINADO
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //CODIGO QUE EJECUTA EL HANDLER
                Intent Intent = new Intent(Splash.this,MainActivity.class);
                startActivity(Intent);
            }
        }, Duracion_Splash);
    }
}