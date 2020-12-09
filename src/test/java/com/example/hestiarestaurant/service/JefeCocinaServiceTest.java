package com.example.hestiarestaurant.service;

import com.example.hestiarestaurant.exception.HestiaException;
import com.example.hestiarestaurant.model.JefeCocina;
import com.example.hestiarestaurant.repository.JefeCocinaRepository;
import com.example.hestiarestaurant.service.Impl.JefeCocinaServiceImplementation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class JefeCocinaServiceTest {

    @Mock
    private JefeCocinaRepository jefeCocinaRepository;

    @InjectMocks
    private JefeCocinaServiceImplementation jefeCocinaServiceImplementation;

    @Test
    void obtengoTodosLosJefeCocina() {
        //Test listAll();
        //Arrange
        JefeCocina jefeCocina = new JefeCocina(1, "Lichi", "Gatica", 201913212);
        ArrayList<JefeCocina> jefeCocinas = new ArrayList<>();
        jefeCocinas.add(jefeCocina);
        when(jefeCocinaRepository.findAll()).thenReturn(jefeCocinas);
        List<JefeCocina> response;

        //Act
        response = jefeCocinaServiceImplementation.listAll();

        //Assert
        assertNotNull(response);
        assertAll("response",
                () -> assertEquals(1, response.get(0).getIdCocinero()),
                () -> assertEquals( "Lichi", response.get(0).getNombre()),
                () -> assertEquals( "Gatica", response.get(0).getApellido()),
                () -> assertEquals( 201913212, response.get(0).getRutCocinero())
                );
    }

    @Test
    void obtengoTodosLosJefeCocinaYNoExiste() {
        //Test Cuando listAll() == null;
        //Arrange
        ArrayList<JefeCocina> jefeCocinas = new ArrayList<>();
        when(jefeCocinaRepository.findAll()).thenReturn(jefeCocinas);
        List<JefeCocina> response;

        //Act
        response = jefeCocinaServiceImplementation.listAll();

        //Assert
        assertTrue(response.isEmpty());
        }

    @Test
    void guardarElJefeCocinaCuandoNoExiste() throws HestiaException {
        //Test save()
        //Arrange
        JefeCocina jefeCocina= new JefeCocina(1, "Lichi", "Gatica", 201913212);
        when(jefeCocinaRepository.save(jefeCocina)).thenReturn(jefeCocina);
        JefeCocina response;

        //Act
        response = jefeCocinaServiceImplementation.save(jefeCocina);

        //Assert
        assertSame(jefeCocina, response);

    }
    @Test
    void guardarElJefeCocinaCuandoExiste() {
        //Test save() cuando ya existe return Exception
        JefeCocina jefeCocina= new JefeCocina(1, "Lichi", "Gatica", 201913212);
        when(jefeCocinaRepository.findById(jefeCocina.getIdCocinero())).thenReturn(java.util.Optional.of(jefeCocina)    );

        //Act + Assert
        assertThrows(HestiaException.class, ()-> jefeCocinaServiceImplementation.save(jefeCocina));
    }

    @Test
    void guardarElJefeCocinaCuandoRecibeComoParametroNullLanzaUnaExcepcion(){
        //Test save() = null return Exception
        assertThrows(HestiaException.class, ()-> jefeCocinaServiceImplementation.save(null));
    }

    @Test
    void encuentraAlJefeCocinaSegunSuId() {
        //Test Encuentra el jefe de Cocina segun su ID findById()
        //Arrange
        JefeCocina jefeCocina = new JefeCocina(1, "Lichi", "Gatica", 201913212);
        when(jefeCocinaRepository.getOne(1)).thenReturn(jefeCocina);
        JefeCocina resultado;

        //Act
        resultado = jefeCocinaServiceImplementation.findById(1);

        //Assert
        assertNotNull(resultado);
        assertTrue(resultado.equals(jefeCocina));
    }

    @Test
    void encuentraAlJefeCocinaSegunSuIdYNoExiste() {
        //Test Encuentra el jefe de Cocina segun su ID findById() == Null
        //Arrange
        JefeCocina jefeCocina = new JefeCocina(1, "Lichi", "Gatica", 201913212);
        when(jefeCocinaRepository.getOne(1)).thenReturn(null);
        JefeCocina resultado;

        //Act
        resultado = jefeCocinaServiceImplementation.findById(1);

        //Assert
        assertNull(resultado);
    }


    @Test
    void borrarCuandoSeEncuentraElJefeCocina() {
        //Test delete()
        //Arrange
        JefeCocina jefeCocina = new JefeCocina(1, "Lichi", "Gatica", 201913212);
        doNothing().when(jefeCocinaRepository).deleteById(1);
        Boolean response;

        //Act
        response = jefeCocinaServiceImplementation.delete(1);

        //Assert
        assertTrue(response);
    }

}