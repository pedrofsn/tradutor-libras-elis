package br.ufg.si.pedrofsn.teclado;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import br.ufg.si.pedrofsn.R;
import br.ufg.si.pedrofsn.teclado.adapters.AdapterViewPager;

public class FragmentElisKeyboard extends Fragment implements OnClickListener {

	private AdapterViewPager adapterViewPager;
	private ViewPager viewPager;
	private Button buttonSobrescrito;
	private Button buttonEspaco;
	private Button buttonSublinhado;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_elis_keyboard, container, false);
		viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
		buttonSobrescrito = (Button) rootView.findViewById(R.id.buttonSobrescrito);
		buttonEspaco = (Button) rootView.findViewById(R.id.buttonEspaco);
		buttonSublinhado = (Button) rootView.findViewById(R.id.buttonSublinhado);
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
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonSobrescrito:

			break;
		case R.id.buttonEspaco:

			break;
		case R.id.buttonSublinhado:

			break;
		}
	}
}
