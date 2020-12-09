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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MeseroServiceTest {

    @Mock
    MeseroRepository meseroRepository;

    @InjectMocks
    MeseroServiceImplementation meseroServiceImp;

    @Test
    void listAll() {
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
    void listAllNull(){
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
    void savePeroNull(){
        //Act + Assert
        assertThrows(HestiaException.class,()->meseroServiceImp.save(null));
    }

    @Test
    void saveNoGuardado() throws HestiaException {
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
    void savePeroYaGuardado(){
        //Arrange
        Mesero mes= new Mesero(1,"Mario","Moya", 123456789);
        when(meseroRepository.findById(1)).thenReturn(java.util.Optional.of(mes));

        //Act + Assert
        assertThrows(HestiaException.class,()->meseroServiceImp.save(mes));
    }

    @Test
    void findById() {
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
    void delete() {
    }
}