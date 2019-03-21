package no.integrasjon.avtale.integration.brevtjeneste.client;

import no.integrasjon.avtale.model.Avtale;
import no.integrasjon.avtale.types.SendeStatus;
import org.springframework.stereotype.Service;

import static no.integrasjon.avtale.types.SendeStatus.SENDT;

@Service
public class BrevtjenesteRestClient {

    /**
     * Sender avtale til kunde via brevtjeneste.
     *
     * @param avtale Avtalen som skal sendes kunde.
     * @return SendeStatus.
     */
    public SendeStatus sendAvtaleTilKunde(final Avtale avtale) {
        return SENDT;
    }
}
