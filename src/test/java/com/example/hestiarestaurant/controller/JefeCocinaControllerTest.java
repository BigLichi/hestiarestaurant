package com.example.hestiarestaurant.controller;

import com.example.hestiarestaurant.exception.HestiaException;
import com.example.hestiarestaurant.model.JefeCocina;
import com.example.hestiarestaurant.service.JefeCocinaService;
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
class JefeCocinaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private JefeCocinaService jefeCocinaService;
    private JacksonTester<JefeCocina> jsonJefeCocina;

    @InjectMocks
    private JefeCocinaController jefeCocinaController;

    @BeforeEach
    void setup(){
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc= MockMvcBuilders.standaloneSetup(jefeCocinaController).build();
    }

    @Test
    void obtenerTodosLosJefeCocinaYRetornaOK() throws Exception {
        //test getAllJefeCocina()
        //Given
        JefeCocina pabloM= new JefeCocina(1, "Pablo", "Mainstream", 123454321);
        JefeCocina pabloF= new JefeCocina(2, "Pablo", "Facha", 987656789);
        ArrayList<JefeCocina> jefeCocinaLista= new ArrayList<JefeCocina>();
        jefeCocinaLista.add(pabloM);
        jefeCocinaLista.add(pabloF);
        given(jefeCocinaService.listAll()).willReturn(jefeCocinaLista);

        //When
        MockHttpServletResponse response= mockMvc.perform(get("/jefeCocina/")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        //Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void obtenerTodosLosJefeCocinaYRetornaNoContent() throws Exception{
        //test getAllJefeCocina() pero la lista esta vacia
        //Given
        ArrayList<JefeCocina> jefeCocinaLista= new ArrayList<JefeCocina>();
        given(jefeCocinaService.listAll()).willReturn(jefeCocinaLista);

        //When
        MockHttpServletResponse response= mockMvc.perform(get("/jefeCocina/")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        //Then
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

    @Test
    void crearJefeCocinaYRetornaOK() throws Exception {
        //test crearJefeCocina return = OK
        //Given
        JefeCocina pabloF= new JefeCocina(2, "Pablo", "Facho", 987656789);
        when(jefeCocinaService.save(Mockito.any(JefeCocina.class)))
                .thenReturn(pabloF);

        //When
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/jefeCocina/")
                .accept(MediaType.APPLICATION_JSON).content(jsonJefeCocina.write(pabloF).getJson()).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        //Then
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals(jsonJefeCocina.write(pabloF).getJson(),response.getContentAsString());
    }

    @Test
    void crearJefeCocinaErradoYRetornaBadRequest() throws Exception {
        //test crearJefeCocina() errado y retorna Bad_Request
        //Given
        JefeCocina pabloF= new JefeCocina(2, "Pablo", "Facho", 987656789);
        when(jefeCocinaService.save(Mockito.any(JefeCocina.class))).thenThrow(HestiaException.class);

        //When
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/jefeCocina/")
                .accept(MediaType.APPLICATION_JSON).content(jsonJefeCocina.write(pabloF).getJson()).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        //Then
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    void ObtenerJefeCocinaPorSuIdYRetornaOK() throws Exception {
        //test getJefeCocinaById
        //given
        JefeCocina pabloL= new JefeCocina(1, "Pablo", "Ladron", 987656789);
        given(jefeCocinaService.findById(1)).willReturn(pabloL);

        //When
        MockHttpServletResponse response= mockMvc.perform(get("/jefeCocina/1")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertEquals(jsonJefeCocina.write(pabloL).getJson(),response.getContentAsString());
    }

    @Test
    void obtenerJefeCocinaByIdYNoExisteRetornaNoContent() throws Exception {
        //test getJefeCocinaById
        //given
        given(jefeCocinaService.findById(1)).willReturn(null);

        //When
        MockHttpServletResponse response= mockMvc.perform(get("/jefeCocina/1")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        //Then
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

    @Test
    void borrarElJefeCocinaYRetornaOK() throws Exception {
        //test borrarJefeCocina
        //Given
        JefeCocina pabloL= new JefeCocina(1, "Pablo", "Ladrón", 987656789);
        given(jefeCocinaService.delete(1)).willReturn(true);

        //When
        MockHttpServletResponse response= mockMvc.perform(delete("/jefeCocina/1")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertEquals("1", response.getContentAsString());

    }

    @Test
    void borrarElJefeCocinaNoLoEncuentraYRetornaNotFound() throws Exception {
        //test borrarJefeCocina
        //Given
        given(jefeCocinaService.delete(1)).willReturn(false);

        //When
        MockHttpServletResponse response= mockMvc.perform(delete("/jefeCocina/1")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        //Then
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }
}