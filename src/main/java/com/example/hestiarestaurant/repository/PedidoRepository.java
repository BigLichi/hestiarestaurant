package com.example.hestiarestaurant.repository;

import com.example.hestiarestaurant.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
