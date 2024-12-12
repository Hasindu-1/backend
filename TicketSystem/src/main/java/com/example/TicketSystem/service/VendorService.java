package com.example.TicketSystem.service;
import com.example.TicketSystem.model.Configuration;
import com.example.TicketSystem.model.Ticket;
import com.example.TicketSystem.model.Vendor;
import com.example.TicketSystem.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class VendorService implements Runnable{

    public static int globalV;
    private final ConfigurationService configurationService;
    private final TicketpoolService ticketpoolService;
    private final VendorRepository vendorRepository;

    private String vendorId;
    private static final Logger logger = Logger.getLogger(TicketpoolService.class.getName());





    public VendorService(ConfigurationService configurationService, TicketpoolService ticketpoolService, VendorRepository vendorRepository) {
        this.configurationService = configurationService;
        this.ticketpoolService = ticketpoolService;
        this.vendorRepository = vendorRepository;


    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }


@Override
public void run(){
    Configuration configuration = configurationService.getConfiguration();
    globalV=configuration.getTotalTickets();
    while(globalV > 0){

        Ticket ticket =  new Ticket();

        Vendor v1 = new Vendor(this.vendorId,Thread.currentThread().getName());
        ticketpoolService.addTickets(ticket,vendorId);
        vendorRepository.save(v1);

        globalV--;
        try {
            Thread.sleep(configuration.getTicketReleaseRate());

        }catch (InterruptedException e){
            System.out.println("case Vendor");
            logger.warning("Vendor interrupted: " + e.getMessage());
            throw new RuntimeException(e);


        }
    }
    logger.info( " has released all tickets.");
}







}
