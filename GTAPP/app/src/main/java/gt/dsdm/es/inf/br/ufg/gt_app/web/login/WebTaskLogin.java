/*
 * Copyright (c) 2018 Marcelo Quinta
 * Copyright (c) 2018 Antonio Silva
 * Copyright (c) 2018 Keslley Lima
 * MIT License.
 */
package gt.dsdm.es.inf.br.ufg.gt_app.web.login;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import gt.dsdm.es.inf.br.ufg.gt_app.model.Usuario;

public class WebTaskLogin extends WebTaskBase {

    private static String URL = "login/";
    private String FIELD_EMAIL = "email";
    private String FIELD_PASSWORD = "password";

    private String email;
    private String password;

    public WebTaskLogin(Context context,  String email, String password) {
        super(context, URL);
        this.email = email;
        this.password = password;
    }

    @Override
    String getRequestBody() {
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put(FIELD_EMAIL, email );
        requestMap.put(FIELD_PASSWORD, password );

        JSONObject json = new JSONObject(requestMap);
        return json.toString();

    }

    @Override
    void handleResponse(String response) {
        String responseBody = null;
        Usuario usuario = new Usuario();

        try {
            JSONObject nameAsJSON = new JSONObject(response);
            usuario.setUsuario(nameAsJSON.getString("usuario"));
            usuario.setNome(nameAsJSON.getString("nome"));
            usuario.setCpf(nameAsJSON.getString("cpf"));
            usuario.setTelefone(nameAsJSON.getString("telefone"));
            usuario.setEmail(nameAsJSON.getString("email"));
            usuario.setToken(nameAsJSON.getString("token"));
            EventBus.getDefault().post(usuario);
        } catch (JSONException e) {
            EventBus.getDefault().post(
                    new WebError(
                            "Resposta inválida do servidor", URL));
        }
    }

    @Override
    HttpMethod getMethod() {
        return HttpMethod.POST;
    }

}
