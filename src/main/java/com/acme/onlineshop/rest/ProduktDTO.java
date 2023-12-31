package com.acme.onlineshop.rest;

import java.util.List;

/**
 * ValueObject für das Neuanlegen und Ändern eines neuen Produktes.
 *
 * @author <a href="mailto:gale1012@h-ka.de">Leon Gauweiler</a>
 * @param name Name eines Produktes.
 * @param kategorie Kategorie eines Produktes mit eingeschränkten Werten.
 */
public record ProduktDTO(
    String name,
    KategorieDTO kategorie,
    List<ProduktAttributDTO> attribute
) {
}
