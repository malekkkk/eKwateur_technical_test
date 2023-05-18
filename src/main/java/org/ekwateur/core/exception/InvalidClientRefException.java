package org.ekwateur.core.exception;

public class InvalidClientRefException extends IllegalArgumentException {

    private static final String INVALID_CLIENT_REF_ERR = """
            Invalid client reference: %s.
            Client reference should always start with 'EKW' followed by 8 digits.
            """;

    public InvalidClientRefException(String clientRef) {
         super(String.format(INVALID_CLIENT_REF_ERR, clientRef));
    }
}
