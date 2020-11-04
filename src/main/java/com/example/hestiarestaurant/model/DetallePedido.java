package com.example.hestiarestaurant.model;

import javax.persistence.*;

@Entity
public class DetallePedido {

    @EmbeddedId
    DetallePedidoKey id;

    @ManyToOne
    @MapsId("idPedido")
    @JoinColumn(name = "id_pedido")
    Pedido pedido;

    @ManyToOne
    @MapsId("idPlato")
    @JoinColumn(name = "id_plato")
    Plato plato;

    int cantidadPlato;
}
