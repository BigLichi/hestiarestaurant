package com.example.hestiarestaurant.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pedido {
    @Id
    private Integer nroPedido;

    private int mesa;
    private int recibo;

    public Pedido() {
    }

    public Pedido(Integer nroPedido, int mesa, int recibo) {
        this.nroPedido = nroPedido;
        this.mesa = mesa;
        this.recibo = recibo;
    }

    public Integer getNroPedido() {
        return nroPedido;
    }

    public void setNroPedido(Integer nroPedido) {
        this.nroPedido = nroPedido;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public int getRecibo() {
        return recibo;
    }

    public void setRecibo(int recibo) {
        this.recibo = recibo;
    }
}
