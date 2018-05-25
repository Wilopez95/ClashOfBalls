package com.example.wilop.clashofballs;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    TextView text_error,txt_registro;
    EditText Correo,Contraseña;
    ImageButton Login;

    FirebaseAuth mAuth;


    @Override
        protected void onStart() {
        super.onStart();
        //FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        text_error = findViewById(R.id.text_error);
        Correo = findViewById(R.id.correo);
        Contraseña = findViewById(R.id.contraseña);

        txt_registro = findViewById(R.id.txt_registro);
        txt_registro.setText(Html.fromHtml("<u>Registro</u>"));

        Login = findViewById(R.id.login);

        txt_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Registro.class);
                startActivity(intent);
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtcorreo = Correo.getText().toString();
                String txtcontra = Contraseña.getText().toString();
                if (txtcorreo.isEmpty()||txtcontra.isEmpty()){
                    text_error.setText("Datos invalidos");
                    text_error.setVisibility(View.VISIBLE);
                }else {
                    text_error.setVisibility(View.INVISIBLE);
                    loginUser();


                }


            }
        });

    }
    private void loginUser(){
        String txtcorreo = Correo.getText().toString();
        String txtcontra = Contraseña.getText().toString();
        mAuth.signInWithEmailAndPassword(txtcorreo, txtcontra)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            updateUI(null);
                        }

                        // ...
                    }
                });

    }
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

        } else {
            text_error.setText("Datos invalidos");
            text_error.setVisibility(View.VISIBLE);
            //Toast.makeText(Login.this, "Error al iniciar sesion",
                    //Toast.LENGTH_SHORT).show();
        }
    }
}
