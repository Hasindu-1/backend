package com.example.TicketSystem.controller;

import com.example.TicketSystem.model.Configuration;
import com.example.TicketSystem.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/configure")
@CrossOrigin(origins = "http://localhost:3000")
public class ConfigurationController {

    @Autowired
    private ConfigurationService configurationService;

    @PostMapping("/save")
    public String saveConfiguration(@RequestBody Configuration configuration) {
        configurationService.saveConfiguration(configuration);
        return " Successfully Saved";
    }

//    @PostMapping("/save")
//    public ResponseEntity<String> saveConfiguration(@RequestBody Configuration configuration) {
//        System.out.println("Received configuration: " + configuration);
//        return ResponseEntity.ok("Configuration saved successfully!");
//    }

    @GetMapping("/get")
    public Configuration getConfiguration() {
        configurationService.getConfiguration();
        return configurationService.getConfiguration();

    }


}
