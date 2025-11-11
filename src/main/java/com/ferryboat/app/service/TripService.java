package com.ferryboat.app.service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ferryboat.app.data.dto.TripDTO;
import com.ferryboat.app.data.form.TripForm;
import com.ferryboat.app.entity.Trip;
import com.ferryboat.app.repository.TripRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    public TripDTO createTrip(TripForm form) {
        Trip trip = new Trip();
        trip.setFrom(form.getFrom());
        trip.setTo(form.getTo());
        trip.setTripDate(form.getTripDate());
        trip.setHumanCapacity(form.getHumanCapacity());
        trip.setVehicleCapacity(form.getVehicleCapacity());
        trip.setHumanCapacityCount(form.getHumanCapacityCount());
        trip.setVehicleCapacityCount(form.getVehicleCapacityCount());
        trip.setTripStatus(form.getTripStatus());
        trip.setPrice(form.getPrice());
        trip.setCreatedAt(Instant.now());
        trip.setUpdatedAt(Instant.now());
        tripRepository.save(trip);
        return toDTO(trip);
    }

    public TripDTO updateTrip(UUID id, TripForm form) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found with id: " + id));

        trip.setFrom(form.getFrom());
        trip.setTo(form.getTo());
        trip.setTripDate(form.getTripDate());
        trip.setHumanCapacity(form.getHumanCapacity());
        trip.setVehicleCapacity(form.getVehicleCapacity());
        trip.setHumanCapacityCount(form.getHumanCapacityCount());
        trip.setVehicleCapacityCount(form.getVehicleCapacityCount());
        trip.setTripStatus(form.getTripStatus());
        trip.setPrice(form.getPrice());
        trip.setUpdatedAt(Instant.now());

        tripRepository.save(trip);
        return toDTO(trip);
    }

    public List<TripDTO> getAllTrips() {
        return tripRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public TripDTO getTripById(UUID id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found with id: " + id));
        return toDTO(trip);
    }

    public List<TripDTO> getTripsByDate(LocalDate date) {
        return tripRepository.findByTripDate(date)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteTrip(UUID id) {
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found with id: " + id));
        tripRepository.delete(trip);
    }

    private TripDTO toDTO(Trip trip) {
        TripDTO dto = new TripDTO();
        dto.setId(trip.getId());
        dto.setFrom(trip.getFrom());
        dto.setTo(trip.getTo());
        dto.setTripDate(trip.getTripDate());
        dto.setHumanCapacity(trip.getHumanCapacity());
        dto.setVehicleCapacity(trip.getVehicleCapacity());
        dto.setHumanCapacityCount(trip.getHumanCapacityCount());
        dto.setVehicleCapacityCount(trip.getVehicleCapacityCount());
        dto.setTripStatus(trip.getTripStatus());
        dto.setPrice(trip.getPrice());
        return dto;
    }
}
