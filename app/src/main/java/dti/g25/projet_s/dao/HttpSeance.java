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

import dti.g25.projet_s.domaine.entité.CoursGroupe;
import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.domaine.interacteurs.GestionHoraire;
import dti.g25.projet_s.domaine.interacteurs.GestionSeance;

public class HttpSeance {
    private final static String lienUrl = "https://projet-s.dti.crosemont.quebec/api/v0/groupe/";
    URL url=null;
    String résultat="";

    public HttpSeance() {

    }


//    public List<Seance> getSeancesParCourGroupe(CoursGroupe courGroupe) throws Exception {
//
//        RequestQueue requestQueue;
//
//     //   RequestQueue queue = Volley.newRequestQueue();
//
//            // Préparation de la requête
//        String url=lienUrl + courGroupe.getId() + "/seances";
//        JsonObjectRequest requête = new JsonObjectRequest(Request.Method.GET,
//                url,
//                null,
//                //Méthode exécutée dans le fil principal
//                //lorsque la réponse arrive
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject réponse) {
//                        //Faire quelque chose avec la réponse
//                    }
//
//                },
//
//                //Méthode exécutée dans le fil principal
//                //en cas d'erreur
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //Afficher un message d'erreur
//                    }
//                });
//
//
//        //Instancie l'URL
//        url= new String(lienUrl + courGroupe.getId() + "/seances");
//
//        List<Seance> seances = new ArrayList<>();
//        HttpURLConnection connexion =
//                (HttpURLConnection) url.openConnection();
//
//        connexion.setRequestMethod("GET");
//
//        //Vérifions le résultat de la requête
//        InputStream responseBody = new BufferedInputStream(connexion.getInputStream());
//
//
//
//        InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
//
//        BufferedReader stringReader = new BufferedReader(responseBodyReader);
//
//        String ligne;
//        do{
//            ligne=stringReader.readLine();
//            if(ligne!=null) résultat+=ligne;
//        }while(ligne!=null);
//
//        seances = décoderJsonSéeances(ligne, courGroupe);
//
//        connexion.disconnect();
//
//
//        return seances;
//    }

    public List<Seance> décoderJsonSéeances(JSONObject résultat, CoursGroupe coursGroupe) throws JSONException {
        List<Seance> seances = new ArrayList<>();

        résultat = (JSONObject) résultat.get("_embedded");
        Log.d("embedded", String.valueOf(résultat));
        résultat = (JSONObject) résultat.get("seances");
        JSONArray listeSeance = résultat.names();

        for(int i = 0; i < listeSeance.length(); i++) {
            JSONObject objectAcuel = (JSONObject) résultat.get(listeSeance.getString(i));

            Log.d("une seance", String.valueOf(objectAcuel));

            Double heureDébut = obtenirHeureEnDouble(objectAcuel.getString("début"));
            Double heureFin = obtenirHeureEnDouble(objectAcuel.getString("fin"));
            int id = objectAcuel.getInt("id");
            Date date = obtenirStringEnDate(objectAcuel.getString("date"));
            seances.add(new GestionSeance().creerSeance(coursGroupe, new GestionHoraire().créerHoraire(heureDébut, heureFin), date, id));
        }

        return seances;
    }

    private Date obtenirStringEnDate(String uneDate) {
        String[] partie = uneDate.split("-");

        Date date = new Date(Integer.parseInt(partie[0]), Integer.parseInt(partie[1]), Integer.parseInt(partie[2]));
        return date;
    }

    private Double obtenirHeureEnDouble(String heureString) {
        Double temp;
        String[] partie = heureString.split(":");

        temp = Double.parseDouble(partie[0]);

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
