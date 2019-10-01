package br.ufg.si.pedrofsn.teclado.adapters;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import br.ufg.si.pedrofsn.R;
import br.ufg.si.pedrofsn.teclado.Constantes;
import br.ufg.si.pedrofsn.teclado.fragments.FragmentConjuntoTeclas;

public class AdapterViewPager extends FragmentStatePagerAdapter {

    private final int QUANTIDADE_DE_GRUPOS = 6;
    private Context context;

    public AdapterViewPager(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int posicao) {

        Fragment fragment = new FragmentConjuntoTeclas();
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
            case Constantes.PONTUACAO:
                return context.getString(R.string.PONTUACAO);
            case Constantes.NUMEROS:
                return context.getString(R.string.NUMEROS);
        }
    }
}