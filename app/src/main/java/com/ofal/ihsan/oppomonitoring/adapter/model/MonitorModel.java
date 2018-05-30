package com.ofal.ihsan.oppomonitoring.adapter.model;


/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 0878253827096
 * on 12/05/2018
 * ------------------------------
 * This class for
 */

public class MonitorModel {
    private String nik;
    private String nama;
    private String hari;
    private Double lat;
    private Double lng;
    private String alamat;
    public  MonitorModel (){

    }
    public MonitorModel(String nik, String nama, String hari, Double lat, Double lng, String alamat) {
        this.nik = nik;
        this.nama = nama;
        this.hari = hari;
        this.lat = lat;
        this.lng = lng;
        this.alamat = alamat;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}
