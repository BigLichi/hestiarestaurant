package com.example.hestiarestaurant.service;

import com.example.hestiarestaurant.model.Ingrediente;
import com.example.hestiarestaurant.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredienteServiceImplementation implements IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Override
    public List<Ingrediente> listAll(){
        return ingredienteRepository.findAll();
    }

    @Override
    public Ingrediente save(Ingrediente ingrediente){
        return ingredienteRepository.save(ingrediente);
    }

    @Override
    public Ingrediente findById(int id){
        return ingredienteRepository.getOne(id);
    }

    @Override
    public void delete(int id){
        ingredienteRepository.deleteById(id);
    }

}