package com.acme.onlineshop.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Enum für Interessen. Dazu können auf der Clientseite z.B. Checkboxen realisiert werden.
 *
 * @author <a href="mailto:leon.gauweiler@gmail.com">Leon Gauweiler</a>
 */
public enum ProduktAttributType {
    /**
     * _Marke_ mit dem internen Wert `marke`
     */
    MARKE("marke"),
    /**
     * _Gewicht_ mit dem internen Wert `gewicht`
     */
    GEWICHT("gewicht"),

    /**
     * _Groesse_ mit dem internen Wert `groesse`
     */
    GROESSE("groesse");

    private final String value;

    ProduktAttributType(final String value) {
        this.value = value;
    }

    /**
     * Konvertierung eines Strings in einen Enum-Wert.
     *
     * @author <a href="mailto:Juergen.Zimmermann@h-ka.de">Jürgen Zimmermann</a>
     *
     * @param value Der String, zu dem ein passender Enum-Wert ermittelt werden soll.
     * @return Passender Enum-Wert oder null.
     */
    public static Optional<ProduktAttributType> of(final String value) {
        return Stream.of(values())
            .filter(interesse -> Objects.equals(interesse.value, value))
            .findFirst();
    }

    /**
     * Einen enum-Wert als String mit dem internen Wert ausgeben.
     * Dieser Wert wird durch Jackson in einem JSON-Datensatz verwendet.
     * [<a href="https://github.com/FasterXML/jackson-databind/wiki">Wiki-Seiten</a>]
     * @author <a href="mailto:Juergen.Zimmermann@h-ka.de">Jürgen Zimmermann</a>
     * @return Interner Wert
     */
    @JsonValue
    @Override
    public String toString() {
        return value;
    }
}
