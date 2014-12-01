package br.ufg.si.pedrofsn;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import br.ufg.si.pedrofsn.teclado.Constantes;
import br.ufg.si.pedrofsn.teclado.interfaces.IElisKeyboard;
import br.ufg.si.pedrofsn.teclado.models.Termo;

public class AsyncTaskPOST extends AsyncTask<String, Void, String> {

    private Context context;
    private Termo termo;

    public
    AsyncTaskPOST(Context context, Termo termo) {
        this.context = context;
        this.termo = termo;
    }

    @Override
    protected String doInBackground(String... urls) {

        InputStream inputStream = null;
        String resultado = "";
        try {

            HttpClient httpclient = new DefaultHttpClient();

            HttpPost httpPost = new HttpPost(urls[0]);
            /* HttpGet httpPost = new HttpGet(urls[0]); */

            String jsonEmString = "";

            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("caso", 0);//termo.getTraduzirDe().toLowerCase());
            jsonObject.accumulate("termo", "teste");///termo.getTraduzirPara().toLowerCase());
            //jsonObject.accumulate("termo", ((String) termo.getTermo()).toLowerCase());

            jsonEmString = jsonObject.toString();
            Log.e(Constantes.LOG, ">> " + jsonEmString);

            StringEntity stringEntity = new StringEntity(jsonEmString);

            httpPost.setEntity(stringEntity);

            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
/*
            HttpResponse httpResponse = httpclient.execute(httpPost);
            inputStream = httpResponse.getEntity().getContent();
*/

            if (inputStream != null)
                resultado = converterInputStreamParaString(inputStream);
            else
                resultado = context.getString(R.string.ops_erro);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }

    @Override
    protected void onPostExecute(String retornoDoServidor) {

        Log.e(Constantes.LOG, "Retorno do servidor: " + retornoDoServidor);

        try {
            JSONObject jsonObject = new JSONObject(retornoDoServidor);
            Log.e(Constantes.LOG, "Resultado: " + jsonObject.getString("termos"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ((IElisKeyboard) context).onBotaoTraduzirTermoPressionado(termo);
    }

    private String converterInputStreamParaString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String linhaAtual = "";
        String resultado = "";
        while ((linhaAtual = bufferedReader.readLine()) != null)
            resultado += linhaAtual;

        inputStream.close();
        return resultado;

    }

}
