package com.example.TicketSystem.repository;
import com.example.TicketSystem.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer,String> {


}
