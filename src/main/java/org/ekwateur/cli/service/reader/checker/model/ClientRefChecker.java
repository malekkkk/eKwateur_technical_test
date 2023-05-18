package org.ekwateur.cli.service.reader.checker.model;

import org.beryx.textio.InputReader;
import org.ekwateur.core.exception.InvalidClientRefException;
import org.ekwateur.core.model.ClientRef;

import java.util.List;

public class ClientRefChecker implements InputReader.ValueChecker<String> {

    public ClientRefChecker() {

    }

    @Override
    public List<String> getErrorMessages(String val, String itemName) {
        try {
            ClientRef.parse(val);
            return null;
        } catch (InvalidClientRefException invalidClientRefException) {
            return List.of(invalidClientRefException.getMessage());
        }
    }
}
