package com.acme.onlineshop.graphql;

import com.acme.onlineshop.entity.Kategorie;
import com.acme.onlineshop.entity.ProduktAttribut;

import java.util.List;

/**
 * Eingabedaten für ein Produkt.
 *
 * @author <a href="mailto:gale1012@h-ka.de">Leon Gauweiler</a>
 * @param name Der Name des Produkts als unveränderliches Pflichtfeld.
 * @param attribute Die Attribute des Produkts.
 * @param kategorie Die Kategorie des Produkts.
 */
public record ProduktInput(
    String name,
    List<ProduktAttribut> attribute,
    Kategorie kategorie
) {
}
