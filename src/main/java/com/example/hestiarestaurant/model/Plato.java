package com.example.hestiarestaurant.model;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Plato {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPlato;

    @OneToMany(mappedBy = "plato")
    Set<DetallePlato> DetallePlatoSet;

    private String nombre;
    private int precio;
    private boolean disponibilidad;

    public Plato() {
    }

    public Plato(Integer idPlato, Set<DetallePlato> detallePlatoSet, String nombre, int precio, boolean disponibilidad) {
        this.idPlato = idPlato;
        DetallePlatoSet = detallePlatoSet;
        this.nombre = nombre;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
    }

    public Integer getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(Integer idPlato) {
        this.idPlato = idPlato;
    }

    public Set<DetallePlato> getDetallePlatoSet() {
        return DetallePlatoSet;
    }

    public void setDetallePlatoSet(Set<DetallePlato> detallePlatoSet) {
        DetallePlatoSet = detallePlatoSet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}
