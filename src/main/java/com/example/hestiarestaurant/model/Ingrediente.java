package com.example.hestiarestaurant.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idIngrediente;
 //   @OneToMany(mappedBy = "ingrediente")
  //  Set<DetallePlato> DetallePlatoSet;

    private int cantidad;
    private String nombre;
    private String unidadDeMedida;

    public Ingrediente() {
    }

    public Ingrediente(int ID_Ingrediente, int cantidad, String nombre, String unidadDeMedida) {
        this.idIngrediente = ID_Ingrediente;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.unidadDeMedida = unidadDeMedida;
    }


    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(String unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }

}