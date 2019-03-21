package no.integrasjon.avtale.api.v1;

import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class OpprettAvtaleKjoretoyPayload {

    private String merke;
    private String modell;
    private String motor;
    private Integer aarsmodell;
    private Integer kjorelengde;
    private String dekning;
    private String bonus;
    private Integer egenandel;
    private Boolean alarm;
    private Boolean sporing;
    private String parkering;
    private Boolean tredjemannsinteresse;
    private Boolean ung;
    private Boolean pant;


    public OpprettAvtaleKjoretoyPayload() {
        // Kun for demotest
    }

    public OpprettAvtaleKjoretoyPayload(final String merke,
                                        final String modell,
                                        final String motor,
                                        final Integer aarsmodell,
                                        final Integer kjorelengde,
                                        final String dekning,
                                        final String bonus,
                                        final Integer egenandel,
                                        final Boolean alarm,
                                        final Boolean sporing,
                                        final String parkering,
                                        final Boolean tredjemannsinteresse,
                                        final Boolean ung,
                                        final Boolean pant) {
        this.merke = merke;
        this.modell = modell;
        this.motor = motor;
        this.aarsmodell = aarsmodell;
        this.kjorelengde = kjorelengde;
        this.dekning = dekning;
        this.bonus = bonus;
        this.egenandel = egenandel;
        this.alarm = alarm;
        this.sporing = sporing;
        this.parkering = parkering;
        this.tredjemannsinteresse = tredjemannsinteresse;
        this.ung = ung;
        this.pant = pant;
    }

    public String getMerke() {
        return this.merke;
    }

    public String getModell() {
        return this.modell;
    }

    public String getMotor() {
        return this.motor;
    }

    public Integer getAarsmodell() {
        return this.aarsmodell;
    }

    public Integer getKjorelengde() {
        return this.kjorelengde;
    }

    public String getDekning() {
        return this.dekning;
    }

    public String getBonus() {
        return this.bonus;
    }

    public Integer getEgenandel() {
        return this.egenandel;
    }

    public Boolean getAlarm() {
        return this.alarm;
    }

    public Boolean getSporing() {
        return this.sporing;
    }

    public String getParkering() {
        return this.parkering;
    }

    public Boolean getTredjemannsinteresse() {
        return this.tredjemannsinteresse;
    }

    public Boolean getUng() {
        return this.ung;
    }

    public Boolean getPant() {
        return this.pant;
    }

    @Override
    public String toString() {
        return "OpprettAvtaleKjoretoyPayload {" +
                "modell=" + Objects.toString(this.modell, EMPTY) +
                ", motor=" + Objects.toString(this.motor, EMPTY) +
                ", aarsmodell=" + Objects.toString(this.aarsmodell, EMPTY) +
                ", kjorelengde=" + Objects.toString(this.kjorelengde, EMPTY) +
                ", dekning=" + Objects.toString(this.dekning, EMPTY) +
                ", bonus=" + Objects.toString(this.bonus, EMPTY) +
                ", egenandel=" + Objects.toString(this.egenandel, EMPTY) +
                ", alarm=" + Objects.toString(this.alarm, "[]") +
                ", sporing=" + Objects.toString(this.sporing, EMPTY) +
                ", parkering=" + Objects.toString(this.parkering, EMPTY) +
                ", tredjemannsinteresse=" + Objects.toString(this.tredjemannsinteresse, EMPTY) +
                ", ung=" + Objects.toString(this.ung, EMPTY) +
                ", pant=" + Objects.toString(this.pant, EMPTY) +
                '}';
    }
}