package dti.g25.projet_s.présentation.vue;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dti.g25.projet_s.R;
import dti.g25.projet_s.domaine.entité.Role;
import dti.g25.projet_s.présentation.ContratVpVoirUnCoursGroupe;
import dti.g25.projet_s.présentation.présenteur.PresenteurVoirUnCourGroupe;
import dti.g25.projet_s.présentation.vue.adapter.SeanceAdapterUnCourGroupe;

import static java.lang.String.valueOf;

public class VueVoirUnCourGroupe extends Fragment implements ContratVpVoirUnCoursGroupe.IVueVoirCoursGroupe {

    private ContratVpVoirUnCoursGroupe.IPrensenteurVoirCourGroupe _presenteur;
    private TextView tvSigleDuCours;
    private TextView tvNomDuCours;
    private TextView tvNbElevesInscrits;
    private Button btnVoirListeEleves;
    private RecyclerView rvListeSeance;
    private SeanceAdapterUnCourGroupe seanceAdapter;

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
        tvSigleDuCours=racine.findViewById(R.id.tvSigleCours);
        tvNbElevesInscrits= racine.findViewById(R.id.tvNbEleves);
        tvNomDuCours=racine.findViewById(R.id.tvNomCour);
        seanceAdapter = new SeanceAdapterUnCourGroupe(_presenteur);
        rvListeSeance = racine.findViewById(R.id.rvListeSeance);
        rvListeSeance.setAdapter(seanceAdapter);
        rvListeSeance.setLayoutManager(new LinearLayoutManager(getContext()));

        btnVoirListeEleves.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View bouton){
                _presenteur.requeteVoirListeEleves();
            }
        });
        setRetainInstance(true);
        return racine;
    }

    public void afficherNomCour(String titre) {
        tvNomDuCours.setText(titre);
    }

    public void afficherSigleCour(String sigle) {
        tvSigleDuCours.setText(sigle);
    }

    public void afficherNombreÉlèvesInscrit(int unNombre) {
        tvNbElevesInscrits.setText(String.valueOf(unNombre));
    }

    @Override
    public void rafraichir() {
        if(seanceAdapter!=null) {
            seanceAdapter.notifyDataSetChanged();
        }
    }

}


