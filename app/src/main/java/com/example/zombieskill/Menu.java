package com.example.zombieskill;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Menu extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference JUGADORES;
    Button CerrarSesionBtn, JugarBtn, PuntuacionesBtn, AcercaDeBtn;
    TextView MiPuntuaciontxt, uid, correo, nombre, MenuTxt, Zombies,fecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();
        JUGADORES = firebaseDatabase.getReference("ZOMBIES KILL DB");

        //UBICACION
        String ubicacion = "fuentes/zombie.TTF";
        Typeface Tf = Typeface.createFromAsset(Menu.this.getAssets(), ubicacion);


        Zombies = findViewById(R.id.Zombies);
        MiPuntuaciontxt = findViewById(R.id.MiPuntuaciontxt);
        uid = findViewById(R.id.uid);
        correo = findViewById(R.id.correo);
        nombre = findViewById(R.id.nombre);
        fecha = findViewById(R.id.fecha);
        MenuTxt = findViewById(R.id.Menutxt);

        JugarBtn = findViewById(R.id.JugarBtn);
        PuntuacionesBtn = findViewById(R.id.PuntuacionesBtn);
        AcercaDeBtn = findViewById(R.id.AcercaDeBtn);
        CerrarSesionBtn = findViewById(R.id.CerrarSesionBtn);

        MiPuntuaciontxt.setTypeface(Tf);
        uid.setTypeface(Tf);
        correo.setTypeface(Tf);
        nombre.setTypeface(Tf);
        MenuTxt.setTypeface(Tf);

        JugarBtn.setTypeface(Tf);
        PuntuacionesBtn.setTypeface(Tf);
        AcercaDeBtn.setTypeface(Tf);
        CerrarSesionBtn.setTypeface(Tf);

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
            Consulta();
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

    private void Consulta(){
        //CONSULTA
        Query query = JUGADORES.orderByChild("Email").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                for(DataSnapshot ds : datasnapshot.getChildren()){
                    //OBTENCION DE LOS DATOS
                    String zombiesString = ""+ds.child("Zombies").getValue();
                    String uidString = ""+ds.child("Uid").getValue();
                    String emailString = ""+ds.child("Email").getValue();
                    String nombreString = ""+ds.child("Nombres").getValue();
                    String fechaString = ""+ds.child("Fecha").getValue();
                    //ASGINAR LOS DATOS OBTENIDOS
                    Zombies.setText(zombiesString);
                    uid.setText(uidString);
                    correo.setText(emailString);
                    nombre.setText(nombreString);
                    fecha.setText(fechaString);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}