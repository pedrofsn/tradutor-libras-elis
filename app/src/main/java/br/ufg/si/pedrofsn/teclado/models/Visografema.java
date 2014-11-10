package br.ufg.si.pedrofsn.teclado.models;

public class Visografema {

    private String valorElis;
    private boolean sobrescrito;
    private boolean sublinhado;

    public Visografema(String valorElis) {
        this.valorElis = valorElis;
    }

    public String getValorElis() {
        return valorElis;
    }

    public void setValorElis(String valorElis) {
        this.valorElis = valorElis;
    }

    public boolean isSublinhado() {
        return sublinhado;
    }

    public void setSublinhado(boolean sublinhado) {
        this.sublinhado = sublinhado;
    }

    public boolean isSobrescrito() {
        return sobrescrito;
    }

    public void setSobrescrito(boolean sobrescrito) {
        this.sobrescrito = sobrescrito;
    }
}
