package org.allex.task.syngatdemo.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.allex.task.syngatdemo.R;


import java.util.ArrayList;

public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.ViewHolder> implements View.OnClickListener {

    private RecyclerView recyclerView;
    private Context context;

    ArrayList<String> listaPersonas;
    private View.OnClickListener listener;


    public PersonaAdapter(ArrayList<String> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNmbre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNmbre=itemView.findViewById(R.id.tvNombre_Lista);

        }

        public void verPersonas(String personas) {
            tvNmbre.setText(personas);
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
        viewHolder.verPersonas(listaPersonas.get(position));
    }

    @Override
    public int getItemCount() {
        return listaPersonas.size();
    }

    public void setOnclicListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!= null){
            listener.onClick(view);
        }

    }


}
