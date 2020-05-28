package dti.g25.projet_s.présentation.présenteur;

import android.app.Activity;
import android.content.Intent;
import android.os.Debug;
import android.util.Log;

import dti.g25.projet_s.domaine.entité.Role;
import dti.g25.projet_s.présentation.IContatVuePresenteurVoirCoursGroupe;
import dti.g25.projet_s.présentation.modèle.Modèle;
import dti.g25.projet_s.présentation.vue.VueVoirCoursGroupe;
import dti.g25.projet_s.ui.activité.ConnexionActivité;
import dti.g25.projet_s.ui.activité.VoirUnCourGroupeActivity;

public class PresenteurVoirCoursGroupe implements IContatVuePresenteurVoirCoursGroupe.IPresenteurVoirCoursGroupe {
    private static final String EXTRA_CLÉ_CONNEXION = "dti.g25.projet_s.cléConnexion";
    private static final String EXTRA_POSITION_GROUPE = "dti.g25.projet_s.positionCourGroupe";
    private static final int REQUEST_CODE_CONEXION= 55;
    private String cléConnexion;

    private IContatVuePresenteurVoirCoursGroupe.IVueVoirCoursGroupe vueVoirCoursGroupe;
    private Modèle modèle;
    private Activity activity;
    private int positionCoursGroupe;

    public PresenteurVoirCoursGroupe(IContatVuePresenteurVoirCoursGroupe.IVueVoirCoursGroupe vueVoirCoursGroupe, Modèle modèle, Activity activity) {
        this.vueVoirCoursGroupe = vueVoirCoursGroupe;
        this.modèle = modèle;
        this.activity = activity;
        if(modèle.getUtilisateurConnecte() == null)
            activity.startActivityForResult(new Intent(activity, ConnexionActivité.class), REQUEST_CODE_CONEXION);
    }

    @Override
    public int getNombresItems() {
        if(modèle.getCoursGroupes() != null) {
            return modèle.getCoursGroupes().size();
        }
        return 0;
    }

    @Override
    public void requeteVoirCoursGroupe(int position) {
        Intent intentVoirSéance = new Intent(activity, VoirUnCourGroupeActivity.class);
        intentVoirSéance.putExtra(EXTRA_CLÉ_CONNEXION, cléConnexion);
        intentVoirSéance.putExtra(EXTRA_POSITION_GROUPE, position);
        activity.startActivity(intentVoirSéance);
    }

    @Override
    public String getTitreCoursGroupe(int position) {
        return modèle.getCoursGroupes().get(position).toString();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) throws Exception {
        if (requestCode == REQUEST_CODE_CONEXION && resultCode == Activity.RESULT_OK) {
            cléConnexion=data.getStringExtra(EXTRA_CLÉ_CONNEXION);
            modèle.setCléUtilisateur(cléConnexion);
            modèle.rafraîchir();
            Log.d("cour", modèle.getCourGroupeParPos(0).toString());
            vueVoirCoursGroupe.rafraichir();
        }
    }
}
