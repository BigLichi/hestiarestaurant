package com.example.hestiarestaurant.controller;

import com.example.hestiarestaurant.model.Plato;
import com.example.hestiarestaurant.service.PlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/platos")
public class PlatoController {

    @Autowired
    PlatoService platoService;

    @GetMapping("/")
    public List<Plato> getAllPlatos(){
        return platoService.listAll();
    }

    @PostMapping("/")
    public Plato crearPlato(@RequestBody Plato plato){
        return platoService.save(plato);
    }

    @GetMapping("/{id}")
    public Plato getPlatoById(@PathVariable(value = "id") int platoBuscar){
        return platoService.findById(platoBuscar);
    }

    @DeleteMapping("/{id}")
    public void borrarPlato(@PathVariable(value = "id") int platoBuscar){
        platoService.delete(platoBuscar);
    }
}
