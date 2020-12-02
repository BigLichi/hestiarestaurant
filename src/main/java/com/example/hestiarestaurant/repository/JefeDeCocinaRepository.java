package com.example.hestiarestaurant.repository;

import com.example.hestiarestaurant.model.JefeDeCocina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JefeDeCocinaRepository extends JpaRepository<JefeDeCocina, Integer> {
}
