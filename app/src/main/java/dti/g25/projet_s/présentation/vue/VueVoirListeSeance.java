package dti.g25.projet_s.présentation.vue;

import androidx.fragment.app.Fragment;

import dti.g25.projet_s.présentation.ContratVpVoirListeSeances;
import dti.g25.projet_s.présentation.présenteur.PresenteurVoirListeSeance;

public class VueVoirListeSeance extends Fragment implements ContratVpVoirListeSeances.IVueVoirListeSeance {
    private  PresenteurVoirListeSeance _presenteur;

    public void set_presenteur(PresenteurVoirListeSeance presenteur) {
        this._presenteur = presenteur;
    }

    public PresenteurVoirListeSeance get_presenteur() {
        return _presenteur;
    }
}
