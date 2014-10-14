package br.ufg.si.pedrofsn.teclado.interfaces;

import android.widget.TextView;

import br.ufg.si.pedrofsn.teclado.enums.TipoBotaoEspecial;

/**
 * Created by pedrofsn on 29/09/2014.
 */
public interface CallbackFragmentToActivity {
    public void getTextViewElis(TextView v);
    public void botaoPressionado(TipoBotaoEspecial tipoBotaoEspecial);
    public void getResultadoTraducao(String resultado);
}
