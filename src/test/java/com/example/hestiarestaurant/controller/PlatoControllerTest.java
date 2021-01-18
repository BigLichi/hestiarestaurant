package com.example.hestiarestaurant.controller;

import com.example.hestiarestaurant.exception.HestiaException;
import com.example.hestiarestaurant.model.DetallePlato;
import com.example.hestiarestaurant.model.Plato;
import com.example.hestiarestaurant.service.PlatoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
class PlatoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PlatoService platoService;
    private JacksonTester<Plato> jsonPlato;

    @InjectMocks
    private PlatoController platoController;

    @BeforeEach
    void setup(){
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(platoController).build();
    }

    @Test
    void obtieneUnaListaDeTodosLosPlatos() throws Exception {
        //test getAllPlatos() return = OK
        //Given
        Set<DetallePlato> detallePlatoSet = new HashSet<>();
        Plato plato1 = new Plato(1, detallePlatoSet, "Pan", 3000, true);
        Plato plato2 = new Plato(2, detallePlatoSet, "Queso", 3000, true);
        ArrayList<Plato> platos = new ArrayList<Plato>();
        platos.add(plato1);
        platos.add(plato2);
        given(platoService.listAll()).willReturn(platos);

        //When
        MockHttpServletResponse response = mockMvc.perform(get("/platos/")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        //Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void obtieneUnaListaDeTodosLosPlatosVaciaYRetornaNoContent() throws Exception {
        //test getAllPlatos() return = No_Content
        //Given
        ArrayList<Plato> platos = new ArrayList<Plato>();
        given(platoService.listAll()).willReturn(platos);

        //When
        MockHttpServletResponse response = mockMvc.perform(get("/platos/")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        //Then
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

    @Test
    void crearPlatoLoAceptaYRetornaOK() throws Exception {
        //test crearPlato() return = CREATED
        //Given
        Set<DetallePlato> detallePlatoSet = new HashSet<>();
        Plato plato = new Plato(1, detallePlatoSet, "Pan", 3000, true);
        when(platoService.save(Mockito.any(Plato.class))).thenReturn(plato);

        //When
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/platos/")
                .accept(MediaType.APPLICATION_JSON).content(jsonPlato.write(plato).getJson()).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        //Then
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals(jsonPlato.write(plato).getJson(), response.getContentAsString());
    }

    @Test
    void crearPlatoNoAceptaYRetornaBadRequest() throws Exception {
        //test crearPlato() return = BAD_REQUEST
        //Given
        Set<DetallePlato> detallePlatoSet = new HashSet<>();
        Plato plato = new Plato(1, detallePlatoSet, "Pan", 3000, true);
        when(platoService.save(Mockito.any(Plato.class))).thenThrow(HestiaException.class);

        //When
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/platos/")
                .accept(MediaType.APPLICATION_JSON).content(jsonPlato.write(plato).getJson()).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        //Then
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    void obtenerPlatoSegunSuIDYRetornarOK() throws Exception {
        //test getPlatoById() return = OK
        //Given
        Set<DetallePlato> detallePlatoSet = new HashSet<>();
        Plato plato = new Plato(1, detallePlatoSet, "Pan", 3000, true);
        given(platoService.findById(1)).willReturn(plato);

        //When
        MockHttpServletResponse response = mockMvc.perform(get("/platos/1")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        //Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(jsonPlato.write(plato).getJson(), response.getContentAsString());
    }

    @Test
    void obtenerPlatoSegunSuIDNoEncuentraYRetornaNoContent() throws Exception {
        //test getPlatoById() return = NO_CONTENT
        //Given
        given(platoService.findById(1)).willReturn(null);

        //When
        MockHttpServletResponse response = mockMvc.perform(get("/platos/1")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        //Then
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

    @Test
    void borrarPlatoAlEncontrarloYReturnOK() throws Exception {
        //test borrarPlato() return = OK
        //Given
        Set<DetallePlato> detallePlatoSet = new HashSet<>();
        Plato plato = new Plato(1, detallePlatoSet, "Pan", 3000, true);
        given(platoService.delete(1)).willReturn(true);

        //When
        MockHttpServletResponse response = mockMvc.perform(delete("/platos/1")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        //Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("1", response.getContentAsString());
    }

    @Test
    void borrarPlatoAlNoEncontrarloYReturnNotFound() throws Exception {
        //test borrarPlato() return = NOT_FOUND
        //Given
        given(platoService.delete(1)).willReturn(false);

        //When
        MockHttpServletResponse response = mockMvc.perform(delete("/platos/1")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        //Then
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }
}