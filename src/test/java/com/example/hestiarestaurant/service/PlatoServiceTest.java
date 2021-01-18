package com.example.hestiarestaurant.service;

import com.example.hestiarestaurant.exception.HestiaException;
import com.example.hestiarestaurant.model.DetallePlato;
import com.example.hestiarestaurant.model.Plato;
import com.example.hestiarestaurant.repository.PlatoRepository;
import com.example.hestiarestaurant.service.Impl.PlatoServiceImplementation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlatoServiceTest {

    @Mock
    PlatoRepository platoRepository;

    @InjectMocks
    PlatoServiceImplementation platoServiceImplementation;

    @Test
    void generaUnaListaConTodosLosPlatos() {
        //Test listAll()
        //Arrange
        ArrayList<Plato> listaPlatos = new ArrayList<Plato>();
        Set<DetallePlato> detallePlatoSet = new HashSet<>();
        listaPlatos.add(new Plato(1, detallePlatoSet, "Arroz Con Pollo", 5000, true));
        listaPlatos.add(new Plato(2, detallePlatoSet, "Pure con Longaniza", 5000, true));
        when(platoRepository.findAll()).thenReturn(listaPlatos);
        List<Plato> resultado;

        //Act
        resultado = platoServiceImplementation.listAll();

        //Assert
        assertNotNull(resultado);
    }

    @Test
    void trataDeGenerarUnaListaDePedidosPeroNoExisteNinguno(){
        //Test listAll() == isEmpty();
        //Arrange
        ArrayList<Plato> listaVacia = new ArrayList<Plato>();
        when(platoRepository.findAll()).thenReturn(listaVacia);
        List<Plato> resultado;

        //Act
        resultado= platoServiceImplementation.listAll();

        //Assert
        assertTrue(resultado.isEmpty());
    }

    @Test
    void trataDeGuardarElPedidoPeroElIngresadoEsNull() {
        //Test save() == null
        //Act + Asserts
        assertThrows(HestiaException.class,()->platoServiceImplementation.save(null));
    }

    @Test
    void trataDeGuardarElPedidoPeroEsteYaExiste() {
        //Test save() No guarda el pedido, ya que, ya existe
        //Arrange

        Set<DetallePlato> detallePlatoSet = new HashSet<>();
        Plato plato = new Plato(1, detallePlatoSet, "Arroz Con Pollo", 5000, true);
        when(platoRepository.findById(1)).thenReturn(java.util.Optional.of(plato));

        //Act + Assert
        assertThrows(HestiaException.class,()-> platoServiceImplementation.save(plato));
    }

    @Test
    void guardaUnPedidoNoExistenteALaListaDePedidos() throws HestiaException {
        //Test save() Guarda el pedido
        //Arrange
        Set<DetallePlato> detallePlatoSet = new HashSet<>();
        Plato plato = new Plato(2, detallePlatoSet, "Pure con Longaniza", 5000, true);
        when(platoRepository.save(plato)).thenReturn(plato);

        //Act
        Plato resultado= platoServiceImplementation.save(plato);

        //Assert
        assertNotNull(resultado);
        assertEquals(plato,resultado);
    }

    @Test
    void encuentraElPedidoSegunSuIdYLoDevuelve() {
        //Test findById()
        //Arrange
        Set<DetallePlato> detallePlatoSet = new HashSet<>();
        Plato plato = new Plato(2, detallePlatoSet, "Pure con Longaniza", 5000, true);
        when(platoRepository.getOne(1)).thenReturn(plato);

        //Act
        Plato resultado = platoServiceImplementation.findById(1);

        //Assert
        assertNotNull(resultado);
        assertEquals(plato,resultado);
    }

    @Test
    void encuentraAlPedidoSegunSuIdYNoExiste() {
        //Test Encuentra el Ingrediente segun su ID, pero findById() == Null
        //Arrange
        Set<DetallePlato> detallePlatoSet = new HashSet<>();
        Plato plato = new Plato(2, detallePlatoSet, "Pure con Longaniza", 5000, true);
        when(platoRepository.getOne(1)).thenReturn(null);
        Plato resultado;

        //Act
        resultado = platoServiceImplementation.findById(1);

        //Assert
        assertNull(resultado);
    }

    @Test
    void borrarCuandoSeEncuentreUnIngrediente() {
        //Test delete()
        //Arrange
        Set<DetallePlato> detallePlatoSet = new HashSet<>();
        Plato plato = new Plato(2, detallePlatoSet, "Pure con Longaniza", 5000, true);
        doNothing().when(platoRepository).deleteById(1);
        Boolean response;

        //Act
        response = platoServiceImplementation.delete(1);

        //Assert
        assertTrue(response);
    }
}