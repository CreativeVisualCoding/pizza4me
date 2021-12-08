package org.acme.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQuery(name = "AlleKunden",query ="select s from Kunde s")
public class Kunde implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Version
    private Long version;
    private String vorname;
    private String nachname;
    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="a_id")
    private Adresse adresse;

    public Kunde() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
}
