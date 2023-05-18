package org.ekwateur.cli;

import org.beryx.textio.TextIO;
import org.ekwateur.cli.model.CustomerType;
import org.ekwateur.cli.service.reader.ConsumptionReaderService;
import org.ekwateur.cli.service.reader.IndividualCustomerReaderService;
import org.ekwateur.cli.service.reader.ProfessionalCustomerReaderService;
import org.ekwateur.cli.service.writer.InvoiceWriterService;
import org.ekwateur.core.model.Consumption;
import org.springframework.stereotype.Component;

@Component
public class CliOrchestrator {

    private final TextIO textIO;

    private final ConsumptionReaderService consumptionReaderService;

    private final IndividualCustomerReaderService individualCustomerReaderService;

    private final ProfessionalCustomerReaderService professionalCustomerReaderService;

    private final InvoiceWriterService invoiceWriterService;

    public CliOrchestrator(TextIO textIO, ConsumptionReaderService consumptionReaderService, IndividualCustomerReaderService individualCustomerReaderService,
                           ProfessionalCustomerReaderService professionalCustomerReaderService, InvoiceWriterService invoiceWriterService) {
        this.textIO = textIO;
        this.consumptionReaderService = consumptionReaderService;
        this.invoiceWriterService = invoiceWriterService;
        this.professionalCustomerReaderService = professionalCustomerReaderService;
        this.individualCustomerReaderService = individualCustomerReaderService;
    }

    public void run() {

        CustomerType customerType = textIO.newEnumInputReader(CustomerType.class)
                .read("Please chose the customer type");

        var customer = switch (customerType) {
            case INDIVIDUAL -> this.individualCustomerReaderService.readIndividualCustomer();
            case PROFESSIONAL -> this.professionalCustomerReaderService.readProfessionalCustomer();
        };

        Consumption consumption = consumptionReaderService.readCustomerConsumption();

        invoiceWriterService.writeInvoice(customer, consumption);

    }
}
