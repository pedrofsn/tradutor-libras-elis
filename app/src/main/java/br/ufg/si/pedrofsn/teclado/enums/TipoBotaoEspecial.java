package br.ufg.si.pedrofsn.teclado.enums;

/**
 * Created by pedrofsn on 29/09/2014.
 */
public enum TipoBotaoEspecial {
    SOBRESCRITO(0),
    ESPACO(1),
    SUBLINHADO(2);

    private int valor;

    TipoBotaoEspecial(int valor) {
        this.valor = valor;
    }

    private TipoBotaoEspecial getTipoBotao() {
        switch (valor) {
            case 0: {
                return SOBRESCRITO;
            }
            case 1: {
                return ESPACO;
            }
            default: {
                return SUBLINHADO;
            }
        }
    }
}