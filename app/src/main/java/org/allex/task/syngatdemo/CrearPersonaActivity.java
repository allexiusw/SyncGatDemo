package org.allex.task.syngatdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.allex.task.syngatdemo.Entities.Persona;
import org.allex.task.syngatdemo.Interfaces.IPersonaService;
import org.allex.task.syngatdemo.Services.PersonaService;
import org.allex.task.syngatdemo.Utils.GenericObjectResponse;
import org.allex.task.syngatdemo.Utils.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CrearPersonaActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText etPrimerNombre, etSegundoNombre, etPrimerApellido, etSegundoApellido, etFechaNacimiento;
    private Button btnGuardar, btnCancelar;
    private IPersonaService personaService;
    private EditText[] editTexts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_persona);

        personaService = new PersonaService(this);
        etPrimerNombre = findViewById(R.id.etPrimerNombre);
        etSegundoNombre = findViewById(R.id.etSegundoNombre);
        etPrimerApellido = findViewById(R.id.etPrimerApellido);
        etSegundoApellido = findViewById(R.id.etSegundoApellido);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnCancelar= findViewById(R.id.btnCancelar);

        editTexts = new EditText[]{etPrimerNombre, etSegundoNombre, etPrimerApellido,
                etSegundoApellido, etFechaNacimiento};

        btnGuardar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnGuardar:{
                String primerNombre, segundoNombre, primerApellido, segundoApellido, fechaNacimiento;
                primerNombre = etPrimerNombre.getText().toString();
                segundoNombre = etSegundoNombre.getText().toString();
                primerApellido = etPrimerApellido.getText().toString();
                segundoApellido = etSegundoApellido.getText().toString();
                fechaNacimiento = etFechaNacimiento.getText().toString();
                boolean isValidData = Util.validarCampos(this , new String[]{primerNombre, primerApellido});
                if(isValidData){
                    try {
                        Date nacimiento = new SimpleDateFormat("dd-MM-yyyy")
                                .parse(fechaNacimiento.replace("/", "-"));
                        Persona persona = new Persona(primerNombre, segundoNombre, primerApellido, segundoApellido, nacimiento);

                        GenericObjectResponse<Boolean, String> response = personaService.create(persona);
                        if(response.getBoolResponse()){
                            Intent intent = new Intent(CrearPersonaActivity.this, MostrarPersonaActivity.class);
                            intent.putExtra("id", response.getMessageResponse());
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(this, "No se ha podido registrar la persona", Toast.LENGTH_SHORT).show();
                        }
                        Util.limpiarEditText(editTexts);
                    }catch (Exception ex){
                        Log.e("error", ex.getMessage());
                        Toast.makeText(this, "La fecha se ha ingresado incorrectamente", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            }

            case R.id.btnCancelar:{
                Util.limpiarEditText(editTexts);
            }
        }
    }
}