package com.example.hestiarestaurant.controller;

import com.example.hestiarestaurant.model.JefeCocina;
import com.example.hestiarestaurant.service.JefeCocinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jefeCocina")
public class JefeCocinaController {

    @Autowired
    JefeCocinaService jefeCocinaService;

    @GetMapping("/")
    public List<JefeCocina> getAllPlatos(){
        return jefeCocinaService.listAll();
    }

    @PostMapping("/")
    public JefeCocina crearJefeCocina(@RequestBody JefeCocina jefeCocina){
        return jefeCocinaService.save(jefeCocina);
    }

    @GetMapping("/{id}")
    public JefeCocina getJefeCocinaById(@PathVariable(value = "id") int jefeCocinaBuscar){
        return jefeCocinaService.findById(jefeCocinaBuscar);
    }

    @DeleteMapping("/{id}")
    public void borrarJefeCocina(@PathVariable(value = "id") int jefeCocinaBuscar){
        jefeCocinaService.delete(jefeCocinaBuscar);
    }
}