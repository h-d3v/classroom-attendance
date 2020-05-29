package dti.g25.projet_s.dao;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.LibelleCours;
import dti.g25.projet_s.domaine.interacteurs.GestionCoursGroupe;

public class ConvertisseurJsonGroupe {

    public List<CoursGroupe> décoderJsonGroupe(JSONObject réponse) {
        List<CoursGroupe> groupes = new ArrayList<>();

        try {
            Iterator<String> iterator = réponse.keys();
            while (iterator.hasNext()){
                JSONObject jsonObject =réponse.getJSONObject(iterator.next());
                int idCg = jsonObject.getInt("id");
                String titre = jsonObject.getString("titre");
                String sigle = jsonObject.getString("sigle");
                int num = jsonObject.getInt("num\u00e9ro");
                groupes.add(new GestionCoursGroupe().creerCoursGroupe(new LibelleCours(titre,sigle), num, idCg));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return groupes;
    }
}
