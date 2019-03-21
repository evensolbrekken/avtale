package no.integrasjon.avtale.api.v1;

import no.integrasjon.avtale.types.AvtaleKategori;
import no.integrasjon.avtale.types.AvtaleType;
import no.integrasjon.avtale.types.AvtaleUnderkategori;

import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class OpprettAvtalePayload {

    private AvtaleType type;
    private AvtaleKategori kategori;
    // Typisk vil det som her er representert med enumer være koder hentet fra kodeverk og som regel være cachet for ytelse
    private AvtaleUnderkategori underkategori;
    private OpprettAvtaleKundePayload kunde;
    private OpprettAvtaleKjoretoyPayload kjoretoy;
    // Andre informasjonsholdere relatert til andre typer, kategorier og underkategorier


    public OpprettAvtalePayload(final AvtaleType type,
                                final AvtaleKategori kategori,
                                final AvtaleUnderkategori underkategori,
                                final OpprettAvtaleKundePayload kunde,
                                final OpprettAvtaleKjoretoyPayload kjoretoy) {

        this.type = type;
        this.kategori = kategori;
        this.underkategori = underkategori;
        this.kunde = kunde;
        this.kjoretoy = kjoretoy;
    }

    public AvtaleType getType() {
        return this.type;
    }

    public AvtaleKategori getKategori() {
        return this.kategori;
    }

    public AvtaleUnderkategori getUnderkategori() {
        return this.underkategori;
    }

    public OpprettAvtaleKundePayload getKunde() {
        return this.kunde;
    }

    public OpprettAvtaleKjoretoyPayload getKjoretoy() {
        return this.kjoretoy;
    }

    @Override
    public String toString() {
        return "OpprettAvtalePayload {" +
                "type=" + Objects.toString(this.type, EMPTY) +
                ", kategori=" + Objects.toString(this.kategori, EMPTY) +
                ", underkategori=" + Objects.toString(this.underkategori, EMPTY) +
                ", kunde=" + Objects.toString(this.kunde, EMPTY) +
                ", kjoretoy=" + Objects.toString(this.kjoretoy, EMPTY) +
                '}';
    }
}
