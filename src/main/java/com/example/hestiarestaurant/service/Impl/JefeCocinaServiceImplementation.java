package com.example.hestiarestaurant.service.Impl;

import com.example.hestiarestaurant.exception.HestiaException;
import com.example.hestiarestaurant.model.JefeCocina;
import com.example.hestiarestaurant.repository.JefeCocinaRepository;
import com.example.hestiarestaurant.service.JefeCocinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JefeCocinaServiceImplementation implements JefeCocinaService{

    @Autowired
    private JefeCocinaRepository jefeCocinaRepository;

    @Override
    public List<JefeCocina> listAll(){
        return jefeCocinaRepository.findAll();
    }

    @Override
    public JefeCocina save(JefeCocina jefeCocina) throws HestiaException {
        if(jefeCocina != null){
            Optional<JefeCocina> jefeCocinaHallado = jefeCocinaRepository.findById(jefeCocina.getIdCocinero());
            if(jefeCocinaHallado.isEmpty()){
                return jefeCocinaRepository.save(jefeCocina);
            }
        }
        throw new HestiaException();
    }

    @Override
    public JefeCocina findById(int id){
        return jefeCocinaRepository.getOne(id);
    }

    @Override
    public boolean delete(int id){
        jefeCocinaRepository.findById(id);
        return true;
    }


}
