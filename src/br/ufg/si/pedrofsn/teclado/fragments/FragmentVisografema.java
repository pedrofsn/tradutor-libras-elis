package br.ufg.si.pedrofsn.teclado.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import br.ufg.si.pedrofsn.R;
import br.ufg.si.pedrofsn.teclado.Constantes;
import br.ufg.si.pedrofsn.teclado.adapters.AdapterTecla;
import br.ufg.si.pedrofsn.teclado.models.Visografema;

public class FragmentVisografema extends Fragment {

	private GridView gridView;
	private AdapterTecla customGridAdapter;
	private List<Visografema> gridArray;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.adapter_conjunto_teclas,
				container, false);

		gridArray = new ArrayList<Visografema>();

		Bundle args = getArguments();

		setVisografemasNoGrupoAtual(args.getInt("numeroGrupoDeVisografemas"));

		gridView = (GridView) rootView.findViewById(R.id.gridViewTeclas);

		customGridAdapter = new AdapterTecla(getActivity(),
				R.layout.adapter_tecla, gridArray);

		gridView.setAdapter(customGridAdapter);

		return rootView;
	}

	private void setVisografemasNoGrupoAtual(int numeroGrupoDeVisografemas) {
		// Sem este IF, o array continua a ser populado por novas "teclas", mas
		// sem renderizar na tela.
		if (gridArray.size() >= 0) {
			gridArray.clear();
		}

		switch (numeroGrupoDeVisografemas) {

		case Constantes.CONFIGURACOES_DE_DEDO_POLEGAR:
			gridArray.add(new Visografema("q"));
			gridArray.add(new Visografema("w"));
			gridArray.add(new Visografema("e"));
			gridArray.add(new Visografema("r"));
			gridArray.add(new Visografema("t"));
			gridArray.add(new Visografema("y"));
			break;

		case Constantes.CONFIGURACOES_DE_DEDO_DEMAIS_DEDOS:
			gridArray.add(new Visografema("u"));
			gridArray.add(new Visografema("i"));
			gridArray.add(new Visografema("o"));
			gridArray.add(new Visografema("p"));
			gridArray.add(new Visografema("¹"));
			gridArray.add(new Visografema("²"));
			gridArray.add(new Visografema("a"));
			gridArray.add(new Visografema("s"));
			gridArray.add(new Visografema("d"));
			gridArray.add(new Visografema("³"));
			gridArray.add(new Visografema("£"));
			gridArray.add(new Visografema("f"));
			gridArray.add(new Visografema("g"));
			gridArray.add(new Visografema("h"));
			gridArray.add(new Visografema("¢"));
			gridArray.add(new Visografema("¬"));
			// R ?
			gridArray.add(new Visografema("j"));
			// S ?
			gridArray.add(new Visografema("k"));
			break;

		case Constantes.ORIENTACAO_DA_PALMA:
			gridArray.add(new Visografema("l"));
			gridArray.add(new Visografema("ç"));
			gridArray.add(new Visografema("z"));
			gridArray.add(new Visografema("x"));
			gridArray.add(new Visografema("c"));
			gridArray.add(new Visografema("v"));
			break;

		case Constantes.PONTOS_DE_ARTICULACAO_CABECA:
			gridArray.add(new Visografema("Q"));
			gridArray.add(new Visografema("W"));
			gridArray.add(new Visografema("E"));
			gridArray.add(new Visografema("R"));
			gridArray.add(new Visografema("T"));
			gridArray.add(new Visografema("Y"));
			gridArray.add(new Visografema("U"));
			gridArray.add(new Visografema("I"));
			gridArray.add(new Visografema("O"));
			gridArray.add(new Visografema("P"));
			gridArray.add(new Visografema("A"));
			gridArray.add(new Visografema("S"));
			gridArray.add(new Visografema("D"));
			gridArray.add(new Visografema("F"));
			gridArray.add(new Visografema("G"));
			break;

		case Constantes.PONTOS_DE_ARTICULACAO_TRONCO:
			gridArray.add(new Visografema("H"));
			gridArray.add(new Visografema("J"));
			gridArray.add(new Visografema("K"));
			gridArray.add(new Visografema("L"));
			gridArray.add(new Visografema("Ç"));
			break;

		case Constantes.PONTOS_DE_ARTICULACAO_MEMBROS:
			gridArray.add(new Visografema("\\"));
			gridArray.add(new Visografema("Z"));
			gridArray.add(new Visografema("X"));
			gridArray.add(new Visografema("C"));
			gridArray.add(new Visografema("V"));
			gridArray.add(new Visografema("B"));
			gridArray.add(new Visografema("N"));
			gridArray.add(new Visografema("M"));
			break;

		case Constantes.PONTOS_DE_ARTICULACAO_MAO:
			gridArray.add(new Visografema("@"));
			gridArray.add(new Visografema("#"));
			gridArray.add(new Visografema("$"));
			gridArray.add(new Visografema("%"));
			gridArray.add(new Visografema("&"));
			gridArray.add(new Visografema("*"));
			gridArray.add(new Visografema("_"));
			break;

		case Constantes.MOVIMENTOS_EXTERNOS:
			gridArray.add(new Visografema("à"));
			gridArray.add(new Visografema("á"));
			gridArray.add(new Visografema("â"));
			gridArray.add(new Visografema("ã"));
			gridArray.add(new Visografema("ä"));
			gridArray.add(new Visografema("è"));
			gridArray.add(new Visografema("é"));
			gridArray.add(new Visografema("ê"));
			gridArray.add(new Visografema("ë"));
			gridArray.add(new Visografema("ì"));
			gridArray.add(new Visografema("í"));
			gridArray.add(new Visografema("î"));
			gridArray.add(new Visografema("ï"));
			gridArray.add(new Visografema("ò"));
			gridArray.add(new Visografema("ó"));
			gridArray.add(new Visografema("ô"));
			gridArray.add(new Visografema("õ"));
			gridArray.add(new Visografema("ö"));
			gridArray.add(new Visografema("ù"));
			gridArray.add(new Visografema("ú"));
			break;

		case Constantes.MOVIMENTOS_EXTERNOS_MOVIMENTOS_INTERNOS:
			gridArray.add(new Visografema("û"));
			gridArray.add(new Visografema("ü"));
			gridArray.add(new Visografema("À"));
			gridArray.add(new Visografema("Á"));
			gridArray.add(new Visografema("Â"));
			gridArray.add(new Visografema("Ã"));
			gridArray.add(new Visografema("Ä"));
			gridArray.add(new Visografema("È"));
			gridArray.add(new Visografema("É"));
			gridArray.add(new Visografema("Ê"));
			gridArray.add(new Visografema("Ë"));
			gridArray.add(new Visografema("Ì"));
			break;

		case Constantes.MOVIMENTOS_EXTERNOS_MOVIMENTOS_SEM_AS_MAOS:
			gridArray.add(new Visografema("Í"));
			gridArray.add(new Visografema("Î"));
			gridArray.add(new Visografema("Ï"));
			gridArray.add(new Visografema("Ò"));
			gridArray.add(new Visografema("Ó"));
			gridArray.add(new Visografema("Ô"));
			gridArray.add(new Visografema("Õ"));
			gridArray.add(new Visografema("Ö"));
			gridArray.add(new Visografema("Ù"));
			gridArray.add(new Visografema("Ú"));
			gridArray.add(new Visografema("Û"));
			gridArray.add(new Visografema("Ü"));
			break;

		case Constantes.DIACRITICOS:
			gridArray.add(new Visografema("n"));
			gridArray.add(new Visografema("m"));
			gridArray.add(new Visografema("<"));
			gridArray.add(new Visografema(">"));
			gridArray.add(new Visografema("§"));
			break;

		case Constantes.PONTUACAO:
			gridArray.add(new Visografema("/"));
			gridArray.add(new Visografema("b"));
			gridArray.add(new Visografema("-"));
			gridArray.add(new Visografema(":"));
			gridArray.add(new Visografema("."));
			gridArray.add(new Visografema(","));
			gridArray.add(new Visografema("?"));
			gridArray.add(new Visografema("!"));
			gridArray.add(new Visografema("Ý"));
			gridArray.add(new Visografema("ý"));
			gridArray.add(new Visografema("("));
			gridArray.add(new Visografema(")"));
			break;

		case Constantes.NUMEROS:
			gridArray.add(new Visografema("1"));
			gridArray.add(new Visografema("2"));
			gridArray.add(new Visografema("3"));
			gridArray.add(new Visografema("4"));
			gridArray.add(new Visografema("5"));
			gridArray.add(new Visografema("6"));
			gridArray.add(new Visografema("7"));
			gridArray.add(new Visografema("8"));
			gridArray.add(new Visografema("9"));
			gridArray.add(new Visografema("0"));
			break;
		}
	}
}