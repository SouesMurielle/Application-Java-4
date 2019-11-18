package com.soues.opendata.model.webservice;

import android.util.Log;

import com.google.gson.Gson;
import com.soues.opendata.model.beans.Fields;
import com.soues.opendata.model.beans.Record;
import com.soues.opendata.model.beans.Resultat;

import java.util.ArrayList;

public class OpenDataWS {

    private static final String WS_URL = "https://data.toulouse-metropole.fr/api/records/1.0/search/?dataset=agenda-des-manifestations-culturelles-so-toulouse&facet=type_de_manifestation";

    public static ArrayList<Fields> getFieldsDuServeur() throws Exception {

        String responseEnJson = OkHttpUtils.sendGetOkHttpRequest(WS_URL);

        //On instancie le json
        Gson gson = new Gson();
        //Notre String représente notre classe java et tous les attributs seront mis dans l'objet de type résultat
        Resultat resultat = gson.fromJson(responseEnJson, Resultat.class);

        ArrayList<Fields> fields = new ArrayList<>();

        if (resultat==null){
            throw new Exception("Variable resultat à null");
        }
        else if (resultat.getRecords()!=null) {
            for (Record record : resultat.getRecords()) {
                if (record.getFields()!=null) {
                    fields.add(record.getFields());
                }
            }
        }

        Log.w("Tag", responseEnJson);

        return fields;

    }
}
