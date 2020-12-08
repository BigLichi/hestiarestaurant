package com.example.hestiarestaurant.service;

import com.example.hestiarestaurant.exception.HestiaException;
import com.example.hestiarestaurant.model.Ingrediente;
import com.example.hestiarestaurant.repository.IngredienteRepository;
import com.example.hestiarestaurant.service.Impl.IngredienteServiceImplementation;
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
class IngredienteServiceTest {

    @Mock
    IngredienteRepository ingredienteRepository;

    @InjectMocks
    IngredienteServiceImplementation ingredienteServiceImp;

    @Test
    void listAll() {
        //Arrange
        Ingrediente ing1= new Ingrediente(1,5,"palta","kg");
        Ingrediente ing2= new Ingrediente(2,5,"tomate","kg");
        ArrayList<Ingrediente> listaIng= new ArrayList<>();
        listaIng.add(ing1);
        listaIng.add(ing2);
        when(ingredienteRepository.findAll()).thenReturn(listaIng);
        List<Ingrediente> resultado;

        //Act
        resultado=ingredienteServiceImp.listAll();

        //Assert
        assertNotNull(resultado);
    }

    @Test
    void ListAllPeroRetornaNada(){
        //Arrange
        ArrayList<Ingrediente> listaVacia=new ArrayList<Ingrediente>();
        when(ingredienteRepository.findAll()).thenReturn(listaVacia);
        List<Ingrediente> resultado;

        //Act
        resultado=ingredienteServiceImp.listAll();

        //Assert
        assertTrue(resultado.isEmpty());
    }

    @Test
    void saveNull(){

    }
    @Test
    void saveNoGuardado() throws HestiaException {
        //Arrange
        Ingrediente ing= new Ingrediente(1,5,"aguacate", "kg");
        when(ingredienteRepository.save(ing)).thenReturn(ing);

        //Act
        Ingrediente resultado= ingredienteServiceImp.save(ing);

        //Assert
        assertNotNull(resultado);
        assertEquals(ing,resultado);
    }

    @Test
    void saveYaGuardado(){

    }
    @Test
    void findById() {
        //Arrange
        Ingrediente ing=new Ingrediente(1,5,"avocado","kg");
        when(ingredienteRepository.getOne(1)).thenReturn(ing);

        //Act
        Ingrediente resultado=ingredienteServiceImp.findById(1);

        //Assert
        assertNotNull(resultado);
        assertEquals(ing,resultado);
    }

    @Test
    void delete() {

    }
}