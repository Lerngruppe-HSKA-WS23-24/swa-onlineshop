package com.acme.onlineshop.graphql;

import com.acme.onlineshop.entity.ProduktAttributType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Eine Value-Klasse für Eingabedaten passend zu Suchkriterien aus dem GraphQL-Schema.
 *
 * @param name      Der Name, der als Suchkriterium verwendet wird.
 * @param kategorie Die Kategorie, die als Suchkriterium verwendet wird.
 * @param produktAttributKey Der ProduktAttributKey, der als Suchkriterium verwendet wird.
 * @param attributValue Der AttributValue, der als Suchkriterium verwendet wird.
 * @author <a href="mailto:gale1012@h-ka.de">Leon Gauweiler</a>
 */
public record Suchkriterien(
    String name,
    String kategorie,
    ProduktAttributType produktAttributKey,
    String attributValue
) {
    /**
     * Konvertierung in eine Map.
     *
     * @return Das konvertierte Map-Objekt
     */
    Map<String, List<String>> toMap() {
        final Map<String, List<String>> map = new HashMap<>(2, 1);
        if (name != null) {
            map.put("name", List.of(name));
        }

        if (kategorie != null) {
            map.put("kategorie", List.of(kategorie));
        }

        return map;
    }
}
