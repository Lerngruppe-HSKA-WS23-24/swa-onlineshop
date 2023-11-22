package com.acme.onlineshop.graphql;

import com.acme.onlineshop.entity.Produkt;
import com.acme.onlineshop.service.ProduktReadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import static java.util.Collections.emptyMap;

/**
 * Eine Controller-Klasse für das Lesen von Produktdaten mit der GraphQL-Schnittstelle.
 * Stellt Methoden zur Abfrage von Produktdaten bereit.
 *
 * @author <a href="mailto:gale1012@h-ka.de">Leon Gauweiler</a>
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class ProduktQueryController {
    private final ProduktReadService service;

    /**
     * Suche anhand der Produkt-sku.
     *
     * @param sku sku des gesuchten Produkts
     * @return Das gefundene Produkt
     */
    @QueryMapping("produkt")
    Produkt findBySku(@Argument final UUID sku) {
        log.debug("findBySku: id = {}", sku);
        final var produkt = service.findBySku(sku);
        log.debug("findBySku: Produkt= {}", produkt);
        return produkt;
    }

    /**
     * Suche mit diversen Suchkriterien.
     *
     * @param input Suchkriterien und ihre Werte, z.B. Name einer Fakultät
     * @return Die gefundenen Fakultäten als Collection
     */
    @QueryMapping("fakultaeten")
    Collection<Produkt> find(@Argument final Optional<Suchkriterien> input) {
        log.debug("find: suchkriterien = {}", input);
        final var suchkriterien = input.map(Suchkriterien::toMap).orElse(emptyMap());
        log.debug("find: suchkriterien = {}", suchkriterien);
        final var fakultaeten = service.find(suchkriterien);
        log.debug("find: fakultaeten = {}", fakultaeten);
        return fakultaeten;
    }

    /**
     * Liefert alle Fakultäten.
     *
     * @return Eine Collection aller Fakultäten
     */
    @QueryMapping("findAll")
    Collection<Produkt> findAll() {
        log.debug("findAll:");
        final var produkte = service.findAll();
        log.debug("findAll: Produkte = {}", produkte);
        return produkte;
    }
}
