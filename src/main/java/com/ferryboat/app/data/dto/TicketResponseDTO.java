package com.ferryboat.app.data.dto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

import com.ferryboat.app.entity.enums.UserTripStatus;

import lombok.Data;

@Data
public class TicketResponseDTO {
    private UUID userId;
    private UUID tripId;
    private String tripFrom;
    private String tripTo;
    private String carType;
    private LocalDateTime tripDate;
    private UserTripStatus status;
    private Instant createdAt;
}