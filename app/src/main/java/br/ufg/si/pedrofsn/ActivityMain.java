/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.ufg.si.pedrofsn;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import br.ufg.si.pedrofsn.AsyncTaskPOST.InterfaceAsyncTaskPostCallback;
import br.ufg.si.pedrofsn.model.ELiS;
import br.ufg.si.pedrofsn.teclado.FragmentElisKeyboard;
import br.ufg.si.pedrofsn.teclado.interfaces.IElisKeyboard;
import br.ufg.si.pedrofsn.teclado.models.Visografema;

public class ActivityMain extends FragmentActivity implements InterfaceAsyncTaskPostCallback, IElisKeyboard, OnClickListener {

    private int LINGUAGEM_ATUAL = 0;

    private TextView textViewDe;
    private ImageView imageViewTrocaLinguagem;
    private FrameLayout frameLayoutKeyboardElis;
    private TextView textViewPara;
    private ImageView imageViewTraduzir;
    private EditText editTextPtBr;
    private TextView textViewElis;

    private String htmlTextViewElis;

    private Animation animationRotacionar;

    private ELiS tradutorDeTermosBaseadoEmElis;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewDe = (TextView) findViewById(R.id.textViewDe);
        frameLayoutKeyboardElis = (FrameLayout) findViewById(R.id.frameLayoutKeyboardElis);
        imageViewTrocaLinguagem = (ImageView) findViewById(R.id.imageViewTrocaLinguagem);
        textViewPara = (TextView) findViewById(R.id.textViewPara);
        imageViewTraduzir = (ImageView) findViewById(R.id.imageViewTraduzir);
        editTextPtBr = (EditText) findViewById(R.id.editTextPtBr);
        textViewElis = (TextView) findViewById(R.id.textViewElis);

        // Carrega animação
        animationRotacionar = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotacionar);

        // Inicia com o keyboard-elis fechado
        frameLayoutKeyboardElis.setVisibility(View.GONE);

        CalculoTamanhoTela calculoTamanhoTela = new CalculoTamanhoTela(this);
        int maxHeight = (int) (calculoTamanhoTela.getHeightScreen() * 0.5);
        editTextPtBr.setMaxHeight(maxHeight);

        tradutorDeTermosBaseadoEmElis = new ELiS();

        imageViewTrocaLinguagem.setOnClickListener(this);
        imageViewTraduzir.setOnClickListener(this);

        Utils.aplicarFonteElis(this, textViewElis);

        Navegacao.showFragment(new FragmentElisKeyboard(), getSupportFragmentManager(), FragmentElisKeyboard.TAG, R.id.frameLayoutKeyboardElis);
    }

    @Override
    public void postRealizado(String retornoDoServidor) {
        Toast.makeText(this, "Servidor retornou na activity: " + retornoDoServidor, Toast.LENGTH_LONG).show();

        try {
            JSONObject jsonObject = new JSONObject(retornoDoServidor);
            retornoDoServidor = jsonObject.getString("termos");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Utils.aplicarFonteElis(this, editTextPtBr);
        editTextPtBr.setText(retornoDoServidor);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewTraduzir:

                tradutorDeTermosBaseadoEmElis.setDe(getLinguagem(1));
                tradutorDeTermosBaseadoEmElis.setPara(getLinguagem(0));
                tradutorDeTermosBaseadoEmElis.setTermo(getTermoInseridoPeloUsuario());

                AsyncTaskPOST httpAsyncTask = new AsyncTaskPOST(this, tradutorDeTermosBaseadoEmElis);
                httpAsyncTask.execute("http://elis2.apiary-mock.com/search");
                break;

            case R.id.imageViewTrocaLinguagem:
                chavearLinguagem();
                break;
        }
    }

    private void chavearLinguagem() {
        imageViewTrocaLinguagem.startAnimation(animationRotacionar);
        if (textViewDe.getText().toString().equalsIgnoreCase(getString(R.string.portugues))) {
            textViewDe.setText(getString(R.string.elis));
            textViewPara.setText(getString(R.string.portugues));
            textViewElis.setVisibility(View.VISIBLE);
            editTextPtBr.setVisibility(View.GONE);
            frameLayoutKeyboardElis.setVisibility(View.VISIBLE);
            LINGUAGEM_ATUAL = 1;
        } else {
            textViewDe.setText(getString(R.string.portugues));
            textViewPara.setText(getString(R.string.elis));
            textViewElis.setVisibility(View.GONE);
            editTextPtBr.setVisibility(View.VISIBLE);
            frameLayoutKeyboardElis.setVisibility(View.GONE);
            LINGUAGEM_ATUAL = 0;
        }

    }

    private String getLinguagem(int origemOuDestino) {
        return LINGUAGEM_ATUAL == origemOuDestino ? "elis" : "pt-br";
    }

    private String getTermoInseridoPeloUsuario() {
        return LINGUAGEM_ATUAL == 1 ? textViewElis.getText().toString() : editTextPtBr.getText().toString();
    }

    @Override
    public void getVisografemaClicado(Visografema visografema) {
        htmlTextViewElis += textViewElis.getText().toString() + visografema.getValorElis().toString();
        textViewElis.setText(htmlTextViewElis);
    }

    @Override
    public void setAcaoBotaoAdicional() {
        // TODO Setar a ação dos botões adicionais (espaço, x² e _)

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sobre:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(Html.fromHtml("Este aplicativo está sendo desenvolvido por Pedro Francisco de Sousa Neto, graduando em Sistemas de Informação pela Universidade Federal de Goiás (UFG).<br /> <br /> <br /> <li>-Prof. Ms. Marcelo Ricardo Quinta (Orientador)</li> <br /> <li>-Profa. Dra. Mariângela Estelita Barros (Coorientadora)</li>")).setTitle(R.string.sobre).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
