package com.example.hestiarestaurant.service.Impl;

import com.example.hestiarestaurant.exception.HestiaException;
import com.example.hestiarestaurant.model.Plato;
import com.example.hestiarestaurant.repository.PlatoRepository;
import com.example.hestiarestaurant.service.PlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlatoServiceImplementation implements PlatoService {

    @Autowired
    private PlatoRepository platoRespository;

    @Override
    public List<Plato> listAll(){
        return platoRespository.findAll();
    }

    @Override
    public Plato save(Plato plato) throws HestiaException {
        if(plato != null){
            Optional<Plato> Hallado = platoRespository.findById(plato.getIdPlato());
            if(Hallado.isEmpty()){
                return platoRespository.save(plato);
            }
        }
        throw new HestiaException();
    }

    @Override
    public Plato findById(int id){
        return platoRespository.getOne(id);
    }

    @Override
    public boolean delete(int id){
        platoRespository.deleteById(id);
        return true;
    }
}
