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
import br.ufg.si.pedrofsn.teclado.models.Visografema;

public class AdapterTecla extends ArrayAdapter<Visografema> {

	private Context context;
	private int layoutResourceId;
	private List<Visografema> data;
	private OnClickListener onClickListener;

	public AdapterTecla(Context context, int layoutResourceId, List<Visografema> gridArray, OnClickListener onClickListener) {
		super(context, layoutResourceId, gridArray);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = gridArray;
		this.onClickListener = onClickListener;
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

		Visografema item = data.get(position);
		holder.buttonTecla.setText(Html.fromHtml(item.getValorElis()));
		holder.buttonTecla.setContentDescription(item.getValorElis());
		holder.buttonTecla.setOnClickListener(onClickListener);

		Utils.aplicarFonteElis(getContext(), holder.buttonTecla);

		return viewTecla;

	}

	static class ViewHolder {
		Button buttonTecla;

	}
}