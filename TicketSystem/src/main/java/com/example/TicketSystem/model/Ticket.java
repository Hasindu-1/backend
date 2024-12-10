package com.example.TicketSystem.model;

public class Ticket {

    private int id;
    private int vendorId;
    private int customerId;


    public Ticket() {
    }
    public Ticket(int id, int vendorId, int customerId) {
        this.id = id;
        this.vendorId = vendorId;
        this.customerId = customerId;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
