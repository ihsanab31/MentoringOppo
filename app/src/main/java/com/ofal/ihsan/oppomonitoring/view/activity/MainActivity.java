package com.ofal.ihsan.oppomonitoring.view.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.OnClick;

public class MainActivity extends NormalActivity implements LocationListener {
    private static final String TAG1 = MainActivity.class.getSimpleName();
    String nik, nama, hari, lat, lng, alamat, date;
    SharedPreferences preference;
    Double latitude, longitude;
    Criteria criteria;
    Location location;
    String provider;
    LocationManager locationManager;
    SessionManager session;
    @Override
    protected int getActivityView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionManager(getApplicationContext());
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        iniGPS();
        initData();
        lokasi();
    }

    @Override
    protected boolean isActionBarEnable() {
        return false;
    }


    private void register() {
        showProgressDialog("Pushing Data...");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BaseUrl.URL_BASE + Constant.URL_MONITOR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG1, "Response: " + response.toString());

                        try {
                            JSONObject jObj = new JSONObject(response);
                            Constant.success = jObj.getInt(Constant.TAG_SUCCESS);
                            if (Constant.success == 1) {
                                Log.d("v Add", jObj.toString());
                                Toast.makeText(getApplicationContext(), jObj.getString(Constant.TAG_MESSAGE), Toast.LENGTH_SHORT).show();
                                date = "";
                                startActivity(new Intent(getApplicationContext(), FingerPrintActivity.class));
                                finish();
                            } else {
                                dismissProgressDialog();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        dismissProgressDialog();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dismissProgressDialog();
                        Toast.makeText(getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
                        Log.d(TAG1, error.getMessage().toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(Constant.NIK, nik);
                params.put(Constant.NAMA, nama);
                params.put(Constant.HARI, date);
                params.put(Constant.LAT, String.valueOf(latitude));
                params.put(Constant.LONG, String.valueOf(longitude));
                params.put(Constant.ALAMAT, alamat);


                Log.d(TAG1, "" + params);
                return params;
            }
        };

        AppMentoring.getInstance().addToRequestQueue(stringRequest, Constant.tag_json_obj);
    }

    @SuppressLint("SimpleDateFormat")
    public void initData() {
        preference = getApplicationContext().getSharedPreferences("data", 0);
        nik = preference.getString("nip", null);
        nama = preference.getString("nama", null);
        alamat = preference.getString("alamat", null);
        date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        Log.e(TAG, "User location latitude:" + latitude + ", longitude:" + longitude);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    private void iniGPS() {
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.gps_disabled_message)
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(intent);
                            lokasi();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    @SuppressLint("MissingPermission")
    private void lokasi() {
        location = locationManager.getLastKnownLocation(provider);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        locationManager.requestLocationUpdates(provider, 10000, 3, this);

        if (location != null) {
            onLocationChanged(location);
            register();
        } else {
            Toast.makeText(getApplicationContext(), "Lokasi Tidak Ditemukan", Toast.LENGTH_SHORT).show();
        }


    }

    @OnClick({R.id.iv_menu, R.id.ic_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_menu:
                logoutUser();
                break;
            case R.id.ic_menu:
                startActivity(new Intent(getApplicationContext(), FingerPrintActivity.class));
                break;
        }
    }
    private void logoutUser() {
        session.logoutUser();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}
