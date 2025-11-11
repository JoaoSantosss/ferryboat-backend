package com.ferryboat.app.data.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.ferryboat.app.entity.enums.TripStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripDTO {
    private UUID id;
    private String from;
    private String to;
    private LocalDateTime tripDate;
    private Integer humanCapacity;
    private Integer vehicleCapacity;
    private Integer humanCapacityCount;
    private Integer vehicleCapacityCount;
    private TripStatus tripStatus;
    private BigDecimal price;
}

