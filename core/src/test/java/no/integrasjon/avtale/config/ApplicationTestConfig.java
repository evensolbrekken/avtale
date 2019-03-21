package no.integrasjon.avtale.config;

import no.integrasjon.avtale.integration.brevtjeneste.client.BrevtjenesteRestClient;
import no.integrasjon.avtale.integration.fagsystem.client.FagsystemRestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.spy;

@Configuration
public class ApplicationTestConfig {

    @Bean
    @Primary
    public FagsystemRestClient fagsystemRestClient() {
        return spy(new FagsystemRestClient());
    }

    @Bean
    @Primary
    public BrevtjenesteRestClient brevtjenesteRestClient() {
        return spy(new BrevtjenesteRestClient());
    }
}
