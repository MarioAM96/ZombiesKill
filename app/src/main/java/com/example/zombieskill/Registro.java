package com.example.zombieskill;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Registro extends AppCompatActivity {

    EditText Correo, Password, Nombre;
    TextView Fecha;
    Button Registro;
    FirebaseAuth auth; //Autentificacion de Firebase
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Correo = findViewById(R.id.correoEt);
        Password = findViewById(R.id.passEt);
        Nombre = findViewById(R.id.nombreEt);
        Fecha = findViewById(R.id.fechaTxt);
        Registro = findViewById(R.id.Registrar);

        auth = FirebaseAuth.getInstance();

        Date date = new Date();
        SimpleDateFormat fecha = new SimpleDateFormat("d 'de' MMMM 'del' YYYY");
        String StringFecha = fecha.format(date);
        Fecha.setText(StringFecha);

        Registro.setOnClickListener(new View.OnClickListener() {
                  public void onClick(View view){
                      String email = Correo.getText().toString();
                      String password = Password.getText().toString();

                      //Validacion
                      if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                          Correo.setError("Correno no válido");
                          Correo.setFocusable(true);
                      } else if (password.length()<6) {
                          Password.setError("La contraseña debe tener almenos 6 caractes");
                          Password.setFocusable(true);
                      }else {
                          RegistrarJugador(email,password);
                      }
                  }
        });
    }

    //METODO PARA EL REGISTRO DEL JUGADOR
    private void RegistrarJugador(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
            //SI EL JUGADOR FUE REGISTRADO CORRECTAMENTE
                if(task.isSuccessful()) {
                    FirebaseUser user = auth.getCurrentUser();

                    int contador = 0;

                    assert user != null; //EL USUARIO NO DEBE SER NULO
                    String uidString = user.getUid();
                    String correoString = Correo.getText().toString();
                    String passString = Password.getText().toString();
                    String nombreString = Nombre.getText().toString();
                    String fechaString = Fecha.getText().toString();

                    HashMap<Object, Object> DatosJUGADOR = new HashMap<>();
                    DatosJUGADOR.put("Uid", uidString);
                    DatosJUGADOR.put("Email", correoString);
                    DatosJUGADOR.put("Password", passString);
                    DatosJUGADOR.put("Nombres", nombreString);
                    DatosJUGADOR.put("Fecha", fechaString);
                    DatosJUGADOR.put("Zombies", contador);

                    FirebaseDatabase database = FirebaseDatabase.getInstance(); //INSTANCIA DE BASE DE DATOS
                    DatabaseReference reference = database.getReference("ZOMBIES KILL DB"); //NOMBRE ASIGNADO A LA BASE DE DATOS
                    reference.child(uidString).setValue(DatosJUGADOR);
                    startActivity(new Intent(Registro.this,Menu.class));
                    Toast.makeText(Registro.this, "USUARIO REGISTRADO CON ÉXITO", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(Registro.this, "HA OCURRIDO UN ERROR", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            //SI FALLA EL REGISTRO
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Registro.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}