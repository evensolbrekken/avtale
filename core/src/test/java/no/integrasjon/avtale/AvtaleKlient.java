package no.integrasjon.avtale;

import io.restassured.RestAssured;
import no.integrasjon.avtale.api.v1.OpprettAvtaleKjoretoyPayload;
import no.integrasjon.avtale.api.v1.OpprettAvtaleKundePayload;
import no.integrasjon.avtale.api.v1.OpprettAvtalePayload;
import no.integrasjon.avtale.integration.brevtjeneste.client.BrevtjenesteRestClient;
import no.integrasjon.avtale.integration.fagsystem.client.FagsystemRestClient;
import no.integrasjon.avtale.types.SendeStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static no.integrasjon.avtale.types.AvtaleKategori.KJORETOY;
import static no.integrasjon.avtale.types.AvtaleStatus.SENDT;
import static no.integrasjon.avtale.types.AvtaleType.PRIVAT;
import static no.integrasjon.avtale.types.AvtaleUnderkategori.BILFORSIKRING;
import static no.integrasjon.avtale.types.SendeStatus.SENDEFEIL;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.*;

@ActiveProfiles({"test"})
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class AvtaleKlient {

    @LocalServerPort
    private int port;

    @Autowired
    private FagsystemRestClient fagsystemRestClient;

    @Autowired
    private BrevtjenesteRestClient brevtjenesteRestClient;

    private OpprettAvtalePayload opprettAvtalePayload = new OpprettAvtalePayload(
            PRIVAT,
            KJORETOY,
            BILFORSIKRING,
            new OpprettAvtaleKundePayload("12345678911", "Trude", "Luth"),
            new OpprettAvtaleKjoretoyPayload(
                    "FORD",
                    "FIESTA",
                    "1.0",
                    2016,
                    12000,
                    "Kasko super",
                    "75% bonusfri skade",
                    4000,
                    true,
                    false,
                    "Egen garasje",
                    true,
                    false,
                    false
            )
    );

    @Before
    public void before() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        Mockito.reset(this.fagsystemRestClient);
        Mockito.reset(this.brevtjenesteRestClient);
    }

    @Test
    public void opprettAvtale() {
        given()
                .body(this.opprettAvtalePayload)
                .contentType(JSON)
                .when()
                .post("/api/v1/avtaler")
                .then()
//                .log().all()
                .statusCode(CREATED.value())
                .contentType(JSON)
                .body("avtalenummer", equalTo("1000000001"))
                .body("avtalestatus", is(SENDT.name()));
    }

    @Test
    public void opprettAvtaleSkalFeileHvisKundeIkkeKanOpprettes() {
        doThrow(new RuntimeException("Feil ved opprettelse av kunde")).when(this.fagsystemRestClient).opprettKunde(any());
        given()
                .body(this.opprettAvtalePayload)
                .contentType(JSON)
                .when()
                .post("/api/v1/avtaler")
                .then()
                .log().all()
                .statusCode(INTERNAL_SERVER_ERROR.value())
                .body("message", equalTo("Feil ved opprettelse av kunde"));
    }

    @Test
    public void opprettAvtaleSkalFeileHvisAvtaleIkkeKanOpprettes() {
        doThrow(new RuntimeException("Feil ved opprettelse av avtale")).when(this.fagsystemRestClient).opprettAvtale(any());
        given()
                .body(this.opprettAvtalePayload)
                .contentType(JSON)
                .when()
                .post("/api/v1/avtaler")
                .then()
//                .log().all()
                .statusCode(INTERNAL_SERVER_ERROR.value())
                .body("message", equalTo("Feil ved opprettelse av avtale"));
    }

    @Test
    public void opprettAvtaleSkalFeileHvisSendAvtaleTilKundeFeiler() {
        doThrow(new RuntimeException("Feil ved sending av avtale til kunde")).when(this.brevtjenesteRestClient).sendAvtaleTilKunde(any());
        given()
                .body(this.opprettAvtalePayload)
                .contentType(JSON)
                .when()
                .post("/api/v1/avtaler")
                .then()
//                .log().all()
                .statusCode(INTERNAL_SERVER_ERROR.value())
                .body("message", equalTo("Feil ved sending av avtale til kunde"));
    }

    @Test
    public void opprettAvtaleSkalFeileHvisSendAvtaleTilKundeReturnererStatusSendefeil() {
        doReturn(SENDEFEIL).when(this.brevtjenesteRestClient).sendAvtaleTilKunde(any());
        given()
                .body(this.opprettAvtalePayload)
                .contentType(JSON)
                .when()
                .post("/api/v1/avtaler")
                .then()
//                .log().all()
                .statusCode(INTERNAL_SERVER_ERROR.value())
                .body("message", equalTo("Feil ved sending av avtale til kunde"));
    }

    @Test
    public void opprettAvtaleSkalIkkeFeilHvisOppdateringAvAvtaleStatusFeiler() {
        doThrow(new RuntimeException("Feil ved oppdatering av avtalestatus")).when(this.fagsystemRestClient).oppdaterAvtaleStatus(any(), any());
        given()
                .body(this.opprettAvtalePayload)
                .contentType(JSON)
                .when()
                .post("/api/v1/avtaler")
                .then()
//                .log().all()
                .statusCode(CREATED.value())
                .body("avtalenummer", equalTo("1000000001"))
                .body("avtalestatus", is(SENDT.name()));
    }

    @Test
    public void opprettAvtaleSkalGiValideringsfeilForManglendeInformasjon() {
        given()
                .body(
                        new OpprettAvtalePayload(
                                null, null, null,
                                new OpprettAvtaleKundePayload(null, null, null),
                                new OpprettAvtaleKjoretoyPayload()
                        )
                )
                .contentType(JSON)
                .when()
                .post("/api/v1/avtaler")
                .then()
//                .log().all()
                .statusCode(UNPROCESSABLE_ENTITY.value())
                .body("message", equalTo("Valideringsfeil!"))

                .body("fieldErrorMessages", hasEntry("type", "Type mangler"))
                .body("fieldErrorMessages", hasEntry("kategori", "Kategori mangler"))
                .body("fieldErrorMessages", hasEntry("underkategori", "Underkategori mangler"))

                .body("fieldErrorMessages", hasEntry("kunde.fodselsnummer", "Fodselsnummer mangler"))
                .body("fieldErrorMessages", hasEntry("kunde.fornavn", "Fornavn mangler"))
                .body("fieldErrorMessages", hasEntry("kunde.etternavn", "Etternavn mangler"))

                .body("fieldErrorMessages", hasEntry("kjoretoy.pant", "Pant mangler"))
                .body("fieldErrorMessages", hasEntry("kjoretoy.kjorelengde", "Kjorelengde mangler"))
                .body("fieldErrorMessages", hasEntry("kjoretoy.ung", "Ung mangler"))
                .body("fieldErrorMessages", hasEntry("kjoretoy.merke", "Merke mangler"))
                .body("fieldErrorMessages", hasEntry("kjoretoy.modell", "Modell mangler"))
                .body("fieldErrorMessages", hasEntry("kjoretoy.alarm", "Alarm mangler"))
                .body("fieldErrorMessages", hasEntry("kjoretoy.bonus", "Bonus mangler"))
                .body("fieldErrorMessages", hasEntry("kjoretoy.motor", "Motor  mangler"))
                .body("fieldErrorMessages", hasEntry("kjoretoy.dekning", "Dekning mangler"))
                .body("fieldErrorMessages", hasEntry("kjoretoy.aarsmodell", "Arsmodell  mangler"))
                .body("fieldErrorMessages", hasEntry("kjoretoy.sporing", "Sporing mangler"))
                .body("fieldErrorMessages", hasEntry("kjoretoy.sporing", "Sporing mangler"))
                .body("fieldErrorMessages", hasEntry("kjoretoy.parkering", "Parkering mangler"))
                .body("fieldErrorMessages", hasEntry("kjoretoy.egenandel", "Egenandel mangler"))
                .body("fieldErrorMessages", hasEntry("kjoretoy.tredjemannsinteresse", "Tredjemannsinteresse mangler"))
        ;
    }
}
