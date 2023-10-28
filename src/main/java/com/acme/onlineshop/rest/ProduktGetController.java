package com.acme.onlineshop.rest;

import com.acme.onlineshop.entity.Produkt;
import com.acme.onlineshop.service.ProduktReadService;

import java.util.Collection;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private final ProduktReadService service;

    /**
     * Finde all Produkte.
     *
     * @return Alle Produkte.
     */
    @GetMapping(path = "", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Produkt>> getAll() {
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
     * @return Gefundenes Produkt.
     */
    @GetMapping(path = "{sku:" + ID_PATTERN + "}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Produkt> getBySku(@PathVariable final UUID sku) {
        log.debug("getBySku: sku={}, Thread={}", sku, Thread.currentThread().getName());

        // Geschaeftslogik bzw. Anwendungskern
        final var produkt = service.findBySku(sku);
        log.debug("produkt: {}", produkt);
        return ResponseEntity.ok(produkt);
    }
}
