package org.allex.task.syngatdemo.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.allex.task.syngatdemo.Entities.Persona;
import org.allex.task.syngatdemo.Interfaces.IPersonaService;
import org.allex.task.syngatdemo.MostrarPersonaActivity;
import org.allex.task.syngatdemo.R;

import java.util.ArrayList;

public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.ViewHolder> implements View.OnClickListener {

    private ArrayList<Persona> listaPersonas;
    private Context context;
    private View.OnClickListener listener;


    public void setData(ArrayList<Persona> lista) {
        listaPersonas = lista;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNmbre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNmbre = itemView.findViewById(R.id.tvNombre_Lista);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_persona, parent, false);
        view.setOnClickListener(this);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Persona persona = listaPersonas.get(position);
        setPersonaData(persona,viewHolder);
    }

    @Override
    public int getItemCount() {
        return listaPersonas.size();
    }

    public void setOnclicListener(View.OnClickListener listener){this.listener = listener;}

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }

    private void setPersonaData(Persona persona, ViewHolder viewHolder) {
        viewHolder.tvNmbre.setText(persona.getPrimerNombre() + " " + persona.getPrimerApellido());
    }


}
