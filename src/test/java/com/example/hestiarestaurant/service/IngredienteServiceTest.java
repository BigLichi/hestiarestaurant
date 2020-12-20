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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IngredienteServiceTest {

    @Mock
    IngredienteRepository ingredienteRepository;

    @InjectMocks
    IngredienteServiceImplementation ingredienteServiceImp;

    @Test
    void generaUnaListaConTodosLosIngredientesExistentes() {
        //Test listAll()
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
    void trataDeListarLosIngredientesPeroLaListaEstaVaciaYNoRetornaNada(){
        //
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
    void trataDeGuardarUnIngredientePeroElIngresadoPorParametroEsNull(){
        //Test save() == null
        //Act + Assert
        assertThrows(HestiaException.class,()->ingredienteServiceImp.save(null));
    }

    @Test
    void guardaElIngredienteIngresadoPorParametroYAgregaElIngrediente() throws HestiaException {
        //Test save() guarda el ingrediente
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
    void guardarUnIngredienteYaExistente(){
        //Test save() cuando ya existia anteriormente
        //Arrange
        Ingrediente ingrediente = new Ingrediente(1,3,"Pancit", "Unidad");
        when(ingredienteRepository.findById(1)).thenReturn(java.util.Optional.of(ingrediente));

        //Act + Assert
        assertThrows(HestiaException.class,()-> ingredienteServiceImp.save(ingrediente));
    }

    @Test
    void encuentraElIngredienteSegunSuIdYLoDevuelve() {
        //Test findById()
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
    void encuentraAlJefeCocinaSegunSuIdYNoExiste() {
        //Test Encuentra el Ingrediente segun su ID, pero findById() == Null
        //Arrange
        Ingrediente ingrediente = new Ingrediente(1, 3, "Pancito", "Unidad");
        when(ingredienteRepository.getOne(1)).thenReturn(null);
        Ingrediente resultado;

        //Act
        resultado = ingredienteServiceImp.findById(1);

        //Assert
        assertNull(resultado);
    }

    @Test
    void borrarCuandoSeEncuentreUnIngrediente() {
        //Test delete()
        //Arrange
        Ingrediente ingrediente = new Ingrediente(1, 3, "Pancito", "Unidad");
        doNothing().when(ingredienteRepository).deleteById(1);
        Boolean response;

        //Act
        response = ingredienteServiceImp.delete(1);

        //Assert
        assertTrue(response);
    }
}