package br.ufg.si.pedrofsn.teclado.interfaces;

import br.ufg.si.pedrofsn.teclado.enums.TipoBotaoEspecial;
import br.ufg.si.pedrofsn.teclado.models.Termo;
import br.ufg.si.pedrofsn.teclado.models.Visografema;

public interface IElisKeyboard {

    public void onBotaoVisografemaPressionado(Visografema visografema);

    public void onBotaoEspecialPressionado(TipoBotaoEspecial tipoBotaoEspecial);

    public void onBotaoTraduzirTermoPressionado(Termo resultado);
}
