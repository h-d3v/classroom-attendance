package dti.g25.projet_s.présentation.présenteur;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.util.Log;
import com.android.volley.Response;
import dti.g25.projet_s.dao.DAOFactoryRESTAPI;
import dti.g25.projet_s.dao.ServeurFactice;
import dti.g25.projet_s.dao.UtlisateurFactice;
import dti.g25.projet_s.présentation.ContratVuePrésenteurConnexion;
import dti.g25.projet_s.présentation.modèle.Modèle;
import dti.g25.projet_s.présentation.modèle.dao.ModèleDAO;
import dti.g25.projet_s.ui.activité.VoirListeSeancesActivity;
import org.json.JSONException;
import org.json.JSONObject;

public class PrésenteurConnexion implements ContratVuePrésenteurConnexion.IPrésenteurConnexion {
    private static final String EXTRA_CLÉ_CONNEXION = "dti.g25.projet_s.cléConnexion";

    ModèleDAO modèle;
    Activity activité;
    ContratVuePrésenteurConnexion.IVueConnexion vue;
    SharedPreferences sharedPreferences;

    /**
     * Constructeur du presenteur pour la connexion
     * @param activité Activité dans laquelle le présenteur de connexion sera placé
     * @param vue La vue qui est relié au présenteur de la connexion
     * @param modèle le modele du MVP
     */
    public PrésenteurConnexion(Activity activité, ContratVuePrésenteurConnexion.IVueConnexion vue, ModèleDAO modèle) {
        this.activité=activité;
        this.vue=vue;
        this.modèle=modèle;
        sharedPreferences = activité.getSharedPreferences("infosLogin", Context.MODE_PRIVATE);
    }

    @Override
    public Boolean tenterConnexion(final String nomUtilisateur, final String motDePasse) {
        final String[] cléConnexion = new String[1];
        final boolean[] estReussi = new boolean[1];
        DAOFactoryRESTAPI daoFactoryRESTAPI= new  DAOFactoryRESTAPI(activité);
        daoFactoryRESTAPI.setResponse(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    cléConnexion[0] =    response.getString("auth_token");
                    if (cléConnexion[0] != null) {
                        Log.i("Cle de connection", cléConnexion[0]);
                        estReussi[0] = true;
                        sauvegarderIdentifiants(nomUtilisateur, motDePasse);
                        Intent donnéesRetour=new Intent();
                        donnéesRetour.putExtra(EXTRA_CLÉ_CONNEXION, cléConnexion[0]);
                        activité.setResult(activité.RESULT_OK, donnéesRetour);
                        activité.finish();


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    estReussi[0]=false;
                }
            }
        });
        daoFactoryRESTAPI.tenterConnection(nomUtilisateur, motDePasse);





        return estReussi[0];
    }

    @Override
    public void tenterConnectionAutomatique() {
        if(!sharedPreferences.getString("nomUtilisateur", "").isEmpty() &&
            !sharedPreferences.getString("motDePasse", "").isEmpty()){
            tenterConnexion(getNomUtilisateurSauvegarde(), getMotPasseUtilisateurSauvegarde());
        }
    }

    @Override
    public void sauvegarderIdentifiants(String nomUtilisateur, String motDePasseUtilisateur) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nomUtilisateur",nomUtilisateur);
        editor.putString("motDePasse", motDePasseUtilisateur);
        editor.apply();
    }

    @Override
    public String getNomUtilisateurSauvegarde() {
        return sharedPreferences.getString("nomUtilisateur", "");
    }

    @Override
    public String getMotPasseUtilisateurSauvegarde() {
        return sharedPreferences.getString("motDePasse", "");
    }

    @Override
    public void supprimerIdentifiants() {
        sharedPreferences.edit().clear().apply();
    }



}
