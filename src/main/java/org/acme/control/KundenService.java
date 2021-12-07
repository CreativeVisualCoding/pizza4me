package org.acme.control;

import org.acme.entity.Adresse;
import org.acme.entity.Kunde;
import org.acme.gateway.KundenRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;

@ApplicationScoped
public class KundenService {

    @Inject
    KundenRepository kundenRepository;

    public Kunde kundeAnlegen(Kunde kunde) {
        return kundenRepository.kundeAnlegen(kunde);
    }

    public Kunde kundeUpdaten(long id, Kunde kunde) {
        return kundenRepository.kundeUpdaten(id, kunde);
    }

    public Collection<Kunde> kundenAbfragen() {
        return kundenRepository.kundenAbfragen();
    }

    public Kunde kundeAbfragen(long kundennr) {
        return kundenRepository.kundeAbfragen(kundennr);
    }

    public Kunde kundeLoeschen(long kundennr) {
        return kundenRepository.kundeLoeschen(kundennr);
    }

    public Adresse adresseAnlegen(long kundennr, Adresse adresse) {
        return kundenRepository.adresseAnlegen(kundennr, adresse);
    }

    public Adresse adresseAendern(long kundenr, Adresse adresse) {
        return kundenRepository.adresseAendern(kundenr, adresse);
    }

    public Adresse adresseAbfragen(long kundennr) {
        return kundenRepository.adresseAbfragen(kundennr);
    }

    public Adresse adresseLoeschen(long kundennr) {
        return kundenRepository.adresseLoeschen(kundennr);
    }
}
