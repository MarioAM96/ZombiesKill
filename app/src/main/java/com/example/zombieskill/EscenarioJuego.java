package com.example.zombieskill;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class EscenarioJuego extends AppCompatActivity {

    String UIDS, NOMBRES, ZOMBIES;
    TextView TvContador, TvNombre, TvTiempo;
    ImageView IvZombie;

    int contador = 0;

    //j


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escenario_juego);

        IvZombie = findViewById(R.id.IvZombie);

        TvContador = findViewById(R.id.TvContador);
        TvNombre = findViewById(R.id.TvNombre);
        TvTiempo = findViewById(R.id.TvTiempo);

        Bundle intent = getIntent().getExtras();
        UIDS = intent.getString("UID");
        NOMBRES = intent.getString("NOMBRE");
        ZOMBIES = intent.getString("ZOMBIE");

        TvNombre.setText(NOMBRES);
        TvContador.setText(ZOMBIES);

        IvZombie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contador++;
                TvContador.setText(String.valueOf(contador));

                IvZombie.setImageResource(R.drawable.zombieaplastado);

                //PERMITE EJECUTAR UN MENSAJE U OBJETO DENTRO DE ESTE CODIGO
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //SE EJECUTA LO DE AQUI DENTRO
                        IvZombie.setImageResource(R.drawable.zombie);
                    }
                }, 500);
            }
        });
    }
}