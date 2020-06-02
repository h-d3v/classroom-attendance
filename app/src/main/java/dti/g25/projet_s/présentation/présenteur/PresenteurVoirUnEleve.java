package dti.g25.projet_s.présentation.présenteur;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import dti.g25.projet_s.dao.Singleton;
import dti.g25.projet_s.domaine.entité.Utilisateur;
import dti.g25.projet_s.présentation.IContratVuePrésenteurVoirUnEleve;
import dti.g25.projet_s.présentation.modèle.Modèle;
import dti.g25.projet_s.util.VolleyErrorListener;

public class PresenteurVoirUnEleve implements IContratVuePrésenteurVoirUnEleve.IPrésenteurVoirUnEleve {
    private static final String EXTRA_CLÉ_CONNEXION = "dti.g25.projet_s.cléConnexion";
    private static final String EXTRA_POSITION_PRÉSENCE = "dti.g25.projet_s.présence";
    private static final String EXTRA_POSITION_ÉLÈVES = "dti.g25.projet_s.positionÉlèves";

    private IContratVuePrésenteurVoirUnEleve.IVueVoirUnEleve vue;
    private Modèle modèle;
    private Activity activity;
    private int positionSeance;
    private int positionGroupe;
    private int positionÉlèves;
    private String cléUtilisateur;

    public PresenteurVoirUnEleve(Activity activity, IContratVuePrésenteurVoirUnEleve.IVueVoirUnEleve vue, Modèle modèle) {
        this.activity=activity;
        this.vue=vue;
        this.modèle=modèle;
    }

    @Override
    public void requêteAjouterAbsence(boolean b) {
        if(b){//On envoie la requete de presence au serveur

            StringRequest request  =  new StringRequest(Request.Method.PUT,"https://projet-s.dti.crosemont.quebec/api/v1/seance/" + positionSeance +"/present/" + modèle.getListeEtudiantsParCoursGroupe().get(positionÉlèves).getId(), new Response.Listener() {
                @Override
                public void onResponse(Object response) {
                    Toast toast = Toast.makeText(activity, "présence enregistrée!", Toast.LENGTH_SHORT);
                    toast.show();
                }}, VolleyErrorListener.fabriquerErrorListnerAfficherToasts(activity)

            ) {
                @Override
                public Map<String,String > getHeaders()  {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Authorization", "Bearer "+ modèle.getCléConnexion());
                    return headers;
                }
            };
            Singleton.getInstance(activity).addToRequestQueue(request);
        } else {
            StringRequest request =  new StringRequest(Request.Method.DELETE,"https://projet-s.dti.crosemont.quebec/api/v1/seance/" + positionSeance +"/present/" + modèle.getListeEtudiantsParCoursGroupe().get(positionÉlèves).getId(), new Response.Listener() {
                @Override
                public void onResponse(Object response) {
                    Toast toast = Toast.makeText(activity, "absence enregistrée!", Toast.LENGTH_SHORT);
                    toast.show();
                }},VolleyErrorListener.fabriquerErrorListnerAfficherToasts(activity)
            ) {
                @Override
                public Map<String,String > getHeaders()  {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Authorization", "Bearer "+ modèle.getCléConnexion());
                    return headers;
                }
            };
            Singleton.getInstance(activity).addToRequestQueue(request);
        }
        Intent donnéesRetour=new Intent();
        donnéesRetour.putExtra(EXTRA_POSITION_PRÉSENCE, b);
        donnéesRetour.putExtra(EXTRA_POSITION_ÉLÈVES, positionÉlèves);
        activity.setResult(activity.RESULT_OK, donnéesRetour);
        activity.finish();
    }

    @Override
    public void commencerVoirUnÉlèves(int positionSeance, int positionGroupe, int positionÉlèves, String cléUtilisateur) {
        this.positionSeance =positionSeance;
        this.positionGroupe = positionGroupe;
        this.positionÉlèves = positionÉlèves;
        this.cléUtilisateur = cléUtilisateur;
        if(positionSeance == -1) {
            vue.setVisibilitéPrésence(false);
        }
        modèle.setCléConnexion(this.cléUtilisateur);
        vue.setUsername(modèle.getListeEtudiantsParCoursGroupe().get(positionÉlèves).getUsername());
    }

    @Override
    public String getNomUtilisateur(int position){
        Utilisateur leUser = modèle.getListeEtudiantsParCoursGroupe().get(position);
        return leUser.getUsername();
    }

    @Override
    public void rafraichir(){
        vue.setUsername(modèle.getListeEtudiantsParCoursGroupe().get(this.positionÉlèves).getUsername());
    }

}
