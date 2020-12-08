package com.example.hestiarestaurant.service;

import com.example.hestiarestaurant.model.Plato;
import java.util.List;

public interface PlatoService {
    List<Plato> listAll();

    Plato save(Plato plato);

    Plato findById(int Id);

    boolean delete(int Id);
}
