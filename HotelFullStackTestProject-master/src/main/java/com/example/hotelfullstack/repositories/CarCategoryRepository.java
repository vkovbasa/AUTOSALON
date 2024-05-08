package com.example.hotelfullstack.repositories;

import com.example.hotelfullstack.models.CarCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarCategoryRepository extends JpaRepository<CarCategory, Long> {
}

