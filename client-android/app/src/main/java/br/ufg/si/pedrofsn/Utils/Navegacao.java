package br.ufg.si.pedrofsn.Utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Navegacao {

    // SETAR FRAGMENTS INICIAS SEM ADICIONAR AO BACKSTACK - PILHA
    public static void showFragmentInicial(Fragment frag, FragmentManager fm, String tag, int idLayout) {
        if (fm.findFragmentByTag(tag) == null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(idLayout, frag, tag);
            ft.commit();
        }
    }

    public static void replaceFragment(Fragment frag, FragmentManager fm, String tag, int idLayout) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(idLayout, frag, tag);
        ft.commit();
    }

    public static void detachFragment(Fragment frag, FragmentManager fm) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.detach(frag);
        ft.commit();
    }

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
    public static void removeFragment(FragmentManager fm, String tag) {
        fm.popBackStackImmediate(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
