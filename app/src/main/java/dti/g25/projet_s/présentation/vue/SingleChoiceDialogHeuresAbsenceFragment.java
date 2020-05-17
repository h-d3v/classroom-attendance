package dti.g25.projet_s.présentation.vue;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import dti.g25.projet_s.R;

public class SingleChoiceDialogHeuresAbsenceFragment extends DialogFragment {

    int posiition=0; //Option par default

    public interface SingleChoiceListener{
        void onClickPositif(String[] list,int position);
        void onClickNegatif();
    }

    SingleChoiceListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener= (SingleChoiceListener) context;
        } catch (Exception e) {
          throw new ClassCastException(getActivity().toString() + " - SingleChoiceListener doit etre implémentée");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final String[] list = getActivity().getResources().getStringArray(R.array.choix_heures_absences);

        builder.setTitle("Selectionez le nombre d'heures absentées")
                .setSingleChoiceItems(list, posiition, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        posiition = which;

                    }
                })
                .setPositiveButton("Ajouter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onClickPositif(list,posiition);
                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onClickNegatif();
                    }
                });
        return builder.create();
    }
}
