package com.example.hestiarestaurant.model;

import javax.persistence.*;

@Entity
public class DetallePlato {

    @EmbeddedId
    DetallePlatoKey id;

    @ManyToOne
    @MapsId("idPlato")
    @JoinColumn(name = "id_plato")
    Plato plato;

    @ManyToOne
    @MapsId("idIngrediente")
    @JoinColumn(name = "id_ingrediente")
    Ingrediente ingrediente;

    Integer cantidad;

}
