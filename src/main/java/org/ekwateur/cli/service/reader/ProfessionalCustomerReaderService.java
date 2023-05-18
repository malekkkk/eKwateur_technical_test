package org.ekwateur.cli.service.reader;

import org.beryx.textio.TextIO;
import org.ekwateur.cli.service.reader.checker.InputCheckerFactory;
import org.ekwateur.core.model.ClientRef;
import org.ekwateur.core.model.ProfessionalCustomer;
import org.ekwateur.core.model.Siret;
import org.springframework.stereotype.Service;

@Service
public class ProfessionalCustomerReaderService extends CustomerReader {

    public ProfessionalCustomerReaderService(TextIO textIO) {
        super(textIO);
    }

    public ProfessionalCustomer readProfessionalCustomer() {

        ClientRef clientRef = readClientRef();

        String siretStr = textIO.newStringInputReader()
                .withValueChecker(InputCheckerFactory.getSiretChecker())
                .read("Please enter the SIRET number the company");
        String companyName = textIO.newStringInputReader()
                .read("Please enter the company name");
        double annualRevenue = textIO.newDoubleInputReader().withMinVal(0d)
                .read("Please enter the company annual revenue");

        return new ProfessionalCustomer(clientRef, Siret.parse(siretStr), companyName, annualRevenue);

    }
}
