package com.example.TicketSystem.service;

import com.example.TicketSystem.model.Configuration;
import com.example.TicketSystem.model.Ticket;
import com.example.TicketSystem.repository.TicketPoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendorService implements Runnable{

    private final ConfigurationService configurationService;
    //private final TicketPoolRepository ticketPoolrepository;
    private final TicketpoolService ticketpoolService;


    public VendorService(ConfigurationService configurationService,  TicketpoolService ticketpoolService) {
        this.configurationService = configurationService;
        //this.ticketPoolrepository = ticketPoolrepository;
        this.ticketpoolService = ticketpoolService;
    }

    @Override
    public void run(){
        Configuration configuration = configurationService.getConfiguration();

          for (int j = 1; j <= configuration.getTotalTickets(); j++) {
            Ticket ticket =new Ticket(1,1,1);
            ticketpoolService.addTickets(ticket);
           // boolean added = tPool.addTickets(ticket);
            // Construct each ticket string dynamically
            //tPool.addTickets(ticket);

            if (!ticketpoolService.addTickets(ticket)) {
                System.out.println( j+ " stopped adding tickets (Global limit reached)");
                break;
            }


//
            try {
                Thread.sleep(1);

            }catch (InterruptedException e){
                System.out.println("case Vendor");
                throw new RuntimeException(e);


            }
        }


    }




}
