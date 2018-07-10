/*
 * Copyright (c) 2018 Marcelo Quinta
 * Copyright (c) 2018 Antonio Silva
 * Copyright (c) 2018 Keslley Lima
 * MIT License.
 */
package gt.dsdm.es.inf.br.ufg.gt_app.web;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import gt.dsdm.es.inf.br.ufg.gt_app.model.Mural;
import okhttp3.Response;

public class WebMural extends WebConnection {

    private static final String SERVICE = "mural/";

    public WebMural(){
        super(SERVICE);
    }

    @Override
    String getRequestContent() {
//        Map<String,String> requestMap = new HashMap<>();
//        JSONObject json = new JSONObject(requestMap);
//        String jsonString = json.toString();

        return  "";
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

    @Override
    HttpMethod getMethod() {
        return HttpMethod.GET;
    }
}
