package org.ekwateur.core.model;

import java.math.BigDecimal;

public enum ProductUnitPricing {

    INDIVIDUAL_CUSTOMER("0.121", "0.115"),
    BIG_COMPANY("0.114", "0.111"),
    SMALL_COMPANY("0.118", "0.113");

    private BigDecimal kwhElectricityInEuro;
    private BigDecimal kwhGazInEuro;


    ProductUnitPricing(String electricityPrice, String gazPrice) {
        this.kwhElectricityInEuro = new BigDecimal(electricityPrice);
        this.kwhGazInEuro = new BigDecimal(gazPrice);
    }

    public BigDecimal getKwhElectricityInEuro() {
        return kwhElectricityInEuro;
    }

    public BigDecimal getKwhGazInEuro() {
        return kwhGazInEuro;
    }

    private static final double ANNUAL_REVENUE_REF_EURO = 1_000_000;

    public static ProductUnitPricing getInstance(Customer customer) {
        if (customer instanceof ProfessionalCustomer) {
            return ((ProfessionalCustomer) customer).getAnnualRevenueInEuro() < ANNUAL_REVENUE_REF_EURO ? SMALL_COMPANY : BIG_COMPANY;
        } else {
            return INDIVIDUAL_CUSTOMER;
        }
    }
}
