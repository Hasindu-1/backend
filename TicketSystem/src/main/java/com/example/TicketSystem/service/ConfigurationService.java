package com.example.TicketSystem.service;

import com.example.TicketSystem.model.Configuration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class ConfigurationService {
    private Configuration configuration;


    public Configuration saveConfiguration(Configuration configuration) {
        saveToFile(configuration,"configure.json");
        this.configuration = configuration;
        return configuration;

    }

    public Configuration getConfiguration() {
        if (this.configuration == null) {
            return this.configuration = readFile("configure.json");
        }else {
            return this.configuration;
        }
//        readFile("configure.json");
//        return configuration;
    }

    public void saveToFile(Configuration configuration,String filename) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filename)){
            gson.toJson(configuration,writer);

        } catch (IOException exception) {
            System.out.println("There is a error called - "+exception);
        }
    }

    public  Configuration readFile(String filename) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filename)) {
            return gson.fromJson(reader, Configuration.class);


        } catch (IOException exception) {
            System.out.println("There is an error Reading this file- " + exception);
            return null;
        }
    }

    @Override
    public String toString() {
        return   "|****************************************|\n"+
                "|            Configure Details           |\n" +
                "|****************************************|\n"+
                String.format("| %-25s : %-10d |\n", "Total Tickets", configuration.getTotalTickets()) +
                String.format("| %-25s : %-10d |\n", "Ticket Release Rate", configuration.getTicketReleaseRate()) +
                String.format("| %-25s : %-10d |\n", "Customer Retrieval Rate", configuration.getCustomerRetrievalRate()) +
                String.format("| %-25s : %-10d |\n", "Max Ticket Capacity", configuration.getMaxTicketCapacity())+


                "|****************************************|\n" ;
    }

   //111
    public Configuration getConfigurationFromFile() {
        if (this.configuration == null) {
            return this.configuration = readFile("configure.json");
        }else {
            return this.configuration;
        }
    }






}
