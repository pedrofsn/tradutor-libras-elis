package br.ufg.si.pedrofsn.Utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;

public class CalculoTamanhoTela {
    public final static int versionSDKUser = android.os.Build.VERSION.SDK_INT;
    private Activity activity;
    private int width;
    private int height;

    public CalculoTamanhoTela(Activity activity) {
        this.activity = activity;
    }

    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return px / (metrics.densityDpi / 160f);
    }

    @SuppressWarnings("deprecation")
    public int getPolegadasTela() {
        getWidthScreen();
        getHeightScreen();

        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        double x = Math.pow(width / dm.xdpi, 2);
        double y = Math.pow(height / dm.ydpi, 2);

        return ((int) Math.sqrt(x + y));
    }

    @SuppressLint("NewApi")
    public int getWidthScreen() {
        if (versionSDKUser >= 13) {
            Point size = new Point();
            activity.getWindowManager().getDefaultDisplay().getSize(size);
            width = size.x;
        } else {
            width = activity.getWindowManager().getDefaultDisplay().getWidth();
        }

        return width;
    }

    @SuppressLint("NewApi")
    public int getHeightScreen() {
        if (versionSDKUser >= 13) {
            Point size = new Point();
            activity.getWindowManager().getDefaultDisplay().getSize(size);
            height = size.y;
        } else {
            height = activity.getWindowManager().getDefaultDisplay().getHeight();
        }
        return height;
    }
}
