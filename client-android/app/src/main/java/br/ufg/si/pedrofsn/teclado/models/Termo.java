package br.ufg.si.pedrofsn.teclado.models;

import java.io.Serializable;

import br.ufg.si.pedrofsn.teclado.enums.TipoLingua;

public class Termo implements Serializable {

    private TipoLingua tipoLingua;
    private Object termo;

    public Termo(TipoLingua tipoLingua) {
        this.tipoLingua = tipoLingua;
    }

    public Termo(TipoLingua tipoLingua, String termo) {
        this.tipoLingua = tipoLingua;
        this.termo = termo;
    }

    public TipoLingua getTipoLingua() {
        return tipoLingua;
    }

    public void setTipoLingua(TipoLingua tipoLingua) {
        this.tipoLingua = tipoLingua;
    }

    public Object getTermo() {
        return termo;
    }

    public void setTermo(Object termo) {
        this.termo = termo;
    }

    public boolean isExibirResultadoEmElis() {
        return getTipoLingua() == TipoLingua.ELIS;
    }

    public String getTraduzirDe() {
        return tipoLingua == TipoLingua.ELIS ? "ELiS" : "PT-BR";
    }

    public String getTraduzirPara() {
        return tipoLingua != TipoLingua.ELIS ? "ELiS" : "PT-BR";
    }
}
