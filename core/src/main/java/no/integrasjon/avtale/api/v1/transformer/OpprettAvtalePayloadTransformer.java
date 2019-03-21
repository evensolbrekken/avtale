package no.integrasjon.avtale.api.v1.transformer;

import no.integrasjon.avtale.api.v1.OpprettAvtalePayload;
import no.integrasjon.avtale.model.Avtale;

import java.util.function.Function;


/**
 * Transformator for {@link OpprettAvtalePayload} og {@link Avtale}.
 */
public class OpprettAvtalePayloadTransformer {

    public static Function<OpprettAvtalePayload, Avtale> fraPayload() {
        return opprettAvtalePayload -> new Avtale(
                opprettAvtalePayload.getType(),
                opprettAvtalePayload.getKategori(),
                opprettAvtalePayload.getUnderkategori(),
                OpprettAvtaleKjoretoyPayloadTransformer.fraPayload().apply(opprettAvtalePayload.getKjoretoy())
        );
    }
}
