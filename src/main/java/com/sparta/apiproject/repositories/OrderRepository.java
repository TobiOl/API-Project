package com.sparta.apiproject.repositories;

import com.sparta.apiproject.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {
}
