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
     * Nicht-vorhandene sku.
     */
    private final UUID sku;

    /**
     * Suchkriterien, zu denen nichts gefunden wurde.
     */
    private final Map<String, List<String>> suchkriterien;

    NotFoundException(final UUID sku) {
        super(STR."Kein Produkt mit der sku \{sku} gefunden.");
        this.sku = sku;
        suchkriterien = null;
    }
}
