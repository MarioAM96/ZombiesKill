package com.example.zombieskill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Menu extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
    }

    //ESTE METODO SE EJECUTA CUANDO SE ABRE EL JUEGO
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
}