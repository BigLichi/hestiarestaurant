package com.example.hestiarestaurant.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Ingrediente {
    @Id
    private Integer idIngrediente;
    @OneToMany(mappedBy = "ingrediente")
    Set<DetallePlato> detalleplato;

    private int cantidad;
    private String nombre;
    private String unidadDeMedida;

    private LocalDateTime fechaVencimiento;

    public Ingrediente() {
    }

    public Ingrediente(int ID_Ingrediente, int cantidad, String nombre, String unidadDeMedida, LocalDateTime fecha_Vencimiento) {
        this.idIngrediente = ID_Ingrediente;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.unidadDeMedida = unidadDeMedida;
        fechaVencimiento = fecha_Vencimiento;
    }

    public int getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(int ID_Ingrediente) {
        this.idIngrediente = ID_Ingrediente;
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

    public LocalDateTime getFecha_Vencimiento() {
        return fechaVencimiento;
    }

    public void setFecha_Vencimiento(LocalDateTime fecha_Vencimiento) {
        fechaVencimiento = fecha_Vencimiento;
    }
}