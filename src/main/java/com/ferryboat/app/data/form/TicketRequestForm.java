package com.ferryboat.app.data.form;

import java.util.UUID;

import lombok.Data;

@Data
public class TicketRequestForm {
    private UUID tripId;
    private UUID carTypeId; // Optional — null if no car
}