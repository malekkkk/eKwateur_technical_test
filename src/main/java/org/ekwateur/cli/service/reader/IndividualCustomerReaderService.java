package org.ekwateur.cli.service.reader;

import org.beryx.textio.TextIO;
import org.ekwateur.core.model.Civility;
import org.ekwateur.core.model.ClientRef;
import org.ekwateur.core.model.IndividualCustomer;
import org.springframework.stereotype.Service;

@Service
public class IndividualCustomerReaderService extends CustomerReader {

    public IndividualCustomerReaderService(TextIO textIO) {
        super(textIO);
    }

    public IndividualCustomer readIndividualCustomer() {

        ClientRef clientRef = readClientRef();
        Civility civility = textIO.newEnumInputReader(Civility.class)
                .read("Please chose the customer civility");

        String firstName = textIO.newStringInputReader()
                .read("Please enter the customer first name");

        System.out.println();
        String lastName = textIO.newStringInputReader()
                .read("Please enter the customer last name");

        return new IndividualCustomer(clientRef, civility, firstName, lastName);
    }
}
