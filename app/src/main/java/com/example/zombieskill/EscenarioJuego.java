package com.example.zombieskill;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class EscenarioJuego extends AppCompatActivity {

    String UIDS, NOMBRES, ZOMBIES;
    TextView TvContador, TvNombre, TvTiempo;
    ImageView IvZombie;
    TextView AnchoTv, AltoTv;

    int AnchoPantalla, AltoPantalla;

    Random Aleatorio;
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

        AnchoTv = findViewById(R.id.AnchoTv);
        AltoTv = findViewById(R.id.AltoTv);

        TvNombre.setText(NOMBRES);
        TvContador.setText(ZOMBIES);

        Pantalla();
        CuentaAtras();

        IvZombie.setOnClickListener(view -> {
            contador++;
            TvContador.setText(String.valueOf(contador));

            IvZombie.setImageResource(R.drawable.zombieaplastado);

            //PERMITE EJECUTAR UN MENSAJE U OBJETO DENTRO DE ESTE CODIGO
            new Handler().postDelayed(() -> {
                //SE EJECUTA LO DE AQUI DENTRO
                IvZombie.setImageResource(R.drawable.zombie);
                Movimiento();
            }, 200);
        });
    }

    private void Pantalla(){

        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);

        AnchoPantalla = point.x;
        AltoPantalla = point.y;

        String ANCHOS = String.valueOf(AnchoPantalla);
        String ALTOS = String.valueOf(AltoPantalla);

        AnchoTv.setText(ANCHOS);
        AltoTv.setText(ALTOS);

        Aleatorio = new Random();
    }

    private void Movimiento(){
        int min = 0;
        //MAXIMOS EN LOS QUE LA IMAGEN SE PUDED MOVER
        int MaximoX = AnchoPantalla - IvZombie.getWidth();
        int MaximoY = AltoPantalla - IvZombie.getHeight();

        int randomX = Aleatorio.nextInt(((MaximoX-min)+1)+min);
        int randmY = Aleatorio.nextInt(((MaximoY-min)+1)+min);

        IvZombie.setX(randomX);
        IvZombie.setY(randmY);
    }

    private void CuentaAtras() {
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                //SE EJCUTA CADA SEGUNDO
                long segundosRestantes = millisUntilFinished/1000;
                TvTiempo.setText(segundosRestantes+"S");

            }
            //CUANDO SE ACABA EL TIEMPO
            public void onFinish() {
                TvTiempo.setText("0S");
            }
        }.start();
    }
}