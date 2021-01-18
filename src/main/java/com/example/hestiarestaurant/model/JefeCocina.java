package com.example.hestiarestaurant.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class JefeCocina {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCocinero;

    private String nombre;
    private String apellido;
    private int rutCocinero;

    public JefeCocina() {
    }

    public JefeCocina(Integer idCocinero, String nombre, String apellido, int rutCocinero) {
        this.idCocinero = idCocinero;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rutCocinero = rutCocinero;
    }

    public Integer getIdCocinero() {
        return idCocinero;
    }

    public void setIdCocinero(Integer idCocinero) {
        this.idCocinero = idCocinero;
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

    public int getRutCocinero() {
        return rutCocinero;
    }

    public void setRutCocinero(int rutCocinero) {
        this.rutCocinero = rutCocinero;
    }
}
