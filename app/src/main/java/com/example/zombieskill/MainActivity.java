package com.example.zombieskill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button BLOGIN, BREGISTRO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BLOGIN = findViewById(R.id.BTNLOGIN);
        BREGISTRO = findViewById(R.id.BTNREGISTRO);

        //UBICACION
        String ubicacion = "fuentes/zombie.TTF";
        Typeface Tf = Typeface.createFromAsset(MainActivity.this.getAssets(), ubicacion);

        BLOGIN.setTypeface(Tf);
        BREGISTRO.setTypeface(Tf);

        //BOTON QUE REDIRIGE A LOGIN
        BLOGIN.setOnClickListener(view -> {
            //El Toast muestra un mensaje
            //Toast.makeText(MainActivity.this, "SE AH PRESIONADO EL BOTON LOGIN", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        });
        //BOTON QUE REDIRIGE AL REGISTRO
        BREGISTRO.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,Registro.class);
            startActivity(intent);
        });

    }
}