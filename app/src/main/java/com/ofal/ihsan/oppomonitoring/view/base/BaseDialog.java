package com.ofal.ihsan.oppomonitoring.view.base;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;


import com.ofal.ihsan.oppomonitoring.BaseView;

import butterknife.ButterKnife;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 0878253827096
 * on Sunday, 19-11-2017
 * ------------------------------
 * This class for base dialog
 */
public abstract class BaseDialog extends DialogFragment implements DialogInterface, BaseView {
    protected Dialog dialog;
    protected Context context;
    private ProgressDialog progressDialog;

    public BaseDialog() {

    }

    public BaseDialog(Context context) {
        this.context = context;
        this.progressDialog = new ProgressDialog(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new Dialog(getActivity(), getTheme()) {
            @Override
            public void onBackPressed() {
                if (BaseDialog.this.onBackPressed())
                    dialog.dismiss();
            }
        };
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(isDialogCancelable());
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getDialogView(), container, false);
        ButterKnife.bind(this, view);
        if (savedInstanceState != null)
            onLoadInstance(savedInstanceState);
        onPostInit();
        return view;
    }

    @Override
    public void showProgressDialog(String message) {
        if (context != null && progressDialog == null)
            progressDialog = new ProgressDialog(context);

        if (progressDialog != null && !progressDialog.isShowing()) {
            progressDialog.setMessage(message);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }

    @Override
    public void showProgressDialog(@StringRes int message) {
        showProgressDialog(getString(message));
    }


    @Override
    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    @Override
    public void showMessage(String message) {
        if (context != null)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        else if (getActivity() != null)
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(@StringRes int message) {
        showMessage(getString(message));
    }

    public void show() {
        if (context != null)
            super.show(((AppCompatActivity) context).getSupportFragmentManager(), "");
        else if (getActivity() != null)
            super.show(getActivity().getSupportFragmentManager(), "");
    }

    @Override
    public boolean onBackPressed() {
        return true;
    }
}
