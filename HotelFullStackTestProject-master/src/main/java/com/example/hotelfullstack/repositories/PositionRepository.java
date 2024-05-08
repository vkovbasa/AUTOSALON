package com.example.hotelfullstack.repositories;

import com.example.hotelfullstack.models.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PositionRepository extends JpaRepository<Position, Long> {

    @Query("SELECT p FROM Position p RIGHT JOIN Employee e ON p.id = e.position.id")
    List<Position> findPositionsWithEmployees();
}
