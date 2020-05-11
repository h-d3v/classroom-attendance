package dti.g25.projet_s.présentation.présenteur;

import android.app.Activity;
import dti.g25.projet_s.présentation.IContatVuePresenteurVoirCoursGroupe;
import dti.g25.projet_s.présentation.modèle.dao.Modèle;

public class PresenteurVoirCoursGroupe implements IContatVuePresenteurVoirCoursGroupe.IPresenteurVoirCoursGroupe {

    private IContatVuePresenteurVoirCoursGroupe.IVueVoirCoursGroupe vueVoirCoursGroupe;
    private Modèle modèle;
    private Activity activity;
    private int positionCoursGroupe;

    public PresenteurVoirCoursGroupe(IContatVuePresenteurVoirCoursGroupe.IVueVoirCoursGroupe vueVoirCoursGroupe, Modèle modèle, Activity activity) {
        this.vueVoirCoursGroupe = vueVoirCoursGroupe;
        this.modèle = modèle;
        this.activity = activity;
    }

    @Override
    public int getNombresItems() {
        if(modèle ==null){
            return 0;
        }
        return modèle.getCoursGroupes().size();

    }

    @Override
    public void requeteVoirCoursGroupe(int position) {
        // TODO
    }

    @Override
    public String getTitreCoursGroupe(int position) {
        return modèle.getCoursGroupes().get(position).toString();
    }
}
