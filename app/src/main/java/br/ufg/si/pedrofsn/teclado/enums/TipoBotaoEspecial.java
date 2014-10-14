package br.ufg.si.pedrofsn.teclado.enums;

/**
 * Created by pedrofsn on 29/09/2014.
 */
public enum TipoBotaoEspecial {
    SOBRESCRITO(0),
    ESPACO(1),
    SUBLINHADO(2),
    NUMEROS(3),
    PONTUACAO(4),
    BACKSPACE(5);

    private int valor;

    TipoBotaoEspecial(int valor) {
        this.valor = valor;
    }

    private TipoBotaoEspecial getTipoBotao() {
        switch (valor) {
            case 0:
                return SOBRESCRITO;

            case 1:
                return ESPACO;

            case 2:
                return SUBLINHADO;

            case 3:
                return NUMEROS;

            case 4:
                return PONTUACAO;

            default:
                return BACKSPACE;

        }
    }
}