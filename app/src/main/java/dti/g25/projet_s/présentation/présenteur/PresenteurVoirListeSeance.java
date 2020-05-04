package dti.g25.projet_s.présentation.présenteur;

import android.app.Activity;

import dti.g25.projet_s.présentation.ContratVpVoirListeSeances;
import dti.g25.projet_s.présentation.modèle.Modèle;

public class PresenteurVoirListeSeance implements ContratVpVoirListeSeances.IPresenteurVoirListeSeances {

    private Modèle _modele;
    private ContratVpVoirListeSeances.IVueVoirListeSeance _vue;
    private Activity _activite;
    private int _positionSeance;


}
