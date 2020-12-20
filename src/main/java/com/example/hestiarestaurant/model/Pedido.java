package com.example.hestiarestaurant.model;

import javax.persistence.*;
import java.util.Set;
import java.util.Date;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer nroPedido;

    private boolean estado;
    private Date fecha;
    private int mesa;
    private int recibo;

    @OneToMany(mappedBy = "pedido")
    Set<DetallePedido> detallePedido;

    public Pedido() {
    }

    public Pedido(Integer nroPedido, int mesa, int recibo, Date fecha, boolean estado) {
        this.nroPedido = nroPedido;
        this.mesa = mesa;
        this.recibo = recibo;
        this.fecha = fecha;
        this.estado = estado;

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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}