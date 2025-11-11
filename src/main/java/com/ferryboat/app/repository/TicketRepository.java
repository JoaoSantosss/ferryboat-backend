package com.ferryboat.app.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ferryboat.app.entity.Ticket;
import com.ferryboat.app.entity.id.TicketId;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, TicketId>{
	
	List<Ticket> findByUserId(UUID userId);

}
