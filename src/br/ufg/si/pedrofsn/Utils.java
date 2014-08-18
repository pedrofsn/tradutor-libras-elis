package br.ufg.si.pedrofsn;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Utils {

	public static boolean isConectado(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		return networkInfo != null && networkInfo.isConnected() ? true : false;
	}

	public static void aplicarFonteElis(Context context, View view) {
		if (context != null && view != null) {
			Typeface fonteElis = Typeface.createFromAsset(context.getAssets(), "elis.ttf");

			if (view instanceof TextView) {
				((TextView) view).setTypeface(fonteElis);
			} else if (view instanceof EditText) {
				((EditText) view).setTypeface(fonteElis);
			} else if (view instanceof Button) {
				((Button) view).setTypeface(fonteElis);
			}
		}
	}

}
