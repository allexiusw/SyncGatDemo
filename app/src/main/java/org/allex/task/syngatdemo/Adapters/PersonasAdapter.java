package org.allex.task.syngatdemo.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prueba_items.R;

import java.util.ArrayList;

public class PersonasAdapter extends RecyclerView.Adapter<PersonasAdapter.ViewHolder> implements View.OnClickListener {
//Crear la lista y constructor:
    ArrayList<String> listaPersonas;

    public PersonasAdapter(ArrayList<String> listaDatos) {
        this.listaPersonas = listaDatos;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            nombre = itemView.findViewById(R.id.tvNombre);
        }

        //Se le asigna el dato que se le ingresa o guarda:
        public void verPersonas(String personas) {
            nombre.setText(personas);
        }
    }

    @Override
    public void onClick(View v) {

    }

    //Se llama al item creado para
    @NonNull
    @Override
    public PersonasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_persona, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonasAdapter.ViewHolder holder, int position) {
        holder.verPersonas(listaPersonas.get(position));
    }

    //Devuelve el ancho de la lista o datos que se mandan
    @Override
    public int getItemCount() {

        return listaPersonas.size();
    }


}
