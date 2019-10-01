package br.ufg.si.pedrofsn.teclado.fragments;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import br.ufg.si.pedrofsn.ActivityMain;
import br.ufg.si.pedrofsn.AsyncTaskGET;
import br.ufg.si.pedrofsn.R;
import br.ufg.si.pedrofsn.Url;
import br.ufg.si.pedrofsn.Utils.Navegacao;
import br.ufg.si.pedrofsn.Utils.Utils;
import br.ufg.si.pedrofsn.teclado.enums.TipoLingua;
import br.ufg.si.pedrofsn.teclado.interfaces.CallbackFragmentToActivity;
import br.ufg.si.pedrofsn.teclado.models.Termo;

/**
 * Created by pedrofsn on 29/09/2014.
 */
public class FragmentTopoTradutor extends Fragment implements View.OnClickListener {

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
        return inflater.inflate(R.layout.fragment_topo_tradutor, container, false);
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

        // Habilita o scroll no textview
        textViewElis.setMovementMethod(new ScrollingMovementMethod());

        // Faço com que o textview seja acessível no escopo da activity
        callback.getTextViewElisNaActivity(textViewElis);

        // Botão enter do teclado, dispara pesquisa - quando se está traduzindo de pt-br para elis
        editTextPtBr.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    onClick(imageViewTraduzir);
                    return true;
                }
                return false;
            }
        });
    }

    private void chavearLinguagem() {
        imageViewTrocaLinguagem.startAnimation(animationRotacionar);
        tipoLingua = tipoLingua.alterarLingua();
        setValoresNosTextViewsDeTraducao();
        alterarVisibilidadeDeViews(((ActivityMain) getActivity()).getElisKeyboard(), editTextPtBr, textViewElis);
        Utils.ocultarTecladoAndroid(getActivity(), editTextPtBr);
        ((ActivityMain) getActivity()).ocultarCardResultado();

        if (tipoLingua != null && tipoLingua == TipoLingua.ELIS) {
            ((ActivityMain) getActivity()).getElisKeyboard().setVisibility(View.VISIBLE);
            Navegacao.replaceFragment(new FragmentElisKeyboard(), getActivity().getSupportFragmentManager(), FragmentElisKeyboard.TAG, R.id.frameLayoutKeyboardElis);
        }

        ((ActivityMain) getActivity()).setTipoTraducao(tipoLingua.traduzirPara());
    }

    private void setValoresNosTextViewsDeTraducao() {
        termo.setTipoLingua(tipoLingua);
        textViewDe.setText(termo.getTraduzirDe());
        textViewPara.setText(termo.getTraduzirPara());
    }

    private void alterarVisibilidadeDeViews(View... views) {
        for (View v : views) {
            if (v.getVisibility() == View.VISIBLE)
                v.setVisibility(View.GONE);
            else
                v.setVisibility(View.VISIBLE);
        }
    }

    private Object getValorInseridoPeloUsuario() {
        return tipoLingua.isElis() ? ((ActivityMain) getActivity()).getListaVisografemasInputados() : editTextPtBr.getText().toString();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callback = (CallbackFragmentToActivity) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " precisa implementar a interface CallbackTelaFragmentTradutor");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewTraduzir:

                if (Utils.isConectado(getActivity())) {
                    termo.setTipoLingua(tipoLingua);
                    termo.setTermo(getValorInseridoPeloUsuario());

                    //AsyncTaskPOST httpAsyncTask = new AsyncTaskPOST(getActivity(), termo);
                    //httpAsyncTask.execute(Url.URL);

                    AsyncTaskGET asyncTaskGET = new AsyncTaskGET(getActivity(), termo);
                    asyncTaskGET.execute(Url.URL);
                }

                break;

            case R.id.imageViewTrocaLinguagem:
                chavearLinguagem();
                break;
        }
    }

}