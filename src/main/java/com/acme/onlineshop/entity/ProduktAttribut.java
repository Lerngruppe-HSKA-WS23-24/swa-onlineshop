package com.acme.onlineshop.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Ein Schluessel und ein value für Informationen fuer ein Produkt.
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
    @NotNull
    private ProduktAttributType key;

    /**
     * Der Value den das Attribut hat
     * @param value Der Value als String.
     * @return Der Value.
     */
    @NotNull
    private String value;
}
