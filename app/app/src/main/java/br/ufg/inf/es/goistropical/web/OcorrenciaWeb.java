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

import br.ufg.inf.es.goistropical.model.Ocorrencia;
import okhttp3.Response;

public class OcorrenciaWeb extends WebConnection {

    private static String SERVICE = "ocorrencia/";
    private String token;

    public OcorrenciaWeb(String token) {
        super(SERVICE + token);
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
        List<Ocorrencia> ocorrencias = new LinkedList<>();
        try {
            responseBody = response.body().string();

            JSONArray jsonArray = new JSONArray(responseBody);
            for(int i=0; i < jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Ocorrencia ocorrencia = new Ocorrencia();

                ocorrencia.setId(Integer.parseInt(jsonObject.getString("id")));
                ocorrencia.setTitulo(jsonObject.getString("titulo"));
                ocorrencia.setCategoria(jsonObject.getString("categoria"));
                ocorrencia.setLocalizacao(jsonObject.getString("localizacao"));
                ocorrencia.setStatus(jsonObject.getString("status"));
                ocorrencia.setDescricao(jsonObject.getString("descricao"));

                ocorrencias.add(ocorrencia);
            }
            EventBus.getDefault().post(ocorrencias);
        } catch (IOException e) {
            EventBus.getDefault().post(e);
        } catch (JSONException e) {
            EventBus.getDefault().post(new Exception("A resposta do servidor não é válida"));
        }

    }
}
