package com.example.TicketSystem.model;

import jakarta.persistence.*;

@Entity
public class Vendor {
    @Id
    private String vendorId;


    private String vendorName;

    public Vendor() {
    }

    public Vendor(String vendorName) {
        this.vendorName = vendorName;
    }

    public Vendor(String vendorId, String vendorName) {
        this.vendorId = vendorId;
        this.vendorName = vendorName;
    }


    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }
}
