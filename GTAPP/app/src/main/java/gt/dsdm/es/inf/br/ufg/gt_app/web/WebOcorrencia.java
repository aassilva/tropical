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

import gt.dsdm.es.inf.br.ufg.gt_app.model.Ocorrencia;
import okhttp3.Response;

public class WebOcorrencia extends WebConnection {

    private static String SERVICE = "ocorrencia/";

    public WebOcorrencia(String token){
        super(SERVICE + token);
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
        List<Ocorrencia> ocorrencias = new LinkedList<>();
        try {
            responseBody = response.body().string();

            JSONArray jsonArray = new JSONArray(responseBody);
            for(int i=0; i < jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Ocorrencia ocorrencia = new Ocorrencia(jsonObject.getString("titulo"),
                        jsonObject.getString("descricao"),
                        jsonObject.getString("status"),
                        jsonObject.getString("localizacao"),
                        jsonObject.getString("categoria")
                        );
                ocorrencias.add(ocorrencia);
            }
            EventBus.getDefault().post(ocorrencias);
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
