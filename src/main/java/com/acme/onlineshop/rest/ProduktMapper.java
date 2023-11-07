package com.acme.onlineshop.rest;

import com.acme.onlineshop.entity.Kategorie;
import com.acme.onlineshop.entity.Produkt;
import com.acme.onlineshop.entity.ProduktAttribut;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

/**
 * Mapper zwischen Entity-Klassen. Siehe build\generated\sources\annotationProcessor\java\main\...\KundeMapperImpl.java.
 *
 * @author <a href="mailto:gale1012@h-ka.de">Leon Gauweiler</a>
 */
@Mapper(componentModel = "spring", nullValueIterableMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
interface ProduktMapper {
    /**
     * Ein DTO-Objekt von ProduktDTO in ein Objekt für Produkt konvertieren.
     *
     * @param dto DTO-Objekt für ProduktDTO ohne sku
     * @return Konvertiertes Produkt-Objekt mit null als sku
     */
    @Mapping(target = "sku", ignore = true)
    Produkt toProdukt(ProduktDTO dto);

    /**
     * Ein DTO-Objekt von KategorieDTO in ein Objekt für Kategorie konvertieren.
     *
     * @param dto DTO-Objekt für KategorieDTO ohne id
     * @return Konvertiertes Kategorie-Objekt mit null als id
     */
    @Mapping(target = "id")
    Kategorie toKategorie(KategorieDTO dto);

    /**
     * Ein DTO-Objekt von ProduktAttributDTO in ein Objekt für ProduktAttribut konvertieren.
     *
     * @param dto DTO-Objekt für ProduktAttributDTO
     * @return Konvertiertes ProduktAttribut-Objekt
     */
    ProduktAttribut toProduktAttribut(ProduktAttributDTO dto);
}
