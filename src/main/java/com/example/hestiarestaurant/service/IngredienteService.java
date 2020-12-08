package com.example.hestiarestaurant.service;

import com.example.hestiarestaurant.exception.HestiaException;
import com.example.hestiarestaurant.model.Ingrediente;
import java.util.List;

public interface IngredienteService {
    List<Ingrediente> listAll();

    Ingrediente save(Ingrediente ingrediente) throws HestiaException;

    Ingrediente findById(int Id);

    boolean delete(int Id);
}
