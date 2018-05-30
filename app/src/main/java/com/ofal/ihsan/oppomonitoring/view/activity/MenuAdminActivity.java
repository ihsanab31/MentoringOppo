package com.ofal.ihsan.oppomonitoring.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ofal.ihsan.oppomonitoring.R;
import com.ofal.ihsan.oppomonitoring.util.pref.SessionManager;
import com.ofal.ihsan.oppomonitoring.view.base.NormalActivity;

import butterknife.OnClick;

public class MenuAdminActivity extends NormalActivity {
    SessionManager session;
    @Override
    protected int getActivityView() {
        return R.layout.activity_menu_admin;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionManager(getApplicationContext());
        if (!session.isLoggedIn()) {
            logoutUser();
        }
    }

    @Override
    protected boolean isActionBarEnable() {
        return false;
    }


    @OnClick({R.id.iv_menu, R.id.btn_loc, R.id.btn_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_menu:
                logoutUser();
                break;
            case R.id.btn_loc:
                startActivity(new Intent(getApplicationContext(), GeoActivity.class));
                finish();
                break;
            case R.id.btn_list:
                startActivity(new Intent(getApplicationContext(), ListMonitorActivity.class) );
                finish();
                break;
        }
    }
    private void logoutUser() {
        session.logoutUser();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}
