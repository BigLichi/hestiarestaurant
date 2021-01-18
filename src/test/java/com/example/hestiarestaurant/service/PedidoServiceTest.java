package com.example.hestiarestaurant.service;

import com.example.hestiarestaurant.exception.HestiaException;
import com.example.hestiarestaurant.model.Pedido;
import com.example.hestiarestaurant.repository.PedidoRepository;
import com.example.hestiarestaurant.service.Impl.PedidoServiceImplementation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    PedidoRepository pedidoRepository;

    @InjectMocks
    PedidoServiceImplementation pedidoServiceImplementation;

    @Test
    void generaUnaListaConTodosLosPedidosRealizados() {
        //Test listAll()
        //Arrange
        ArrayList<Pedido> listaPedido = new ArrayList<Pedido>();
        listaPedido.add(new Pedido(1, 3, 3000, Calendar.getInstance().getTime(), false));
        listaPedido.add(new Pedido(2, 4, 10000, Calendar.getInstance().getTime(), false));
        when(pedidoRepository.findAll()).thenReturn(listaPedido);
        List<Pedido> resultado;

        //Act
        resultado = pedidoServiceImplementation.listAll();

        //Assert
        assertNotNull(resultado);
    }

    @Test
    void trataDeGenerarUnaListaDePedidosPeroNoExisteNinguno(){
        //Test listAll() == isEmpty();
        //Arrange
        ArrayList<Pedido> listaVacia = new ArrayList<Pedido>();
        when(pedidoRepository.findAll()).thenReturn(listaVacia);
        List<Pedido> resultado;

        //Act
        resultado= pedidoServiceImplementation.listAll();

        //Assert
        assertTrue(resultado.isEmpty());
    }

    @Test
    void trataDeGuardarElPedidoPeroElIngresadoEsNull() {
        //Test save() == null
        //Act + Asserts
        assertThrows(HestiaException.class,()->pedidoServiceImplementation.save(null));
    }

    @Test
    void trataDeGuardarElPedidoPeroEsteYaExiste() {
        //Test save() No guarda el pedido, ya que, ya existe
        //Arrange
        Pedido pedido= new Pedido(1, 3, 3000, Calendar.getInstance().getTime(), false);
        when(pedidoRepository.findById(1)).thenReturn(java.util.Optional.of(pedido));

        //Act + Assert
        assertThrows(HestiaException.class,()-> pedidoServiceImplementation.save(pedido));
    }

    @Test
    void guardaUnPedidoNoExistenteALaListaDePedidos() throws HestiaException {
        //Test save() Guarda el pedido
        //Arrange
        Pedido pedido= new Pedido(1, 3, 3000, Calendar.getInstance().getTime(), false);
        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        //Act
        Pedido resultado= pedidoServiceImplementation.save(pedido);

        //Assert
        assertNotNull(resultado);
        assertEquals(pedido,resultado);
    }

    @Test
    void encuentraElPedidoSegunSuIdYLoDevuelve() {
        //Test findById()
        //Arrange
        Pedido pedido= new Pedido(1, 3, 3000, Calendar.getInstance().getTime(), false);
        when(pedidoRepository.getOne(1)).thenReturn(pedido);

        //Act
        Pedido resultado = pedidoServiceImplementation.findById(1);

        //Assert
        assertNotNull(resultado);
        assertEquals(pedido,resultado);
    }

    @Test
    void encuentraAlPedidoSegunSuIdYNoExiste() {
        //Test Encuentra el Ingrediente segun su ID, pero findById() == Null
        //Arrange
        Pedido pedido= new Pedido(1, 3, 3000, Calendar.getInstance().getTime(), false);
        when(pedidoRepository.getOne(1)).thenReturn(null);
        Pedido resultado;

        //Act
        resultado = pedidoServiceImplementation.findById(1);

        //Assert
        assertNull(resultado);
    }

    @Test
    void borrarCuandoSeEncuentreUnIngrediente() {
        //Test delete()
        //Arrange
        Pedido pedido= new Pedido(1, 3, 3000, Calendar.getInstance().getTime(), false);
        doNothing().when(pedidoRepository).deleteById(1);
        Boolean response;

        //Act
        response = pedidoServiceImplementation.delete(1);

        //Assert
        assertTrue(response);
    }
}