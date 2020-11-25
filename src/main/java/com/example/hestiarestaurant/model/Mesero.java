package com.example.hestiarestaurant.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mesero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idMesero;

    private Integer rutMesero;
    private String nombre;
    private String apellido;

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

    public Integer getRutMesero() {
        return rutMesero;
    }

    public void setRutMesero(Integer rutMesero) {
        this.rutMesero = rutMesero;
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

}
