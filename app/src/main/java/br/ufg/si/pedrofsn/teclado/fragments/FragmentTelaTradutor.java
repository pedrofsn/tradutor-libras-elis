package br.ufg.si.pedrofsn.teclado.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import br.ufg.si.pedrofsn.ActivityMain;
import br.ufg.si.pedrofsn.AsyncTaskPOST;
import br.ufg.si.pedrofsn.R;
import br.ufg.si.pedrofsn.Utils.Utils;
import br.ufg.si.pedrofsn.teclado.enums.TipoLingua;
import br.ufg.si.pedrofsn.teclado.interfaces.CallbackFragmentToActivity;
import br.ufg.si.pedrofsn.teclado.models.Termo;

/**
 * Created by pedrofsn on 29/09/2014.
 */
public class FragmentTelaTradutor extends Fragment implements AsyncTaskPOST.InterfaceAsyncTaskPostCallback, View.OnClickListener {

    public final static String TAG = "FragmentTradutor";

    private TipoLingua tipoLingua;

    private TextView textViewDe;
    private ImageView imageViewTrocaLinguagem;
    private TextView textViewPara;
    private ImageView imageViewTraduzir;
    private EditText editTextPtBr;
    private TextView textViewElis;

    private CallbackFragmentToActivity callback;

    private Animation animationRotacionar;

    private Termo termo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_conteudo, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textViewDe = (TextView) view.findViewById(R.id.textViewDe);
        imageViewTrocaLinguagem = (ImageView) view.findViewById(R.id.imageViewTrocaLinguagem);
        textViewPara = (TextView) view.findViewById(R.id.textViewPara);
        imageViewTraduzir = (ImageView) view.findViewById(R.id.imageViewTraduzir);
        editTextPtBr = (EditText) view.findViewById(R.id.editTextPtBr);
        textViewElis = (TextView) view.findViewById(R.id.textViewElis);
    }

    @Override
    public void onStart() {
        super.onStart();
        tipoLingua = TipoLingua.PTBR;

        // Carrega animação
        animationRotacionar = AnimationUtils.loadAnimation(getActivity(), R.anim.rotacionar);

        termo = new Termo(tipoLingua);

        setValoresNosTextViewsDeTraducao();

        imageViewTrocaLinguagem.setOnClickListener(this);
        imageViewTraduzir.setOnClickListener(this);

        callback.getTextViewElis(textViewElis);

        Utils.aplicarFonteElis(getActivity(), textViewElis);
    }

    @Override
    public void postRealizado(String retornoDoServidor) {
        Toast.makeText(getActivity(), "Servidor retornou na activity: " + retornoDoServidor, Toast.LENGTH_LONG).show();

        try {
            JSONObject jsonObject = new JSONObject(retornoDoServidor);
            retornoDoServidor = jsonObject.getString("termos");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Utils.aplicarFonteElis(getActivity(), editTextPtBr);
        editTextPtBr.setText(retornoDoServidor);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewTraduzir:

                termo.setTipoLingua(tipoLingua);
                termo.setTermo(getTermoInseridoPeloUsuario());

                AsyncTaskPOST httpAsyncTask = new AsyncTaskPOST(this, termo);
                httpAsyncTask.execute("http://elis2.apiary-mock.com/search");
                break;

            case R.id.imageViewTrocaLinguagem:
                chavearLinguagem();
                break;
        }
    }

    private void chavearLinguagem() {
        imageViewTrocaLinguagem.startAnimation(animationRotacionar);
        tipoLingua = tipoLingua.alterarLingua();
        setValoresNosTextViewsDeTraducao();
        alterarVisibilidade(((ActivityMain) getActivity()).getFrameLayoutKeyboardElis(), editTextPtBr, textViewElis);
        Utils.ocultarTecladoAndroid(getActivity(), editTextPtBr);
    }

    private void setValoresNosTextViewsDeTraducao() {
        termo.setTipoLingua(tipoLingua);
        textViewDe.setText(termo.getTraduzirDe());
        textViewPara.setText(termo.getTraduzirPara());
    }

    private void alterarVisibilidade(View... views) {
        for (View v : views) {
            if (v.getVisibility() == View.VISIBLE)
                v.setVisibility(View.GONE);
            else
                v.setVisibility(View.VISIBLE);
        }
    }

    private String getTermoInseridoPeloUsuario() {
        return tipoLingua.isElis() ? textViewElis.getText().toString() : editTextPtBr.getText().toString();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            callback = (CallbackFragmentToActivity) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " precisa implementar a interface CallbackTelaFragmentTradutor");
        }
    }
}