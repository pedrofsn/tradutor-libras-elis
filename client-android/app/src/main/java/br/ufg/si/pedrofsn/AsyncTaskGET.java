package br.ufg.si.pedrofsn;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import br.ufg.si.pedrofsn.teclado.Constantes;
import br.ufg.si.pedrofsn.teclado.enums.TipoLingua;
import br.ufg.si.pedrofsn.teclado.interfaces.IElisKeyboard;
import br.ufg.si.pedrofsn.teclado.models.Termo;
import br.ufg.si.pedrofsn.teclado.models.TermoResponse;
import br.ufg.si.pedrofsn.teclado.models.Visografema;

public class AsyncTaskGET extends AsyncTask<String, Void, String> {

    private Context context;
    private Termo termo;

    private int caso = 1;
    private String termoEmString = "";


    public AsyncTaskGET(Context context, Termo termo) {
        this.context = context;
        this.termo = termo;
    }

    @Override
    protected String doInBackground(String... urls) {

        InputStream inputStream = null;
        String resultado = "";
        try {

            HttpClient httpclient = new DefaultHttpClient();
            if (termo.getTipoLingua().isElis())
                caso = 0;

            if (termo.getTermo() instanceof String) {
                termoEmString = ((String) termo.getTermo()).toLowerCase();
            } else {
                List<Visografema> listaDeVisografemas = (List<Visografema>) termo.getTermo();
                for (Visografema v : listaDeVisografemas) {
                    termoEmString = termoEmString.concat(v.getValorElis());
                }
            }

            String url = urls[0] + "busca/" + caso + "/" + termoEmString.trim();

            Log.e(Constantes.LOG, "url: " + url);

            HttpGet httpPost = new HttpGet(url);

            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            HttpResponse httpResponse = httpclient.execute(httpPost);
            inputStream = httpResponse.getEntity().getContent();

            if (inputStream != null)
                resultado = converterInputStreamParaString(inputStream);
            else
                resultado = context.getString(R.string.ops_erro);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(Constantes.LOG, ">> " + e.getMessage());
        }

        return resultado;
    }

    @Override
    protected void onPostExecute(String retornoDoServidor) {

        Log.e(Constantes.LOG, "Retorno do servidor: " + retornoDoServidor);

        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<TermoResponse>>() {
        }.getType();
        Collection<TermoResponse> itens = gson.fromJson(retornoDoServidor, collectionType);

        if (itens != null && itens.size() > 0 && !retornoDoServidor.equals("[]")) {

            List<TermoResponse> termoResponseList = (List<TermoResponse>) itens;
            TermoResponse t = termoResponseList.get(0);

            Termo termo = new Termo();

            if (caso == 0) {
                termo.setTipoLingua(TipoLingua.ELIS);
                termo.setTermo(t.getPtbr());
            } else {
                termo.setTipoLingua(TipoLingua.PTBR);
                termo.setTermo(t.getElis());
            }

            ((IElisKeyboard) context).onBotaoTraduzirTermoPressionado(termo);
        } else {
            ((IElisKeyboard) context).setErrorMessage("Termo não cadastrado");
        }
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
