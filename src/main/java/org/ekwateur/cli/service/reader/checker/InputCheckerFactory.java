package org.ekwateur.cli.service.reader.checker;

import org.beryx.textio.InputReader;
import org.ekwateur.cli.service.reader.checker.model.ClientRefChecker;
import org.ekwateur.cli.service.reader.checker.model.SiretChecker;

public final class InputCheckerFactory {

    public static InputReader.ValueChecker<String> getClientRefChecker() {
        return new ClientRefChecker();
    }

    public static InputReader.ValueChecker<String> getSiretChecker() {
        return new SiretChecker();
    }
}
