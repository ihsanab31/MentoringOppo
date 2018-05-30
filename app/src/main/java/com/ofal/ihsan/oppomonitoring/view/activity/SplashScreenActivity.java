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
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.ofal.ihsan.oppomonitoring.R;
import com.ofal.ihsan.oppomonitoring.util.pref.SessionManager;
import com.ofal.ihsan.oppomonitoring.util.receiver.AlarmManagerUtil;
import com.ofal.ihsan.oppomonitoring.view.base.NoActionBarConfig;
import com.ofal.ihsan.oppomonitoring.view.base.NormalActivity;

import butterknife.BindView;

public class SplashScreenActivity extends NormalActivity implements Animation.AnimationListener, LocationListener {
    Animation animBlink;
    SessionManager session;
    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    int intervalsplash = 3000;
    SharedPreferences preference;
    Criteria criteria;
    Location location;
    String provider;
    Double latitude, longitude;
    LocationManager locationManager;
    @Override
    protected int getActivityView() {
        return R.layout.activity_splash_screen;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlarmManagerUtil alarmManagerUtil = new AlarmManagerUtil();
        alarmManagerUtil.initAlarmNotification(this);
        NoActionBarConfig noActionBarConfig = new NoActionBarConfig();
        noActionBarConfig.fullScreen(this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        iniGPS();
        lokasi();
        session = new SessionManager(getApplicationContext());
        animBlink = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);
        ivLogo.startAnimation(animBlink);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String username;
                preference = getApplicationContext().getSharedPreferences("BelajarPref", 0);
                username = preference.getString("username", null);
                session = new SessionManager(getApplicationContext());
                if (session.isLoggedIn()) {
                    if (username.equals("eko")) {
                        startActivity(new Intent(getApplicationContext(), MenuAdminActivity.class));
                        finish();
                    } else {
                        startActivity(new Intent(getApplicationContext(), FingerPrintActivity.class));
                        finish();
                    }
                } else {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }


            }

        }, intervalsplash);
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        // Take any action after completing the animation

        // check for blink animation
        if (animation == animBlink) {
            Log.d("Sukses", "Sukses");
        }

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    protected boolean isActionBarEnable() {
        return false;
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
        } else {
            Toast.makeText(getApplicationContext(), "Lokasi Tidak Ditemukan", Toast.LENGTH_SHORT).show();
        }


    }
}
