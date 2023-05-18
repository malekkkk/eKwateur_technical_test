package org.ekwateur.service;

import org.ekwateur.core.model.*;
import org.ekwateur.core.service.InvoiceService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class InvoiceServiceTest {

    final InvoiceService invoiceService = new InvoiceService();

    final IndividualCustomer individualCustomer = new IndividualCustomer(
            ClientRef.parse("EKW12345678"),
            Civility.MR,
            "firstName",
            "lastName"
    );

    final ProfessionalCustomer smallCompany = new ProfessionalCustomer(
            ClientRef.parse("EKW53805348"),
            Siret.parse("12345678983685"),
            "small company",
            100_000
    );

    final ProfessionalCustomer bigCompany = new ProfessionalCustomer(
            ClientRef.parse("EKW92505344"),
            Siret.parse("12945286983689"),
            "big company",
            2_000_000
    );


    @Test
    void testElectricityPriceCalculatorForIndividualCustomer() {

        // Given
        double electricityConsumption = 367.35;

        // WHEN
        BigDecimal electricityPriceForIndividualCustomer = invoiceService
                .calculateElectricityPriceConsumption(individualCustomer, electricityConsumption);

        // Then
        BigDecimal expectedElectricityPriceForIndividualCustomer = BigDecimal.valueOf(electricityConsumption)
                .multiply(ProductUnitPricing.INDIVIDUAL_CUSTOMER.getKwhElectricityInEuro());

        assertThat(electricityPriceForIndividualCustomer)
                .isEqualTo(expectedElectricityPriceForIndividualCustomer);

    }

    @Test
    void testElectricityPriceCalculatorForSmallCompany() {

        // Given
        double electricityConsumption = 367.35;

        // When
        BigDecimal electricityConsumptionForSmallCompany = invoiceService
                .calculateElectricityPriceConsumption(smallCompany, electricityConsumption);

        // Then
        BigDecimal expectedElectricityPriceForSmallCompany = BigDecimal.valueOf(electricityConsumption)
                .multiply(ProductUnitPricing.SMALL_COMPANY.getKwhElectricityInEuro());

        assertThat(electricityConsumptionForSmallCompany)
                .isEqualTo(expectedElectricityPriceForSmallCompany);

    }

    @Test
    void testElectricityPriceCalculatorForBigCompany() {

        // Given
        double electricityConsumption = 367.35;

        // When
        BigDecimal electricityConsumptionForBigCompany = invoiceService
                .calculateElectricityPriceConsumption(bigCompany, electricityConsumption);

        // Then
        BigDecimal expectedElectricityPriceForBigCompany = BigDecimal.valueOf(electricityConsumption)
                .multiply(ProductUnitPricing.BIG_COMPANY.getKwhElectricityInEuro());

        assertThat(electricityConsumptionForBigCompany)
                .isEqualTo(expectedElectricityPriceForBigCompany);

    }

    @Test
    void testGazPriceCalculatorForIndividualCustomer() {

        // Given
        double gazConsumption = 639.35;

        // WHEN
        BigDecimal gazPriceForIndividualCustomer = invoiceService
                .calculateGazPriceConsumption(individualCustomer, gazConsumption);

        // Then
        BigDecimal expectedGazPriceForIndividualCustomer = BigDecimal.valueOf(gazConsumption)
                .multiply(ProductUnitPricing.INDIVIDUAL_CUSTOMER.getKwhGazInEuro());

        assertThat(gazPriceForIndividualCustomer)
                .isEqualTo(expectedGazPriceForIndividualCustomer);

    }

    @Test
    void testGazPriceCalculatorForSmallCompany() {

        // Given
        double gazConsumption = 639.35;

        // When
        BigDecimal gazConsumptionForSmallCompany = invoiceService
                .calculateGazPriceConsumption(smallCompany, gazConsumption);

        // Then
        BigDecimal expectedGazPriceForSmallCompany = BigDecimal.valueOf(gazConsumption)
                .multiply(ProductUnitPricing.SMALL_COMPANY.getKwhGazInEuro());

        assertThat(gazConsumptionForSmallCompany)
                .isEqualTo(expectedGazPriceForSmallCompany);

    }

    @Test
    void testGazPriceCalculatorForBigCompany() {

        // Given
        double gazConsumption = 639.35;

        // When
        BigDecimal gazConsumptionForBigCompany = invoiceService
                .calculateGazPriceConsumption(bigCompany, gazConsumption);

        // Then
        BigDecimal expectedGazPriceForBigCompany = BigDecimal.valueOf(gazConsumption)
                .multiply(ProductUnitPricing.BIG_COMPANY.getKwhGazInEuro());

        assertThat(gazConsumptionForBigCompany)
                .isEqualTo(expectedGazPriceForBigCompany);

    }

    @Test
    void testTotalPriceCalculatorForIndividualCustomer() {

        // Given
        Consumption consumption = new Consumption(153.84, 527.84);

        // WHEN
        BigDecimal totalPriceForIndividualCustomer = invoiceService
                .calculateTotalInvoice(individualCustomer, consumption);

        // Then
        BigDecimal expectedTotalPriceForIndividualCustomer = invoiceService.calculateElectricityPriceConsumption(individualCustomer, consumption.electricityKwh())
                .add(invoiceService.calculateGazPriceConsumption(individualCustomer, consumption.gazKwh()));

        assertThat(totalPriceForIndividualCustomer)
                .isEqualTo(expectedTotalPriceForIndividualCustomer);
    }

    @Test
    void testTotalPriceCalculatorForSmallCompany() {

        // Given
        Consumption consumption = new Consumption(153.84, 527.84);

        // When
        BigDecimal totalInvoiceForSmallCompany = invoiceService
                .calculateTotalInvoice(smallCompany, consumption);

        // Then
        BigDecimal expectedTotalPriceForSmallCompany = invoiceService.calculateGazPriceConsumption(smallCompany, consumption.gazKwh())
                .add(invoiceService.calculateElectricityPriceConsumption(smallCompany, consumption.electricityKwh()));

        assertThat(totalInvoiceForSmallCompany)
                .isEqualTo(expectedTotalPriceForSmallCompany);

    }

    @Test
    void testTotalPriceCalculatorForBigCompany() {

        // Given
        Consumption consumption = new Consumption(153.84, 527.84);

        // When
        BigDecimal totalInvoiceForBigCompany = invoiceService
                .calculateTotalInvoice(bigCompany, consumption);

        // Then
        BigDecimal expectedTotalPriceForBigCompany = invoiceService.calculateGazPriceConsumption(bigCompany, consumption.gazKwh())
                .add(invoiceService.calculateElectricityPriceConsumption(bigCompany, consumption.electricityKwh()));

        assertThat(totalInvoiceForBigCompany)
                .isEqualTo(expectedTotalPriceForBigCompany);

    }
}
