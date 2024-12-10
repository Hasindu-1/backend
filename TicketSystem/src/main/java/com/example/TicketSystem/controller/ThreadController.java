package com.example.TicketSystem.controller;

import com.example.TicketSystem.repository.TicketPoolRepository;
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
    VendorService vendorService;

    @Autowired
    CustomerService customerService;

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private TicketPoolRepository ticketPoolRepository;

    @Autowired
    private TicketpoolService ticketpoolService;

    @PostMapping("/threadrun")
    public String ThreadController() {

        TicketpoolService ticketpoolService1 = new TicketpoolService(configurationService);

        VendorService vendorService1 = new VendorService(configurationService,ticketpoolService);
        Thread thread = new Thread(vendorService1);
        thread.start();

        VendorService vendorService2 = new VendorService(configurationService, ticketpoolService);
        Thread thread2 = new Thread(vendorService2);
        thread2.start();


        CustomerService customerService1 =new CustomerService(configurationService, ticketpoolService);
        Thread thread3 = new Thread(customerService1);
        thread3.start();

        CustomerService customerService2 =new CustomerService(configurationService, ticketpoolService);
        Thread thread4 = new Thread(customerService2);
        thread4.start();

//        if(){
//            configurationService.getConfigurationFromFile();
//        }

        return   "Thread successfully started";



    }
}
