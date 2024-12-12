package com.example.TicketSystem.service;

import com.example.TicketSystem.model.Configuration;
import com.example.TicketSystem.model.Customer;
import com.example.TicketSystem.model.Ticket;
import com.example.TicketSystem.model.TicketPool;
import com.example.TicketSystem.repository.CustomerRepository;
import com.example.TicketSystem.repository.TicketPoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements Runnable{


    private ConfigurationService configurationService;
    private TicketpoolService ticketpoolService;
    private CustomerRepository customerRepository;
    private String customerId;


    public CustomerService( ConfigurationService customerService,TicketpoolService ticketpoolService,CustomerRepository customerRepository) {
    this.configurationService= customerService;
    this.ticketpoolService=ticketpoolService;
    this.customerRepository=customerRepository;
    }



    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public void run() {
        for (int i = 0; i < configurationService.getConfiguration().getTotalTickets(); i++) {
            try {
                Ticket ticket =new Ticket();
                ticketpoolService.buyTicket(customerId);
                if (ticket != null) {
                    Customer c1 = new Customer(customerId,Thread.currentThread().getName());
                    customerRepository.save(c1);

                    Thread.sleep(configurationService.getConfiguration().getCustomerRetrievalRate());
                } else {
                    break; // Exit if no more tickets are available
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
               // logger.warning("Customer interrupted: " + e.getMessage());
                break;
            }
        }
    }


}
