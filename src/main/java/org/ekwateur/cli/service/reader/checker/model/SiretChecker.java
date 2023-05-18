package org.ekwateur.cli.service.reader.checker.model;

import org.beryx.textio.InputReader;
import org.ekwateur.core.exception.InvalidSiretException;
import org.ekwateur.core.model.Siret;

import java.util.List;

public class SiretChecker implements InputReader.ValueChecker<String> {

    public SiretChecker() {

    }
    @Override
    public List<String> getErrorMessages(String val, String itemName) {
        try {
            Siret.parse(val);
            return null;
        } catch (InvalidSiretException invalidSiretException) {
            return List.of(invalidSiretException.getMessage());
        }
    }
}
