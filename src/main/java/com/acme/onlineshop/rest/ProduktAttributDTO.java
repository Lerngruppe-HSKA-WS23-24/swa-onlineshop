package com.acme.onlineshop.rest;

import com.acme.onlineshop.entity.ProduktAttributType;

/**
 * ValueObject für ein Produktattribut mit Schluessel und ein value für
 * Informationen fuer ein Produkt.
 *
 * @param key Der Key.
 * @param value Der Value als String.
 */
record ProduktAttributDTO (
    ProduktAttributType key,
    String value
) {

}
