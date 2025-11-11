package com.ferryboat.app.entity.id;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class TicketId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private UUID userId;
    private UUID tripId;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TicketId that)) return false;
        return Objects.equals(userId, that.userId) && Objects.equals(tripId, that.tripId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, tripId);
    }

}
