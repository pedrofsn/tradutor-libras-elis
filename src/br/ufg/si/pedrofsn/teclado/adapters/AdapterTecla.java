package br.ufg.si.pedrofsn.teclado.adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import br.ufg.si.pedrofsn.R;
import br.ufg.si.pedrofsn.teclado.models.Visografema;

public class AdapterTecla extends ArrayAdapter<Visografema> {
	Context context;
	int layoutResourceId;
	List<Visografema> data;

	public AdapterTecla(Context context, int layoutResourceId,
			List<Visografema> gridArray) {
		super(context, layoutResourceId, gridArray);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = gridArray;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View viewTecla = convertView;
		ViewHolder holder = null;

		Typeface fonteElis = Typeface.createFromAsset(context.getAssets(),
				"elis.ttf");

		if (viewTecla == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			viewTecla = inflater.inflate(layoutResourceId, parent, false);

			holder = new ViewHolder();
			
			holder.buttonTecla = (Button) viewTecla
					.findViewById(R.id.buttonTecla);
			
			viewTecla.setTag(holder);
		} else {
			holder = (ViewHolder) viewTecla.getTag();
		}

		Visografema item = data.get(position);
		holder.buttonTecla.setText(item.getValorElis());
		holder.buttonTecla.setContentDescription(item.getValorElis());
		holder.buttonTecla.setTypeface(fonteElis);

		return viewTecla;

	}

	static class ViewHolder {
		Button buttonTecla;

	}
}