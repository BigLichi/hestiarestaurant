package com.example.hestiarestaurant.service;

import com.example.hestiarestaurant.model.Plato;
import com.example.hestiarestaurant.repository.PlatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatoServiceImplementation implements PlatoService{

    @Autowired
    private PlatoRepository platoRespository;

    @Override
    public List<Plato> listAll(){
        return platoRespository.findAll();
    }

    @Override
    public Plato save(Plato plato){
        return platoRespository.save(plato);
    }

    @Override
    public Plato findById(int id){
        return platoRespository.getOne(id);
    }

    @Override
    public void delete(int id){
        platoRespository.deleteById(id);
    }
}
