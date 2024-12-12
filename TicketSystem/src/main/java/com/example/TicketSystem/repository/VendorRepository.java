package com.example.TicketSystem.repository;

import com.example.TicketSystem.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface VendorRepository extends JpaRepository<Vendor, String> {
}
