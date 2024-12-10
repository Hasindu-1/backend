package com.example.TicketSystem.repository;

import com.example.TicketSystem.model.TicketPool;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketPoolRepository extends JpaRepository<TicketPool, Integer> {
}
