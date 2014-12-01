package br.ufg.si.pedrofsn.teclado.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import br.ufg.si.pedrofsn.R;
import br.ufg.si.pedrofsn.teclado.Constantes;
import br.ufg.si.pedrofsn.teclado.adapters.AdapterTecla;
import br.ufg.si.pedrofsn.teclado.models.Visografema;

public class FragmentConjuntoTeclas extends Fragment {

    private GridView gridView;
    private AdapterTecla customGridAdapter;
    private List<Visografema> gridTeclas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.adapter_conjunto_teclas, container, false);

        gridTeclas = new ArrayList<Visografema>();

        gridView = (GridView) rootView.findViewById(R.id.gridViewTeclas);

        // desenvolver e montar o listener dos botões aqui
        customGridAdapter = new AdapterTecla(getActivity(), R.layout.adapter_tecla, gridTeclas);

        gridView.setAdapter(customGridAdapter);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        setVisografemasNoGrupoAtual(getArguments().getInt("numeroGrupoDeVisografemas"));
    }


    private void setVisografemasNoGrupoAtual(int numeroGrupoDeVisografemas) {
        // Sem este IF, o array continua a ser populado por novas "teclas", mas
        // sem renderizar na tela.
        if (gridTeclas.size() >= 0) {
            gridTeclas.clear();
        }

        switch (numeroGrupoDeVisografemas) {

            case Constantes.CONFIGURACAO_DE_DEDO:
                gridTeclas.add(new Visografema("q"));
                gridTeclas.add(new Visografema("w"));
                gridTeclas.add(new Visografema("e"));
                gridTeclas.add(new Visografema("r"));
                gridTeclas.add(new Visografema("t"));
                gridTeclas.add(new Visografema("y"));
                gridTeclas.add(new Visografema("u"));
                gridTeclas.add(new Visografema("i"));
                gridTeclas.add(new Visografema("o"));
                gridTeclas.add(new Visografema("p"));
                gridTeclas.add(new Visografema("¹"));
                gridTeclas.add(new Visografema("²"));
                gridTeclas.add(new Visografema("a"));
                gridTeclas.add(new Visografema("s"));
                gridTeclas.add(new Visografema("d"));
                gridTeclas.add(new Visografema("³"));
                gridTeclas.add(new Visografema("£"));
                gridTeclas.add(new Visografema("f"));
                gridTeclas.add(new Visografema("g"));
                gridTeclas.add(new Visografema("h"));
                gridTeclas.add(new Visografema("¢"));
                gridTeclas.add(new Visografema("¬"));
                gridTeclas.add(new Visografema("j"));
                gridTeclas.add(new Visografema("k"));
                break;

            case Constantes.ORIENTACAO_DE_PALMA:
                gridTeclas.add(new Visografema("l"));
                gridTeclas.add(new Visografema("ç"));
                gridTeclas.add(new Visografema("z"));
                gridTeclas.add(new Visografema("x"));
                gridTeclas.add(new Visografema("c"));
                gridTeclas.add(new Visografema("v"));
                break;

            case Constantes.PONTOS_DE_ARTICULACAO:
                gridTeclas.add(new Visografema("Q"));
                gridTeclas.add(new Visografema("W"));
                gridTeclas.add(new Visografema("E"));
                gridTeclas.add(new Visografema("R"));
                gridTeclas.add(new Visografema("T"));
                gridTeclas.add(new Visografema("Y"));
                gridTeclas.add(new Visografema("U"));
                gridTeclas.add(new Visografema("I"));
                gridTeclas.add(new Visografema("O"));
                gridTeclas.add(new Visografema("P"));
                gridTeclas.add(new Visografema("A"));
                gridTeclas.add(new Visografema("S"));
                gridTeclas.add(new Visografema("D"));
                gridTeclas.add(new Visografema("F"));
                gridTeclas.add(new Visografema("G"));
                gridTeclas.add(new Visografema("H"));
                gridTeclas.add(new Visografema("J"));
                gridTeclas.add(new Visografema("K"));
                gridTeclas.add(new Visografema("L"));
                gridTeclas.add(new Visografema("Ç"));
                gridTeclas.add(new Visografema("\\"));
                gridTeclas.add(new Visografema("Z"));
                gridTeclas.add(new Visografema("X"));
                gridTeclas.add(new Visografema("C"));
                gridTeclas.add(new Visografema("V"));
                gridTeclas.add(new Visografema("B"));
                gridTeclas.add(new Visografema("N"));
                gridTeclas.add(new Visografema("M"));
                gridTeclas.add(new Visografema("@"));
                gridTeclas.add(new Visografema("#"));
                gridTeclas.add(new Visografema("$"));
                gridTeclas.add(new Visografema("%"));
                gridTeclas.add(new Visografema("&"));
                gridTeclas.add(new Visografema("*"));
                gridTeclas.add(new Visografema("_"));
                break;

            case Constantes.MOVIMENTOS:
                gridTeclas.add(new Visografema("à"));
                gridTeclas.add(new Visografema("á"));
                gridTeclas.add(new Visografema("â"));
                gridTeclas.add(new Visografema("ã"));
                gridTeclas.add(new Visografema("ä"));
                gridTeclas.add(new Visografema("è"));
                gridTeclas.add(new Visografema("é"));
                gridTeclas.add(new Visografema("ê"));
                gridTeclas.add(new Visografema("ë"));
                gridTeclas.add(new Visografema("ì"));
                gridTeclas.add(new Visografema("í"));
                gridTeclas.add(new Visografema("î"));
                gridTeclas.add(new Visografema("ï"));
                gridTeclas.add(new Visografema("ò"));
                gridTeclas.add(new Visografema("ó"));
                gridTeclas.add(new Visografema("ô"));
                gridTeclas.add(new Visografema("õ"));
                gridTeclas.add(new Visografema("ö"));
                gridTeclas.add(new Visografema("ù"));
                gridTeclas.add(new Visografema("ú"));
                gridTeclas.add(new Visografema("û"));
                gridTeclas.add(new Visografema("ü"));
                gridTeclas.add(new Visografema("À"));
                gridTeclas.add(new Visografema("Á"));
                gridTeclas.add(new Visografema("Â"));
                gridTeclas.add(new Visografema("Ã"));
                gridTeclas.add(new Visografema("Ä"));
                gridTeclas.add(new Visografema("È"));
                gridTeclas.add(new Visografema("É"));
                gridTeclas.add(new Visografema("Ê"));
                gridTeclas.add(new Visografema("Ë"));
                gridTeclas.add(new Visografema("Ì"));
                gridTeclas.add(new Visografema("Í"));
                gridTeclas.add(new Visografema("Î"));
                gridTeclas.add(new Visografema("Ï"));
                gridTeclas.add(new Visografema("Ò"));
                gridTeclas.add(new Visografema("Ó"));
                gridTeclas.add(new Visografema("Ô"));
                gridTeclas.add(new Visografema("Õ"));
                gridTeclas.add(new Visografema("Ö"));
                gridTeclas.add(new Visografema("Ù"));
                gridTeclas.add(new Visografema("Ú"));
                gridTeclas.add(new Visografema("Û"));
                gridTeclas.add(new Visografema("Ü"));
                break;

            case Constantes.PONTUACAO:
                gridTeclas.add(new Visografema("/"));
                gridTeclas.add(new Visografema("b"));
                gridTeclas.add(new Visografema("-"));
                gridTeclas.add(new Visografema(":"));
                gridTeclas.add(new Visografema("."));
                gridTeclas.add(new Visografema(","));
                gridTeclas.add(new Visografema("?"));
                gridTeclas.add(new Visografema("!"));
                gridTeclas.add(new Visografema("Ý"));
                gridTeclas.add(new Visografema("ý"));
                gridTeclas.add(new Visografema("("));
                gridTeclas.add(new Visografema(")"));
                break;

            case Constantes.NUMEROS:
                gridTeclas.add(new Visografema("0"));
                gridTeclas.add(new Visografema("1"));
                gridTeclas.add(new Visografema("2"));
                gridTeclas.add(new Visografema("3"));
                gridTeclas.add(new Visografema("4"));
                gridTeclas.add(new Visografema("5"));
                gridTeclas.add(new Visografema("6"));
                gridTeclas.add(new Visografema("7"));
                gridTeclas.add(new Visografema("8"));
                gridTeclas.add(new Visografema("9"));
                break;

        }
    }
}