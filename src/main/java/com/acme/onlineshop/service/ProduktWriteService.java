package com.acme.onlineshop.service;

import com.acme.onlineshop.entity.Produkt;
import com.acme.onlineshop.repository.ProduktRepository;
import jakarta.validation.Validator;
import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Anwendungslogik für Produlte.
 *
 * @author <a href="mailto:gale1012@h-ka.de">Leon Gauweiler</a>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProduktWriteService {
    private final ProduktRepository repo;

    private final Validator validator;

    /**
     * Einen neuen Kunden anlegen.
     *
     * @param produkt Das Objekt mit den Daten (ohne sku)
     * @return Das neu angelegte Produkt mit generierter sku
     * @throws ConstraintViolationsException Falls mindestens ein Constraint verletzt ist.
     */
    public Produkt create(final Produkt produkt) {
        log.debug("create: {}", produkt);

        final var violations = validator.validate(produkt);
        if (!violations.isEmpty()) {
            log.debug("create: violations={}", violations);
            throw new ConstraintViolationsException(violations);
        }

        if (repo.isNameExisting(produkt.getName())) {
            throw new NameExistsException(produkt.getName());
        }

        final var produktDB = repo.create(produkt);
        log.debug("create: {}", produktDB);
        return produktDB;
    }

    /**
     * Einen vorhandenes Produkt aktualisieren.
     *
     * @param produkt Das Objekt mit den neuen Daten (ohne sku)
     * @param sku des zu aktualisierenden Produktes
     * @throws ConstraintViolationsException Falls mindestens ein Constraint verletzt ist.
     * @throws NotFoundException Kein Kunde zur ID vorhanden.
     **/
    public void update(final Produkt produkt, final UUID sku) {
        log.debug("update: {}", produkt);
        log.debug("update: sku={}", sku);

        final var violations = validator.validate(produkt);
        if (!violations.isEmpty()) {
            log.debug("update: violations={}", violations);
            throw new ConstraintViolationsException(violations);
        }

        final var produktDbOptional = repo.findBySku(sku);
        if (produktDbOptional.isEmpty()) {
            throw new NotFoundException(sku);
        }

        final var produktDb = produktDbOptional.get();

        produkt.setSku(sku);
        repo.update(produkt);
    }
}
