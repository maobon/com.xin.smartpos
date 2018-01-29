package com.xin.smartpos.utils;

import android.content.Context;

import com.xin.smartpos.R;
import com.xin.smartpos.view.LoadingBarDialog;


/**
 * Created by kevin on 17/4/26.
 */

public class LoadingBarHelper {

    private static LoadingBarDialog mDialog;

    public static void showLoadingBar(Context ctx) {
        if (null == mDialog) {
            mDialog = new LoadingBarDialog(ctx, R.style.CustomDialog);
        }
        mDialog.show();
    }

    public static void dismissLoadingBar() {
        if (null != mDialog) {
            mDialog.dismiss();
            mDialog = null;
        }
    }
}
