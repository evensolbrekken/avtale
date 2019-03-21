package no.integrasjon.avtale.integration.fagsystem;

import no.integrasjon.avtale.api.v1.OpprettAvtaleKundePayload;
import no.integrasjon.avtale.integration.fagsystem.client.FagsystemRestClient;
import no.integrasjon.avtale.model.Avtale;
import no.integrasjon.avtale.types.AvtaleStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FagsystemIntegrationService {

    private final FagsystemRestClient fagsystemRestClient;


    @Autowired
    public FagsystemIntegrationService(final FagsystemRestClient fagsystemRestClient) {
        this.fagsystemRestClient = fagsystemRestClient;
    }

    public String opprettKunde(final OpprettAvtaleKundePayload kunde) {
        return this.fagsystemRestClient.opprettKunde(kunde);
    }

    public String opprettAvtale(final Avtale avtale) {
        return this.fagsystemRestClient.opprettAvtale(avtale);
    }

    public AvtaleStatus oppdaterStatus(final String avtalenummer, final AvtaleStatus status) {
        return this.fagsystemRestClient.oppdaterAvtaleStatus(avtalenummer, status);
    }
}
