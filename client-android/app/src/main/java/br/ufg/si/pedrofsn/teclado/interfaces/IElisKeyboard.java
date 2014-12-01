package br.ufg.si.pedrofsn.teclado.interfaces;

import br.ufg.si.pedrofsn.teclado.models.Termo;
import br.ufg.si.pedrofsn.teclado.models.TermoResponse;
import br.ufg.si.pedrofsn.teclado.models.Visografema;

public interface IElisKeyboard {

    public void onBotaoVisografemaPressionado(Visografema visografema);

    public void onBotaoEspacoPressionado();

    public void onBotaoBackspacePressionado();

    public void onBotaoTraduzirTermoPressionado(Termo resultado);

    public void setErrorMessage(String mensagem);
}
