package com.example.zombieskill;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText    correoLogin, passLogin;
    Button      BtnLogin;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        correoLogin = findViewById(R.id.correoLogin);
        passLogin = findViewById(R.id.passLogin);
        BtnLogin = findViewById(R.id.BtnLogin);
        auth = FirebaseAuth.getInstance();

        BtnLogin.setOnClickListener(view -> {
            String email = correoLogin.getText().toString();
            String pass = passLogin.getText().toString();

            //Validacion de correo electronico
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                correoLogin.setError("Correno no válido");
                correoLogin.setFocusable(true);
            } else if (pass.length()<6) {
                passLogin.setError("La contraseña debe tener almenos 6 caractes");
                passLogin.setFocusable(true);
            }else {
                LogeoDeJugador(email,pass);
            }
        });
    }

    private void LogeoDeJugador(String email, String pass){
        auth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        FirebaseUser user = auth.getCurrentUser();
                        startActivity(new Intent(Login.this, Menu.class));
                        assert user != null; //PARA QUE EL USUARIO NO SEA NULO
                        Toast.makeText(Login.this, "BIENVENIDO "+user.getEmail(),Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(e -> Toast.makeText(Login.this, ""+e.getMessage(),Toast.LENGTH_SHORT).show());
    }
}