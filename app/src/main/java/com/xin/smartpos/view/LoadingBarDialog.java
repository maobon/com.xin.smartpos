package com.xin.smartpos.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

import com.xin.smartpos.R;


public class LoadingBarDialog extends ProgressDialog {

    public LoadingBarDialog(Context context) {
        super(context);
    }

    public LoadingBarDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(getContext());
    }

    private void init(Context context) {
        setContentView(R.layout.item_load_dialog);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
    }

    @Override
    public void show() {
        super.show();
    }
}