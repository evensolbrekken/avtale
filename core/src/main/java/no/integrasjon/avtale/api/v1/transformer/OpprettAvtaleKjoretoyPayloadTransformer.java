package no.integrasjon.avtale.api.v1.transformer;

import no.integrasjon.avtale.api.v1.OpprettAvtaleKjoretoyPayload;
import no.integrasjon.avtale.model.Kjoretoy;

import java.util.function.Function;


/**
 * Transformator for {@link OpprettAvtaleKjoretoyPayload} og {@link Kjoretoy}.
 */
class OpprettAvtaleKjoretoyPayloadTransformer {

    static Function<OpprettAvtaleKjoretoyPayload, Kjoretoy> fraPayload() {
        return opprettAvtaleKjoretoyPayload -> new Kjoretoy(
                opprettAvtaleKjoretoyPayload.getMerke(),
                opprettAvtaleKjoretoyPayload.getModell(),
                opprettAvtaleKjoretoyPayload.getMotor(),
                opprettAvtaleKjoretoyPayload.getAarsmodell(),
                opprettAvtaleKjoretoyPayload.getKjorelengde(),
                opprettAvtaleKjoretoyPayload.getDekning(),
                opprettAvtaleKjoretoyPayload.getBonus(),
                opprettAvtaleKjoretoyPayload.getEgenandel(),
                opprettAvtaleKjoretoyPayload.getAlarm(),
                opprettAvtaleKjoretoyPayload.getSporing(),
                opprettAvtaleKjoretoyPayload.getParkering(),
                opprettAvtaleKjoretoyPayload.getTredjemannsinteresse(),
                opprettAvtaleKjoretoyPayload.getUng(),
                opprettAvtaleKjoretoyPayload.getPant()
        );
    }
}
