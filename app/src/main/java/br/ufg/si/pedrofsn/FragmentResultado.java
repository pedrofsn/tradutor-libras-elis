package br.ufg.si.pedrofsn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import br.ufg.si.pedrofsn.Utils.Navegacao;
import br.ufg.si.pedrofsn.Utils.Utils;
import br.ufg.si.pedrofsn.teclado.enums.TipoLingua;
import br.ufg.si.pedrofsn.teclado.fragments.FragmentElisKeyboard;
import br.ufg.si.pedrofsn.teclado.models.Termo;
import br.ufg.si.pedrofsn.teclado.models.Visografema;


/**
 * Created by pedrofsn on 13/10/2014.
 */
public class FragmentResultado extends Fragment implements View.OnClickListener {

    public static final String TAG = "FragmentResultado";

    private TextView textViewResultado;
    private Button buttonOk;

    private Termo resultado;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        resultado = (Termo) getArguments().getSerializable("resultado");
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
        String contentResultado = "";

        // Obter o que deseja ser traduzido
        if (resultado != null) {
            if (resultado.getTipoLingua() == TipoLingua.PTBR) {
                contentResultado = (String) resultado.getTermo();
            } else {
                List<Visografema> listaVisografemasDigitados = resultado.getTermo() instanceof List ? (List<Visografema>) resultado.getTermo() : null;
                for (Visografema v : listaVisografemasDigitados) {
                    contentResultado = contentResultado.concat(v.getValorElis());
                }
            }
        }
        /*
        //////////////// MOCK

        String mock = null;
        if (resultado.isExibirResultadoEmElis() == false) {
            if (contentResultado.equalsIgnoreCase("PAIXÃO")) {
                mock = "kzKân";
            } else if (contentResultado.equalsIgnoreCase("COMPUTADOR")) {
                mock = "/qoqlJúnb/tsvJÄm";
            } else if (contentResultado.equalsIgnoreCase("LINDO")) {
                mock = "tgçQÄ";
            } else if (contentResultado.equalsIgnoreCase("TRABALHAR")) {
                mock = "/tgqvJân";
            }

        } else {
            if (contentResultado.equals("q")) {
                mock = "Este é um mock, um exemplo da tradução de ELIS para LIBRAS/PT-BR.\n Caso [1]";
            } else if (contentResultado.equals("l")) {
                mock = "Este é um mock, um exemplo da tradução de ELIS para LIBRAS/PT-BR.\n Caso [2]";
            } else if (contentResultado.equals("Q")) {
                mock = "Este é um mock, um exemplo da tradução de ELIS para LIBRAS/PT-BR.\n Caso [3]";
            }
        }

        if (mock == null) {
            contentResultado = "Não foi encontrado uma tradução para este termo.";
        } else{
            // Se o termo inserido bater com o mock, aplicar o mock
            contentResultado = mock;
        }
        */

        textViewResultado.setText(contentResultado);

        // Aplicar a fonte da ELiS, caso o resultado seja em ELiS
        if (resultado.isExibirResultadoEmElis() == false) {
            Utils.aplicarFonteElis(getActivity(), textViewResultado);
        }

        // Ocultar o teclado ao exibir o card de resultado
        Utils.ocultarTecladoAndroid(getActivity(), textViewResultado);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonOk:
                Navegacao.detachFragment(this, getActivity().getSupportFragmentManager());
                Navegacao.replaceFragment(new FragmentElisKeyboard(), getActivity().getSupportFragmentManager(), FragmentElisKeyboard.TAG, R.id.frameLayoutKeyboardElis);

                if (resultado.getTipoLingua() == TipoLingua.PTBR) {
                    ((ActivityMain) getActivity()).getElisKeyboard().setVisibility(View.INVISIBLE);
                }

                break;
        }
    }
}
