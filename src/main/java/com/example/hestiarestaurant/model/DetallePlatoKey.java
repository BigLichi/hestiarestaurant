package com.example.hestiarestaurant.model;

import com.example.hestiarestaurant.model.Plato;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DetallePlatoKey implements Serializable {

    @Column(name = "id_plato")
    Integer idPlato;

    @Column(name = "id_ingrediente")
    Integer idIngrediente;

    public DetallePlatoKey(Integer idPlato, Integer idIngrediente) {
        this.idPlato = idPlato;
        this.idIngrediente = idIngrediente;
    }

    public DetallePlatoKey() {
    }

    public Integer getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(Integer idPlato) {
        this.idPlato = idPlato;
    }

    public Integer getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(Integer idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetallePlatoKey that = (DetallePlatoKey) o;
        return Objects.equals(idPlato, that.idPlato) &&
                Objects.equals(idIngrediente, that.idIngrediente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPlato, idIngrediente);
    }
}
