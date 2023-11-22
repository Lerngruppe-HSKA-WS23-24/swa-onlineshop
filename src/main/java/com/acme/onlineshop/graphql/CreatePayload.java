package com.acme.onlineshop.graphql;

import java.util.UUID;

/**
 * Value-Klasse für das Resultat, wenn an der GraphQL-Schnittstelle ein neues Produkt angelegt wurde.
 *
 * @author <a href="mailto:gale1012@h-ka.de">Leon Gauweiler</a>
 *
 * @param sku id die eindeutige sku des neu angelegten Produktes.
 */
public record CreatePayload(UUID sku) {
}
