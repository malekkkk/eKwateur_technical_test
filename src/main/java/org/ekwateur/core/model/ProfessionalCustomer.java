package org.ekwateur.core.model;

public class ProfessionalCustomer extends Customer {

    private final Siret siret;
    private final String companyName;
    private double annualRevenueInEuro;

    public ProfessionalCustomer(ClientRef clientRef, Siret siret, String companyName, double annualRevenue) {
        super(clientRef);
        this.siret = siret;
        this.companyName = companyName;
        this.annualRevenueInEuro = annualRevenue;
    }

    public Siret getSiret() {
        return this.siret;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public double getAnnualRevenueInEuro() {
        return this.annualRevenueInEuro;
    }
}
