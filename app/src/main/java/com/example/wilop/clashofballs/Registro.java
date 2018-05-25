package com.example.wilop.clashofballs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Registro extends AppCompatActivity {
    EditText Nombre,Correo,Contraseña,Contraseña_conf;
    TextView Msj_error;
    ImageButton Registro;

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
                            //DO TODO
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
}
