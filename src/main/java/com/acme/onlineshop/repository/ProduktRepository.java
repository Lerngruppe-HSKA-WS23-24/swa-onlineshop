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
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static com.acme.onlineshop.repository.DB.PRODUKTE;

/**
 * Repository für den DB-Zugriff bei Produkten.
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
        log.debug("findBySku: sku={}", sku);
        final var result = PRODUKTE.stream()
            .filter(produkt -> Objects.equals(produkt.getSku(), sku))
            .findFirst();
        log.debug("findBySku: {}", result);
        return result;
    }

    /**
     * Alle Produktn als Collection ermitteln, wie sie später auch von der DB kommen.
     *
     * @return Alle Produktn
     */
    public @NonNull Collection<Produkt> findAll() {
        return PRODUKTE;
    }
}
