package dti.g25.projet_s.présentation.vue.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapLabel;
import com.beardedhen.androidbootstrap.api.attributes.BootstrapBrand;

import dti.g25.projet_s.R;
import dti.g25.projet_s.domaine.entité.Seance;
import dti.g25.projet_s.présentation.ContratVpVoirUnCoursGroupe;

public class SeanceAdapterUnCourGroupe extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ContratVpVoirUnCoursGroupe.IPrensenteurVoirCourGroupe _presenteur;
    private BootstrapButton btnModifierPrésence;
    private BootstrapButton btnModifierPrendrePrésence;
    private BootstrapButton btnVoirDétails;
    private BootstrapLabel tvEtatSeance;

    public SeanceAdapterUnCourGroupe(ContratVpVoirUnCoursGroupe.IPrensenteurVoirCourGroupe _presenteur) {
        this._presenteur = _presenteur;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout racine = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.seance_view_cour_groupe, parent, false);

        return new RecyclerView.ViewHolder(racine){};
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        Seance seance= null;
        try {
            seance = _presenteur.getSeanceParPos(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
        btnModifierPrésence =  (holder.itemView.findViewById(R.id.btnModifierPrésence));
        btnModifierPrendrePrésence = (holder.itemView.findViewById(R.id.btnPrendrePrésence));
        btnVoirDétails = (holder.itemView.findViewById(R.id.btnVoirDétails));

        assert seance != null;
        ((TextView)holder.itemView.findViewById(R.id.jourSeance)).setText(seance.get_horaires().getDate());
        ((TextView)holder.itemView.findViewById(R.id.tvHeureSe)).setText("De "+seance.get_horaires().getHeureFinString()+"À "+seance.get_horaires().getHeureFinString());
        tvEtatSeance=holder.itemView.findViewById(R.id.tvEtatSeance);
        tvEtatSeance.setText(seance.get_etat().name());

        if (seance.get_etat().name().toUpperCase().equals("ANULLEE")){
            tvEtatSeance.setBackgroundColor(tvEtatSeance.getResources().getColor(R.color.bootstrap_brand_danger, null));
        }

        (holder.itemView.findViewById(R.id.btnModifierPrésence)).setVisibility(_presenteur.getVisibilteBouton());
        (holder.itemView.findViewById(R.id.btnModifierPrésence)).setEnabled(_presenteur.getUtilisateurUilisateurBouton());
        (holder.itemView.findViewById(R.id.btnPrendrePrésence)).setVisibility(_presenteur.getVisibilteBouton());
        (holder.itemView.findViewById(R.id.btnPrendrePrésence)).setEnabled(_presenteur.getUtilisateurUilisateurBouton());

        (holder.itemView.findViewById(R.id.btnModifierPrésence)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View bouton) {
                _presenteur.requeteModifierPrésence(position);
            }
        });

        (holder.itemView.findViewById(R.id.btnPrendrePrésence)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View bouton) {
                _presenteur.requetePrendrePrésence(position);
            }
        });

        (holder.itemView.findViewById(R.id.btnVoirDétails)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View bouton) {
                _presenteur.requeteVoirSeance(position); }
        });
    }

    @Override
    public int getItemCount() {
        if(_presenteur==null)
            return 0;
        try {
            return _presenteur.getNbSeancesModele();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}

