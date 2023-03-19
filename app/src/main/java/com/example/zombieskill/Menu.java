package com.example.zombieskill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Menu extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    Button CerrarSesionBtn, JugarBtn, PuntuacionesBtn, AcercaDeBtn;
    TextView MiPuntuaciontxt, uid, correo, nombre, MenuTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        MiPuntuaciontxt = findViewById(R.id.MiPuntuaciontxt);
        uid = findViewById(R.id.uid);
        correo = findViewById(R.id.correo);
        nombre = findViewById(R.id.nombre);
        MenuTxt = findViewById(R.id.Menutxt);

        JugarBtn = findViewById(R.id.JugarBtn);
        PuntuacionesBtn = findViewById(R.id.PuntuacionesBtn);
        AcercaDeBtn = findViewById(R.id.AcercaDeBtn);
        CerrarSesionBtn = findViewById(R.id.CerrarSesionBtn);

        JugarBtn.setOnClickListener(view -> Toast.makeText(Menu.this, "JUGAR", Toast.LENGTH_SHORT).show());

        PuntuacionesBtn.setOnClickListener(view -> Toast.makeText(Menu.this, "PUNTUACIONES", Toast.LENGTH_SHORT).show());

        AcercaDeBtn.setOnClickListener(view -> Toast.makeText(Menu.this, "ACERCA DE", Toast.LENGTH_SHORT).show());

        CerrarSesionBtn.setOnClickListener(view -> CerrarSesion());
    }

    //ESTE METODO SE EJECUTA CUANDO SE ABRE EL JUEGOS
    protected void onStart(){
        UsuarioLogueado();
        super.onStart();
    }
    //EL METODO COMPRUEBA SI EL JUGADOR HA INICIADO SESIONMN
    private void UsuarioLogueado(){
        if (user != null){
            Toast.makeText(this, "Jugador en linea", Toast.LENGTH_SHORT).show();
        }
        else {
            startActivity(new Intent(Menu.this, MainActivity.class));
            finish();
        }
    }

    private void CerrarSesion(){
        auth.signOut();
        startActivity(new Intent(Menu.this, MainActivity.class));
        Toast.makeText(this, "Sesión finalizada", Toast.LENGTH_SHORT).show();
    }
}