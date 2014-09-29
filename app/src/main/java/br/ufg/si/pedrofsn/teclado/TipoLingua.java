package br.ufg.si.pedrofsn.teclado;

/**
 * Created by pedrofsn on 28/09/2014.
 */
public enum TipoLingua {
    ELIS(0),
    PTBR(1);

    private int valorLingua;

    TipoLingua(int valorLingua) {
        this.valorLingua = valorLingua;
    }

    private TipoLingua getTipoLingua() {
        if(valorLingua == 0) {
            return TipoLingua.ELIS;
        } else {
            return TipoLingua.PTBR;
        }
    }

    public boolean isElis() {
        return getTipoLingua() == TipoLingua.ELIS;
    }

    public TipoLingua alterarLingua() {
        if (getTipoLingua() == TipoLingua.ELIS) {
            return TipoLingua.PTBR;
        } else {
            return TipoLingua.ELIS;
        }
    }

}