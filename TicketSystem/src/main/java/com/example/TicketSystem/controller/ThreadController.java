package com.example.TicketSystem.controller;

import com.example.TicketSystem.repository.CustomerRepository;
import com.example.TicketSystem.repository.TicketPoolRepository;
import com.example.TicketSystem.repository.TicketRepository;
import com.example.TicketSystem.repository.VendorRepository;
import com.example.TicketSystem.service.ConfigurationService;
import com.example.TicketSystem.service.CustomerService;
import com.example.TicketSystem.service.TicketpoolService;
import com.example.TicketSystem.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/threadstart")
@CrossOrigin(origins = "http://localhost:3000")
public class ThreadController {

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private TicketPoolRepository ticketPoolRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private TicketpoolService ticketpoolService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @PostMapping("/threadrun")
    public String ThreadController() {

        TicketpoolService ticketpoolService1 = new TicketpoolService(configurationService,ticketPoolRepository,ticketRepository);

        VendorService vendorService1 = new VendorService(configurationService,ticketpoolService,vendorRepository);
        Thread thread = new Thread(vendorService1);
        thread.start();
        vendorService1.setVendorId("VENDOR_001");

        VendorService vendorService2 = new VendorService(configurationService, ticketpoolService,vendorRepository);
        Thread thread2 = new Thread(vendorService2);
        thread2.start();
        vendorService2.setVendorId("VENDOR_002");


        CustomerService customerService1 =new CustomerService(configurationService, ticketpoolService,customerRepository);
        Thread thread3 = new Thread(customerService1);
        thread3.start();
        customerService1.setCustomerId("CUSTOMER_001");

        CustomerService customerService2 =new CustomerService(configurationService, ticketpoolService,customerRepository);
        Thread thread4 = new Thread(customerService2);
        thread4.start();
        customerService2.setCustomerId("CUSTOMER_002");



        return   "Thread successfully started";



    }


}
