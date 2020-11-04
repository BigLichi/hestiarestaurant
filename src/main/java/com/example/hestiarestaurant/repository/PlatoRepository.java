package com.example.hestiarestaurant.repository;

import com.example.hestiarestaurant.model.Plato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatoRepository extends JpaRepository<Plato, Integer> {

    //Funcion imprimir en pantalla lista


}
