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
import static java.util.UUID.randomUUID;

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
     * Produkte anhand von Suchkriterien ermitteln.
     *
     * @param suchkriterien Suchkriterien.
     * @return Gefundene Produkte oder eine leere Collection.
     */
    @SuppressWarnings({"ReturnCount"})
    public @NonNull Collection<Produkt> find(final Map<String, ? extends List<String>> suchkriterien) {
        log.debug("find: suchkriterien={}", suchkriterien);

        if (suchkriterien.isEmpty()) {
            return findAll();
        }

        // for-Schleife statt "forEach" wegen return
        for (final var entry : suchkriterien.entrySet()) {
            switch (entry.getKey()) {
                case "name" -> {
                    return findByName(entry.getValue().getFirst());
                }
                case "kategorie" -> {
                    return findByKategorie(entry.getValue().getFirst());
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
     * Produkte anhand des Namens suchen.
     *
     * @param name Der Name der gesuchten Produkte
     * @return Die gefundenen Produkte oder eine leere Collection
     */
    public Collection<Produkt> findByName(final String name){
        log.debug("findByName: name={}", name);
        final var produkte = PRODUKTE.stream()
            .filter(produkt -> produkt.getName().contains(name))
            .toList();
        return produkte;
    }

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

    /**
     * Produkte anhand von Kategorie suchen.
     *
     * @param kategorie Die Kategorie der gesuchten Produkte
     * @return Die gefundenen Produkte oder eine leere Collection
     */
    public @NonNull Collection<Produkt> findByKategorie(final String kategorie) {
        log.debug("findByKategorie: kategorie={}", kategorie);

        final var produkte = PRODUKTE.stream()
            .filter(produkt -> produkt.getKategorie().getName().contains(kategorie))
            .toList();
        log.debug("findByKategorie: produkte={}", produkte);
        return produkte;
    }

    /**
     * Ein neues Produkt.
     *
     * @param produkt Das Objekt des neu anzulegenden Produkts.
     * @return Das neu angelegte Produkt mit generierter sku
     */
    public @NonNull Produkt create(final @NonNull Produkt produkt) {
        log.debug("create: {}", produkt);
        produkt.setSku(randomUUID());
        PRODUKTE.add(produkt);
        log.debug("create: {}", produkt);
        return produkt;
    }
}
