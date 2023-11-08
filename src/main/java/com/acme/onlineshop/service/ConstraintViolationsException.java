package com.acme.onlineshop.service;

import com.acme.onlineshop.entity.Produkt;
import jakarta.validation.ConstraintViolation;
import java.util.Collection;
import lombok.Getter;

/**
 * Exception, falls es mindestens ein verletztes Constraint gibt.
 *
 */
@Getter
public class ConstraintViolationsException extends RuntimeException {
    /**
     * Die verletzten Constraints.
     */
    private final transient Collection<ConstraintViolation<Produkt>> violations;

    ConstraintViolationsException(
        final Collection<ConstraintViolation<Produkt>> violations
    ) {
        super("Constraints sind verletzt");
        this.violations = violations;
    }
}
