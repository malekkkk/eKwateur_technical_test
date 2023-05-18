package org.ekwateur.core.service;

import org.ekwateur.core.model.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class InvoiceService {

    public BigDecimal calculateElectricityPriceConsumption(Customer customer, double electricityConsumptionInKwh) {
        return ProductUnitPricing.getInstance(customer).getKwhElectricityInEuro().multiply(BigDecimal.valueOf(electricityConsumptionInKwh));
    }

    public BigDecimal calculateGazPriceConsumption(Customer customer, double gazConsumptionInKwh) {
        return ProductUnitPricing.getInstance(customer).getKwhGazInEuro().multiply(BigDecimal.valueOf(gazConsumptionInKwh));
    }

    public BigDecimal calculateTotalInvoice(Customer customer, Consumption consumption) {
        return calculateElectricityPriceConsumption(customer, consumption.electricityKwh()).add(calculateGazPriceConsumption(customer, consumption.gazKwh()));
    }
}
