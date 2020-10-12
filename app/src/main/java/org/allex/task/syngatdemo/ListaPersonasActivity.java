package org.allex.task.syngatdemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prueba_items.Adapters.PersonasAdapter;

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

        PersonasAdapter adapter = new PersonasAdapter(listaPersonas);
        recyclerView.setAdapter(adapter);











    }
}