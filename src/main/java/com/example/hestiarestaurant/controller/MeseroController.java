package com.example.hestiarestaurant.controller;

import com.example.hestiarestaurant.model.Mesero;
import com.example.hestiarestaurant.service.MeseroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meseros")
public class MeseroController {

    @Autowired
    MeseroService meseroService;

    @GetMapping("/")
    public List<Mesero> getAllMeseros(){
        return meseroService.listAll();
    }

    @PostMapping("/")
    public Mesero crearMesero(@RequestBody Mesero mesero){
        return meseroService.save(mesero);
    }

    @GetMapping("/{id}")
    public Mesero getPlatoById(@PathVariable(value = "id") int meseroBuscar){
        return meseroService.findById(meseroBuscar);
    }

    @DeleteMapping("/{id}")
    public void borrarMesero(@PathVariable(value = "id") int meseroBuscar){
        meseroService.delete(meseroBuscar);
    }
}
