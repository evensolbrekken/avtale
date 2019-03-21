package no.integrasjon.avtale.api.v1.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import no.integrasjon.avtale.api.v1.OpprettAvtalePayload;
import no.integrasjon.avtale.api.v1.OpprettAvtaleResponsePayload;
import no.integrasjon.avtale.api.v1.validator.OpprettAvtalePayloadValidator;
import no.integrasjon.avtale.service.AvtaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(value = "/api/v1/avtaler", produces = APPLICATION_JSON_UTF8_VALUE)
@Api(tags = {"Avtale"}, description = "Avtale API")
public class AvtaleController {

    private final AvtaleService avtaleService;


    @Autowired
    public AvtaleController(final AvtaleService avtaleService) {
        this.avtaleService = avtaleService;
    }


    @InitBinder
    protected void initBinder(final WebDataBinder binder) {
        binder.setValidator(new OpprettAvtalePayloadValidator());
    }

    /**
     * Oppretter ny avtale.
     * Tanker: I en virkelige verden bør tjenesten sikres med f.eks. oidc.
     *
     * @param opprettAvtalePayload Avtale som skal opprettes.
     * @return Avtalenummer og status for avtale.
     * @throws Exception Se {@link no.integrasjon.avtale.api.v1.error.ExceptionControllerAdvice}
     */
    @ApiOperation("Opprett avtale")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Avtale opprettet"),
            @ApiResponse(code = 422, message = "Valideringsfeil"),
            @ApiResponse(code = 500, message = "Ukjent server error")
    })
    @PostMapping
    public ResponseEntity<OpprettAvtaleResponsePayload> opprettAvtale(@Valid @RequestBody final OpprettAvtalePayload opprettAvtalePayload) throws Exception {
        return status(CREATED).body(this.avtaleService.opprettAvtale(opprettAvtalePayload));
    }
}
