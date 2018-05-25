package com.example.wilop.clashofballs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    TextView text_error,txt_registro;
    EditText Correo,Contraseña;
    ImageButton Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });

    }
}
