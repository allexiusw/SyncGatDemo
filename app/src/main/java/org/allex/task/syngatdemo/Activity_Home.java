package org.allex.task.syngatdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_Home extends AppCompatActivity implements View.OnClickListener {
    private Button btnCrear, btnListar, btnInformacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__home);

        btnCrear = findViewById(R.id.btnCrear);
        btnListar = findViewById(R.id.btnListar);
        btnInformacion = findViewById(R.id.btnInformacion);

        btnCrear.setOnClickListener(this);
        btnListar.setOnClickListener(this);
        btnInformacion.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCrear:{
                Intent intent = new Intent(Activity_Home.this, CrearPersonaActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btnListar:{
                Intent intent = new Intent(Activity_Home.this, ListaPersonasActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btnInformacion:{
                Intent intent = new Intent(Activity_Home.this, activity_informacion.class);
                startActivity(intent);
                break;
            }
        }
    }
}