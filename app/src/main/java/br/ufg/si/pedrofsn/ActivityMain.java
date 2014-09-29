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
import android.widget.FrameLayout;
import android.widget.TextView;

import br.ufg.si.pedrofsn.teclado.FragmentElisKeyboard;
import br.ufg.si.pedrofsn.teclado.fragments.FragmentTradutor;
import br.ufg.si.pedrofsn.teclado.interfaces.CallbackTelaFragmentTradutor;
import br.ufg.si.pedrofsn.teclado.interfaces.IElisKeyboard;
import br.ufg.si.pedrofsn.teclado.models.Visografema;

public class ActivityMain extends FragmentActivity implements CallbackTelaFragmentTradutor, IElisKeyboard {

    private FrameLayout frameLayoutKeyboardElis;
    private FrameLayout frameLayoutTelaTradutor;
    private TextView textViewElis;
    private CalculoTamanhoTela calculoTamanhoTela;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayoutTelaTradutor = (FrameLayout) findViewById(R.id.frameLayoutTelaTradutor);
        frameLayoutKeyboardElis = (FrameLayout) findViewById(R.id.frameLayoutKeyboardElis);

        // Inicia com o keyboard-elis fechado
        frameLayoutKeyboardElis.setVisibility(View.GONE);

        calculoTamanhoTela = new CalculoTamanhoTela(this);

        Navegacao.showFragment(new FragmentElisKeyboard(), getSupportFragmentManager(), FragmentElisKeyboard.TAG, R.id.frameLayoutKeyboardElis);
        Navegacao.showFragment(new FragmentTradutor(), getSupportFragmentManager(), FragmentTradutor.TAG, R.id.frameLayoutTelaTradutor);
    }

    public FrameLayout getFrameLayoutKeyboardElis() {
        return frameLayoutKeyboardElis;
    }

    public CalculoTamanhoTela getCalculoTamanhoTela() {
        return calculoTamanhoTela;
    }

    @Override
    public void getVisografemaClicado(Visografema visografema) {
        textViewElis.setText(textViewElis.getText().toString() + visografema.getValorElis());
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

    @Override
    public void getTextViewElis(TextView v) {
        textViewElis = (TextView) v;
    }
}
