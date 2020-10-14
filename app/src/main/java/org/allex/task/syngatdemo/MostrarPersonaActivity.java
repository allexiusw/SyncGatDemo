package org.allex.task.syngatdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.allex.task.syngatdemo.Entities.Persona;
import org.allex.task.syngatdemo.Interfaces.IPersonaService;
import org.allex.task.syngatdemo.Services.PersonaService;
import org.allex.task.syngatdemo.Utils.GenericObjectResponse;

import java.text.SimpleDateFormat;

public class MostrarPersonaActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvNombrePersona, tvPrimerNombre, tvSegundoNombre, tvPrimerApellido, tvSegundoApellido, tvFechaNacimiento;
    private IPersonaService personaService;
    private String id;
    private Button btnActualizar, btnEliminar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_persona);
        personaService = new PersonaService(this);
        id = getIntent().getExtras().getString("id");
        GenericObjectResponse<Boolean, Persona> response = personaService.getById(id);

        tvNombrePersona = findViewById(R.id.tvNombrePersona);
        tvPrimerNombre = findViewById(R.id.tvPrimerNombre);
        tvSegundoNombre = findViewById(R.id.tvSegundoNombre);
        tvPrimerApellido = findViewById(R.id.tvPrimerApellido);
        tvSegundoApellido = findViewById(R.id.tvSegundoApellido);
        tvFechaNacimiento = findViewById(R.id.tvFechaNacimiento);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnEliminar = findViewById(R.id.btnEliminar);
        if (response.getBoolResponse()){
            setData(response.getMessageResponse());
            btnActualizar.setOnClickListener(this);
            btnEliminar.setOnClickListener(this);
        }
        else
            Toast.makeText(this, "No se ha encontrado el registro", Toast.LENGTH_SHORT).show();

    }

    private void setData(Persona persona){
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormato = formatFecha.format(persona.getFechaNacimiento());
        tvNombrePersona.setText(persona.getPrimerNombre().concat(" ").concat(persona.getPrimerApellido()));
        tvPrimerNombre.setText(persona.getPrimerNombre());
        tvSegundoNombre.setText(persona.getSegundoNombre().isEmpty() ? "No registrado" : persona.getSegundoNombre());
        tvPrimerApellido.setText(persona.getPrimerApellido());
        tvSegundoApellido.setText(persona.getSegundoApellido().isEmpty() ? "No registrado" : persona.getSegundoApellido());
        tvFechaNacimiento.setText(fechaFormato);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnActualizar:{
                Intent intent = new Intent(MostrarPersonaActivity.this, ActualizarActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                finish();
            }
            case R.id.btnEliminar:{
                GenericObjectResponse<Boolean, String> response = personaService.delete(id);
                if(response.getBoolResponse()){
                    Toast.makeText(this, response.getMessageResponse(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MostrarPersonaActivity.this, Activity_Home.class);
                    startActivity(intent);
                    finish();
                }else
                    Toast.makeText(this, response.getMessageResponse(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}