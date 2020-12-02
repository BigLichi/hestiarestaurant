package com.example.hestiarestaurant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class DetallePlato {

    @EmbeddedId
    DetallePlatoKey id;

    @JsonBackReference(value = "DetallePlato")
    @ManyToOne
    @MapsId("idPlato")
    @JoinColumn(name = "id_plato")
    Plato plato;


    @JsonManagedReference(value = "DetalleIngrediente")
    @ManyToOne
    @MapsId("idIngrediente")
    @JoinColumn(name = "id_ingrediente")
    @JsonIgnore
    Ingrediente ingrediente;

    Integer cantidad;

    public DetallePlato() {
    }

    public DetallePlato(DetallePlatoKey id, Plato plato, Ingrediente ingrediente, Integer cantidad) {
        this.id = id;
        this.plato = plato;
        this.ingrediente = ingrediente;
        this.cantidad = cantidad;
    }

    public DetallePlatoKey getId() {
        return id;
    }

    public void setId(DetallePlatoKey id) {
        this.id = id;
    }

    public Plato getPlato() {
        return plato;
    }

    public void setPlato(Plato plato) {
        this.plato = plato;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "DetallePlato{" +
                "id=" + id +
                ", plato=" + plato +
                ", ingrediente=" + ingrediente +
                ", cantidad=" + cantidad +
                '}';
    }
}
