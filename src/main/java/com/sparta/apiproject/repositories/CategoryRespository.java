package com.sparta.apiproject.repositories;

import com.sparta.apiproject.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRespository extends JpaRepository<CategoryEntity, String> {
}
