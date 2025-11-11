package com.ferryboat.app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ferryboat.app.data.dto.TicketResponseDTO;
import com.ferryboat.app.data.form.TicketRequestForm;
import com.ferryboat.app.service.TicketService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/buy")
    public ResponseEntity<TicketResponseDTO> buyTrip(@RequestBody TicketRequestForm dto) {
        return ResponseEntity.ok(ticketService.buyTicket(dto));
    }

    @GetMapping
    public ResponseEntity<List<TicketResponseDTO>> getMyTickets() {
        return ResponseEntity.ok(ticketService.getUserTickets());
    }
}