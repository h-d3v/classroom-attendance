package dti.g25.projet_s.présentation.vue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import dti.g25.projet_s.R;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dti.g25.projet_s.présentation.vue.IContatVuePresenteurVoirCoursGroupe;

public class CoursGroupeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private IContatVuePresenteurVoirCoursGroupe.IPresenteurVoirCoursGroupe presenteurVoirCoursGroupe;
    public CoursGroupeAdapter(IContatVuePresenteurVoirCoursGroupe.IPresenteurVoirCoursGroupe presenteurVoirCoursGroupe){
        this.presenteurVoirCoursGroupe=presenteurVoirCoursGroupe;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        LinearLayout racine = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cours_groupe_view, parent, false);

        return new RecyclerView.ViewHolder(racine) {
        };
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((Button)holder.itemView.findViewById(R.id.btnVoirCoursGroupe)).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View bouton){
                presenteurVoirCoursGroupe.requeteVoirCoursGroupe(position);
            }
        });
        ((TextView)holder.itemView.findViewById(R.id.tv_textCoursGroupe)).setText(presenteurVoirCoursGroupe.getTitreCoursGroupe(position));
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        if(presenteurVoirCoursGroupe==null) {
            return 0;
        }
        else {
            return presenteurVoirCoursGroupe.getNombresItems();
        }
    }
}
