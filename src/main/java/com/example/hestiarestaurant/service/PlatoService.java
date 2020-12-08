package com.example.hestiarestaurant.service;

import com.example.hestiarestaurant.exception.HestiaException;
import com.example.hestiarestaurant.model.Plato;
import java.util.List;

public interface PlatoService {
    List<Plato> listAll();

    Plato save(Plato plato) throws HestiaException;

    Plato findById(int Id);

    boolean delete(int Id);
}
