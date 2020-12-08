package com.example.hestiarestaurant.controller;


import com.example.hestiarestaurant.model.Ingrediente;
import com.example.hestiarestaurant.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredientes")
public class IngredienteController {

    @Autowired
    IngredienteService ingredienteService;

    @GetMapping("/")
    public ResponseEntity<List<Ingrediente>> getAllIngredientes(){
        List<Ingrediente> ingredienteList = ingredienteService.listAll();
        if(ingredienteList.isEmpty()){
            return new ResponseEntity<List<Ingrediente>>(HttpStatus.NO_CONTENT);
        }else
        {
            return new ResponseEntity<List<Ingrediente>>(ingredienteList, HttpStatus.OK);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Ingrediente> crearIngrediente(@RequestBody Ingrediente ingrediente){
        Ingrediente ingredienteNuevo = ingredienteService.save(ingrediente);
        return new ResponseEntity<Ingrediente>(ingredienteNuevo,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingrediente> getIngredienteById(@PathVariable(value = "id") int ingredienteBuscar){
        Ingrediente ingrediente = ingredienteService.findById(ingredienteBuscar);
        if(ingrediente != null){
            return  new ResponseEntity<Ingrediente>(ingrediente,HttpStatus.OK);
        } else {
            return new ResponseEntity<Ingrediente>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> borrarIngrediente(@PathVariable(value = "id") int ingredienteBuscar){
       boolean isRemoved = ingredienteService.delete(ingredienteBuscar);
        if(!isRemoved) {
            return new ResponseEntity<Integer>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Integer>(ingredienteBuscar, HttpStatus.OK);
    }
}