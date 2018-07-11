package gt.dsdm.es.inf.br.ufg.gt_app.presenter.list;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import gt.dsdm.es.inf.br.ufg.gt_app.presenter.activity.AtualizarPerfilActivity;
import gt.dsdm.es.inf.br.ufg.gt_app.MainActivity;
import gt.dsdm.es.inf.br.ufg.gt_app.R;
import gt.dsdm.es.inf.br.ufg.gt_app.persistencia.EasySharedPreferences;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {

    private EasySharedPreferences easySharedPreferences = new EasySharedPreferences();
    private View view;
    private Context context;

    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_perfil, container, false);

        ImageView buttonClose = view.findViewById(R.id.button_logoff);

        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                easySharedPreferences.setStringFromKey(context, "token", null);
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });

        setupButtonRegister();

        return view;

    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public void onStart() {

//        if (easySharedPreferences.getStringFromKey(context, "token").equals(null) ||
//                easySharedPreferences.getStringFromKey(context, "token").equals("")) {
//            Intent intentPassword = new Intent(this.getContext(), NotLoginActivity.class);
//            startActivity(intentPassword);
//            super.onStart();
//        } else {
            tryPerfil();
            super.onStart();
//        }

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

    private void setupButtonRegister() {
        Button buttonRegister =
                view.findViewById(R.id.button_atualizar);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(),
                        AtualizarPerfilActivity.class);
                startActivity(intent);
            }
        });
    }

}
