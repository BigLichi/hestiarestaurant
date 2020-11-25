package com.example.hestiarestaurant.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cliente {
    @Id
    private Integer idCliente;

    private String nombre;
    private String apellido;
    private String eMail;

    public Cliente() {
    }

    public Cliente(Integer idCliente, String nombre, String apellido, String eMail) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.eMail = eMail;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
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

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
}
