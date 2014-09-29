package br.ufg.si.pedrofsn.model;

public class ELiS {

    private String de;
    private String para;
    private String termo;

    public ELiS() {
    }

    public ELiS(String de, String para, String termo) {
        this.de = de;
        this.para = para;
        this.termo = termo;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public String getTermo() {
        return termo;
    }

    public void setTermo(String termo) {
        this.termo = termo;
    }

}
