package com.acme.onlineshop.graphql;

import com.acme.onlineshop.entity.Produkt;
import com.acme.onlineshop.entity.Kategorie;
import com.acme.onlineshop.entity.ProduktAttribut;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

/**
 * Mapper zwischen Entity-Klassen.
 *
 * @author <a href="mailto:gale1012@h-ka.de">Leon Gauweiler</a>
 */
@Mapper(componentModel = "spring", nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface ProduktInputMapper {
    /**
     * Konvertiert ein {@code ProduktInput} in ein {@code Produkt}-Entity-Objekt.
     *
     * @param input Das zu konvertierende {@code ProduktInput}-Objekt.
     * @return Ein {@code Produkt}-Entity-Objekt mit {@code null} als sku.
     */
    @Mapping(target = "sku", ignore = true)
    Produkt toProdukt(ProduktInput input);

    /**
     * Konvertiert ein {@code KategorieInput} in ein {@code Kategorie}-Entity-Objekt.
     *
     * @param input Das zu konvertierende {@code KategorieInput}-Objekt.
     * @return Ein {@code Kategorie}-Entity-Objekt mit {@code null} als ID.
     */
    @Mapping(target = "id", ignore = true)
    Kategorie toKategorie(KategorieInput input);

    /**
     * Konvertiert ein {@code ProduktAttributInput} in ein {@code ProduktAttribut}-Entity-Objekt.
     *
     * @param input Das zu konvertierende {@code ProduktAttributInput}-Objekt.
     * @return Ein {@code ProduktAttribut}-Entity-Objekt
     */
    @Mapping(target = "key", ignore = false)
    ProduktAttribut toProduktAttribut(ProduktAttributInput input);
}
