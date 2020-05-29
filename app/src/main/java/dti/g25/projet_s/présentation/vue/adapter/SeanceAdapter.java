package dti.g25.projet_s.présentation.vue.adapter;

import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dti.g25.projet_s.R;
import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.présentation.présenteur.PresenteurVoirListeSeance;

public class SeanceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private PresenteurVoirListeSeance _presenteur;

    public SeanceAdapter(PresenteurVoirListeSeance _presenteur) {
        this._presenteur = _presenteur;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LinearLayout racine = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.seance_view, parent, false);

        return new RecyclerView.ViewHolder(racine){};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Seance seance=_presenteur.getSeanceParPos(position);
        ((TextView)holder.itemView.findViewById(R.id.tvSigleCoursGroupeSeance)).setText(seance.get_coursGroupe().getLibelleCours().getSigle());
        ((TextView)holder.itemView.findViewById(R.id.tvHeureDebutSe)).setText(seance.get_horaires().getHeureDebutString());
        ((TextView)holder.itemView.findViewById(R.id.tvHeureFinSe)).setText(seance.get_horaires().getHeureFinString());
        ((TextView)holder.itemView.findViewById(R.id.tvEtatSeance)).setText(seance.get_etat().name());

        //TODO les Onclick pour les boutons

    }

    @Override
    public int getItemCount() {
        if(_presenteur==null) return 0;
        return _presenteur.getNbSeancesModele();
    }
}
