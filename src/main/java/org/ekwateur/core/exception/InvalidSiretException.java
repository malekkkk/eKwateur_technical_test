package org.ekwateur.core.exception;

public class InvalidSiretException extends IllegalArgumentException {

    private static final String INVALID_SIRET = """
            Invalid SIRET number: %s.
            SIRET number should only contain 14 digits.
            """;

    public InvalidSiretException(String siret) {
        super(String.format(INVALID_SIRET, siret));
    }
}
