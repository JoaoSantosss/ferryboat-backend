package com.ferryboat.app.entity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

import com.ferryboat.app.entity.enums.TripStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "trip_table")
@Data
public class Trip {
	
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "from_location")
    private String from;

    @Column(name = "to_location")
    private String to;

    private LocalDateTime tripDate;
    private Integer humanCapacity;
    private Integer vehicleCapacity;
    private Integer humanCapacityCount;
    private Integer vehicleCapacityCount;

    @Enumerated(EnumType.STRING)
    private TripStatus tripStatus;

    private Instant createdAt;
    private Instant updatedAt;

}
