package org.allex.task.syngatdemo.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.allex.task.syngatdemo.Entities.Persona;
import org.allex.task.syngatdemo.Interfaces.IPersonaService;
import org.allex.task.syngatdemo.MostrarPersonaActivity;
import org.allex.task.syngatdemo.R;

import java.util.ArrayList;

public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.ViewHolder> implements View.OnClickListener {

    private ArrayList<Persona> listaPersonas;
    private IPersonaService iPersonaService;
    private Context context;
    private View.OnClickListener listener;


    public void setData(ArrayList<Persona> lista, IPersonaService service) {
        listaPersonas = lista;
        iPersonaService= service;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNmbre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNmbre=itemView.findViewById(R.id.tvNombre_Lista);

        }

        public void verPersonas(Persona personas) {
            tvNmbre.setText((CharSequence) personas);
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
        //viewHolder.verPersonas(listaPersonas.get(position));
    }

    private void setPersonaData(Persona persona, ViewHolder viewHolder) {
        ArrayList<Persona> nombrePersona = iPersonaService.get();
        viewHolder.tvNmbre.setText(persona.getPrimerNombre() + " " + persona.getPrimerApellido());

    }


    @Override
    public int getItemCount() {
        return listaPersonas.size();
    }

    @Override
    public void onClick(View itemView) {
        int Nombre= getItemViewType(R.id.tvNombre_Lista);
        Intent intent = new Intent(itemView.getContext(), MostrarPersonaActivity.class);
        intent.putExtra("nombre", Nombre);
        itemView.getContext().startActivity(intent);

       /* if(listener!= null){
            listener.onClick(view);

        }*/

    }


}
