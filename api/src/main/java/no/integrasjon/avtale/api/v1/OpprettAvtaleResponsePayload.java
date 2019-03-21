package no.integrasjon.avtale.api.v1;

import no.integrasjon.avtale.types.AvtaleStatus;

import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class OpprettAvtaleResponsePayload {

    private String avtalenummer;
    private AvtaleStatus avtalestatus;


    public OpprettAvtaleResponsePayload(final String avtalenummer, final AvtaleStatus avtalestatus) {
        this.avtalenummer = avtalenummer;
        this.avtalestatus = avtalestatus;
    }

    public String getAvtalenummer() {
        return this.avtalenummer;
    }

    public AvtaleStatus getAvtalestatus() {
        return this.avtalestatus;
    }

    @Override
    public String toString() {
        return "OpprettAvtaleResponsePayload {" +
                "avtalenummer=" + Objects.toString(this.avtalenummer, EMPTY) +
                ", avtalestatus=" + Objects.toString(this.avtalestatus, EMPTY) +
                '}';
    }
}
