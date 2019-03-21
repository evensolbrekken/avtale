package no.integrasjon.avtale.model;

import no.integrasjon.avtale.types.AvtaleKategori;
import no.integrasjon.avtale.types.AvtaleType;
import no.integrasjon.avtale.types.AvtaleUnderkategori;

import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class Avtale {

    private String avtalenummer;
    private AvtaleType type;
    private AvtaleKategori kategori;
    private AvtaleUnderkategori underkategori;
    private String kundenummer;
    private Kjoretoy kjoretoy;


    public Avtale(final AvtaleType type,
                  final AvtaleKategori kategori,
                  final AvtaleUnderkategori underkategori,
                  final Kjoretoy kjoretoy) {
        this.type = type;
        this.kategori = kategori;
        this.underkategori = underkategori;
        this.kjoretoy = kjoretoy;
    }

    public String getAvtalenummer() {
        return this.avtalenummer;
    }

    public void setAvtalenummer(final String avtalenummer) {
        this.avtalenummer = avtalenummer;
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

    public String getKundenummer() {
        return this.kundenummer;
    }

    public void setKundenummer(final String kundenummer) {
        this.kundenummer = kundenummer;
    }

    public Kjoretoy getKjoretoy() {
        return this.kjoretoy;
    }

    @Override
    public String toString() {
        return "Avtale {" +
                "avtalenummer=" + Objects.toString(this.avtalenummer, EMPTY) +
                ", type=" + Objects.toString(this.type, EMPTY) +
                ", kategori=" + Objects.toString(this.kategori, EMPTY) +
                ", underkategori=" + Objects.toString(this.underkategori, EMPTY) +
                ", kundenummer=" + Objects.toString(this.kundenummer, EMPTY) +
                ", kjoretoy=" + Objects.toString(this.kjoretoy, EMPTY) +
                '}';
    }
}
