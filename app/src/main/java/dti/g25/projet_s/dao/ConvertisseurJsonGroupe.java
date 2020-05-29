package dti.g25.projet_s.dao;

import android.os.Debug;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.LibelleCours;
import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.domaine.interacteurs.GestionCoursGroupe;

public class ConvertisseurJsonGroupe {

    public List<CoursGroupe> décoderJsonListeGroupes(JSONObject réponse) {
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

    public CoursGroupe décoderJsonGroupe (JSONObject réponse) throws Exception {
        CoursGroupe unCourGroupe;

        int idCg = réponse.getInt("id");
        String titre = réponse.getString("titre");
        String sigle = réponse.getString("sigle");
        int num = réponse.getInt("num\u00e9ro");

        Log.d("lgine json", String.valueOf(réponse));
        réponse = (JSONObject) réponse.get("_embedded");
        réponse = (JSONObject) réponse.get("membres");
        JSONArray listeÉtudiantsJson = réponse.names();

        unCourGroupe = new GestionCoursGroupe().creerCoursGroupe(new LibelleCours(titre,sigle), num, idCg);

        Log.d("id Groupe", String.valueOf(unCourGroupe.getId()));
        for(int i = 0; i < listeÉtudiantsJson.length(); i++) {
            JSONObject objectAcuel = (JSONObject) réponse.get(listeÉtudiantsJson.getString(i));
            new GestionCoursGroupe().ajouterParticipant(new ConvertisseurJsonUtilisateur().décoderUtilisateur(objectAcuel), unCourGroupe);
        }

        return unCourGroupe;
    }

}
