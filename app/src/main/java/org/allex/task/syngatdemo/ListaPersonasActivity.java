package org.allex.task.syngatdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.allex.task.syngatdemo.Adapters.PersonaAdapter;

import java.util.ArrayList;

public class ListaPersonasActivity extends AppCompatActivity {

    ArrayList<String> listaPersonas;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personas);

        recyclerView=(RecyclerView)findViewById(R.id.rvListaPersonas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        listaPersonas=new ArrayList<String>();

        for (int i=0; i<50;  i++){
            listaPersonas.add("Persona"+ i+ " Bienvenido");
        }

        PersonaAdapter adapter = new PersonaAdapter(listaPersonas);
        recyclerView.setAdapter(adapter);











    }
}