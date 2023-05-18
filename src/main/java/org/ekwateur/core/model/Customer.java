package org.ekwateur.core.model;

public abstract class Customer {

    protected final ClientRef clientRef;

    public Customer(ClientRef clientRef) {
        this.clientRef = clientRef;
    }

    public ClientRef getClientRef() {
        return this.clientRef;
    }


}
