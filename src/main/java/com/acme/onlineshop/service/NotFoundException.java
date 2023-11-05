package com.acme.onlineshop.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import lombok.Getter;

/**
 * RuntimeException, falls kein Produkt gefunden wurde.
 */
@Getter
public final class NotFoundException extends RuntimeException {
    /**
     * Nicht-vorhandene id.
     */
    private final UUID sku;
    private final Map<String, List<String>> suchkriterien;

    NotFoundException(final UUID sku) {
        super(STR."Kein Produkt mit der sku \{sku} gefunden.");
        this.sku = sku;
        suchkriterien = null;
    }

    NotFoundException(final Map<String, List<String>> suchkriterien) {
        super("Keine entsprechende Fakultät mit diesen Suchkriterien gefunden.");
        this.sku = null;
        this.suchkriterien = suchkriterien;
    }
}
