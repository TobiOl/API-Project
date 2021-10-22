package com.sparta.apiproject.repositories;

import com.sparta.apiproject.entities.ShipperEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipperRepository extends JpaRepository<ShipperEntity, String> {
}
