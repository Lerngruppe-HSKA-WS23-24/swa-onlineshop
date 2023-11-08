package com.acme.onlineshop.service;

import lombok.Getter;

/**
 * Exception, falls der Name bereits existiert.
 *
 * @author <a href="mailto:gale1012@h-ka.de">Leon Gauweiler</a>
 */
@Getter
public class NameExistsException extends RuntimeException {
    /**
     * Bereits vorhandener Produktname.
     */
    private final String name;

    NameExistsException(final String name) {
        super(STR."Der Name \{name} existiert bereits");
        this.name = name;
    }
}
