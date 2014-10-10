package br.ufg.si.pedrofsn.teclado.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.List;

import br.ufg.si.pedrofsn.R;
import br.ufg.si.pedrofsn.Utils.Utils;
import br.ufg.si.pedrofsn.teclado.interfaces.IElisKeyboard;
import br.ufg.si.pedrofsn.teclado.models.Visografema;

public class AdapterTecla extends ArrayAdapter<Visografema> {

    private Context context;
    private int layoutResourceId;
    private List<Visografema> data;
    private IElisKeyboard callback;

    public AdapterTecla(Context context, int layoutResourceId, List<Visografema> gridArray) {
        super(context, layoutResourceId, gridArray);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = gridArray;
        this.callback = (IElisKeyboard) context;
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
        holder.buttonTecla.setText(visografemaAtual.getValorElis());
        holder.buttonTecla.setContentDescription(visografemaAtual.getValorElis());
        holder.buttonTecla.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.getVisografemaClicado(new Visografema(visografemaAtual.getValorElis()));
            }
        });

        Utils.aplicarFonteElis(getContext(), holder.buttonTecla);

        return viewTecla;

    }

    static class ViewHolder {
        Button buttonTecla;
    }

}