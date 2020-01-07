package com.example.sensuspenduduk;

public class Sensus {
    private String provinsi, kota, kecamatan, kelurahan;
    private Integer rt, rw, kepala_keluarga, penduduk;

    public Sensus() {
    }

    public Sensus(String provinsi, String kota, String kecamatan, String kelurahan, Integer rt, Integer rw, Integer kepala_keluarga, Integer penduduk) {
        this.provinsi = provinsi;
        this.kota = kota;
        this.kecamatan = kecamatan;
        this.kelurahan = kelurahan;
        this.rt = rt;
        this.rw = rw;
        this.kepala_keluarga = kepala_keluarga;
        this.penduduk = penduduk;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public Integer getRt() {
        return rt;
    }

    public void setRt(Integer rt) {
        this.rt = rt;
    }

    public Integer getRw() {
        return rw;
    }

    public void setRw(Integer rw) {
        this.rw = rw;
    }

    public Integer getKepala_keluarga() {
        return kepala_keluarga;
    }

    public void setKepala_keluarga(Integer kepala_keluarga) {
        this.kepala_keluarga = kepala_keluarga;
    }

    public Integer getPenduduk() {
        return penduduk;
    }

    public void setPenduduk(Integer penduduk) {
        this.penduduk = penduduk;
    }
}
