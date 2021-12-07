package org.acme.entity;

public class Adresse {

    private String plz, ort, straße, hausnr;

    public Adresse(String plz, String ort, String straße, String hausnr) {
        this.plz = plz;
        this.ort = ort;
        this.straße = straße;
        this.hausnr = hausnr;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getStraße() {
        return straße;
    }

    public void setStraße(String straße) {
        this.straße = straße;
    }

    public String getHausnr() {
        return hausnr;
    }

    public void setHausnr(String hausnr) {
        this.hausnr = hausnr;
    }
}
