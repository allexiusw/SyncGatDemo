package org.allex.task.syngatdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.allex.task.syngatdemo.Utils.Util;

import java.util.Date;

public class CrearPersonaActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText etPrimerNombre, etSegundoNombre, etPrimerApellido, etSegundoApellido, etFechaNacimiento;
    private Button btnGuardar, btnCancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_persona);

        etPrimerNombre = findViewById(R.id.etPrimerNombre);
        etSegundoNombre = findViewById(R.id.etSegundoNombre);
        etPrimerApellido = findViewById(R.id.etPrimerApellido);
        etSegundoApellido = findViewById(R.id.etSegundoApellido);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnCancelar= findViewById(R.id.btnCancelar);

        btnGuardar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnGuardar:{

                break;
            }

            case R.id.btnCancelar:{
                EditText[] editTexts = {etPrimerNombre, etSegundoNombre, etPrimerApellido,
                    etSegundoApellido, etFechaNacimiento};
                Util.limpiarEditText(editTexts);
            }
        }
    }
    }