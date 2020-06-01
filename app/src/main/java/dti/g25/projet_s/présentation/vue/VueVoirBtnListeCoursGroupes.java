package dti.g25.projet_s.présentation.vue;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import dti.g25.projet_s.R;
import dti.g25.projet_s.présentation.présenteur.PresenteurBtnVoirListeCoursGroupe;

public class VueVoirBtnListeCoursGroupes extends Fragment {

    private Button btnVoirUnCourGroupe;
    private PresenteurBtnVoirListeCoursGroupe _presenteur;

    public void setPresenteur(PresenteurBtnVoirListeCoursGroupe presenteur) {
        _presenteur=presenteur;
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
        View racine=inflater.inflate(R.layout.frag_btn_voir_un_courgroupe, container, false);
        racine.findViewById(R.id.btnVoirCourGroupe).setOnClickListener(new View.OnClickListener() {
                                                                           @Override
                                                                           public void onClick(View v) {
                                                                               _presenteur.requeteVoirUnCourGroupe();
                                                                           }
                                                                       }
        );

        //setRetainInstance(true);
        return racine;
    }
}