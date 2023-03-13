package com.example.zombieskill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button BLOGIN, BREGISTRO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BLOGIN = findViewById(R.id.BTNLOGIN);
        BREGISTRO = findViewById(R.id.BTNREGISTRO);

        BLOGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //El Toast muestra un mensaje
                Toast.makeText(MainActivity.this, "SE AH PRESIONADO EL BOTON LOGIN", Toast.LENGTH_SHORT).show();
            }
        });

        BREGISTRO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Registro.class);
                startActivity(intent);
            }
        });

    }
}