package org.acme.gateway;

import org.acme.entity.Adresse;
import org.acme.entity.Kunde;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@ApplicationScoped
public class KundenRepository {
    // Transiente Speicherung
    private final Map<Long, Kunde> kundenDB = new ConcurrentHashMap<>();
    // TODO: Alle Methoden <methoden-name>Persistent richtig umsetzen
    private final boolean shouldPersistData = true;
    @Inject
    protected EntityManager em;
    private long newID = 1L;


    public KundenRepository() {
    }

    public Kunde kundeAnlegen(Kunde kunde) {
        return shouldPersistData ? kundeAnlegenPersistent(kunde) : kundeAnlegenTransient(kunde);
    }

    private Kunde kundeAnlegenTransient(Kunde kunde) {
        kunde.setId(newID++);
        kundenDB.put(kunde.getId(), kunde);
        return kundenDB.get(kunde.getId());
    }

    private Kunde kundeAnlegenPersistent(Kunde kunde) {
        kunde.setId(newID++);
        em.persist(kunde);
        return kunde;
    }

    public Kunde kundeUpdaten(long id, Kunde kunde) {
        return shouldPersistData ? kundeUpdatenPersistent(id, kunde) : kundeUpdatenTransient(id, kunde);
    }

    private Kunde kundeUpdatenTransient(long id, Kunde kunde) {
        Kunde kundeAmPlatzId = kundenDB.get(id);
        if (kundeAmPlatzId == null) return null;
        kundenDB.put(id, kunde);
        return kundenDB.get(id);
    }

    private Kunde kundeUpdatenPersistent(long id, Kunde kunde) {
        this.em.merge(kunde);
        return kunde;
    }

    public Collection<Kunde> kundenAbfragen() {
        return shouldPersistData ? kundenAbfragenPersistent() : kundenAbfragenTransient();
    }

    private Collection<Kunde> kundenAbfragenTransient() {
        return kundenDB.values();
    }

    private Collection<Kunde> kundenAbfragenPersistent() {
        TypedQuery<Kunde> tmp = em.createNamedQuery("AlleKunden", Kunde.class);
        return tmp.getResultList();
    }

    public Kunde kundeAbfragen(long kundenNr) {
        return shouldPersistData ? kundeAbfragenPersistent(kundenNr) : kundeAbfragenTransient(kundenNr);
    }

    private Kunde kundeAbfragenTransient(long kundenNr) {
        return kundenDB.get(kundenNr);
    }

    private Kunde kundeAbfragenPersistent(long kundenNr) {
        return this.em.find(Kunde.class, kundenNr);
    }

    public Kunde kundeLoeschen(long kundenNr) {
        return shouldPersistData ? kundeLoeschenPersistent(kundenNr) : kundeLoeschenTransient(kundenNr);
    }

    private Kunde kundeLoeschenTransient(long kundenNr) {
        return kundenDB.remove(kundenNr);
    }

    private Kunde kundeLoeschenPersistent(long kundenNr) {
        Kunde kunde = kundeAbfragenPersistent(kundenNr);
        if (kunde == null) throw new NotFoundException();
        em.remove(kunde);
        return kunde;
    }

    public Adresse adresseAnlegen(long kundenNr, Adresse adresse) {
        return shouldPersistData ? adresseAnlegenPersistent(kundenNr, adresse) : adresseAnlegenTransient(kundenNr, adresse);
    }

    private Adresse adresseAnlegenTransient(long kundenNr, Adresse adresse) {
        Kunde kunde = kundeAbfragen(kundenNr);
        if (kunde == null) return null;
        kunde.setAdresse(adresse);
        Kunde updatedKunde = kundeUpdaten(kundenNr, kunde);
        if (updatedKunde == null) return null;
        return kundenDB.get(kundenNr).getAdresse();
    }

    private Adresse adresseAnlegenPersistent(long kundenNr, Adresse adresse) {
        Kunde kunde = kundeAbfragen(kundenNr);
        if (kunde == null) return null;
        kunde.setAdresse(adresse);
        kundeUpdatenPersistent(kunde.getId(), kunde);
        return kunde.getAdresse();
    }

    public Adresse adresseAendern(long kundenNr, Adresse adresse) {
        return shouldPersistData ? adresseAendernPersistent(kundenNr, adresse) : adresseAendernTransient(kundenNr, adresse);
    }

    private Adresse adresseAendernTransient(long kundenNr, Adresse adresse) {
        return adresseAnlegen(kundenNr, adresse);
    }

    private Adresse adresseAendernPersistent(long kundenNr, Adresse adresse) {
        return adresseAnlegen(kundenNr, adresse);
    }

    public Adresse adresseAbfragen(long kundenNr) {
        return shouldPersistData ? adresseAbfragenPersistent(kundenNr) : adresseAbfragenTransient(kundenNr);
    }

    private Adresse adresseAbfragenTransient(long kundenNr) {
        Kunde kunde = kundeAbfragen(kundenNr);
        if (kunde == null) return null;
        return kunde.getAdresse();
    }

    private Adresse adresseAbfragenPersistent(long kundenNr) {
        Kunde kunde = kundeAbfragen(kundenNr);
        if (kunde == null) return null;
        return kunde.getAdresse();
    }

    public Adresse adresseLoeschen(long kundenNr) {
        return shouldPersistData ? adresseLoeschenPersistent(kundenNr) : adresseLoeschenTransient(kundenNr);
    }

    private Adresse adresseLoeschenTransient(long kundenNr) {
        Kunde kunde = kundeAbfragen(kundenNr);
        if (kunde == null) return null;
        Adresse oldAdresse = kunde.getAdresse();
        kunde.setAdresse(null);
        return oldAdresse;
    }

    private Adresse adresseLoeschenPersistent(long kundenNr) {
        Kunde kunde = kundeAbfragen(kundenNr);
        if (kunde == null) return null;
        Adresse oldAdresse = kunde.getAdresse();
        kunde.setAdresse(null);
        kundeUpdatenPersistent(kunde.getId(), kunde);
        return oldAdresse;
    }
}
