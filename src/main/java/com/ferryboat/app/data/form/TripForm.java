package com.ferryboat.app.data.form;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ferryboat.app.entity.enums.TripStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TripForm {
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