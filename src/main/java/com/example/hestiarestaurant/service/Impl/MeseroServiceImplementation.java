package com.example.hestiarestaurant.service.Impl;

import com.example.hestiarestaurant.exception.HestiaException;
import com.example.hestiarestaurant.model.Mesero;
import com.example.hestiarestaurant.repository.MeseroRepository;
import com.example.hestiarestaurant.service.MeseroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeseroServiceImplementation implements MeseroService {

    @Autowired
    private MeseroRepository meseroRepository;

    @Override
    public List<Mesero> listAll(){
        return meseroRepository.findAll();
    }

    @Override
    public Mesero save(Mesero mesero) throws HestiaException {
        if(mesero != null){
            Optional<Mesero> hallado = meseroRepository.findById(mesero.getIdMesero());
            if(hallado.isEmpty()){
                return meseroRepository.save(mesero);
            }
        }
        throw new HestiaException();
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