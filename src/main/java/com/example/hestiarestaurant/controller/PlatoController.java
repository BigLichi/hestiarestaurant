package com.example.hestiarestaurant.controller;

import com.example.hestiarestaurant.exception.HestiaException;
import com.example.hestiarestaurant.model.Plato;
import com.example.hestiarestaurant.service.PlatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/platos")
public class PlatoController {

    @Autowired
    PlatoService platoService;

    @GetMapping("/")
    public ResponseEntity<List<Plato>> getAllPlatos(){

        List<Plato> platoList = platoService.listAll();
        if(platoList.isEmpty()){
            return new ResponseEntity<List<Plato>>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<List<Plato>>(platoList, HttpStatus.OK);
        }
    }


    @PostMapping("/")
    public ResponseEntity<Plato> crearPlato(@RequestBody Plato plato){
        try {
            Plato platoNuevo = platoService.save(plato);
            return new ResponseEntity<Plato>(platoNuevo, HttpStatus.OK);
        }
        catch (HestiaException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plato> getPlatoById(@PathVariable(value = "id") int platoBuscar){
        Plato plato = platoService.findById(platoBuscar);
        if(plato != null){
            return  new ResponseEntity<Plato>(plato,HttpStatus.OK);
        } else {
            return new ResponseEntity<Plato>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> borrarPlato(@PathVariable(value = "id") int platoBuscar){
        boolean isRemoved = platoService.delete(platoBuscar);
        if(!isRemoved) {
            return new ResponseEntity<Integer>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Integer>(platoBuscar, HttpStatus.OK);
    }
}
