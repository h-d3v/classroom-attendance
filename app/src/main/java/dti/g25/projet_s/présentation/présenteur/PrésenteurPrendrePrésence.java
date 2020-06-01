package dti.g25.projet_s.présentation.présenteur;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import android.widget.Toast;
import androidx.fragment.app.FragmentTransaction;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import dti.g25.projet_s.R;
import dti.g25.projet_s.dao.Singleton;
import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.présentation.ContratVuePrésenteurPrendrePrésence;
import dti.g25.projet_s.présentation.modèle.Modèle;
import dti.g25.projet_s.présentation.vue.VuePrendrePrésence;
import dti.g25.projet_s.util.VolleyErrorListener;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PrésenteurPrendrePrésence implements ContratVuePrésenteurPrendrePrésence.IPrésenteurPrendrePrésence {

    private static final String EXTRA_POSITION_SEANCE= "dti.g25.projet_s.positionSécance";
    private int positionGroupe;
    private int itérateur = 0;
    private int positionSéeance;
    private String cléUtilisateur;
    private SharedPreferences sharedPreferences;

    Modèle modèle;
    Activity activité;
    ContratVuePrésenteurPrendrePrésence.IVuePrendrePrésence vue;


    /**
     * Constructeur du presenteur pour la prise de présemce
     * @param activité Activité dans laquelle le présenteur de la prise de présence sera placé
     * @param vue La vue qui est relié au présenteur de la prise de présence
     * @param modèle le modele du MVP
     */
    public PrésenteurPrendrePrésence(Activity activité, ContratVuePrésenteurPrendrePrésence.IVuePrendrePrésence vue, Modèle modèle) {
        this.activité=activité;
        this.vue=vue;
        this.modèle=modèle;
        sharedPreferences = activité.getSharedPreferences("infosLogin", Context.MODE_PRIVATE);

    }


    @Override
    public String getNomUtilisteur() {
        return modèle.getListeEtudiantsParCoursGroupe().get(itérateur).getUsername();
    }

    @Override
    public void commencerPrendrePrésence(int positionSéeance, int positionGroupe, String cléUtilisateur) throws Exception {
        this.positionSéeance = positionSéeance;
        this.positionGroupe = positionGroupe;
        this.cléUtilisateur = cléUtilisateur;

        itérateur = 0;
        modèle.rafraîchir();
        vue.setTxtNomÉtudiant(modèle.getListeEtudiantsParCoursGroupe().get(itérateur).getUsername());
    }

    @Override
    public void ajouterAbsence(boolean absence){
        if(absence){//On envoie la requete de presence au serveur

            StringRequest request  =  new StringRequest(Request.Method.PUT,"https://projet-s.dti.crosemont.quebec/api/v1/seance/" + positionSéeance +"/present/" + modèle.getListeEtudiantsParCoursGroupe().get(itérateur).getId(), new Response.Listener() {
                @Override
                public void onResponse(Object response) {
                    Toast toast = Toast.makeText(activité, "présence enregistrée!", Toast.LENGTH_SHORT);
                    toast.show();
                }},VolleyErrorListener.fabriquerErrorListnerAfficherToasts(activité)

            ) {
                @Override
                public Map<String,String > getHeaders()  {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Authorization", "Bearer "+ modèle.getCléConnexion());
                    return headers;
                }
            };
            Singleton.getInstance(activité).addToRequestQueue(request);
        } else {
            StringRequest request =  new StringRequest(Request.Method.DELETE,"https://projet-s.dti.crosemont.quebec/api/v1/seance/" + positionSéeance +"/present/" + modèle.getListeEtudiantsParCoursGroupe().get(itérateur).getId(), new Response.Listener() {
                @Override
                public void onResponse(Object response) {
                    Toast toast = Toast.makeText(activité, "absence enregistrée!", Toast.LENGTH_SHORT);
                    toast.show();
                }},VolleyErrorListener.fabriquerErrorListnerAfficherToasts(activité)
            ) {
                @Override
                public Map<String,String > getHeaders()  {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Authorization", "Bearer "+ modèle.getCléConnexion());
                    return headers;
                }
            };
            Singleton.getInstance(activité).addToRequestQueue(request);
        }
        itérateur +=1;
        if(modèle.getListeEtudiantsParCoursGroupe().size() -1 == itérateur)
            activité.finish();
        else
            vue.setTxtNomÉtudiant(modèle.getListeEtudiantsParCoursGroupe().get(itérateur).getUsername());

    }

}
