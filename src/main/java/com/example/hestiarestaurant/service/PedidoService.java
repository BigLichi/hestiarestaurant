package com.example.hestiarestaurant.service;

import com.example.hestiarestaurant.model.Pedido;

import java.util.List;

public interface PedidoService {

    List<Pedido> listAll();

    Pedido save(Pedido pedido);

    Pedido findById(int Id);

    boolean delete(int Id);

}
