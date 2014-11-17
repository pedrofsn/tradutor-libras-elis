package br.ufg.si.pedrofsn.teclado.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import br.ufg.si.pedrofsn.R;
import br.ufg.si.pedrofsn.teclado.Constantes;
import br.ufg.si.pedrofsn.teclado.adapters.AdapterViewPager;
import br.ufg.si.pedrofsn.teclado.interfaces.IElisKeyboard;

public class FragmentElisKeyboard extends Fragment implements OnClickListener {

    public static String TAG = "FragmentElisKeyboard";

    private AdapterViewPager adapterViewPager;
    private ViewPager viewPager;
    private Button buttonSobrescrito;
    private Button buttonEspaco;
    private Button buttonSublinhado;
    private Button buttonPontuacao;
    private Button buttonNumeros;
    private Button buttonBackspace;

    private IElisKeyboard callback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_elis_keyboard, container, false);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        buttonSobrescrito = (Button) rootView.findViewById(R.id.buttonSobrescrito);
        buttonEspaco = (Button) rootView.findViewById(R.id.buttonEspaco);
        buttonSublinhado = (Button) rootView.findViewById(R.id.buttonSublinhado);
        buttonPontuacao = (Button) rootView.findViewById(R.id.buttonPontuacao);
        buttonNumeros = (Button) rootView.findViewById(R.id.buttonNumeros);
        buttonBackspace = (Button) rootView.findViewById(R.id.buttonBackspace);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapterViewPager = new AdapterViewPager(getActivity(), getFragmentManager());
        viewPager.setAdapter(adapterViewPager);
        buttonSobrescrito.setOnClickListener(this);
        buttonEspaco.setOnClickListener(this);
        buttonSublinhado.setOnClickListener(this);
        buttonPontuacao.setOnClickListener(this);
        buttonNumeros.setOnClickListener(this);
        buttonBackspace.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSobrescrito:
                Constantes.isSobrescritoPressionado = true;
                break;
            case R.id.buttonEspaco:
                callback.onBotaoEspacoPressionado();
                break;
            case R.id.buttonSublinhado:
                Constantes.isSublinhadoPressionado = true;
                break;
            case R.id.buttonPontuacao:
                viewPager.setCurrentItem(4, true);
                break;
            case R.id.buttonNumeros:
                viewPager.setCurrentItem(5, true);
                break;
            case R.id.buttonBackspace:
                callback.onBotaoBackspacePressionado();
                break;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callback = (IElisKeyboard) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " precisa implementar a interface CallbackTelaFragmentTradutor");
        }
    }
}
