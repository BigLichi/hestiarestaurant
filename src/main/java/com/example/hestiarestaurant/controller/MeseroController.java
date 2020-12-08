package com.example.hestiarestaurant.controller;

import com.example.hestiarestaurant.model.Mesero;
import com.example.hestiarestaurant.service.MeseroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meseros")
public class MeseroController {

    @Autowired
    MeseroService meseroService;

    @GetMapping("/")
    public ResponseEntity<List<Mesero>> getAllMeseros(){
        List<Mesero> meseroList = meseroService.listAll();
        if(meseroList.isEmpty()){
            return new ResponseEntity<List<Mesero>>(HttpStatus.NO_CONTENT);
        }else
        {
            return new ResponseEntity<List<Mesero>>(meseroList, HttpStatus.OK);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Mesero> crearMesero(@RequestBody Mesero mesero){
        Mesero meseroNuevo = meseroService.save(mesero);
        return new ResponseEntity<Mesero>(meseroNuevo,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mesero> getPlatoById(@PathVariable(value = "id") int meseroBuscar){
        Mesero mesero = meseroService.findById(meseroBuscar);
        if (mesero != null){
            return new ResponseEntity<Mesero>(mesero,HttpStatus.OK);
        } else {
            return new ResponseEntity<Mesero>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> borrarMesero(@PathVariable(value = "id") int meseroBuscar){
       boolean isRemoved = meseroService.delete(meseroBuscar);
        if(!isRemoved) {
            return new ResponseEntity<Integer>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Integer>(meseroBuscar, HttpStatus.OK);
    }
}
