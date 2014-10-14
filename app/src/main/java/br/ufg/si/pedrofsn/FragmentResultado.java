package br.ufg.si.pedrofsn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import br.ufg.si.pedrofsn.Utils.Navegacao;
import br.ufg.si.pedrofsn.teclado.fragments.FragmentElisKeyboard;


/**
 * Created by pedrofsn on 13/10/2014.
 */
public class FragmentResultado extends Fragment implements View.OnClickListener {

    public static final String TAG = "FragmentResultado";

    private TextView textViewResultado;
    private Button buttonOk;

    private String resultado;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        resultado = getArguments().getString("resultado");
        return inflater.inflate(R.layout.fragment_resultado, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textViewResultado = (TextView) view.findViewById(R.id.textViewResultado);
        buttonOk = (Button) view.findViewById(R.id.buttonOk);
    }

    @Override
    public void onStart() {
        super.onStart();
        exibirResultado();
        buttonOk.setOnClickListener(this);
    }

    private void exibirResultado() {
        textViewResultado.setText(resultado != null ? resultado : "Desculpe, mas não conseguimos traduzir.\n:(");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonOk:
                Navegacao.detachFragment(this, getActivity().getSupportFragmentManager());
                Navegacao.replaceFragment(new FragmentElisKeyboard(), getActivity().getSupportFragmentManager(), FragmentElisKeyboard.TAG, R.id.frameLayoutKeyboardElis);
                break;
        }
    }
}
