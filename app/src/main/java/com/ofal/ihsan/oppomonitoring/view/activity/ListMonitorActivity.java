package com.ofal.ihsan.oppomonitoring.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.ofal.ihsan.oppomonitoring.AppMentoring;
import com.ofal.ihsan.oppomonitoring.Constant;
import com.ofal.ihsan.oppomonitoring.R;
import com.ofal.ihsan.oppomonitoring.adapter.MentoringAdapter;
import com.ofal.ihsan.oppomonitoring.adapter.model.MonitorModel;
import com.ofal.ihsan.oppomonitoring.api.BaseUrl;
import com.ofal.ihsan.oppomonitoring.view.base.NormalActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class ListMonitorActivity extends NormalActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;
    private ArrayList<MonitorModel> monitorModels;
    private MentoringAdapter mAdapter;
    private static final String TAGG = ListMonitorActivity.class.getSimpleName();

    @Override
    protected int getActivityView() {
        return R.layout.activity_list_monitor;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        swipe.setOnRefreshListener(ListMonitorActivity.this);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        monitorModels = new ArrayList<>();
        mAdapter = new MentoringAdapter(monitorModels, this);
        rvList.setAdapter(mAdapter);
        swipe.post(new Runnable() {
            @Override
            public void run() {
                swipe.setRefreshing(true);
                monitorModels.clear();
                callVolley();
            }
        });
    }

    @Override
    protected boolean isActionBarEnable() {
        return false;
    }

    @Override
    public void onRefresh() {
        monitorModels.clear();
        mAdapter.notifyDataSetChanged();
        callVolley();
    }

    private void callVolley() {
        monitorModels.clear();
        mAdapter.notifyDataSetChanged();
        swipe.setRefreshing(true);
        JsonArrayRequest jArr = new JsonArrayRequest(BaseUrl.URL_BASE + Constant.URL_LIST, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        MonitorModel item = new MonitorModel();
                        item.setNik(obj.getString(Constant.NIK));
                        item.setNama(obj.getString(Constant.NAMA));
                        item.setHari(obj.getString(Constant.HARI));
                        item.setLat(obj.getDouble(Constant.LAT));
                        item.setLng(obj.getDouble(Constant.LONG));
                        item.setAlamat(obj.getString(Constant.ALAMAT));
                        monitorModels.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mAdapter.notifyDataSetChanged();
                swipe.setRefreshing(false);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                swipe.setRefreshing(false);
            }
        });

        AppMentoring.getInstance().addToRequestQueue(jArr);
    }

    @OnClick(R.id.ic_menu)
    public void onViewClicked() {
    startActivity(new Intent(getApplicationContext(), MenuAdminActivity.class));
    finish();
    }

}
