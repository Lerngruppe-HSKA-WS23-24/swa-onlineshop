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

        final var produktDB = repo.create(produkt);
        log.debug("create: {}", produktDB);
        return produktDB;
    }

    /**
     * Einen vorhandenen Kunden aktualisieren.
     *
     * @param kunde Das Objekt mit den neuen Daten (ohne ID)
     * @param id ID des zu aktualisierenden Kunden
     * @throws ConstraintViolationsException Falls mindestens ein Constraint verletzt ist.
     * @throws NotFoundException Kein Kunde zur ID vorhanden.
     * @throws EmailExistsException Es gibt bereits einen Kunden mit der Emailadresse.
    public void update(final Kunde kunde, final UUID id) {
        log.debug("update: {}", kunde);
        log.debug("update: id={}", id);

        final var violations = validator.validate(kunde);
        if (!violations.isEmpty()) {
            log.debug("update: violations={}", violations);
            throw new ConstraintViolationsException(violations);
        }

        final var kundeDbOptional = repo.findById(id);
        if (kundeDbOptional.isEmpty()) {
            throw new NotFoundException(id);
        }

        final var email = kunde.getEmail();
        final var kundeDb = kundeDbOptional.get();
        // Ist die neue Email bei einem *ANDEREN* Kunden vorhanden?
        if (!Objects.equals(email, kundeDb.getEmail()) && repo.isEmailExisting(email)) {
            log.debug("update: email {} existiert", email);
            throw new EmailExistsException(email);
        }

        kunde.setId(id);
        repo.update(kunde);
    }

    /**
     * Einen vorhandenen Kunden löschen.
     *
     * @param id Die ID des zu löschenden Kunden.
    public void deleteById(final UUID id) {
        log.debug("deleteById: id={}", id);
        repo.deleteById(id);
    }
    **/
}
