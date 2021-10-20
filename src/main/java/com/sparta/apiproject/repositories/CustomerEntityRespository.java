package com.sparta.apiproject.repositories;

import com.sparta.apiproject.entities.CustomersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerEntityRespository extends JpaRepository<CustomersEntity, String> {
}
