## Avtale
`Demo-prosjekt for innsnevret avtaledomene realisert ifa. en Spring Boot applikasjon som representerer et integrasjonslag. 
Integrasjonslaget eksponerer et REST-API konsumert av en klient ifa. en integrasjonstest. Tjenester benyttet av integrasjonslag
er mocket ut. Sikring av tjenester er utelatt for å redusere omfang, men er en naturlig del av en slik løsning i den virkelige
verden.`

#### Oppsett
* `Last ned og installer Java 8`
* `Last ned og installer Git`
* `Last ned og installer Maven`
* `Hent ned prosjektet med` **_`git clone https://github.com/evensolbrekken/avtale.git`_**
* `Bygg prosjekt`

#### Oppstart
* `Start opp server med` **_`java -jar core/target/avtale-core-0.0.1-SNAPSHOT.jar`_**
* `Gå til` _localhost:8080_
* `For å se API og modell, trykk på link for REST API eller gå til` _http://localhost:8080/swagger-ui.html_

#### Klient
`Klient som konsumerer REST API eksponert av applikasjon er representert ifa. en integrasjonstest og finnes her:`
**_`no.integrasjon.avtale.AvtaleKlient`_**

##### Testsenarier
* `Opprettelse av avtale`
* `Opprettelse av avtale hvor opprettelse av kunde feiler`
* `Opprettelse av avtale hvor opprettelse av avtale feiler`
* `Opprettelse av avtale hvor sending av avtale til kunde feiler`
* `Opprettelse av avtale hvor sending av avtale til kunde returnerer med status sendefeil`
* `Opprettelse av avtale hvor statusendring på avtale feiler`
* `Opprettelse av avtale hvor det er valideringsfeil i attributter på avtale`