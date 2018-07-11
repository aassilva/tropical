package gt.dsdm.es.inf.br.ufg.gt_app.utils;

import android.content.Context;

import gt.dsdm.es.inf.br.ufg.gt_app.model.Usuario;
import gt.dsdm.es.inf.br.ufg.gt_app.persistencia.EasySharedPreferences;

public class UserUtils {
    private static EasySharedPreferences easySharedPreferences = new EasySharedPreferences();

    public static boolean isLoged(Context context){
        return !(easySharedPreferences.getStringFromKey(context, "token").equals(null) ||
                easySharedPreferences.getStringFromKey(context, "token").equals(""));
    }

    public static void saveUser(Usuario usuario, Context context){
        easySharedPreferences.setStringFromKey(context, "usuario", usuario.getUsuario());
        easySharedPreferences.setStringFromKey(context, "nome", usuario.getNome());
        easySharedPreferences.setStringFromKey(context, "cpf", usuario.getCpf());
        easySharedPreferences.setStringFromKey(context, "telefone", usuario.getTelefone());
        easySharedPreferences.setStringFromKey(context, "email", usuario.getEmail());
        easySharedPreferences.setStringFromKey(context, "token", usuario.getToken());
    }

}
