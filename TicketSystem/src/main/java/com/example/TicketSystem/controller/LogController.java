package com.example.TicketSystem.controller;

import com.example.TicketSystem.service.TicketpoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/logs")
@CrossOrigin(origins = "http://localhost:3000")
public class LogController {

    @Autowired
    private TicketpoolService ticketpoolService;

    @GetMapping("/get")
    public List<String> getLogs() {
        // Fetch and return logs as a list
        return ticketpoolService.getLogs().stream().collect(Collectors.toList());
    }
}
