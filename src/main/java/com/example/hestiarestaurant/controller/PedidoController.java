package com.example.hestiarestaurant.controller;

import com.example.hestiarestaurant.exception.HestiaException;
import com.example.hestiarestaurant.model.Pedido;
import com.example.hestiarestaurant.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @GetMapping("/")
    public ResponseEntity<List<Pedido>> getAllPedido(){
        List<Pedido> pedidoList = pedidoService.listAll();
        if(pedidoList.isEmpty()){
            return new ResponseEntity<List<Pedido>>(HttpStatus.NO_CONTENT);
        }else
        {
            return new ResponseEntity<List<Pedido>>(pedidoList, HttpStatus.OK);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido){
        try {
            Pedido pedidoNUevo = pedidoService.save(pedido);
            return new ResponseEntity<Pedido>(pedidoNUevo, HttpStatus.CREATED);
        }
        catch (HestiaException e){
            return new ResponseEntity<Pedido>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable(value = "id") int pedidoBuscar){
        Pedido pedido = pedidoService.findById(pedidoBuscar);
        if(pedido != null){
            return  new ResponseEntity<Pedido>(pedido,HttpStatus.OK);
        } else {
            return new ResponseEntity<Pedido>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> borrarPedido(@PathVariable(value = "id") int pedidoBuscar){
        boolean isRemoved = pedidoService.delete(pedidoBuscar);
        if(!isRemoved) {
            return new ResponseEntity<Integer>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Integer>(pedidoBuscar, HttpStatus.OK);
    }


}
