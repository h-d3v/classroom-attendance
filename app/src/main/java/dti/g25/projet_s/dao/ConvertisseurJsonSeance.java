package dti.g25.projet_s.dao;

import android.os.Debug;
import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dti.g25.projet_s.domaine.entité.Absence;
import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.Horaire;
import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.domaine.interacteurs.GestionHoraire;
import dti.g25.projet_s.domaine.interacteurs.GestionSeance;

public class ConvertisseurJsonSeance {

    public List<Seance> décoderJsonSéeances(JSONObject résultat, CoursGroupe coursGroupe) throws JSONException {
        List<Seance> seances = new ArrayList<>();

        JSONObject résultatZoomé;
        résultatZoomé = (JSONObject) résultat.get("_embedded");
        résultatZoomé = (JSONObject) résultatZoomé.get("seances");
        JSONArray listeSeance = résultatZoomé.names();

        for(int i = 0; i < listeSeance.length(); i++) {
            JSONObject objectAcuel = (JSONObject) résultatZoomé.get(listeSeance.getString(i));
            //Log.d("lance:", String.valueOf(objectAcuel));
            float heureDébut = obtenirHeureEnDouble(objectAcuel.getString("début"));
            float heureFin = obtenirHeureEnDouble(objectAcuel.getString("fin"));
            int id = objectAcuel.getInt("id");
            Horaire horaire = new GestionHoraire().créerHoraire(heureDébut, heureFin, objectAcuel.getString("date"));
            Seance unSeance = new GestionSeance().creerSeance(coursGroupe, horaire, id);
            seances.add(unSeance);
        }

        return seances;
    }

    public void présenceSeance(Seance seance, JSONObject résultat) throws JSONException {
        JSONArray listeUtilisateur = résultat.names();

        if(listeUtilisateur != null) {
            for (int i = 0; i < listeUtilisateur.length(); i++) {
                JSONObject objectAcuel = (JSONObject) résultat.get(listeUtilisateur.getString(i));

                for (Absence absence : seance.getListeAbsence()) {
                    if (objectAcuel.getString("nom").equals(absence.getUtilisateur().getUsername())) {
                        absence.setPrésence(false);
                    }
                }

            }
        }
    }


    private float obtenirHeureEnDouble(String heureString) {
        float temp;
        String[] partie = heureString.split(":");

        temp = Float.parseFloat(partie[0]);

        if (Double.parseDouble(partie[1]) > 30) {
            temp += 0.75;
        } else if (Double.parseDouble(partie[1]) > 15) {
            temp += 0.5;
        } else if(Double.parseDouble(partie[1]) > 0) {
            temp += 0.25;
        }

        return temp;
    }

}
