package gt.dsdm.es.inf.br.ufg.gt_app;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import gt.dsdm.es.inf.br.ufg.gt_app.persistencia.EasySharedPreferences;
import gt.dsdm.es.inf.br.ufg.gt_app.presenter.activity.NotLoginActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {

    private EasySharedPreferences easySharedPreferences = new EasySharedPreferences();
    private View view;

    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_perfil, container, false);

        return view;

    }

    @Override
    public void onStart() {

        if (easySharedPreferences.getStringFromKey(this.getContext(), "token").equals(null) ||
                easySharedPreferences.getStringFromKey(this.getContext(), "token").equals("")) {
            Intent intentPassword = new Intent(this.getContext(), NotLoginActivity.class);
            startActivity(intentPassword);
        } else {
            tryPerfil();
            super.onStart();
        }
    }

    public void tryPerfil()  {
        TextView name = (TextView) view.findViewById(R.id.label_task_nome);
        name.setText(easySharedPreferences.getStringFromKey(this.getContext(), "nome"));

        TextView cpf = (TextView) view.findViewById(R.id.label_task_cpf);
        cpf.setText(easySharedPreferences.getStringFromKey(this.getContext(), "cpf"));

        TextView usuario = (TextView) view.findViewById(R.id.label_task_usuario);
        usuario.setText(easySharedPreferences.getStringFromKey(this.getContext(), "usuario"));

        TextView telefone = (TextView) view.findViewById(R.id.label_task_telefone);
        telefone.setText(easySharedPreferences.getStringFromKey(this.getContext(), "telefone"));

        TextView email = (TextView) view.findViewById(R.id.label_task_email);
        email.setText(easySharedPreferences.getStringFromKey(this.getContext(), "email"));
    }

}
