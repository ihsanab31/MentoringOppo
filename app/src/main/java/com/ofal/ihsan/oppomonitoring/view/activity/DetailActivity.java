package com.ofal.ihsan.oppomonitoring.view.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ofal.ihsan.oppomonitoring.R;
import com.ofal.ihsan.oppomonitoring.view.base.NormalActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class DetailActivity extends NormalActivity implements OnMapReadyCallback {
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.ic_menu)
    ImageView icMenu;
    @BindView(R.id.search_container)
    RelativeLayout searchContainer;
    @BindView(R.id.txt_nik_detail)
    TextView txtNikDetail;
    @BindView(R.id.txt_nama_detail)
    TextView txtNamaDetail;
    @BindView(R.id.txt_tanggal_detail)
    TextView txtTanggalDetail;
    @BindView(R.id.txt_alamat_detail)
    TextView txtAlamatDetail;
    String nik, nama, alamat;
    double lat = 0, lng = 0;
    GoogleMap googleMap;
    @Override
    protected int getActivityView() {
        return R.layout.activity_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_detail);
        mapFragment.getMapAsync(this);
        initData();
        }

    @Override
    protected boolean isActionBarEnable() {
        return false;
    }

    @OnClick(R.id.btn_selesai)
    public void onViewClicked() {
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        LatLng mallLocation = new LatLng(lat, lng);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mallLocation, 15f));
        MarkerOptions markerOptions = new MarkerOptions();
        googleMap.addMarker(markerOptions.position(mallLocation)
                .title("Alamat "+alamat)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.man)));
    }

    private void initData(){
        nik = getBundle().getString("nik");
        nama = getBundle().getString("nama");
        alamat = getBundle().getString("alamat");
        lat = getBundle().getDouble("lat");
        lng = getBundle().getDouble("long");
        txtNikDetail.setText(nik);
        txtNamaDetail.setText(nama);
        txtAlamatDetail.setText(alamat);
    }

}
