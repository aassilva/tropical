package br.ufg.inf.es.goistropical.web;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import br.ufg.inf.es.goistropical.model.Mural;
import okhttp3.Response;

public class MuralWeb extends WebConnection{

    private static final String SERVICE = "mural";
    private String token;

    public MuralWeb(String token) {
        super(SERVICE);
        this.token = token;
    }

    @Override
    String getRequestContent() {
        Map<String,String> requestMap = new HashMap<>();
        requestMap.put("token", token);

        JSONObject json = new JSONObject(requestMap);
        String jsonString = json.toString();

        return  jsonString;
    }

    @Override
    void handleResponse(Response response) {
        String responseBody = null;
        List<Mural> noticias = new LinkedList<>();
        try {
            responseBody = response.body().string();

            JSONArray jsonArray = new JSONArray(responseBody);
            for(int i=0; i < jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Mural noticia = new Mural();
                noticia.setTitulo(jsonObject.getString("titulo"));
                noticia.setDescricao(jsonObject.getString("descricao"));
                noticia.setConteudo(jsonObject.getString("conteudo"));
                noticia.setImagem(jsonObject.getString("link_imagem"));
                noticias.add(noticia);
            }
            EventBus.getDefault().post(noticias);
        } catch (IOException e) {
            EventBus.getDefault().post(e);
        } catch (JSONException e) {
            EventBus.getDefault().post(new Exception("A resposta do servidor não é válida"));
        }

    }
}
