package org.allex.task.syngatdemo;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.allex.task.syngatdemo.Entities.Persona;
import org.allex.task.syngatdemo.Interfaces.IPersonaService;
import org.allex.task.syngatdemo.Services.PersonaService;
import org.allex.task.syngatdemo.Utils.GenericObjectResponse;

import java.text.SimpleDateFormat;

public class MostrarPersonaActivity extends AppCompatActivity {
    private TextView tvNombrePersona, tvPrimerNombre, tvSegundoNombre, tvPrimerApellido, tvSegundoApellido, tvFechaNacimiento;
    private IPersonaService personaService;
    private String id;
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
        if (response.getBoolResponse())
            setData(response.getMessageResponse());
        else
            Toast.makeText(this, "No se ha encontrado el registro", Toast.LENGTH_SHORT).show();

    }

    private void setData(Persona persona){
        SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormato = formatFecha.format(persona.getFechaNacimiento());
        tvNombrePersona.setText(persona.getPrimerNombre().concat(" ").concat(persona.getPrimerApellido()));
        tvPrimerNombre.setText(persona.getPrimerNombre());
        tvSegundoNombre.setText(persona.getSegundoNombre().isEmpty() ? "No registrado" : persona.getSegundoNombre());
        tvPrimerApellido.setText(persona.getPrimerNombre());
        tvSegundoApellido.setText(persona.getSegundoApellido().isEmpty() ? "No registrado" : persona.getSegundoApellido());
        tvFechaNacimiento.setText(fechaFormato);
    }
}