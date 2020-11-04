package com.example.hestiarestaurant.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Mesero {
    @Id
    private Integer idMesero;

    private String nombre;
    private String apellido;
    private int rutMesero;

    public Mesero() {
    }

    public Mesero(Integer idMesero, String nombre, String apellido, int rutMesero) {
        this.idMesero = idMesero;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rutMesero = rutMesero;
    }

    public Integer getIdMesero() {
        return idMesero;
    }

    public void setIdMesero(Integer idMesero) {
        this.idMesero = idMesero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getRutMesero() {
        return rutMesero;
    }

    public void setRutMesero(int rutMesero) {
        this.rutMesero = rutMesero;
    }
}
