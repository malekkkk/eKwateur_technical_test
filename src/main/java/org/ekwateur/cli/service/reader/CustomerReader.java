package org.ekwateur.cli.service.reader;

import org.beryx.textio.TextIO;
import org.ekwateur.cli.service.reader.checker.InputCheckerFactory;
import org.ekwateur.core.exception.InvalidClientRefException;
import org.ekwateur.core.model.ClientRef;

public abstract class CustomerReader {

    protected final TextIO textIO;

    public CustomerReader(TextIO textIO) {
        this.textIO = textIO;
    }

    public ClientRef readClientRef() {
        ClientRef clientRef = null;
        do {
            String clientRefStr = textIO.newStringInputReader()
                    .withValueChecker(InputCheckerFactory.getClientRefChecker())
                    .read("Please enter the client reference");
            try {
                clientRef = ClientRef.parse(clientRefStr);
            } catch (InvalidClientRefException e) {
                textIO.getTextTerminal().println(e.getMessage());
            }
        } while (clientRef == null);

        return clientRef;
    }
}
