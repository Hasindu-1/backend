package com.example.TicketSystem.service;

import com.example.TicketSystem.model.Configuration;
import com.example.TicketSystem.model.Ticket;
import com.example.TicketSystem.model.TicketPool;
import com.example.TicketSystem.repository.TicketPoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements Runnable{


    private ConfigurationService configurationService;

   // private TicketPoolRepository ticketPoolrepository;

    private TicketpoolService ticketpoolService;


    //private  final TicketPool tPool;//shared resource from Ticket pool for customer and Vendor.


    public CustomerService( ConfigurationService customerService,TicketpoolService ticketpoolService) {
        //this.tPool = tPool;
        this.configurationService= customerService;
//        this.totalTickets=totalTickets;
//        this.customerRetrievalRate=customerRetrievalRate;
       // this.ticketPoolrepository=ticketPoolrepository;
        this.ticketpoolService=ticketpoolService;
    }




    @Override
    public void run() {

        Configuration configuration=this.configurationService.getConfiguration();

        for (int i = 0; i < configuration.getTotalTickets(); i++) {

            Ticket ticket = ticketpoolService.buyTicket();

            System.out.println("Ticket bought by" + Thread.currentThread().getName()+"Ticket is ");//ticket);

            try {
                Thread.sleep(1);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);

            }

        }
    }
}
