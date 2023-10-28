package com.acme.onlineshop.rest;

import com.acme.onlineshop.entity.Produkt;
import com.acme.onlineshop.service.ProduktReadService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.acme.onlineshop.rest.ProduktGetController.REST_PATH;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Controller um die Get Requests für die Produkte zu bedienen.
 *
 * @author <a href="mailto:leon.gauweiler@gmail.com">Leon Gauweiler</a>
 */
@RestController
@RequestMapping(REST_PATH)
@RequiredArgsConstructor
@Slf4j
@SuppressWarnings("TrailingComment")
public class ProduktGetController {
    /**
     * Basispfad für die REST-Schnittstelle.
     */
    public static final String REST_PATH = "/rest"; //NOSONAR

    /**
     * Muster für eine UUID. [\dA-Fa-f]{8}{8}-([\dA-Fa-f]{8}{4}-){3}[\dA-Fa-f]{8}{12} enthält eine "capturing group"
     * und ist nicht zulässig.
     */
    public static final String ID_PATTERN =
        "[\\dA-Fa-f]{8}-[\\dA-Fa-f]{4}-[\\dA-Fa-f]{4}-[\\dA-Fa-f]{4}-[\\dA-Fa-f]{12}";

    /**
     * Pfad, um Nachnamen abzufragen.
     */
    private final ProduktReadService service;
    private final UriHelper uriHelper;

    /**
     * Finde all Produkte.
     *
     * @return Gefundener Kunde mit Atom-Links.
     */
    @GetMapping(path = "", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll() {
        log.debug("getAll: Thread={}", Thread.currentThread().getName());

        // Geschaeftslogik bzw. Anwendungskern
        final Collection<Produkt> produkte = service.findAll();

        log.debug("produkt: {}", produkte);
        return ResponseEntity.ok(produkte);
    }

    /**
     * Suche anhand der Produkt-sku als Pfad-Parameter.
     *
     * @param sku sku des zu suchenden Produkts
     * @param request Das Request-Objekt, um Links für HATEOAS zu erstellen.
     * @return Gefundener Kunde mit Atom-Links.
     */
    @GetMapping(path = "{sku:" + ID_PATTERN + "}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBySku(@PathVariable final UUID sku, final HttpServletRequest request) {
        log.debug("getBySku: sku={}, Thread={}", sku, Thread.currentThread().getName());

        // Geschaeftslogik bzw. Anwendungskern
        final var produkt = service.findBySku(sku);

        // HATEOAS
        // evtl. Forwarding von einem API-Gateway
        final var baseUri = uriHelper.getBaseUri(request).toString();
        final var idUri = STR."\{baseUri}/\{produkt.getSku()}";
        final var selfLink = Link.of(idUri);

        final Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("produkt", produkt);
        responseBody.put("links", List.of(selfLink));

        log.debug("produkt: {}", responseBody);
        return ResponseEntity.ok(responseBody);
    }
}
