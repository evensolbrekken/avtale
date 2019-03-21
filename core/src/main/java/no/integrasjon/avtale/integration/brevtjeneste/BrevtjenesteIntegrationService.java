package no.integrasjon.avtale.integration.brevtjeneste;

import no.integrasjon.avtale.integration.brevtjeneste.client.BrevtjenesteRestClient;
import no.integrasjon.avtale.model.Avtale;
import no.integrasjon.avtale.types.SendeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BrevtjenesteIntegrationService {

    private final BrevtjenesteRestClient brevtjenesteRestClient;


    @Autowired
    public BrevtjenesteIntegrationService(final BrevtjenesteRestClient brevtjenesteRestClient) {
        this.brevtjenesteRestClient = brevtjenesteRestClient;
    }

    public SendeStatus sendAvtaleTilKunde(final Avtale avtale){
        return this.brevtjenesteRestClient.sendAvtaleTilKunde(avtale);
    }
}
