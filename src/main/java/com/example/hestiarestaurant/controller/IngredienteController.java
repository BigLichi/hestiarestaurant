package com.example.hestiarestaurant.controller;


import com.example.hestiarestaurant.model.Ingrediente;
import com.example.hestiarestaurant.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredientes")
public class IngredienteController {

    @Autowired
    IngredienteService ingredienteService;

    @GetMapping("/")
    public List<Ingrediente> getAllIngredientes(){
        return ingredienteService.listAll();
    }

    @PostMapping("/")
    public Ingrediente crearIngrediente(@RequestBody Ingrediente ingrediente){
        return ingredienteService.save(ingrediente);
    }

    @GetMapping("/{id}")
    public Ingrediente getIngredienteById(@PathVariable(value = "id") int ingredienteBuscar){
        return ingredienteService.findById(ingredienteBuscar);
    }

    @DeleteMapping("/{id}")
    public void borrarIngrediente(@PathVariable(value = "id") int ingredienteBuscar){
        ingredienteService.delete(ingredienteBuscar);
    }
}