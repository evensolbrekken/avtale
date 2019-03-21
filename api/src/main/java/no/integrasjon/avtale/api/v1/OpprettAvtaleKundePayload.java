package no.integrasjon.avtale.api.v1;

import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class OpprettAvtaleKundePayload {

    private String fodselsnummer;
    private String fornavn;
    private String etternavn;


    public OpprettAvtaleKundePayload(final String fodselsnummer,
                                     final String fornavn,
                                     final String etternavn) {
        this.fodselsnummer = fodselsnummer;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
    }

    public String getFodselsnummer() {
        return this.fodselsnummer;
    }

    public String getFornavn() {
        return this.fornavn;
    }

    public String getEtternavn() {
        return this.etternavn;
    }

    @Override
    public String toString() {
        return "OpprettAvtaleKundePayload {" +
                "fodselsnummer=" + Objects.toString(this.fodselsnummer, EMPTY) +
                ", fornavn=" + Objects.toString(this.fornavn, EMPTY) +
                ", etternavn=" + Objects.toString(this.etternavn, EMPTY) +
                '}';
    }
}
