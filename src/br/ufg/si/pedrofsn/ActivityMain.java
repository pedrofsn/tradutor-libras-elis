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

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import br.ufg.si.pedrofsn.AsyncTaskPOST.InterfaceAsyncTaskPostCallback;
import br.ufg.si.pedrofsn.model.ELiS;
import br.ufg.si.pedrofsn.teclado.FragmentElisKeyboard;
import br.ufg.si.pedrofsn.teclado.interfaces.IOnClick;
import br.ufg.si.pedrofsn.teclado.models.Visografema;

public class ActivityMain extends FragmentActivity implements InterfaceAsyncTaskPostCallback, OnClickListener, IOnClick {


	private int LINGUAGEM_ATUAL = 0;

	private TextView textViewDe;
	private LinearLayout linearLayoutTrocaLinguagem;
	private FrameLayout frameLayoutKeyboardElis;
	private TextView textViewPara;
	private ImageView imageViewTraduzir;
	private EditText editTextPtBr;
	private TextView textViewElis;

	private ELiS termo;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textViewDe = (TextView) findViewById(R.id.textViewDe);
		frameLayoutKeyboardElis = (FrameLayout) findViewById(R.id.frameLayoutKeyboardElis);
		linearLayoutTrocaLinguagem = (LinearLayout) findViewById(R.id.linearLayoutTrocaLinguagem);
		textViewPara = (TextView) findViewById(R.id.textViewPara);
		imageViewTraduzir = (ImageView) findViewById(R.id.imageViewTraduzir);
		editTextPtBr = (EditText) findViewById(R.id.editTextPtBr);
		textViewElis = (TextView) findViewById(R.id.textViewElis);

		// INICIA A TELA COM O KEBYOARD-ELIS FECHADO
		frameLayoutKeyboardElis.setVisibility(View.GONE);

		CalculoTamanhoTela calculoTamanhoTela = new CalculoTamanhoTela(this);
		int maxHeight = (int) (calculoTamanhoTela.getHeightScreen() * 0.5);
		editTextPtBr.setMaxHeight(maxHeight);
		Log.e("teste", "calculoTamanhoTela.getHeightScreen() = " + calculoTamanhoTela.getHeightScreen());
		Log.e("teste", "maxHeight = " + maxHeight);

		termo = new ELiS();

		linearLayoutTrocaLinguagem.setOnClickListener(this);
		imageViewTraduzir.setOnClickListener(this);
		
		Navegacao.showFragment(new FragmentElisKeyboard(), getSupportFragmentManager(), "teste", R.id.frameLayoutKeyboardElis);
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

			termo.setDe(getLinguagem(1));
			termo.setDe(getLinguagem(0));
			termo.setTermo(getTermoInseridoPeloUsuario());

			AsyncTaskPOST httpAsyncTask = new AsyncTaskPOST(this, termo);
			httpAsyncTask.execute("http://elis2.apiary-mock.com/search");
			break;

		case R.id.linearLayoutTrocaLinguagem:
			chavearLinguagem();
			break;
		}
	}

	private void chavearLinguagem() {
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
		textViewElis.setText(textViewElis.getText().toString() + visografema.getValorElis().toString());
		Utils.aplicarFonteElis(this, textViewElis);
	}

}
