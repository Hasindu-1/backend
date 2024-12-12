package com.example.TicketSystem.service;

import com.example.TicketSystem.model.Ticket;
import com.example.TicketSystem.model.TicketPool;
import com.example.TicketSystem.repository.TicketPoolRepository;
import com.example.TicketSystem.repository.TicketRepository;
import org.springframework.stereotype.Service;
import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedDeque;

@Service
public class TicketpoolService {


    private final ConcurrentLinkedDeque<String> logMessages = new ConcurrentLinkedDeque<>();
    public int totalTicketsAdded =0; // Counter for the total number of tickets added
    private final TicketPoolRepository ticketPoolRepository;
    private final TicketRepository ticketRepository;
    private int AddId =1;
    private int RemoveId =1;

    private final ConfigurationService configurationService;
    private final Vector<Ticket> ticketPool =new Vector<>();

    private int totalticketsold =0;




    public TicketpoolService(ConfigurationService configurationService,TicketPoolRepository ticketPoolRepository,TicketRepository ticketRepository) {
        this.configurationService = configurationService;
        this.ticketPoolRepository=ticketPoolRepository;
        this.ticketRepository=ticketRepository;
    }




    public synchronized boolean addTickets(Ticket vendorName,String venId) {
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
            Ticket ticket = new Ticket(AddId,"TicketingEvent",500,venId);
            ticketRepository.save(ticket);

            AddId++;
            ticketPool.add(ticket);

            totalTicketsAdded++;

            //ticket pool database
            TicketPool ticketPool1 = new TicketPool(venId,"Ticket Added");
            ticketPoolRepository.save(ticketPool1);

            String log = "\nVENDOR ACTIONS "+


                    " added Ticket #" + ticket.getId() +
                    " - Current Pool Size: " + ticketPool.size() +
                    " - Total Tickets Added: " + totalTicketsAdded;
            logMessages.add(log);

            System.out.println( " Added Ticket #" + ticket.getId() +
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

    public synchronized Ticket buyTicket( String cusId){

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


        Ticket ticket = new Ticket(RemoveId,"TicketingEvent",500,cusId);
        ticketRepository.save(ticket);
        System.out.println("**********************************************" + RemoveId);
        RemoveId++;


        ticketPool.remove(0);
        totalticketsold++;

        //Database
        TicketPool ticketPool2 = new TicketPool(cusId,"Ticket Purchased");
        ticketPoolRepository.save(ticketPool2);

        System.out.println("Ticket bought by - " + Thread.currentThread().getName() + " - current size is - " + ticketPool.size() + " - Ticket is - " );
        System.out.println(" **********Ticket sold count is "+totalticketsold);

        String log = "\n CUSTOMER ACTIONS  "+"Ticket bought by - " + Thread.currentThread().getName() +
                " - Current Pool Size: " + ticketPool.size();
        logMessages.add(log);

        notifyAll();

        return ticket;
    }

    public ConcurrentLinkedDeque<String> getLogs() {
        return logMessages;
    }

}
