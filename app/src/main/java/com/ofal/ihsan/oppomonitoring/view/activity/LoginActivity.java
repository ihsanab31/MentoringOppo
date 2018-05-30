package com.ofal.ihsan.oppomonitoring.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ofal.ihsan.oppomonitoring.AppMentoring;
import com.ofal.ihsan.oppomonitoring.Constant;
import com.ofal.ihsan.oppomonitoring.R;
import com.ofal.ihsan.oppomonitoring.api.BaseUrl;
import com.ofal.ihsan.oppomonitoring.util.pref.SessionManager;
import com.ofal.ihsan.oppomonitoring.view.base.NormalActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends NormalActivity {

    @BindView(R.id.et_nik)
    EditText username;
    @BindView(R.id.et_password)
    EditText password;
    @BindView(R.id.coordinator_login)
    CoordinatorLayout coordinatorLayout;
    SharedPreferences preference;
    SessionManager session;

    @Override
    protected int getActivityView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionManager(getApplicationContext());
    }

    @Override
    protected boolean isActionBarEnable() {
        return false;
    }

    @OnClick({R.id.masuk, R.id.tanya})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.masuk:
                login(username.getText().toString(), password.getText().toString());
                break;
            case R.id.tanya:
                break;
        }
    }

    private void login(final String username, final String password_login) {
        showProgressDialog("Mengambil Data...");
        StringRequest strReq = new StringRequest(Request.Method.POST, BaseUrl.URL_BASE + Constant.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());
                try {
                    JSONObject jObj = new JSONObject(response);
                    Constant.success = jObj.getInt(Constant.TAG_SUCCESS);

                    // Cek error node pada json
                    if (Constant.success == 1) {
                        Log.d("get edit data", jObj.toString());
                        String nik = jObj.getString(Constant.NIK);
                        String nama = jObj.getString(Constant.NAMA);
                        String no_hp = jObj.getString(Constant.NO_HP);
                        String alamat = jObj.getString(Constant.ALAMAT);
                        preference =
                                getApplicationContext().getSharedPreferences("data", 0);
                        SharedPreferences.Editor editor = preference.edit();
                        editor.putString("nip", nik);
                        editor.putString("nama", nama);
                        editor.putString("no_hp", no_hp);
                        editor.putString("alamat", alamat);
                        editor.commit();
                        dismissProgressDialog();
                        session.createLoginSession(username, password_login);
                        startActivity(new Intent(getApplicationContext(), FingerPrintActivity.class));
                        finish();
                    } else {
                        loginadmin(username, password_login);

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (volleyError instanceof NetworkError) {
                    Snackbar snacka = Snackbar.make(coordinatorLayout, R.string.networkerror, Snackbar.LENGTH_LONG);
                    snacka.show();
                    dismissProgressDialog();
                } else if (volleyError instanceof ServerError) {
                    Snackbar snackb = Snackbar.make(coordinatorLayout, R.string.ServerError, Snackbar.LENGTH_LONG);
                    snackb.show();
                    dismissProgressDialog();
                } else if (volleyError instanceof AuthFailureError) {
                    Snackbar snackc = Snackbar.make(coordinatorLayout, R.string.AuthFailureError, Snackbar.LENGTH_LONG);
                    snackc.show();
                    dismissProgressDialog();
                } else if (volleyError instanceof ParseError) {
                    Snackbar snackd = Snackbar.make(coordinatorLayout, R.string.ParseError, Snackbar.LENGTH_LONG);
                    snackd.show();
                    dismissProgressDialog();
                } else if (volleyError instanceof NoConnectionError) {
                    Snackbar snacke = Snackbar.make(coordinatorLayout, R.string.NoConnectionError, Snackbar.LENGTH_LONG);
                    snacke.show();
                    dismissProgressDialog();
                } else if (volleyError instanceof TimeoutError) {
                    Snackbar snackf = Snackbar.make(coordinatorLayout, R.string.TimeoutError, Snackbar.LENGTH_LONG);
                    snackf.show();
                    dismissProgressDialog();
                }
                dismissProgressDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(Constant.NIK, username);
                params.put(Constant.PASSWORD, password_login);
                return params;
            }

        };
        strReq.setRetryPolicy(new DefaultRetryPolicy(
                120000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppMentoring.getInstance().addToRequestQueue(strReq, Constant.tag_json_obj);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void loginadmin(final String username, final String password_login) {
        StringRequest strReq = new StringRequest(Request.Method.POST, BaseUrl.URL_BASE + Constant.URL_LOGIN_ADMIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());
                try {
                    JSONObject jObj = new JSONObject(response);
                    Constant.success = jObj.getInt(Constant.TAG_SUCCESS);

                    if (Constant.success == 1) {
                        Log.d("get edit data", jObj.toString());
                        session.createLoginSession(username, password_login);
                        startActivity(new Intent(getApplicationContext(), MenuAdminActivity.class));
                        finish();
                        dismissProgressDialog();
                    } else {
                        Snackbar snacka = Snackbar.make(coordinatorLayout, jObj.getString(Constant.TAG_MESSAGE), Snackbar.LENGTH_LONG);
                        snacka.show();
                        dismissProgressDialog();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (volleyError instanceof NetworkError) {
                    Snackbar snacka = Snackbar.make(coordinatorLayout, R.string.networkerror, Snackbar.LENGTH_LONG);
                    snacka.show();
                    dismissProgressDialog();
                } else if (volleyError instanceof ServerError) {
                    Snackbar snackb = Snackbar.make(coordinatorLayout, R.string.ServerError, Snackbar.LENGTH_LONG);
                    snackb.show();
                    dismissProgressDialog();
                } else if (volleyError instanceof AuthFailureError) {
                    Snackbar snackc = Snackbar.make(coordinatorLayout, R.string.AuthFailureError, Snackbar.LENGTH_LONG);
                    snackc.show();
                    dismissProgressDialog();
                } else if (volleyError instanceof ParseError) {
                    Snackbar snackd = Snackbar.make(coordinatorLayout, R.string.ParseError, Snackbar.LENGTH_LONG);
                    snackd.show();
                    dismissProgressDialog();
                } else if (volleyError instanceof NoConnectionError) {
                    Snackbar snacke = Snackbar.make(coordinatorLayout, R.string.NoConnectionError, Snackbar.LENGTH_LONG);
                    snacke.show();
                    dismissProgressDialog();
                } else if (volleyError instanceof TimeoutError) {
                    Snackbar snackf = Snackbar.make(coordinatorLayout, R.string.TimeoutError, Snackbar.LENGTH_LONG);
                    snackf.show();
                    dismissProgressDialog();
                }
                dismissProgressDialog();
            }

        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(Constant.USERNAME, username);
                params.put(Constant.PASSWORD, password_login);
                return params;
            }

        };
        strReq.setRetryPolicy(new DefaultRetryPolicy(
                120000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppMentoring.getInstance().addToRequestQueue(strReq, Constant.tag_json_obj);
    }

}
