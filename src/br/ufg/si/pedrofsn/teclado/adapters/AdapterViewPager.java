package br.ufg.si.pedrofsn.teclado.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import br.ufg.si.pedrofsn.R;
import br.ufg.si.pedrofsn.teclado.Constantes;
import br.ufg.si.pedrofsn.teclado.fragments.FragmentVisografema;

public class AdapterViewPager extends FragmentStatePagerAdapter {

	private final int QUANTIDADE_DE_GRUPOS = 4;
	private Context context;

	public AdapterViewPager(Context context, FragmentManager fm) {
		super(fm);
		this.context = context;
	}

	@Override
	public Fragment getItem(int posicao) {

		Fragment fragment = new FragmentVisografema();
		Bundle bundle = new Bundle();
		bundle.putInt("numeroGrupoDeVisografemas", posicao);
		fragment.setArguments(bundle);

		return fragment;
	}

	@Override
	public int getCount() {
		return QUANTIDADE_DE_GRUPOS;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
		default: // Constantes.CONFIGURACAO_DE_DEDO
			return context.getString(R.string.configuracao_de_dedo);
		case Constantes.ORIENTACAO_DE_PALMA:
			return context.getString(R.string.orientacao_de_palma);
		case Constantes.PONTOS_DE_ARTICULACAO:
			return context.getString(R.string.pontos_de_articulacao);
		case Constantes.MOVIMENTOS:
			return context.getString(R.string.movimentos);

			// default: Constantes.CONFIGURACOES_DE_DEDO_POLEGAR:
			// return context.getString(R.string.CONFIGURACOES_DE_DEDO_POLEGAR);
			// case Constantes.CONFIGURACOES_DE_DEDO_DEMAIS_DEDOS:
			// return context
			// .getString(R.string.CONFIGURACOES_DE_DEDO_DEMAIS_DEDOS);
			// case Constantes.ORIENTACAO_DA_PALMA:
			// return context.getString(R.string.ORIENTACAO_DA_PALMA);
			// case Constantes.PONTOS_DE_ARTICULACAO_CABECA:
			// return context.getString(R.string.PONTOS_DE_ARTICULACAO_CABECA);
			// case Constantes.PONTOS_DE_ARTICULACAO_TRONCO:
			// return context.getString(R.string.PONTOS_DE_ARTICULACAO_TRONCO);
			// case Constantes.PONTOS_DE_ARTICULACAO_MEMBROS:
			// return context.getString(R.string.PONTOS_DE_ARTICULACAO_MEMBROS);
			// case Constantes.PONTOS_DE_ARTICULACAO_MAO:
			// return context.getString(R.string.PONTOS_DE_ARTICULACAO_MAO);
			// case Constantes.MOVIMENTOS_EXTERNOS:
			// return context.getString(R.string.MOVIMENTOS_EXTERNOS);
			// case Constantes.MOVIMENTOS_EXTERNOS_MOVIMENTOS_INTERNOS:
			// return context
			// .getString(R.string.MOVIMENTOS_EXTERNOS_MOVIMENTOS_INTERNOS);
			// case Constantes.MOVIMENTOS_EXTERNOS_MOVIMENTOS_SEM_AS_MAOS:
			// return context
			// .getString(R.string.MOVIMENTOS_EXTERNOS_MOVIMENTOS_SEM_AS_MAOS);
			// case Constantes.DIACRITICOS:
			// return context.getString(R.string.DIACRITICOS);
			// case Constantes.PONTUACAO:
			// return context.getString(R.string.PONTUACAO);
			// case Constantes.NUMEROS:
			// return context.getString(R.string.NUMEROS);
		}
	}
}