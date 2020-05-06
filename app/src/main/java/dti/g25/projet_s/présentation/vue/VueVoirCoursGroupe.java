package dti.g25.projet_s.présentation.vue;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dti.g25.projet_s.R;
import dti.g25.projet_s.présentation.IContatVuePresenteurVoirCoursGroupe;

public class VueVoirCoursGroupe extends Fragment implements IContatVuePresenteurVoirCoursGroupe.IVueVoirCoursGroupe {
    private RecyclerView rvCoursGroupe;
    private CoursGroupeAdapter coursGroupeAdapter;
    private IContatVuePresenteurVoirCoursGroupe.IPresenteurVoirCoursGroupe _presenteur;

    public VueVoirCoursGroupe(IContatVuePresenteurVoirCoursGroupe.IPresenteurVoirCoursGroupe presenteur){
        _presenteur=presenteur;
    }

    public VueVoirCoursGroupe() {

    }
    public void set_presenteur(IContatVuePresenteurVoirCoursGroupe.IPresenteurVoirCoursGroupe presenteur){
        this._presenteur=presenteur;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_voir_cours_groupe, container, false);
        rvCoursGroupe=view.findViewById(R.id.rvVoirCoursGroupe);
        coursGroupeAdapter= new CoursGroupeAdapter(_presenteur);
        rvCoursGroupe.setAdapter(coursGroupeAdapter);
        rvCoursGroupe.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void rafraichir() {
        //TODO
    }
}
