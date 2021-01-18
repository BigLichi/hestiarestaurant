package com.example.hestiarestaurant.controller;

import com.example.hestiarestaurant.exception.HestiaException;
import com.example.hestiarestaurant.model.Mesero;
import com.example.hestiarestaurant.service.MeseroService;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
class MeseroControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MeseroService meseroService;
    private JacksonTester<Mesero> jsonMesero;

    @InjectMocks
    private MeseroController meseroController;

    @BeforeEach
    void setup(){
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders.standaloneSetup(meseroController).build();
    }

    @Test
    void obtieneUnaListaDeMeseros() throws Exception {
        //test getAllMeseros
        //Given
        Mesero mesero1 = new Mesero(1, "pablo", "ladron", 89769897);
        Mesero mesero2 = new Mesero(2, "pablo", "mentiroso", 89769896);
        ArrayList<Mesero> meseros= new ArrayList<Mesero>();
        meseros.add(mesero1);
        meseros.add(mesero2);
        given(meseroService.listAll()).willReturn(meseros);

        //when
        MockHttpServletResponse response = mockMvc.perform(get("/meseros/")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        //then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void obtenerTodosLosMeserosYRetornaNoContent() throws Exception {
        //test getAllMeseros pero la lista no tiene datos
        //Given
        ArrayList<Mesero> meseros = new ArrayList<Mesero>();
        given(meseroService.listAll()).willReturn(meseros);

        //When
        MockHttpServletResponse response = mockMvc.perform(get("/meseros/")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        //Then
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

    @Test
    void crearMeserosLoAceptaYRetornaOK() throws Exception {
        //test crearMesero() retorna OK y lo agrega
        //Given
        Mesero pablo = new Mesero(1, "pablo", "mentiroso", 98799778);
        when(meseroService.save(Mockito.any(Mesero.class)))
                .thenReturn(pablo);
        //When
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/meseros/")
                .accept(MediaType.APPLICATION_JSON).content(jsonMesero.write(pablo).getJson()).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        //Then
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals(jsonMesero.write(pablo).getJson(), response.getContentAsString());
    }

    @Test
    void crearMeseroErradoYRetornaBadRequest() throws Exception {
        //test getPlatoById return Bad_Request
        //Given
        Mesero pablo = new Mesero(1, "pablo", "mentiroso", 98799778);
        when(meseroService.save(Mockito.any(Mesero.class)))
                .thenThrow(HestiaException.class);
        //When
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/meseros/")
                .accept(MediaType.APPLICATION_JSON).content(jsonMesero.write(pablo).getJson()).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        //Then
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    void obtenerMeseroPorSuIdYRetornaOK() throws Exception {
        //test getMeseroById() return = OK
        //Given
        Mesero pablo = new Mesero(1, "pablo", "mentiroso", 98799778);
        given(meseroService.findById(1)).willReturn(pablo);

        //When
        MockHttpServletResponse response = mockMvc.perform(get("/meseros/1")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertEquals(jsonMesero.write(pablo).getJson(), response.getContentAsString());
    }

    @Test
    void obtenerMeseroPorSuIdYRetornaNoContent() throws Exception {
        //test getMeseroById() return = No_Content
        //Given
        given(meseroService.findById(1)).willReturn(null);

        //When
        MockHttpServletResponse response = mockMvc.perform(get("/meseros/1")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        //Then
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

    @Test
    void borrarElMeseroYRetornaOK() throws Exception {
        //test borrarMesero return = OK
        //Given
        Mesero pablo = new Mesero(1, "pablo", "mentiroso", 98799778);
        given(meseroService.delete(1)).willReturn(true);

        //When
        MockHttpServletResponse response = mockMvc.perform(delete("/meseros/1")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertEquals("1", response.getContentAsString());
    }

    @Test
    void borrarElMeseroYRetornaNotFound() throws Exception {
        //test borrarMesero return = Not_Found
        //Given
        given(meseroService.delete(1)).willReturn(false);

        //When
        MockHttpServletResponse response = mockMvc.perform(delete("/meseros/1")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        //Then
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());

    }
}