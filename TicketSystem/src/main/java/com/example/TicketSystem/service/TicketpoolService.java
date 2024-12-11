package com.example.TicketSystem.service;

import com.example.TicketSystem.model.Ticket;
import com.example.TicketSystem.repository.CustomerRepository;
import com.sun.tools.javac.Main;
import org.springframework.stereotype.Service;

import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.logging.Logger;

@Service
public class TicketpoolService {


    private final ConcurrentLinkedDeque<String> logMessages = new ConcurrentLinkedDeque<>();

    private int maxTicket;// Maximum size of the ticket pool

    public int totalTicketsAdded =0; // Counter for the total number of tickets added

    private int totalTickets;

    //public final CustomerRepository customerRepository;

    private final ConfigurationService configurationService;
    private final Vector<Ticket> ticketPool =new Vector<>();

    private int totalticketsold =0;




    public TicketpoolService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }




    public synchronized boolean addTickets(Ticket vendorName) {
        // Check if the total ticket limit has been reached
        while (totalTicketsAdded < configurationService.getConfiguration().getTotalTickets()) {
            // Wait if the ticket pool is full
            while (ticketPool.size() >= configurationService.getConfiguration().getMaxTicketCapacity()) {
                try {
                    System.out.println(vendorName + " waiting as Ticket Pool is full.");
                    wait(); // Wait until there's space in the pool
                } catch (InterruptedException e) {
                    throw new RuntimeException("Vendor interrupted", e);
                }
            }

            // Re-check the global ticket limit after waking up
            if (totalTicketsAdded >= configurationService.getConfiguration().getTotalTickets()) {
                System.out.println(vendorName + " cannot add tickets. Global ticket limit reached.");
                return false; // Indicate that no more tickets can be added
            }

            // Add a new ticket to the pool
            int i =0;
            Ticket ticket = new Ticket((i++),(i++),(i++));
            ticketPool.add(ticket);
            totalTicketsAdded++;
            /// /////////////////////////
            String log = vendorName + " added Ticket #" + ticket.getId() +
                    " - Current Pool Size: " + ticketPool.size() +
                    " - Total Tickets Added: " + totalTicketsAdded;
            logMessages.add(log);

            /// /////////////////////
            System.out.println(vendorName + " added Ticket #" + ticket.getId() +
                    " - Current Pool Size: " + ticketPool.size() +
                    " - Total Tickets Added: " + totalTicketsAdded);

            // Notify other threads (e.g., customers) that a ticket has been added
            notifyAll();
            return true; // Indicate that the ticket was successfully added
        }

        // If global ticket limit has been reached before entering the loop
        System.out.println(vendorName + " cannot add tickets. Global ticket limit reached.");
        return false;
    }

    public synchronized Ticket buyTicket(){

        while(ticketPool.isEmpty()){
            if (totalTicketsAdded >= configurationService.getConfiguration().getTotalTickets()) {
                return null; // No more tickets will be added
            }
            try{
                System.out.println("\n================ CUSTOMER ACTIONS ================");
                System.out.println(Thread.currentThread().getName() +" Current Ticket pool is empty ...Customer  waiting");
                wait();


            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
                return null;
            }
        }

        Ticket ticket = ticketPool.remove(0);
        totalticketsold++;
        System.out.println("Ticket bought by - " + Thread.currentThread().getName() + " - current size is - " + ticketPool.size() + " - Ticket is - " );
        System.out.println(" **********Ticket sold count is "+totalticketsold);
        /// //////////////
        String log = "Ticket bought by - " + Thread.currentThread().getName() +
                " - Current Pool Size: " + ticketPool.size();
        logMessages.add(log);
        /// /////////////
        notifyAll();

        return ticket;
    }

    public ConcurrentLinkedDeque<String> getLogs() {
        return logMessages;
    }

}
