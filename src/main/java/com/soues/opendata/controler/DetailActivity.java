package com.soues.opendata.controler;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.soues.opendata.R;
import com.soues.opendata.model.beans.Fields;

public class DetailActivity extends AppCompatActivity {

    public final static String FIELDS_KEY = "FIELDS_KEY";

    private TextView tv_titre, tv_detail;

    private Fields fields;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tv_titre = findViewById(R.id.tv_titre);
        tv_detail = findViewById(R.id.tv_detail);

        //Pour récupérer un paramètre
        //Serializable : On peut mettre sous forme xml ou json un objet java
        Fields fields = (Fields) getIntent().getExtras().getSerializable(FIELDS_KEY);

        tv_titre.setText(fields.getNom_de_la_manifestation());
        tv_detail.setText(fields.getDescriptif_long());


    }
}
