package com.ferryboat.app.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ferryboat.app.data.dto.TicketResponseDTO;
import com.ferryboat.app.data.form.TicketRequestForm;
import com.ferryboat.app.entity.CarType;
import com.ferryboat.app.entity.Ticket;
import com.ferryboat.app.entity.Trip;
import com.ferryboat.app.entity.User;
import com.ferryboat.app.entity.enums.UserTripStatus;
import com.ferryboat.app.entity.id.TicketId;
import com.ferryboat.app.repository.CarTypeRepository;
import com.ferryboat.app.repository.TicketRepository;
import com.ferryboat.app.repository.TripRepository;
import com.ferryboat.app.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TripRepository tripRepository;
    private final CarTypeRepository carTypeRepository;
    private final UserRepository userRepository;

    @Transactional
    public TicketResponseDTO buyTicket(TicketRequestForm dto) {
        // Get authenticated user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = auth.getName();
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Load trip
        Trip trip = tripRepository.findById(dto.getTripId())
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        // Check trip capacity
        int humanCount = trip.getHumanCapacityCount() + 1;
        if (humanCount > trip.getHumanCapacity()) {
            throw new RuntimeException("No more human seats available for this trip");
        }

        int vehicleCount = trip.getVehicleCapacityCount();
        CarType carType = null;

        if (dto.getCarTypeId() != null) {
            carType = carTypeRepository.findById(dto.getCarTypeId())
                    .orElseThrow(() -> new RuntimeException("Car type not found"));

            vehicleCount += carType.getSpace();
            if (vehicleCount > trip.getVehicleCapacity()) {
                throw new RuntimeException("No more vehicle space available for this trip");
            }
        }

        // Update trip counts
        trip.setHumanCapacityCount(humanCount);
        trip.setVehicleCapacityCount(vehicleCount);
        trip.setUpdatedAt(Instant.now());
        tripRepository.save(trip);

        // Create ticket
        Ticket ticket = new Ticket();
        TicketId ticketId = new TicketId();
        ticketId.setUserId(user.getId());
        ticketId.setTripId(trip.getId());
        ticket.setId(ticketId);
        ticket.setUser(user);
        ticket.setTrip(trip);
        ticket.setUserTripStatus(UserTripStatus.PAID);
        ticket.setCarType(carType);
        ticket.setCreatedAt(Instant.now());

        Ticket saved = ticketRepository.save(ticket);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<TicketResponseDTO> getUserTickets() {
        // Get authenticated user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = auth.getName();
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ticketRepository.findByUserId(user.getId())
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private TicketResponseDTO toResponse(Ticket ticket) {
        TicketResponseDTO dto = new TicketResponseDTO();
        dto.setUserId(ticket.getUser().getId());
        dto.setTripId(ticket.getTrip().getId());
        dto.setTripFrom(ticket.getTrip().getFrom());
        dto.setTripTo(ticket.getTrip().getTo());
        dto.setStatus(ticket.getUserTripStatus());
        dto.setCarType(ticket.getCarType() != null ? ticket.getCarType().getCarType() : null);
        dto.setTripDate(ticket.getTrip().getTripDate());
        dto.setCreatedAt(ticket.getCreatedAt());
        return dto;
    }
}