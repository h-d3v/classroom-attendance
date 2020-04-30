package dti.g25.projet_s.presentation.vue;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import dti.g25.projet_s.R;
import dti.g25.projet_s.presentation.contratVuePresenteur.ContratVpVoirUnCoursGroupe;
import dti.g25.projet_s.presentation.presenteur.PresenteurVoirUnCourGroupe;

public class VueVoirUnCourGroupe extends Fragment implements ContratVpVoirUnCoursGroupe.IVueVoirCoursGroupe {

    private PresenteurVoirUnCourGroupe _presenteur;
    private TextView tvSigleDuCours;
    private TextView tvNomDuCours;
    private TextView tvNbElevesInscrits;
    private Button btnVoirListeEleves;
    private Button btnVoirSeances;

    public void setPresenteur(PresenteurVoirUnCourGroupe _presenteur) {
        this._presenteur = _presenteur;
    }
    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return la vue après qu'elle est été créée
     */
    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState){
        View racine=inflater.inflate(R.layout.frag_voir_un_courgroupe, container, false);
        btnVoirListeEleves=racine.findViewById(R.id.btnVoirListeEleves);
        btnVoirSeances=racine.findViewById(R.id.btnVoirSeanceCour);
        tvSigleDuCours=racine.findViewById(R.id.tvSigleCours);
        tvNomDuCours=racine.findViewById(R.id.tvNomCour);
        tvNbElevesInscrits=racine.findViewById(R.id.tvNbEleves);
        tvNbElevesInscrits.setText("23");
        tvNomDuCours.setText(_presenteur.getLibelleCours().getTITRE());
        tvSigleDuCours.setText(_presenteur.getLibelleCours().getCODE());
        setRetainInstance(true);
        return racine;
    }
}


