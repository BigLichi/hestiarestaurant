package com.example.hestiarestaurant.service;

import com.example.hestiarestaurant.exception.HestiaException;
import com.example.hestiarestaurant.model.Mesero;
import com.example.hestiarestaurant.repository.MeseroRepository;
import com.example.hestiarestaurant.service.Impl.MeseroServiceImplementation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MeseroServiceTest {

    @Mock
    MeseroRepository meseroRepository;

    @InjectMocks
    MeseroServiceImplementation meseroServiceImp;

    @Test
    void generaUnaListaDeTodosLosMeserosExistentes() {
        //Test listAll()
        //Arrange
        Mesero mes1=new Mesero(1,"Mario", "Moya", 123456789);
        Mesero mes2=new Mesero(2,"Marcelo","Mardonez",987654321);
        ArrayList<Mesero> listaMesero= new ArrayList<Mesero>();
        listaMesero.add(mes1);
        listaMesero.add(mes2);
        when(meseroRepository.findAll()).thenReturn(listaMesero);
        List<Mesero> resultado;

        //Act
        resultado=meseroServiceImp.listAll();

        //Assert
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
    }

    @Test
    void trataDeGenerarUnaListaDeLosMeseroPeroLaListaEstaVacia(){
        //Test listAll() == null
        //Arrange
        ArrayList<Mesero> listaVacia= new ArrayList<>();
        when(meseroRepository.findAll()).thenReturn(listaVacia);
        List<Mesero> resultado;

        //Act
        resultado= meseroServiceImp.listAll();

        //Assert
        assertTrue(resultado.isEmpty());
    }

    @Test
    void intentaGuardarUnMeseroNull(){
        //Test save() == null
        //Act + Assert
        assertThrows(HestiaException.class,()->meseroServiceImp.save(null));
    }

    @Test
    void intentaGuardarUnMeseroYEsteNoExisteEnLaListaYLoGuarda() throws HestiaException {
        //Test save()
        //Arrange
        Mesero mes= new Mesero(1,"Mario","Moya", 123456789);
        when(meseroRepository.save(mes)).thenReturn(mes);

        //Act
        Mesero resultado= meseroServiceImp.save(mes);

        //Assert
        assertNotNull(resultado);
        assertEquals(mes,resultado);
    }

    @Test
    void guardarUnMeseroPeroYaEstaGuardado(){
        //Test save() cuando el mesero ya se habia guardado anteriormente
        //Arrange
        Mesero mes= new Mesero(1,"Mario","Moya", 123456789);
        when(meseroRepository.findById(1)).thenReturn(java.util.Optional.of(mes));

        //Act + Assert
        assertThrows(HestiaException.class,()->meseroServiceImp.save(mes));
    }

    @Test
    void buscarMeseroSegunSuIDYLoEncuentra() {
        //Test findById()
        //Arrange
        Mesero mes= new Mesero(1,"Mario","Moya", 123456789);
        when(meseroRepository.getOne(1)).thenReturn(mes);

        //Act
        Mesero resultado= meseroServiceImp.findById(1);

        //Assert
        assertNotNull(resultado);
        assertEquals(mes,resultado);
    }

    @Test
    void buscaUnMeseroSegunSuIdPeroNoExisteEnLaListaDeMeseros(){
        //Test Encuentra el Mesero segun su ID findById() == Null
        //Arrange
        Mesero mesero = new Mesero(1, "Lichi", "Gatica", 201913212);
        when(meseroRepository.getOne(1)).thenReturn(null);
        Mesero resultado;

        //Act
        resultado = meseroServiceImp.findById(1);

        //Assert
        assertNull(resultado);
    }

    @Test
    void borrarCuandoSeEncuentraElMesero() {
        //Test delete() entrega true
        //Arrange
        Mesero mesero = new Mesero(1, "Lichi", "Gatica", 201913212);
        doNothing().when(meseroRepository).deleteById(1);
        Boolean response;

        //Act
        response = meseroServiceImp.delete(1);

        //Assert
        assertTrue(response);
    }
}