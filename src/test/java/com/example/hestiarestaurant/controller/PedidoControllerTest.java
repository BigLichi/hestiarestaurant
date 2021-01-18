package com.example.hestiarestaurant.controller;

import com.example.hestiarestaurant.exception.HestiaException;
import com.example.hestiarestaurant.model.DetallePedido;
import com.example.hestiarestaurant.model.Pedido;
import com.example.hestiarestaurant.service.PedidoService;
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
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
class PedidoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PedidoService pedidoService;
    private JacksonTester<Pedido> jsonPedido;

    @InjectMocks
    private PedidoController pedidoController;

    @BeforeEach
    void setup(){
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc= MockMvcBuilders.standaloneSetup(pedidoController).build();
    }

    @Test
    void obtenerListaDeToodosLosPedidosYRetornaOK() throws Exception {
        //test getAllPedido() return = OK
        //Given
        Date fecha = new Date();
        Set<DetallePedido> detallePedidoSet = new HashSet<DetallePedido>();
        Pedido pedido1 = new Pedido(1,true, fecha, 4, 10,detallePedidoSet);
        Pedido pedido2 = new Pedido(2,true, fecha, 4, 12,detallePedidoSet);
        ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
        pedidos.add(pedido1);
        pedidos.add(pedido2);
        given(pedidoService.listAll()).willReturn(pedidos);

        //When
        MockHttpServletResponse response = mockMvc.perform(get("/pedidos/")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        //Then
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void obtenerListaDeToodosLosPedidosYRetornaNoContent() throws Exception {
        //test getAllPedido() return = No_Content
        //Given
        ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
        given(pedidoService.listAll()).willReturn(pedidos);

        //When
        MockHttpServletResponse response = mockMvc.perform(get("/pedidos/")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        //Then
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

    @Test
    void creaPedidoYRetornaOK() throws Exception {
        //test crearPedido() return = Created
        //Given
        Date fecha = new Date();
        Set<DetallePedido> detallePedidoSet = new HashSet<DetallePedido>();
        Pedido pedido = new Pedido(1,true, fecha, 4, 10,detallePedidoSet);
        when(pedidoService.save(Mockito.any(Pedido.class))).thenReturn(pedido);

        //When
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/pedidos/")
                .accept(MediaType.APPLICATION_JSON).content(jsonPedido.write(pedido).getJson()).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        //Then
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals(jsonPedido.write(pedido).getJson(),response.getContentAsString());
    }

    @Test
    void creaPedidoYRetornaBadRequest() throws Exception {
        //test crearPedido() return = Bad_Request
        //Given
        Date fecha = new Date();
        Set<DetallePedido> detallePedidoSet = new HashSet<DetallePedido>();
        Pedido pedido = new Pedido(1,true, fecha, 4, 10,detallePedidoSet);
        when(pedidoService.save(Mockito.any(Pedido.class))).thenThrow(HestiaException.class);

        //When
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/pedidos/")
                .accept(MediaType.APPLICATION_JSON).content(jsonPedido.write(pedido).getJson()).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        //Then
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    void obtenerPedidosByIdYRetornaOK() throws Exception {
        //test getPedidoById() return = OK
        //Given
        Date fecha = new Date();
        Set<DetallePedido> detallePedidoSet = new HashSet<DetallePedido>();
        Pedido pedido = new Pedido(1,true, fecha, 4, 10,detallePedidoSet);
        given(pedidoService.findById(1)).willReturn(pedido);

        //When
        MockHttpServletResponse response = mockMvc.perform(get("/pedidos/1")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertEquals(jsonPedido.write(pedido).getJson(), response.getContentAsString());
    }

    @Test
    void obtenerPedidosByIdYRetornaNoContent() throws Exception {
        //test getPedidoById() return = No_Content
        //Given
        given(pedidoService.findById(1)).willReturn(null);

        //When
        MockHttpServletResponse response = mockMvc.perform(get("/pedidos/1")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        //Then
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }

    @Test
    void borrarPedidoYRetornaOK() throws Exception {
        //test borrarPedido() return = OK
        //Given
        Date fecha = new Date();
        Set<DetallePedido> detallePedidoSet = new HashSet<DetallePedido>();
        Pedido pedido = new Pedido(1,true, fecha, 4, 10,detallePedidoSet);
        given(pedidoService.delete(1)).willReturn(true);

        //When
        MockHttpServletResponse response = mockMvc.perform(delete("/pedidos/1")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        //Then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertEquals("1", response.getContentAsString());
    }

    @Test
    void borrarPedidoYRetornaNotFound() throws Exception {
        //test borrarPedido() return = Not_Found
        //Given
        given(pedidoService.delete(1)).willReturn(false);

        //When
        MockHttpServletResponse response = mockMvc.perform(delete("/pedidos/1")
                .accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        //Then
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }
}