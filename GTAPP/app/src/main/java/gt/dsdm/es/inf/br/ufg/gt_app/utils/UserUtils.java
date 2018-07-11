package gt.dsdm.es.inf.br.ufg.gt_app.utils;

import android.content.Context;

import gt.dsdm.es.inf.br.ufg.gt_app.persistencia.EasySharedPreferences;

public class UserUtils {
    private static EasySharedPreferences easySharedPreferences = new EasySharedPreferences();

    public static boolean isLoged(Context context){
        return !(easySharedPreferences.getStringFromKey(context, "token").equals(null) ||
                easySharedPreferences.getStringFromKey(context, "token").equals(""));
    }
}
