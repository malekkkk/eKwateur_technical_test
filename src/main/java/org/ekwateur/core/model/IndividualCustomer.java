package org.ekwateur.core.model;

public class IndividualCustomer extends Customer {

    private final Civility civility;
    private final String firstName;
    private final String lastName;

    public IndividualCustomer(ClientRef clientRef, Civility civility, String firstName, String lastName) {
        super(clientRef);
        this.civility = civility;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Civility getCivility() {
        return civility;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
