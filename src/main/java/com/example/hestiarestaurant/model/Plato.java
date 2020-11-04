package com.example.hestiarestaurant.model;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Plato {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPlato;

    @OneToMany(mappedBy = "plato")
    Set<DetallePlato> detalleplato;

    private String nombre;
    private int precio;

    public Plato() {
    }

    public Plato(Integer idPlato, String nombre, int precio) {
        this.idPlato = idPlato;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
