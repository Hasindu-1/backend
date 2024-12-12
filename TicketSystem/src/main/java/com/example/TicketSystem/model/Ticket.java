package com.example.TicketSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int countId;
    private int Id;
    private String eventName;
    private String cus;
    private int ticketPrice;


    public Ticket() {
    }
    public Ticket(int ticketId, String eventName, int ticketPrice, String cus) {
        this.Id = ticketId;
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
        this.cus = cus;


    }

    public int getId() {
        return Id;
    }

    public void setId(int ticketId) {
        this.Id = ticketId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
