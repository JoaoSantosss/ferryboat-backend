package com.ferryboat.app.entity;

import java.time.Instant;

import com.ferryboat.app.entity.enums.UserTripStatus;
import com.ferryboat.app.entity.id.TicketId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ticket_table")
@Data
public class Ticket {

	@EmbeddedId
    private TicketId id;

    @ManyToOne
    @MapsId("userId") // maps the userId in TicketId
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("tripId") // maps the tripId in TicketId
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @Enumerated(EnumType.STRING)
    private UserTripStatus userTripStatus;

    @ManyToOne
    @JoinColumn(name = "car_type_id")
    private CarType carType;

    private Instant createdAt;
}
