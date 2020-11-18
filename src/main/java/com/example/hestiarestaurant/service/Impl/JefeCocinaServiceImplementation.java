package com.example.hestiarestaurant.service.Impl;

import com.example.hestiarestaurant.model.JefeCocina;
import com.example.hestiarestaurant.repository.JefeCocinaRepository;
import com.example.hestiarestaurant.service.JefeCocinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JefeCocinaServiceImplementation implements JefeCocinaService{

    @Autowired
    private JefeCocinaRepository jefeCocinaRepository;

    @Override
    public List<JefeCocina> listAll(){
        return jefeCocinaRepository.findAll();
    }

    @Override
    public JefeCocina save(JefeCocina jefeCocina){
        return jefeCocinaRepository.save(jefeCocina);
    }

    @Override
    public JefeCocina findById(int id){
        return jefeCocinaRepository.getOne(id);
    }

    @Override
    public void delete(int id){
        jefeCocinaRepository.deleteById(id);
    }


}
