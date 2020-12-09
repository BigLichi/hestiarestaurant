package com.example.hestiarestaurant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class DetallePedido {

    @EmbeddedId
    DetallePedidoKey id;

    @JsonBackReference(value = "DetallePedido")
    @ManyToOne
    @MapsId("idPedido")
    @JoinColumn(name = "id_pedido")
    Pedido pedido;

    @JsonManagedReference(value = "DetallePedidoPlato")
    @ManyToOne
    @MapsId("idPlato")
    @JoinColumn(name = "id_plato")
    @JsonIgnore
    Plato plato;

    private int cantidadPlato;

    public DetallePedido(DetallePedidoKey id, Pedido pedido, Plato plato, int cantidadPlato) {
        this.id = id;
        this.pedido = pedido;
        this.plato = plato;
        this.cantidadPlato = cantidadPlato;
    }

    public DetallePedido() {

    }

    public DetallePedidoKey getId() {
        return id;
    }

    public void setId(DetallePedidoKey id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Plato getPlato() {
        return plato;
    }

    public void setPlato(Plato plato) {
        this.plato = plato;
    }

    public int getCantidadPlato() {
        return cantidadPlato;
    }

    public void setCantidadPlato(int cantidadPlato) {
        this.cantidadPlato = cantidadPlato;
    }
}
