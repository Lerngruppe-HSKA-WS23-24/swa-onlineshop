package com.acme.onlineshop.rest;

import com.acme.onlineshop.service.ProduktWriteService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;

import static com.acme.onlineshop.rest.ProduktGetController.REST_PATH;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;

/**
 * Controller um die Post Requests für die Produkte zu bedienen.
 *
 * @author <a href="mailto:leon.gauweiler@gmail.com">Leon Gauweiler</a>
 */
@RestController
@RequestMapping(REST_PATH)
@RequiredArgsConstructor
@Slf4j
@SuppressWarnings("TrailingComment")
public class ProduktWriteController {
    /**
     * Basispfad für die REST-Schnittstelle.
     */
    public static final String REST_PATH = "/rest";

    private final ProduktWriteService service;
    private final UriHelper uriHelper;
    private final ProduktMapper mapper;


    /**
     * Finde all Produkte.
     *
     * @return Alle Produkte.
     */
    @PostMapping(path = "", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addProdukt(
        @RequestBody final ProduktDTO produktDTO,
        final HttpServletRequest request
    ) {
        final var produkt = service.create(mapper.toProdukt(produktDTO));
        final var baseUri = uriHelper.getBaseUri(request).toString();
        final var location = URI.create(STR."\{baseUri}/\{produkt.getSku()}");
        return created(location).build();
    }
}
