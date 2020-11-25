package com.example.hestiarestaurant.repository;

import com.example.hestiarestaurant.model.DetallePlato;
import com.example.hestiarestaurant.model.DetallePlatoKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallePlatoRepository extends JpaRepository<DetallePlato, DetallePlatoKey> {
}
