/*
 * Copyright (C) 2022 - present Juergen Zimmermann, Hochschule Karlsruhe
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.acme.onlineshop.entity;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
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
@SuppressWarnings({"ClassFanOutComplexity", "RequireEmptyLineBeforeBlockTagGroup"})
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

    @NotNull
    @Pattern(regexp = NAME_PATTERN)
    private String nachname;

    /**
     * Die Adresse des Kunden.
     * @param kategrorie Die Kategorie.
     * @return Die Kategorie.
     */
    @Valid
    @Null
    @ToString.Exclude
    private Kategorie kategrorie;
}
