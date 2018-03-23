package com.zqs.snackbar;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by zqs on 2018/3/23.
 */

public class ColoredSnacbar {
    private static final int red = 0xfff44336;
    private static final int green = 0xff4caf50;
    private static final int blue = 0xff2195f3;
    private static final int orange = 0xffffc107;

    private static View getSnackbarLayout(Snackbar snackbar) {
        if (snackbar != null) {
            return snackbar.getView();
        }
        return null;
    }

    private static Snackbar colorSnackbar(Snackbar snackbar, int coloId) {
        View snackBarView = getSnackbarLayout(snackbar);
        if (snackBarView != null) {
            snackBarView.setBackgroundColor(coloId);
        }
        return snackbar;
    }

    public static Snackbar info(Snackbar snackbar) {
        return colorSnackbar(snackbar,blue);
    }

    public static Snackbar warning(Snackbar snackbar) {
        return colorSnackbar(snackbar,orange);
    }

    public static Snackbar alert(Snackbar snackbar) {
        return colorSnackbar(snackbar,red);
    }

    public static Snackbar confirm(Snackbar snackbar) {
        return colorSnackbar(snackbar, green);
    }
}
