package org.ekwateur.cli.service.writer;

import org.beryx.textio.TextIO;
import org.ekwateur.core.model.Consumption;
import org.ekwateur.core.model.Customer;
import org.ekwateur.core.model.IndividualCustomer;
import org.ekwateur.core.model.ProfessionalCustomer;
import org.ekwateur.core.service.InvoiceService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class InvoiceWriterService {

    private final InvoiceService invoiceService;
    private final TextIO textIO;

    public InvoiceWriterService(InvoiceService invoiceService, TextIO textIO) {
        this.invoiceService = invoiceService;
        this.textIO = textIO;
    }

    public void writeInvoice(Customer customer, Consumption consumption) {

        BigDecimal electricityPriceConsumption = invoiceService.calculateElectricityPriceConsumption(customer, consumption.electricityKwh());
        BigDecimal gazPriceConsumption = invoiceService.calculateGazPriceConsumption(customer, consumption.gazKwh());
        BigDecimal totalPriceConsumption = invoiceService.calculateTotalInvoice(customer, consumption);

        if (customer instanceof ProfessionalCustomer) {
            writeProfessionalCustomerInvoice((ProfessionalCustomer) customer, electricityPriceConsumption, gazPriceConsumption, totalPriceConsumption);
        } else {
            writeIndividualCustomerInvoice((IndividualCustomer) customer, electricityPriceConsumption, gazPriceConsumption, totalPriceConsumption);
        }


    }

    private void writeProfessionalCustomerInvoice(ProfessionalCustomer professionalCustomer, BigDecimal electricityPriceConsumption,
                                                  BigDecimal gazPriceConsumption, BigDecimal totalPriceConsumption) {

        textIO.getTextTerminal().println(String.format("""
                ____________________________________________
                                 INVOICE
                ____________________________________________                
                
                Client ref: %s
                Company name: %s
                SIRET: %s
                _____________________________________________      
                
                Electricity: %s EUR
                Gaz: %s EUR
                _____________________________________________
                
                Total: %s EUR
                _____________________________________________
                """,
                professionalCustomer.getClientRef().getValue(),
                professionalCustomer.getCompanyName(),
                professionalCustomer.getSiret().getValue(),
                electricityPriceConsumption,
                gazPriceConsumption,
                totalPriceConsumption
                ));
    }

    private void writeIndividualCustomerInvoice(IndividualCustomer individualCustomer, BigDecimal electricityPriceConsumption,
                                                BigDecimal gazPriceConsumption, BigDecimal totalPriceConsumption) {

        textIO.getTextTerminal().println(String.format("""
                ____________________________________________
                                
                                 INVOICE
                ____________________________________________                
                
                Client ref: %s
                Customer: %s %s %s
                _____________________________________________      
                
                Electricity: %s EUR
                Gaz: %s EUR
                _____________________________________________
                
                Total: %s EUR
                _____________________________________________
                """,
                individualCustomer.getClientRef().getValue(),
                individualCustomer.getCivility().name(),
                individualCustomer.getFirstName(),
                individualCustomer.getLastName(),
                electricityPriceConsumption,
                gazPriceConsumption,
                totalPriceConsumption
        ));
    }
}
