package dti.g25.projet_s.présentation.présenteur;

import android.app.Activity;
import android.content.Intent;
import android.os.Debug;
import android.util.Log;

import dti.g25.projet_s.présentation.IContatVuePresenteurVoirCoursGroupe;
import dti.g25.projet_s.présentation.modèle.Modèle;
import dti.g25.projet_s.présentation.vue.VueVoirCoursGroupe;
import dti.g25.projet_s.ui.activité.ConnexionActivité;

public class PresenteurVoirCoursGroupe implements IContatVuePresenteurVoirCoursGroupe.IPresenteurVoirCoursGroupe {
    private static final String EXTRA_CLÉ_CONNEXION = "dti.g25.projet_s.position";
    private static final int REQUEST_CODE_CONEXION= 55;

    private IContatVuePresenteurVoirCoursGroupe.IVueVoirCoursGroupe vueVoirCoursGroupe;
    private Modèle modèle;
    private Activity activity;
    private int positionCoursGroupe;

    public PresenteurVoirCoursGroupe(IContatVuePresenteurVoirCoursGroupe.IVueVoirCoursGroupe vueVoirCoursGroupe, Modèle modèle, Activity activity) {
        this.vueVoirCoursGroupe = vueVoirCoursGroupe;
        this.modèle = modèle;
        this.activity = activity;
        if(modèle.getUtilisateurConnecte() == null)
            activity.startActivityForResult(new Intent(new Intent(activity, ConnexionActivité.class)), REQUEST_CODE_CONEXION);
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
        // TODO
    }

    @Override
    public String getTitreCoursGroupe(int position) {
        return modèle.getCoursGroupes().get(position).toString();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) throws Exception {
        if (requestCode == REQUEST_CODE_CONEXION && resultCode == Activity.RESULT_OK) {
            String cléConnexion=data.getStringExtra(EXTRA_CLÉ_CONNEXION);
            Log.d("clé utilisateur", cléConnexion);
            modèle.setCléUtilisateur(cléConnexion);
            modèle.rafraîchir();
            vueVoirCoursGroupe.rafraichir();
        }
    }
}
