package com.ferryboat.app.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ferryboat.app.entity.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, UUID>{
	
	@Query("""
	        SELECT t FROM Trip t
	        WHERE CAST(t.tripDate AS date) = :date
	        ORDER BY t.tripDate ASC
	    """)
	    List<Trip> findByTripDate(@Param("date") LocalDate date);
	
	
	@Query("""
	        SELECT t FROM Trip t
	        WHERE CAST(t.tripDate AS date) = :date
	          AND t.from = :fromLocation
	        ORDER BY t.tripDate ASC
	    """)
	    List<Trip> findByDateAndFrom(
	            @Param("date") LocalDate date,
	            @Param("fromLocation") String fromLocation
	    );

}
	
	


