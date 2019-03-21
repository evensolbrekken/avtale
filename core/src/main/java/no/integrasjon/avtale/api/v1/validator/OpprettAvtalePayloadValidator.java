package no.integrasjon.avtale.api.v1.validator;

import no.integrasjon.avtale.api.v1.OpprettAvtalePayload;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static java.util.Objects.isNull;
import static org.springframework.util.Assert.notNull;
import static org.springframework.validation.ValidationUtils.rejectIfEmpty;


public class OpprettAvtalePayloadValidator implements Validator {

    @Override
    public boolean supports(final Class clazz) {
        return OpprettAvtalePayload.class.equals(clazz);
    }

    @Override
    public void validate(final Object pOpprettAvtalePayload, final Errors errors) {
        notNull(pOpprettAvtalePayload, "OpprettAvtalePayload kan ikke validers når den er null!");
        notNull(errors, "OpprettAvtalePayload kan ikke validers når errors er null!");

        final OpprettAvtalePayload payload = (OpprettAvtalePayload)pOpprettAvtalePayload;

        // Avtale
        rejectIfEmpty(errors, "type", "validering.avtale.type.ugyldig.mangler");
        rejectIfEmpty(errors, "kategori", "validering.avtale.kategori.ugyldig.mangler");
        rejectIfEmpty(errors, "underkategori", "validering.avtale.underkategori.ugyldig.mangler");

        // Kunde
        rejectIfEmpty(errors, "kunde", "validering.avtale.kunde.ugyldig.mangler");
        if (!isNull(payload.getKunde())) {
            errors.pushNestedPath("kunde");
            rejectIfEmpty(errors, "fodselsnummer", "validering.avtale.kunde.fodselsnummer.ugyldig.mangler");
            rejectIfEmpty(errors, "fornavn", "validering.avtale.kunde.fornavn.ugyldig.mangler");
            rejectIfEmpty(errors, "etternavn", "validering.avtale.kunde.etternavn.ugyldig.mangler");
            errors.popNestedPath();
        }

        // Kjoretoy
        rejectIfEmpty(errors, "kjoretoy", "validering.avtale.kjoretoy.ugyldig.mangler");
        if (!isNull(payload.getKjoretoy())) {
            errors.pushNestedPath("kjoretoy");
            rejectIfEmpty(errors, "merke", "validering.avtale.kjoretoy.merke.ugyldig.mangler");
            rejectIfEmpty(errors, "modell", "validering.avtale.kjoretoy.modell.ugyldig.mangler");
            rejectIfEmpty(errors, "motor", "validering.avtale.kjoretoy.motor.ugyldig.mangler");
            rejectIfEmpty(errors, "aarsmodell", "validering.avtale.kjoretoy.aarsmodell.ugyldig.mangler");
            rejectIfEmpty(errors, "kjorelengde", "validering.avtale.kjoretoy.kjorelengde.ugyldig.mangler");
            rejectIfEmpty(errors, "dekning", "validering.avtale.kjoretoy.dekning.ugyldig.mangler");
            rejectIfEmpty(errors, "bonus", "validering.avtale.kjoretoy.bonus.ugyldig.mangler");
            rejectIfEmpty(errors, "egenandel", "validering.avtale.kjoretoy.egenandel.ugyldig.mangler");
            rejectIfEmpty(errors, "alarm", "validering.avtale.kjoretoy.alarm.ugyldig.mangler");
            rejectIfEmpty(errors, "sporing", "validering.avtale.kjoretoy.sporing.ugyldig.mangler");
            rejectIfEmpty(errors, "parkering", "validering.avtale.kjoretoy.parkering.ugyldig.mangler");
            rejectIfEmpty(errors, "tredjemannsinteresse", "validering.avtale.kjoretoy.tredjemannsinteresse.ugyldig.mangler");
            rejectIfEmpty(errors, "ung", "validering.avtale.kjoretoy.ung.ugyldig.mangler");
            rejectIfEmpty(errors, "pant", "validering.avtale.kjoretoy.pant.ugyldig.mangler");
            errors.popNestedPath();
        }
    }
}













