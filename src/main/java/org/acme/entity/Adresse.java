package org.acme.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
// https://github.com/cdi-spec/cdi-spec.org/blob/master/_faq/integration/201-why-is-veto-a-best-practice-for-persistent-jpa-entities.asciidoc
//@Vetoed
//@Embeddable
public class Adresse implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
//    @Column(name = "a_id", nullable = false)
    private Long id;

    @Version
    private Long version;

    private String plz;
    private String ort;
    private String straße;
    private String hausnr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Adresse() {
    }

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
