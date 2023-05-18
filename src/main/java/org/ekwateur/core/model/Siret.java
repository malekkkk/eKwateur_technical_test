package org.ekwateur.core.model;

import org.ekwateur.core.exception.InvalidSiretException;

import java.util.regex.Pattern;

public final class Siret {
    private static final Pattern SIRET_PATTERN = Pattern.compile("^\\d{14}$");

    private final String value;

    private Siret(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Siret parse(String siret) {
        if (SIRET_PATTERN.matcher(siret).matches()) {
            return new Siret(siret);
        } else {
            throw new InvalidSiretException(siret);
        }
    }
}
