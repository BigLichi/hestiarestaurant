package com.example.hestiarestaurant.service;

import com.example.hestiarestaurant.model.Ingrediente;
import java.util.List;

public interface IngredienteService {
    List<Ingrediente> listAll();

    Ingrediente save(Ingrediente ingrediente);

    Ingrediente findById(int Id);

    void delete(int Id);
}
