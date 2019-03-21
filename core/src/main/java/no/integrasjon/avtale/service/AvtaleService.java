package no.integrasjon.avtale.service;

import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.RetryPolicy;
import no.integrasjon.avtale.api.v1.OpprettAvtalePayload;
import no.integrasjon.avtale.api.v1.OpprettAvtaleResponsePayload;
import no.integrasjon.avtale.integration.brevtjeneste.BrevtjenesteIntegrationService;
import no.integrasjon.avtale.integration.fagsystem.FagsystemIntegrationService;
import no.integrasjon.avtale.model.Avtale;
import no.integrasjon.avtale.types.AvtaleStatus;
import no.integrasjon.avtale.types.SendeStatus;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.concurrent.TimeUnit.SECONDS;
import static no.integrasjon.avtale.api.v1.transformer.OpprettAvtalePayloadTransformer.fraPayload;
import static no.integrasjon.avtale.types.AvtaleStatus.*;
import static org.slf4j.LoggerFactory.getLogger;

@Service
public class AvtaleService {

    private final static Logger LOGGER = getLogger(AvtaleService.class);

    private FagsystemIntegrationService fagsystem;
    private BrevtjenesteIntegrationService brevtjeneste;


    @Autowired
    public AvtaleService(final FagsystemIntegrationService fagsystem,
                         final BrevtjenesteIntegrationService brevtjeneste) {
        this.fagsystem = fagsystem;
        this.brevtjeneste = brevtjeneste;
    }

    public OpprettAvtaleResponsePayload opprettAvtale(final OpprettAvtalePayload pAvtale) throws Exception {

        final Avtale avtale = fraPayload().apply(pAvtale);
        avtale.setKundenummer(this.fagsystem.opprettKunde(pAvtale.getKunde()));
        avtale.setAvtalenummer(this.fagsystem.opprettAvtale(avtale));

        final SendeStatus sendeStatus = this.brevtjeneste.sendAvtaleTilKunde(avtale);

        if (!sendeStatus.equals(SendeStatus.SENDT)) {
            throw new Exception("Feil ved sending av avtale til kunde");
        }

        /*
         * Kjører retry-strategi som et virkemiddel for skissert problemstilling.
         * Feil logges for manuell behandling og kan overvåkes i f.eks. Kibana. Om dette er feil som oppstår hyppig,
         * ville jeg fokusert på å finne ut hvorfor det feiler og legge innsatsen i å stabilisere tjenesten.
         *
         * Alternativer:
         * - Automatisert retting av dette ifa. en jobb vil kreve at brevtjenesten tilbyr et api for å sjekke om en avtale er sendt.
         * - Sende til feilkø for behandling av en tjeneste hvor melding kun fjernes fra feilkø når status er oppdatert.
         */
        return Failsafe.with(new RetryPolicy().retryOn(Exception.class).withDelay(1, SECONDS).withMaxRetries(3))
                .onFailedAttempt(exception ->
                        LOGGER.error("Feil under statusoppdatering for avtale={}, melding={}", avtale.getAvtalenummer(), exception.getMessage())
                )
                .onRetry((o, exception, executionContext) ->
                        LOGGER.error("Statusoppdatering for avtale={} feilet for {}. gang", avtale.getAvtalenummer(), executionContext.getExecutions())
                )
                .onFailure(listener ->
                        LOGGER.error("Statusoppdatering for avtale={} feilet", avtale.getAvtalenummer())
                )
                .withFallback(exception -> {
                    // Feil er logget og må tas tak i, bryr ikke klient med dette
                    return new OpprettAvtaleResponsePayload(avtale.getAvtalenummer(), SENDT);
                })
                .get(() -> new OpprettAvtaleResponsePayload(avtale.getAvtalenummer(), this.fagsystem.oppdaterStatus(avtale.getAvtalenummer(), SENDT)));
    }
}
