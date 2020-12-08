package com.example.hestiarestaurant.service.Impl;

import com.example.hestiarestaurant.exception.HestiaException;
import com.example.hestiarestaurant.model.Ingrediente;
import com.example.hestiarestaurant.repository.IngredienteRepository;
import com.example.hestiarestaurant.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredienteServiceImplementation implements IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Override
    public List<Ingrediente> listAll(){
        return ingredienteRepository.findAll();
    }

    @Override
    public Ingrediente save(Ingrediente ingrediente) throws HestiaException {
        if(ingrediente != null){
            Optional<Ingrediente> ingredientHallado = ingredienteRepository.findById(ingrediente.getId());
            if(ingredientHallado.isEmpty()){
                return ingredienteRepository.save(ingrediente);
            }
        }
        throw new HestiaException();
    }

    @Override
    public Ingrediente findById(int id){
        return ingredienteRepository.getOne(id);
    }

    @Override
    public boolean delete(int id){
        ingredienteRepository.deleteById(id);
        return true;
    }

}