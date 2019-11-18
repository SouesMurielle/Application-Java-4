package com.soues.opendata.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.soues.opendata.R;
import com.soues.opendata.model.beans.Fields;

import java.util.ArrayList;

//Role : Afficher les données et non les manipuler
public class FieldAdapter extends RecyclerView.Adapter<FieldAdapter.ViewHolder> {

    private ArrayList<Fields> fields;
    private OnFieldsListener onFieldsListener;

    public FieldAdapter(ArrayList<Fields> fields, OnFieldsListener onFieldsListener) {
        this.fields = fields;
        this.onFieldsListener = onFieldsListener;
    }

    //Création de ligne
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ligne_evenement, parent, false);

        return new FieldAdapter.ViewHolder(view);
    }

    //Afficher une ligne
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Fields field = fields.get(position);

        holder.tv_titre.setText(field.getNom_de_la_manifestation());
        holder.tv_description.setText(field.getDescriptif_court());


        //Entre parenthèses : nouvelle classe (dite anonyme) qui n'est valable que dans cette fonction et elle ne possède qu'une méthode : onClick (cf en dessous)
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onFieldsListener != null)
                    onFieldsListener.onClick(field);

//                Toast.makeText(holder.tv_nom.getContext(), "Clic sur" + personneBean.getPrenom() + " " + personneBean.getNom(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Combien d'item dans le recyclerview (nb total de la liste)
    @Override
    public int getItemCount() {
        return fields.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_titre, tv_description;
        public View root;

        //Inner class : classe interne à une autre
        public ViewHolder(View itemView) {
            super(itemView);

            tv_titre = itemView.findViewById(R.id.tv_titre);
            tv_description = itemView.findViewById(R.id.tv_description);
            root = itemView.findViewById(R.id.root);
        }
    }

    public interface OnFieldsListener {

        void onClick(Fields fields);

    }

}
