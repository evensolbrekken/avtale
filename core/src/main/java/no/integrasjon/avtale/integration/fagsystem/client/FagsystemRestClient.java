package no.integrasjon.avtale.integration.fagsystem.client;

import no.integrasjon.avtale.api.v1.OpprettAvtaleKundePayload;
import no.integrasjon.avtale.model.Avtale;
import no.integrasjon.avtale.types.AvtaleStatus;
import org.springframework.stereotype.Service;

@Service
public class FagsystemRestClient {

    /**
     * Opprettelse av ny kunde.
     * Forventer at fagsystem enten oppretter eller returnerer eksisterende kunde?
     *
     * @param kunde Kundeinformasjon.
     * @return Kundenummer.
     */
    public String opprettKunde(final OpprettAvtaleKundePayload kunde) {
        return "1000" + kunde.getFodselsnummer();
    }

    /**
     * Opprettelse av ny avtale.
     * Forventer at fagsystem enten oppretter eller returnerer avtalenummer for eksisterende avtale
     * hvis eksakt samme parametere finnes på eksisterende avtale?
     *
     * @param avtale Avtalen som skal opprettes.
     * @return Avtalenummer.
     */
    public String opprettAvtale(final Avtale avtale) {
        return "1000000001";
    }

    /**
     * Oppdater status på avtale.
     *
     * @param avtalenummer Avtalenummer for avtalen som skal få ny status.
     * @param status Ny status for avtale med gitt avtalenummer.
     * @return AvtaleStatus.
     */
    public AvtaleStatus oppdaterAvtaleStatus(final String avtalenummer, final AvtaleStatus status) {
        return status;
    }
}
