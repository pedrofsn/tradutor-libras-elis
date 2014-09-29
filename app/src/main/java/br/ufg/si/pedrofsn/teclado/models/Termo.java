package br.ufg.si.pedrofsn.teclado.models;

import br.ufg.si.pedrofsn.teclado.enums.TipoLingua;

public class Termo {

    private TipoLingua tipoLingua;
    private String termo;

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

    public String getTermo() {
        return termo;
    }

    public void setTermo(String termo) {
        this.termo = termo;
    }


    public String getTraduzirDe() {
        return tipoLingua == TipoLingua.ELIS ? "ELIS" : "PT-BR";
    }

    public String getTraduzirPara() {
        return tipoLingua != TipoLingua.ELIS ? "ELIS" : "PT-BR";
    }
}
