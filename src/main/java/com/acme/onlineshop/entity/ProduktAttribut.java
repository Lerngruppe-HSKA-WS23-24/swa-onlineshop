package com.acme.onlineshop.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Geldbetrag und Währungseinheit für eine Umsatzangabe.
 *
 * @author <a href="mailto:leon.gauweiler@gmail.com">Leon Gauweiler</a>
 */
@Builder
@Getter
@Setter
@ToString
@SuppressWarnings({"JavadocDeclaration", "RequireEmptyLineBeforeBlockTagGroup"})
public class ProduktAttribut {
    /**
     * Der Key der definiert welche Eigenschaft gespeichert wird.
     * @param key Der Key.
     * @return Der key.
     */
    private ProduktAttributType key;

    /**
     * Der Value den das Attribut hat
     * @param value Der Value als String.
     * @return Der Value.
     */
    private String value;
}
