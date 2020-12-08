package com.example.hestiarestaurant.service;

import com.example.hestiarestaurant.model.Mesero;

import java.util.List;

public interface MeseroService {

    List<Mesero> listAll();

    Mesero save(Mesero mesero);

    Mesero findById(int id);

    boolean delete(int id);

}
