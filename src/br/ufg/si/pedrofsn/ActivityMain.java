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
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import br.ufg.si.pedrofsn.AsyncTaskPOST.InterfaceAsyncTaskPostCallback;
import br.ufg.si.pedrofsn.model.ELiS;
import br.ufg.si.pedrofsn.teclado.adapters.AdapterViewPager;

public class ActivityMain extends FragmentActivity implements InterfaceAsyncTaskPostCallback, OnClickListener {

	private AdapterViewPager adapterViewPager;

	private ViewPager viewPager;

	private TextView textViewDe;
	private LinearLayout linearLayoutTrocaLinguagem;
	private TextView textViewPara;
	private ImageView imageViewTraduzir;
	private TextView textViewTeste;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		textViewDe = (TextView) findViewById(R.id.textViewDe);
		linearLayoutTrocaLinguagem = (LinearLayout) findViewById(R.id.linearLayoutTrocaLinguagem);
		textViewPara = (TextView) findViewById(R.id.textViewPara);
		imageViewTraduzir = (ImageView) findViewById(R.id.imageViewTraduzir);
		textViewTeste = (TextView) findViewById(R.id.textViewTeste);
		viewPager = (ViewPager) findViewById(R.id.viewPager);

		adapterViewPager = new AdapterViewPager(this, getSupportFragmentManager());

		linearLayoutTrocaLinguagem.setOnClickListener(this);
		imageViewTraduzir.setOnClickListener(this);
		viewPager.setAdapter(adapterViewPager);

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

		Utils.aplicarFonteElis(this, textViewTeste);
		textViewTeste.setText(retornoDoServidor);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.imageViewTraduzir:
			AsyncTaskPOST httpAsyncTask = new AsyncTaskPOST(this, new ELiS("elis", "pt-br", "casa"));
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
		} else {
			textViewDe.setText(getString(R.string.portugues));
			textViewPara.setText(getString(R.string.elis));
		}

	}

}
