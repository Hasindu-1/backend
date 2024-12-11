package com.example.TicketSystem.repository;

import com.example.TicketSystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {


}
