package com.example.hestiarestaurant.repository;

import com.example.hestiarestaurant.model.DetallePedido;
import com.example.hestiarestaurant.model.DetallePedidoKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, DetallePedidoKey> {
}
