package com.example.hestiarestaurant.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Pedido {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer nroPedido;

    private Integer mesa;
    private Integer recibo;
    private Date fecha;
    private boolean estado;

    public Pedido() {
    }

    public Pedido(Integer nroPedido, Integer mesa, Integer recibo, Date fecha, boolean estado) {
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

    public Integer getMesa() {
        return mesa;
    }

    public void setMesa(Integer mesa) {
        this.mesa = mesa;
    }

    public Integer getRecibo() {
        return recibo;
    }

    public void setRecibo(Integer recibo) {
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
