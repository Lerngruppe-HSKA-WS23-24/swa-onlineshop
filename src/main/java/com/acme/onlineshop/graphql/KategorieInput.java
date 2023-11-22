package com.acme.onlineshop.graphql;

/**
 * Eingabedaten für eine Kategorie.
 *
 * @author <a href="mailto:gale1012@h-ka.de">Leon Gauweiler</a>
 *
 * @param name Der Name der Kategorie.
 */
public record KategorieInput(
    String name
) {
}
