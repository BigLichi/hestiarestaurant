package com.example.hestiarestaurant.repository;

import com.example.hestiarestaurant.model.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {

}
