package com.acme.onlineshop.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.acme.onlineshop.entity.Produkt;
import com.acme.onlineshop.repository.ProduktRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Anwendungslogik für Produkte.
 *
 * @author <a href="mailto:leon.gauweiler@gmail.com">Leon Gauweiler</a>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProduktReadService {
    private final ProduktRepository produktRepository;

    /**
     * Ein Produkt anhand seiner sku suchen.
     *
     * @param sku Die Id des gesuchten Kunden
     * @return Das gefundene Produkt
     * @throws NotFoundException Falls kein Produkt gefunden wurde
     */
    public @NonNull Produkt findBySku(final UUID sku) {
        log.debug("findBySku: sku={}", sku);
        final var produkt = produktRepository.findBySku(sku)
            .orElseThrow(() -> new NotFoundException(sku));
        log.debug("findBySku: {}", produkt);
        return produkt;
    }

    /**
     * Kunden anhand von Suchkriterien als Collection suchen.
     *
     * @return Alle Produkte oder eine leere Liste
     */
    @SuppressWarnings({"ReturnCount", "NestedIfDepth"})
    public @NonNull Collection<Produkt> findAll() {
        log.debug("Requested findAll");
        return produktRepository.findAll();
    }

    /**
     * Kunden anhand von Suchkriterien als Collection suchen.
     *
     * @param suchkriterien Die Suchkriterien
     * @return Die gefundenen Produkte oder eine leere Liste
     * @throws NotFoundException Falls keine Produkte gefunden wurden
     */
    @SuppressWarnings({"ReturnCount", "NestedIfDepth"})
    public @NonNull Collection<Produkt> find(@NonNull final Map<String, List<String>> suchkriterien) {
        log.debug("find: suchkriterien={}", suchkriterien);

        if (suchkriterien.isEmpty()) {
            return produktRepository.findAll();
        }

        if (suchkriterien.size() == 1) {
            final var name = suchkriterien.get("name");
            if (name != null && name.size() == 1) {
                final var produkte = produktRepository.findByName(name.getFirst());
                log.debug("find (name): {}", produkte);
                return produkte;
            }

            final var kategorie = suchkriterien.get("kategorie");
            if (kategorie != null && kategorie.size() == 1) {
                final var produkte = produktRepository.find(suchkriterien);
                log.debug("find (kategorie): {}", produkte);
                return produkte;
            }
        }

        final var produkte = produktRepository.find(suchkriterien);
        log.debug("find: {}", produkte);
        return produkte;
    }
}
