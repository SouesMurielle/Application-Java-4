package com.soues.opendata.controler;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.soues.opendata.R;
import com.soues.opendata.model.beans.Fields;
import com.soues.opendata.model.webservice.OpenDataWS;
import com.soues.opendata.view.FieldAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, FieldAdapter.OnFieldsListener {

    //Composants graphiques
    private TextView tv_info;
    private RecyclerView rv;
    private Button bt_charger;

    //Données
    private ArrayList<Fields> fields;

    //Outil
    private FieldAdapter fieldAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv);
        tv_info = findViewById(R.id.tv_info);
        bt_charger = findViewById(R.id.bt_charger);

        bt_charger.setOnClickListener(this);

        fields = new ArrayList<>();

        fieldAdapter = new FieldAdapter(fields, this);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(fieldAdapter);
    }

    @Override
    public void onClick(View v) {
        try {
            MonAT monAT = new MonAT();
            monAT.execute();
        } catch (Exception e) {
            e.printStackTrace();
            tv_info.setText(e.getMessage());
        }
    }

    @Override
    public void onClick(Fields fields) {

        //Demande au syst de lancer DetailActivity
        Intent intent = new Intent(this, DetailActivity.class);
        //On ajoute des paramètres
        intent.putExtra(DetailActivity.FIELDS_KEY, fields);

        //On va envoyer mon objet au systeme
        startActivity(intent);
        //Jusque là ce n'est qu'une demande, rien ne s'est passé

    }

    public class MonAT extends AsyncTask {

        private ArrayList<Fields> resultat = null;
        private Exception exception = null;

        @Override
        protected Object doInBackground(Object[] objects) {

            try {
                resultat = OpenDataWS.getFieldsDuServeur();
            } catch (Exception e) {
                exception = e;
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            if (exception != null) {
                exception.printStackTrace();
                tv_info.setText(exception.getMessage());
            } else {
                fields.clear();
                fields.addAll(resultat);
                fieldAdapter.notifyDataSetChanged();
            }
        }
    }
}





