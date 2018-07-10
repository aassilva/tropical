package gt.dsdm.es.inf.br.ufg.gt_app.web;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import gt.dsdm.es.inf.br.ufg.gt_app.model.Usuario;
import okhttp3.Response;

public class WebLogin extends WebConnection {

    private static final String SERVICE = "login/";
    private String FIELD_EMAIL = "email";
    private String FIELD_PASSWORD = "password";

    private String email;
    private String password;

    public WebLogin(String email, String password){
        super(SERVICE);
        this.email = email;
        this.password = password;
    }

    @Override
    String getRequestContent() {
//        Map<String,String> requestMap = new HashMap<>();
//        JSONObject json = new JSONObject(requestMap);
//        String jsonString = json.toString();

        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put(FIELD_EMAIL, email );
        requestMap.put(FIELD_PASSWORD, password );

        JSONObject json = new JSONObject(requestMap);
        return json.toString();
    }

    @Override
    void handleResponse(Response response) {
        String responseBody = null;
        Usuario usuario = new Usuario();
        try {
            responseBody = response.body().string();
            JSONObject nameAsJSON = new JSONObject(responseBody);
            usuario.setUsuario(nameAsJSON.getString("usuario"));
            usuario.setNome(nameAsJSON.getString("nome"));
            usuario.setCpf(nameAsJSON.getString("cpf"));
            usuario.setTelefone(nameAsJSON.getString("telefone"));
            usuario.setEmail(nameAsJSON.getString("email"));
            usuario.setToken(nameAsJSON.getString("token"));
            EventBus.getDefault().post(usuario);
        } catch (IOException e) {
            EventBus.getDefault().post(e);
        } catch (JSONException e) {
            EventBus.getDefault().post(new Exception("A resposta do servidor não é válida"));
        }
    }

    @Override
    HttpMethod getMethod() {
        return HttpMethod.POST;
    }

}
