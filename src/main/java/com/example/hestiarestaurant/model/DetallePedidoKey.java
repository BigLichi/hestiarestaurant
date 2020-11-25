package com.example.hestiarestaurant.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DetallePedidoKey implements Serializable {

    @Column(name = "id_plato")
    Integer idPlato;

    @Column(name = "id_pedido")
    Integer idPedido;

    public DetallePedidoKey() {
    }

    public DetallePedidoKey(Integer idPlato, Integer idPedido) {
        this.idPlato = idPlato;
        this.idPedido = idPedido;
    }

    public Integer getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(Integer idPlato) {
        this.idPlato = idPlato;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetallePedidoKey that = (DetallePedidoKey) o;
        return idPlato.equals(that.idPlato) &&
                idPedido.equals(that.idPedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPlato, idPedido);
    }
}
