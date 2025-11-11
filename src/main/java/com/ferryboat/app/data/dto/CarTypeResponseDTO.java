package com.ferryboat.app.data.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class CarTypeResponseDTO {
    private UUID id;
    private String carType;
    private Integer space;
}
