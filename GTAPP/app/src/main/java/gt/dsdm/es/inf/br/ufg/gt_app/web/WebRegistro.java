package gt.dsdm.es.inf.br.ufg.gt_app.web;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Response;

public class WebRegistro extends WebConnection {

    private static final String SERVICE = "perfil/";
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String user;
    private String pass;

    private String FIELD_NOME = "nome";
    private String FIELD_CPF = "cpf";
    private String FIELD_PHONE = "telefone";
    private String FIELD_EMAIL = "email";
    private String FIELD_USER = "usuario";
    private String FIELD_PASS = "senha";

    public  WebRegistro(String nome, String cpf, String telefone,
                        String email, String usuario, String pass, onRegisterResponse handler) {

        super(SERVICE, handler);
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.user = usuario;
        this.pass = pass;
    }

    @Override
    String getRequestContent() {

        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put(FIELD_NOME, nome);
        requestMap.put(FIELD_CPF, cpf );
        requestMap.put(FIELD_PHONE, telefone);
        requestMap.put(FIELD_EMAIL, email);
        requestMap.put(FIELD_USER, user);
        requestMap.put(FIELD_PASS, pass);

        JSONObject json = new JSONObject(requestMap);
        return json.toString();
    }

    @Override
    void handleResponse(Response response) {
        String responseBody = null;
        try {
            responseBody = response.body().string();
            EventBus.getDefault().post(responseBody);
        } catch (IOException e) {
            EventBus.getDefault().post(e);
        }
    }

    @Override
    HttpMethod getMethod() {
        return HttpMethod.POST;
    }
}
