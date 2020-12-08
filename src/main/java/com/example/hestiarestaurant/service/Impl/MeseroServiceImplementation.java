package com.example.hestiarestaurant.service.Impl;

import com.example.hestiarestaurant.model.Mesero;
import com.example.hestiarestaurant.repository.MeseroRepository;
import com.example.hestiarestaurant.service.MeseroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeseroServiceImplementation implements MeseroService {

    @Autowired
    private MeseroRepository meseroRepository;

    @Override
    public List<Mesero> listAll(){
        return meseroRepository.findAll();
    }

    @Override
    public Mesero save(Mesero mesero){
        return meseroRepository.save(mesero);
    }

    @Override
    public Mesero findById(int id){
        return meseroRepository.getOne(id);
    }

    @Override
    public boolean delete(int id){
        meseroRepository.deleteById(id);
        return true;
    }
}