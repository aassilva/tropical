/*
 * Copyright (c) 2018 Marcelo Quinta
 * MIT License.
 */
package gt.dsdm.es.inf.br.ufg.gt_app.web;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class WebConnection {

    private static final String BASE_URL = "http://private-c9b97-goiastropical.apiary-mock.com/";
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private String serviceName;
    private onRegisterResponse handler;

    public WebConnection(String serviceName, onRegisterResponse handler){
        this.serviceName = serviceName;
        this.handler = handler;
    }

    public void call() {
        OkHttpClient client = new OkHttpClient();

        String url = getUrl();

        RequestBody body = RequestBody.create(JSON, getRequestContent());
//        Request request = new Request.Builder().url(url);
        Request.Builder request = new Request.Builder()
                .url(getUrl());

        switch (getMethod()){
            case POST:
                request.post(body);
                break;
            case PUT:
                request.put(body);
                break;
            case PATCH:
                request.patch(body);
                break;
            case DELETE:
                request.delete(body);
                break;
            case GET:
                request.get();
                break;
        }

        client.newCall(request.build()).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                EventBus.getDefault().post(new Exception("Verifique sua conexão"));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                handleResponse(response);
                handle();
            }
        });
    }

    void handle(){
        handler.handleResponse();
    }

    public String getUrl(){
        return BASE_URL + serviceName;
    }

    abstract String getRequestContent();

    abstract HttpMethod getMethod();

    void handleResponse(Response response) { }

    protected enum HttpMethod {
        GET,POST,PATCH,DELETE,PUT;
    }

    public interface onRegisterResponse{
        void handleResponse();
    }

}
