package com.example.hestiarestaurant.repository;


import com.example.hestiarestaurant.model.Mesero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeseroRepository extends JpaRepository<Mesero, Integer> {
}
