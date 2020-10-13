package org.allex.task.syngatdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
    private LinearLayoutManager linearLayoutManager;

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

        personaAdapter.setData(listaPersonas, iPersonaService);

        recyclerView.setAdapter(personaAdapter);



    }
}