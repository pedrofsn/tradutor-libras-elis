package br.ufg.si.pedrofsn.teclado.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import br.ufg.si.pedrofsn.R;
import br.ufg.si.pedrofsn.Utils;
import br.ufg.si.pedrofsn.teclado.interfaces.IOnClick;
import br.ufg.si.pedrofsn.teclado.models.Visografema;

public class AdapterTecla extends ArrayAdapter<Visografema> {

	private Context context;
	private int layoutResourceId;
	private List<Visografema> data;
	private IOnClick callback;

	public AdapterTecla(Context context, int layoutResourceId, List<Visografema> gridArray) {
		super(context, layoutResourceId, gridArray);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = gridArray;
		this.callback = (IOnClick) context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View viewTecla = convertView;
		ViewHolder holder = null;

		if (viewTecla == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			viewTecla = inflater.inflate(layoutResourceId, parent, false);

			holder = new ViewHolder();

			holder.buttonTecla = (Button) viewTecla.findViewById(R.id.buttonTecla);

			viewTecla.setTag(holder);
		} else {
			holder = (ViewHolder) viewTecla.getTag();
		}

		final Visografema visografemaAtual = data.get(position);
		holder.buttonTecla.setText(Html.fromHtml(visografemaAtual.getValorElis()));
		holder.buttonTecla.setContentDescription(visografemaAtual.getValorElis());
		holder.buttonTecla.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				callback.getVisografemaClicado(visografemaAtual);
			}
		});

		Utils.aplicarFonteElis(getContext(), holder.buttonTecla);

		return viewTecla;

	}

	static class ViewHolder {
		Button buttonTecla;

	}

}