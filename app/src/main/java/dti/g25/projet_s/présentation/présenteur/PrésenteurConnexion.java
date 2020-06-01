package dti.g25.projet_s.présentation.présenteur;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.util.Log;
import com.android.volley.*;

import dti.g25.projet_s.dao.ConvertisseurJsonConnexion;
import dti.g25.projet_s.dao.DAOFactoryRESTAPI;
import dti.g25.projet_s.présentation.ContratVuePrésenteurConnexion;
import dti.g25.projet_s.présentation.modèle.Modèle;
import org.json.JSONException;
import org.json.JSONObject;

public class PrésenteurConnexion implements ContratVuePrésenteurConnexion.IPrésenteurConnexion {
    private static final String EXTRA_CLÉ_CONNEXION = "dti.g25.projet_s.cléConnexion";
    private static final String ID_USER_CONNECT = "dti.g25.projet_s.idUserConnect";

    Modèle modèle;
    Activity activité;
    ContratVuePrésenteurConnexion.IVueConnexion vue;
    SharedPreferences sharedPreferences;

    /**
     * Constructeur du presenteur pour la connexion
     * @param activité Activité dans laquelle le présenteur de connexion sera placé
     * @param vue La vue qui est relié au présenteur de la connexion
     * @param modèle le modele du MVP
     */
    public PrésenteurConnexion(Activity activité, ContratVuePrésenteurConnexion.IVueConnexion vue, Modèle modèle) {
        this.activité=activité;
        this.vue=vue;
        this.modèle=modèle;
        sharedPreferences = activité.getSharedPreferences("infosLogin", Context.MODE_PRIVATE);
    }

    @Override
    public Boolean tenterConnexion(final String nomUtilisateur, final String motDePasse) {
        DAOFactoryRESTAPI daoFactoryRESTAPI= new  DAOFactoryRESTAPI(activité);
        daoFactoryRESTAPI.setResponse(new Response.Listener<JSONObject>(){
                                          @Override
                                          public void onResponse(JSONObject response) {
                                              try {
                                                  modèle.setCléConnexion(new ConvertisseurJsonConnexion().Authentifier(response));
                                              } catch (JSONException e) {
                                                  e.printStackTrace();
                                              }
                                              if(!modèle.getCléConnexion().equals(null)) {
                                                  sauvegarderIdentifiants(nomUtilisateur, motDePasse);
                                                  terminerConnexion();
                                              }
                                          }
                                        });

            Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (error instanceof AuthFailureError) {
                    vue.setMessageErreur("Le mot de passe ou le nom d'utilisateur n'est pas valide");
                } else if (error instanceof ServerError) {
                    vue.setMessageErreur("Le serveur est en panne, veuillez contacter votre administrateur");
                } else if (error instanceof TimeoutError || error instanceof NoConnectionError){
                    vue.setMessageErreur("Pas de connection");
                }
            }
        };

        daoFactoryRESTAPI.tenterConnection(nomUtilisateur, motDePasse, errorListener);

        return true;
    }

    @Override
    public void tenterConnectionAutomatique() {
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

    @Override
    public void terminerConnexion() {
        Intent donnéesRetour=new Intent();
        donnéesRetour.putExtra(EXTRA_CLÉ_CONNEXION, modèle.getCléConnexion());
        if(!vue.getCbSeSouvenir()){
            supprimerIdentifiants();
        }
        activité.setResult(activité.RESULT_OK, donnéesRetour);
        activité.finish();;
    }


}
