package com.example.wilop.clashofballs;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Registro extends AppCompatActivity {
    EditText Nombre,Correo,Contraseña,Contraseña_conf;
    TextView Msj_error;
    ImageButton Registro;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Nombre = findViewById(R.id.r_nombre);
        Correo = findViewById(R.id.r_correo);
        Contraseña = findViewById(R.id.r_contraseña);
        Contraseña_conf = findViewById(R.id.r_contraseña_confirmar);
        Msj_error = findViewById(R.id.texto_registro);
        Registro = findViewById(R.id.registro);
        mAuth = FirebaseAuth.getInstance();

        Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtnombre = Nombre.getText().toString();
                String txtcorreo= Correo.getText().toString();
                String txtcontra = Contraseña.getText().toString();
                String txtcontra_conf = Contraseña_conf.getText().toString();
                if (txtnombre.isEmpty() || txtcorreo.isEmpty() || txtcontra.isEmpty()|| txtcontra_conf.isEmpty()){
                    Msj_error.setVisibility(View.VISIBLE);
                    Msj_error.setText("Datos invalidos");
                }else {
                    if (txtcontra.equals(txtcontra_conf)){
                        if(txtcontra.length()>6){
                            Msj_error.setVisibility(View.INVISIBLE);
                            registro();
                        }else{
                            Msj_error.setVisibility(View.VISIBLE);
                            Msj_error.setText("Las contraseñas es demasiado corta");
                        }

                    }else {
                        Msj_error.setVisibility(View.VISIBLE);
                        Msj_error.setText("Las contraseñas no coinciden");
                    }
                }
            }
        });

    }
    private void registro(){
        String txtnombre = Nombre.getText().toString();
        String txtcorreo= Correo.getText().toString();
        String txtcontra = Contraseña.getText().toString();

        mAuth.createUserWithEmailAndPassword(txtcorreo,txtcontra)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(Registro.this, "Cuenta creada",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Registro.this, "Error al crear la cuenta",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
