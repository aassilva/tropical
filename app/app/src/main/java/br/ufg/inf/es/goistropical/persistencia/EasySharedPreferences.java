package br.ufg.inf.es.goistropical.persistencia;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class EasySharedPreferences {

    public static String KEY_NAME = "nome";
    public static String KEY_CPF = "cpf";
    public static String KEY_TOKEN = "token";
    public static String KEY_USER = "usuario";
    public static String KEY_PHONE = "telefone";
    public static String KEY_EMAIL = "email";

    public static String getStringFromKey(Context context, String key){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getString(key,"");
    }

    public static void setStringFromKey(Context context, String key, String value){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key,value);
        editor.commit();
    }

}
