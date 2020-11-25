package com.example.hestiarestaurant.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class JefeDeCocina {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCocinero;

    private Integer rutCocinero;
    private String nombre;
    private String apellido;

    public JefeDeCocina() {
    }

    public JefeDeCocina(Integer idCocinero, Integer rutCocinero, String nombre, String apellido) {
        this.idCocinero = idCocinero;
        this.rutCocinero = rutCocinero;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Integer getIdCocinero() {
        return idCocinero;
    }

    public void setIdCocinero(Integer idCocinero) {
        this.idCocinero = idCocinero;
    }

    public Integer getRutCocinero() {
        return rutCocinero;
    }

    public void setRutCocinero(Integer rutCocinero) {
        this.rutCocinero = rutCocinero;
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
