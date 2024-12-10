package com.example.TicketSystem.model;

public class Vendor {

    private String vendorName;
    private int vendorId;

    public Vendor(String vendorName, int vendorId) {
        this.vendorName = vendorName;
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }
}
