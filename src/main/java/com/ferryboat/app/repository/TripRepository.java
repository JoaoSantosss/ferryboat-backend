package com.ferryboat.app.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ferryboat.app.entity.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, UUID>{

}
