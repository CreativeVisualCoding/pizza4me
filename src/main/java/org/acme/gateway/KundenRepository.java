package org.acme.gateway;

import org.acme.entity.Adresse;
import org.acme.entity.Kunde;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@ApplicationScoped
public class KundenRepository {
    private Map<Long, Kunde> kundenDB = new HashMap<>();
    private long newID = 1L;

    public KundenRepository() {
    }

    public Kunde kundeAnlegen(Kunde kunde) {
        kunde.setId(newID++);
        kundenDB.put(kunde.getId(), kunde);
        return kundenDB.get(kunde.getId());
    }

    public Kunde kundeUpdaten(long id, Kunde kunde) {
        Kunde kundeAmPlatzId = kundenDB.get(id);
        if (kundeAmPlatzId == null) return null;
        kundenDB.put(id, kunde);
        return kundenDB.get(id);
    }

    public Collection<Kunde> kundenAbfragen() {
        return kundenDB.values();
    }

    public Kunde kundeAbfragen(long kundennr) {
        return kundenDB.get(kundennr);
    }

    public Kunde kundeLoeschen(long kundennr) {
        return kundenDB.remove(kundennr);
    }

    public Adresse adresseAnlegen(long kundennr, Adresse adresse) {
        Kunde kunde = kundeAbfragen(kundennr);
        if (kunde == null) return null;
        kunde.setAdresse(adresse);
        Kunde updatedKunde = kundeUpdaten(kundennr, kunde);
        if (updatedKunde == null) return null;
        return kundenDB.get(kundennr).getAdresse();
    }

    public Adresse adresseAendern(long kundenr, Adresse adresse) {
        return adresseAnlegen(kundenr, adresse);
    }

    public Adresse adresseAbfragen(long kundennr) {
        Kunde kunde = kundeAbfragen(kundennr);
        if (kunde == null) return null;
        return kunde.getAdresse();
    }

    public Adresse adresseLoeschen(long kundennr) {
        Kunde kunde = kundeAbfragen(kundennr);
        if (kunde == null) return null;
        Adresse oldAdresse = kunde.getAdresse();
        kunde.setAdresse(null);
        return oldAdresse;
    }
}
