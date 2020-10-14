package org.allex.task.syngatdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.allex.task.syngatdemo.Adapters.PersonaAdapter;
import org.allex.task.syngatdemo.Entities.Persona;
import org.allex.task.syngatdemo.Interfaces.IPersonaService;
import org.allex.task.syngatdemo.Services.PersonaService;

import java.util.ArrayList;

public class ListaPersonasActivity extends AppCompatActivity {

    private ArrayList<Persona>listaPersonas;
    private RecyclerView recyclerView;
    private PersonaAdapter personaAdapter;
    private IPersonaService iPersonaService;

    //***********************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personas);

        iPersonaService = new PersonaService(this);
        listaPersonas= iPersonaService.get();
        personaAdapter = new PersonaAdapter();

        recyclerView=findViewById(R.id.rvListaPersonas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        personaAdapter.setData(listaPersonas);

        recyclerView.setAdapter(personaAdapter);

        personaAdapter.setOnclicListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idPersona = listaPersonas.get(recyclerView.getChildAdapterPosition(view)).getId();
                Intent intent = new Intent(ListaPersonasActivity.this, MostrarPersonaActivity.class);
                intent.putExtra("id", idPersona);
                startActivity(intent);
                finish();
            }
        });

    }
}