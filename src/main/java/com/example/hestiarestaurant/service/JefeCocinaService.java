package com.example.hestiarestaurant.service;

import com.example.hestiarestaurant.model.JefeCocina;

import java.util.List;

public interface JefeCocinaService {

        List<JefeCocina> listAll();

        JefeCocina save(JefeCocina jefeCocina);

        JefeCocina findById(int Id);

        void delete(int Id);

}
