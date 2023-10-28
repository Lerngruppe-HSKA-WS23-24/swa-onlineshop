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

import com.acme.onlineshop.entity.Produkt;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static com.acme.onlineshop.repository.DB.PRODUKTE;
import static java.util.Collections.emptyList;

/**
 * Repository für den DB-Zugriff bei Produktn.
 *
 * @author <a href="mailto:Juergen.Zimmermann@h-ka.de">Jürgen Zimmermann</a>
 */
@Repository
@Slf4j
@SuppressWarnings("PublicConstructor")
public class ProduktRepository {
    /**
     * Einen Produktn anhand seiner ID suchen.
     *
     * @param sku Die Id des gesuchten Produkts
     * @return Optional mit dem gefundenen Produkt oder leeres Optional
     */
    public Optional<Produkt> findBySku(final UUID sku) {
        log.debug("findById: sku={}", sku);
        final var result = PRODUKTE.stream()
            .filter(produkt -> Objects.equals(produkt.getSku(), sku))
            .findFirst();
        log.debug("findBySku: {}", result);
        return result;
    }

    /**
     * Produktn anhand von Suchkriterien ermitteln.
     * Z.B. mit GET https://localhost:8080/api?nachname=A&amp;plz=7
     *
     * @param suchkriterien Suchkriterien.
     * @return Gefundene Produktn oder leere Collection.
     */
    @SuppressWarnings({"ReturnCount", "JavadocLinkAsPlainText"})
    public @NonNull Collection<Produkt> find(final Map<String, ? extends List<String>> suchkriterien) {
        log.debug("find: suchkriterien={}", suchkriterien);

        if (suchkriterien.isEmpty()) {
            return findAll();
        }

        // for-Schleife statt "forEach" wegen return
        for (final var entry : suchkriterien.entrySet()) {
            switch (entry.getKey()) {
                case "name" -> {
                    final var ProduktOpt = findByName(entry.getValue().get(0));
                    //noinspection OptionalIsPresent
                    return ProduktOpt.isPresent() ? List.of(ProduktOpt.get()) : emptyList();
                }
                default -> {
                    log.debug("find: ungueltiges Suchkriterium={}", entry.getKey());
                    return emptyList();
                }
            }
        }

        return emptyList();
    }

    /**
     * Alle Produktn als Collection ermitteln, wie sie später auch von der DB kommen.
     *
     * @return Alle Produktn
     */
    public @NonNull Collection<Produkt> findAll() {
        return PRODUKTE;
    }

    /**
     * Produkt zu gegebenem Namen aus der DB ermitteln.
     *
     * @param name Name für die Suche
     * @return Gefundenes Produkt oder leeres Optional
     */
    public Optional<Produkt> findByName(final String name) {
        log.debug("findByName: {}", name);
        final var result = PRODUKTE.stream()
            .filter(Produkt -> Objects.equals(Produkt.getName(), name))
            .findFirst();
        log.debug("findByName: {}", result);
        return result;
    }
}
