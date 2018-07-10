package gt.dsdm.es.inf.br.ufg.gt_app.request;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import gt.dsdm.es.inf.br.ufg.gt_app.model.Ocorrencia;

public class ApiarySaveOcurrenceRequest extends AsyncTask<String, String, String> {

    private final Ocorrencia ocorrencia;
    Context context;

    public ApiarySaveOcurrenceRequest(Ocorrencia ocorrencia, Context context){
        this.ocorrencia = ocorrencia;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Random rand = new Random();

            int  n = rand.nextInt(5000) + 1;
            URL url = new URL("http://private-ea8e6-goiastropical.apiary-mock.com/ocorrencia/" + String.valueOf(n));
            Log.i("urlll", url.toString());

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");

            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());

            BufferedWriter writer = new BufferedWriter (new OutputStreamWriter(out, "UTF-8"));

            writer.write(ocorrencia.toJson());

            writer.flush();

            writer.close();

            out.close();

            urlConnection.connect();

        } catch (Exception e) {
            Log.i("----erro_save_apiary", e.getMessage());
        }
        return "";
    }
}
