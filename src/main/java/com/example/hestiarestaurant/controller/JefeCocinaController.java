package com.example.hestiarestaurant.controller;

import com.example.hestiarestaurant.exception.HestiaException;
import com.example.hestiarestaurant.model.JefeCocina;
import com.example.hestiarestaurant.service.JefeCocinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jefeCocina")
public class JefeCocinaController {

    @Autowired
    JefeCocinaService jefeCocinaService;

    @GetMapping("/")
    public ResponseEntity<List<JefeCocina>> getAllJefeCocina(){
        List<JefeCocina> jefeCocinaList = jefeCocinaService.listAll();
        if(jefeCocinaList.isEmpty()){
            return new ResponseEntity<List<JefeCocina>>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<List<JefeCocina>>(jefeCocinaList,HttpStatus.OK);
        }
    }

    @PostMapping("/")
    public ResponseEntity<JefeCocina> crearJefeCocina(@RequestBody JefeCocina jefeCocina){
        try {
            JefeCocina jefeCocinaNuevo = jefeCocinaService.save(jefeCocina);
            return new ResponseEntity<JefeCocina>(jefeCocinaNuevo, HttpStatus.CREATED);
        }
        catch (HestiaException e){
            return new ResponseEntity<JefeCocina>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<JefeCocina> getJefeCocinaById(@PathVariable(value = "id") int jefeCocinaBuscar){
        JefeCocina jefeCocina = jefeCocinaService.findById(jefeCocinaBuscar);
        if (jefeCocina != null) {
            return new ResponseEntity<JefeCocina>(jefeCocina,HttpStatus.OK);
        } else {
            return new ResponseEntity<JefeCocina>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> borrarJefeCocina(@PathVariable(value = "id") int jefeCocinaBuscar){
        boolean isRemoved = jefeCocinaService.delete(jefeCocinaBuscar);
        if(!isRemoved) {
            return new ResponseEntity<Integer>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Integer>(jefeCocinaBuscar, HttpStatus.OK);
    }
}