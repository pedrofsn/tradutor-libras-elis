package br.ufg.si.pedrofsn.teclado;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.ufg.si.pedrofsn.R;
import br.ufg.si.pedrofsn.teclado.adapters.AdapterViewPager;

public class FragmentElisKeyboard extends Fragment {

	private AdapterViewPager adapterViewPager;
	private ViewPager viewPager;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_elis_keyboard, container, false);
		viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
		return rootView;
	}

	@Override
	public void onStart() {
		super.onStart();
		adapterViewPager = new AdapterViewPager(getActivity(), getFragmentManager());
		viewPager.setAdapter(adapterViewPager);
	}
}
