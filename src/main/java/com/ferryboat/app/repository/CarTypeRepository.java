package com.ferryboat.app.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ferryboat.app.entity.CarType;

@Repository
public interface CarTypeRepository extends JpaRepository<CarType, UUID>{

}
