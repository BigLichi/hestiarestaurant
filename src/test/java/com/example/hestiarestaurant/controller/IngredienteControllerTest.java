package com.example.hestiarestaurant.controller;

import com.example.hestiarestaurant.exception.HestiaException;
import com.example.hestiarestaurant.model.Ingrediente;
import com.example.hestiarestaurant.service.IngredienteService;
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


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
class IngredienteControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IngredienteService ingredienteService;
    private JacksonTester<Ingrediente> jsonIngrediente;

    @InjectMocks
    private IngredienteController ingredienteController;

    @BeforeEach
    void setup(){
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(ingredienteController).build();
    }

    @Test
    void obtieneUnaListaDeTodosLosIngredienteExistentes() throws Exception {
        //test getAllIngredientes() return = OK
        //Given
        Ingrediente ingrediente1 = new Ingrediente(1, 40, "Zanahorias", "Kilos");
        Ingrediente ingrediente2 = new Ingrediente(2, 40, "Zapallo", "Kilos");
        ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
        ingredientes.add(ingrediente1);
        ingredientes.add(ingrediente2);
        given(ingredienteService.listAll()).willReturn(ingredientes);

        //When
        MockHttpServletResponse response = mockMvc.perform(get("/ingredientes/")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        //Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void obtieneUnaListaVaciaDeTodosLosIngredienteExistentesYRetornaNoContent() throws Exception {
        //test getAllIngredientes() return = OK
        //Given
        Ingrediente ingrediente1 = new Ingrediente(1, 40, "Zanahorias", "Kilos");
        Ingrediente ingrediente2 = new Ingrediente(2, 40, "Zapallo", "Kilos");
        ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
        ingredientes.add(ingrediente1);
        ingredientes.add(ingrediente2);
        given(ingredienteService.listAll()).willReturn(ingredientes);

        //When
        MockHttpServletResponse response = mockMvc.perform(get("/ingredientes/")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        //Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void crearIngredienteLoAceptaYRetornaOK() throws Exception {
        //test crearIngrediente() return = OK
        //Given
        Ingrediente ingrediente = new Ingrediente(1, 40, "Zanahorias", "Kilos");
        when(ingredienteService.save(Mockito.any(Ingrediente.class))).thenReturn(ingrediente);

        //When
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/ingredientes/")
                .accept(MediaType.APPLICATION_JSON).content(jsonIngrediente.write(ingrediente).getJson()).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        //Then
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals(jsonIngrediente.write(ingrediente).getJson(), response.getContentAsString());
    }

    @Test
    void crearIngredienteLoAceptaYRetornaBadRequest() throws Exception {
        //test crearIngrediente() return = BAD_REQUEST
        //Given
        Ingrediente ingrediente = new Ingrediente(1, 40, "Zanahorias", "Kilos");
        when(ingredienteService.save(Mockito.any(Ingrediente.class))).thenThrow(HestiaException.class);

        //When
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/ingredientes/")
                .accept(MediaType.APPLICATION_JSON).content(jsonIngrediente.write(ingrediente).getJson()).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        //Then
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    void obtenerUnIngredienteSegunSuIDYRetornarOK() throws Exception {
        //test getIngredienteById() return = OK
        //Given
        Ingrediente ingrediente = new Ingrediente(1, 40, "Zanahorias", "Kilos");
        given(ingredienteService.findById(1)).willReturn(ingrediente);
        //When
        MockHttpServletResponse response = mockMvc.perform(get("/ingredientes/1")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        //Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(jsonIngrediente.write(ingrediente).getJson(), response.getContentAsString());
    }

    @Test
    void obtenerUnIngredienteSegunSuIDYRetornarNoContent() throws Exception {
        //test getIngredienteById() return = NO_CONTENT
        //Given
        given(ingredienteService.findById(1)).willReturn(null);
        //When
        MockHttpServletResponse response = mockMvc.perform(get("/ingredientes/1")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        //Then
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

    @Test
    void borrarIngredienteDespuesDeBuscarloYRetornaOK() throws Exception {
        //test borrarIngrediente() return = OK
        //Given
        Ingrediente ingrediente = new Ingrediente(1, 40, "Zanahorias", "Kilos");
        given(ingredienteService.delete(1)).willReturn(true);
        //When
        MockHttpServletResponse response = mockMvc.perform(delete("/ingredientes/1")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        //Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals("1", response.getContentAsString());
    }

    @Test
    void borrarIngredienteNoEncuentraYRetornaNotFound() throws Exception {
        //test borrarIngrediente() return = NOT_FOUND
        //Given
        given(ingredienteService.delete(1)).willReturn(false);
        //When
        MockHttpServletResponse response = mockMvc.perform(delete("/ingredientes/1")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        //Then
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }
}