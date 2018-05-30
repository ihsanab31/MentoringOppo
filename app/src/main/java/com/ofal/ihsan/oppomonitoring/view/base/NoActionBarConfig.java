package com.ofal.ihsan.oppomonitoring.view.base;

import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 0878253827096
 * on Sunday, 19-11-2017
 * ------------------------------
 * This class for fullscreen
 */
public class NoActionBarConfig extends AppCompatActivity {
    public NoActionBarConfig() {}

    public void fullScreen(AppCompatActivity appCompatActivity){
        appCompatActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

}
