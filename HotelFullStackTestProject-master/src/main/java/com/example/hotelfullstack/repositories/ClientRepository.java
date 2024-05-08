package com.example.hotelfullstack.repositories;

import com.example.hotelfullstack.models.Car;
import com.example.hotelfullstack.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByFullNameContaining(String keyword);

}

