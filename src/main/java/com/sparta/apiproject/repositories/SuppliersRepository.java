package com.sparta.apiproject.repositories;


import com.sparta.apiproject.entities.SuppliersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuppliersRepository extends JpaRepository<SuppliersEntity, String> {
}
