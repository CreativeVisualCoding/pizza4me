package org.acme.entity;

import javax.enterprise.inject.Vetoed;
import javax.persistence.*;
import java.io.Serializable;

@Entity
// https://github.com/cdi-spec/cdi-spec.org/blob/master/_faq/integration/201-why-is-veto-a-best-practice-for-persistent-jpa-entities.asciidoc
//@Vetoed
//@NamedQuery(name = "AlleKunden", query = "select k from Kunde k")
@NamedQuery(name = "AlleKunden", query = "select k from Kunde k join Adresse a on k.adresse = a")
public class Kunde implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
//    @GeneratedValue(strategy = GenerationType.TABLE)
//    @Column(name = "id", nullable = false)
    private Long id;
    @Version
    private Long version;
    private String vorname;
    private String nachname;
//    @OneToOne
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "a_id")
//    @Embedded
    private Adresse adresse;

    public Kunde() {
//        adresse = new Adresse();
    }

    public Kunde(Long id, String vorname, String nachname, Adresse adresse) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse = adresse;
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
