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
package com.acme.onlineshop.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Getter
@Setter
@ToString
@SuppressWarnings({"ClassFanOutComplexity", "JavadocDeclaration", "RequireEmptyLineBeforeBlockTagGroup"})
public class Kauefer {
    /**
     * Muster für einen gültigen Nachnamen. --> Copy by Jürgen Zimmermann --> Kunde
     */
    public static final String NACHNAME_PATTERN = "(o'|von|von der|von und zu|van)?[A-ZÄÖÜ][a-zäöüß]+(-[A-ZÄÖÜ][a-zäöüß]+)?";

    /**
     * Muster für einen gültigen Vornamen.
     */
    public static final String VORNAME_PATTERN = "[A-ZÄÖÜ][a-zäöüß]+(-[A-ZÄÖÜ][a-zäöüß]+)?";

    /**
     * Muster für eine gültige E-Mail-Adresse.
     */
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";



    /**
     * Die Emailadresse des Kunden, da diese in jedem Fall gegeben ist und nicht doppelt vergeben sein soll, ist das die ID des Kunden.
     * @param email Die Emailadresse.
     * @return Die Emailadresse.
     */
    @Email
    @NotNull
    @EqualsAndHashCode.Include
    @Pattern(regexp = EMAIL_PATTERN)
    private String email;

    /**
     * Der Nachname des Kunden.
     * @param nachname Der Nachname.
     * @return Der Nachname.
     */
    @NotNull
    @Pattern(regexp = NACHNAME_PATTERN)
    private String nachname;

    /**
     * Der Vorname des Kaeufers.
     * @param vorname Der Vorname.
     * @return Der Vorname.
     */
    @NotNull
    @Pattern(regexp = VORNAME_PATTERN)
    private String vorname;
}
