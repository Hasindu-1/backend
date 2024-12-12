package com.example.TicketSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TicketPool {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ticketId;
    private String entityId;
    private String status;
    public TicketPool() {
    }

    public TicketPool( String entityId, String status) {
        this.entityId = entityId;
        this.status = status;

    }


    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
