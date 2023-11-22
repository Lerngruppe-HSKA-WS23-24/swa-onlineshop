package com.acme.onlineshop.graphql;

import com.acme.onlineshop.entity.ProduktAttributType;

/**
 * Eingabedaten für ein Produkt.
 *
 * @author <a href="mailto:gale1012@h-ka.de">Leon Gauweiler</a>
 */
public record ProduktAttributInput(
    ProduktAttributType key,
    String value
) {
}
