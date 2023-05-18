package org.ekwateur.core.model;


import org.ekwateur.core.exception.InvalidClientRefException;

import java.util.regex.Pattern;

public final class ClientRef {
    private static final Pattern CLIENT_REF_PATTERN = Pattern.compile("^EKW\\d{8}$");

    private final String value;

    private ClientRef(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }


    public static ClientRef parse(String clientRef) {
        if (CLIENT_REF_PATTERN.matcher(clientRef).matches()) {
            return new ClientRef(clientRef);
        } else {
            throw new InvalidClientRefException(clientRef);
        }
    }
}
