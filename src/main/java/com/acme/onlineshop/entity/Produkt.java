package com.acme.onlineshop.entity;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;

import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Eine Produktkategorie für die Produkte
 *
 * @author <a href="mailto:leon.gauweiler@gmail.com">Leon Gauweiler</a>
 */
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Getter
@Setter
@ToString
@SuppressWarnings({"ClassFanOutComplexity", "JavadocDeclaration", "RequireEmptyLineBeforeBlockTagGroup"})
public class Produkt {
    /**
     * Muster für einen Produkt Namen ohne Punkte oder Kommas.
     */
    public static final String NAME_PATTERN = "^[^.,]*$";

    /**
     * Die sku des Produkts.
     * @param sku Die sku.
     * @return Die sku.
     */
    @EqualsAndHashCode.Include
    private UUID sku;

    /**
     * Der Name des Produkts.
     * @param name Der name.
     * @return Der name.
     */
    @NotNull
    @Pattern(regexp = NAME_PATTERN)
    private String name;

    /**
     * Die Adresse des Kunden.
     * @param kategrorie Die Kategorie.
     * @return Die Kategorie.
     */
    @Valid
    @Null
    @ToString.Exclude
    private Kategorie kategrorie;

    /**
     * Die Attribute des Produktes.
     * @param attribute Die Attribute.
     * @return Die Attribute.
     */
    @ToString.Exclude
    private List<ProduktAttribut> attribute;

}
