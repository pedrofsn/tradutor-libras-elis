package br.ufg.si.pedrofsn;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class Navegacao {

	// METODO PARA EXIBIR FRAGMENT SEM DUPLICALO
	public static void showFragment(Fragment frag, FragmentManager fm, String tag, int idLayout) {
		if (fm.findFragmentByTag(tag) == null) {
			FragmentTransaction ft = fm.beginTransaction();
			ft.replace(idLayout, frag, tag);
			ft.addToBackStack(tag);
			ft.commit();
		} else {
			showFragment(fm, tag);
		}
	}

	// IR PARA O FRAGMENT JÁ INSTANCIADO
	public static void showFragment(FragmentManager fm, String tag) {
		fm.popBackStackImmediate(tag, 0);
	}

	// REMOVE TODOS OS FRAGMENTS ADICIONADOS NA PILHA
	public static void removeAllFragments(FragmentManager fm) {
		for (int i = 0; i < fm.getBackStackEntryCount(); i++) {
			fm.popBackStack();
		}
	}

	// REMOVER UM FRAGMENTO
	public static void removerFragment(FragmentManager fm, String tag) {
		fm.popBackStackImmediate(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
	}
}
