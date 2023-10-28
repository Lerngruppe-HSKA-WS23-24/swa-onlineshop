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
package com.acme.onlineshop.repository;

import com.acme.onlineshop.entity.Kategorie;
import com.acme.onlineshop.entity.Produkt;
import com.acme.onlineshop.entity.ProduktAttribut;
import com.acme.onlineshop.entity.ProduktAttributType;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Emulation der Datenbasis für persistente Kunden.
 */
@SuppressWarnings({"UtilityClassCanBeEnum", "UtilityClass", "MagicNumber", "RedundantSuppression"})
final class DB {
    /**
     * Liste der Kunden zur Emulation der DB.
     */
    @SuppressWarnings("StaticCollection")
    static final List<Produkt> PRODUKTE = getProdukte();

    private DB() {
    }

    @SuppressWarnings({"FeatureEnvy", "TrailingComment"})
    private static List<Produkt> getProdukte() {
        // Helper-Methoden ab Java 9: List.of(), Set.of, Map.of, Stream.of
        // List.of() baut eine unveraenderliche Liste: kein Einfuegen, Aendern, Loeschen

        return Stream.of(
            // admin
            Produkt.builder()
                .sku(UUID.fromString("00000000-0000-0000-0000-000000000001"))
                .name("Gucci T-Shirt")
                .kategrorie(
                    Kategorie
                        .builder()
                        .id(UUID.fromString("10000000-0000-0000-0000-000000000001"))
                        .name("Textilien")
                        .build()
                )
                .attribute(Stream.of(
                    ProduktAttribut
                        .builder()
                        .key(ProduktAttributType.GEWICHT)
                        .value("0.5kg")
                        .build(),
                    ProduktAttribut
                        .builder()
                        .key(ProduktAttributType.GROESSE)
                        .value("10x10x5 cm")
                        .build(),
                    ProduktAttribut
                        .builder()
                        .key(ProduktAttributType.MARKE)
                        .value("Gucci")
                        .build()
                ).collect(Collectors.toList()))
                .build(),
            Produkt.builder()
                .sku(UUID.fromString("00000000-0000-0000-0000-000000000002"))
                .name("Lacoste T-Shirt")
                .kategrorie(
                    Kategorie
                        .builder()
                        .id(UUID.fromString("10000000-0000-0000-0000-000000000001"))
                        .name("Textilien")
                        .build()
                )
                .attribute(Stream.of(
                    ProduktAttribut
                        .builder()
                        .key(ProduktAttributType.GEWICHT)
                        .value("0.4kg")
                        .build(),
                    ProduktAttribut
                        .builder()
                        .key(ProduktAttributType.GROESSE)
                        .value("10x10x5 cm")
                        .build(),
                    ProduktAttribut
                        .builder()
                        .key(ProduktAttributType.MARKE)
                        .value("Lacoste")
                        .build()
                ).collect(Collectors.toList()))
                .build(),
            Produkt.builder()
                .sku(UUID.fromString("00000000-0000-0000-0000-000000000003"))
                .name("HKa Tasse")
                .kategrorie(
                    Kategorie
                        .builder()
                        .id(UUID.fromString("10000000-0000-0000-0000-000000000002"))
                        .name("Tassen")
                        .build()
                )
                .attribute(Stream.of(
                    ProduktAttribut
                        .builder()
                        .key(ProduktAttributType.GEWICHT)
                        .value("0.2kg")
                        .build(),
                    ProduktAttribut
                        .builder()
                        .key(ProduktAttributType.GROESSE)
                        .value("2x2x5 cm")
                        .build(),
                    ProduktAttribut
                        .builder()
                        .key(ProduktAttributType.MARKE)
                        .value("HsKa")
                        .build()
                ).collect(Collectors.toList()))
                .build()
        )
            // CAVEAT Stream.toList() erstellt eine "immutable" List
            .collect(Collectors.toList());
    }

    private static URL buildURL(final String url) {
        try {
            return URI.create(url).toURL();
        } catch (final MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
