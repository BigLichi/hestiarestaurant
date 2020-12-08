package com.example.hestiarestaurant.service.Impl;


import com.example.hestiarestaurant.model.Pedido;
import com.example.hestiarestaurant.repository.PedidoRepository;

import com.example.hestiarestaurant.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImplementation implements PedidoService {

        @Autowired
        private PedidoRepository pedidoRepository;

        @Override
        public List<Pedido> listAll(){
            return pedidoRepository.findAll();
        }

        @Override
        public Pedido save(Pedido pedido){
            return pedidoRepository.save(pedido);
        }

        @Override
        public Pedido findById(int id){
            return pedidoRepository.getOne(id);
        }

        @Override
        public boolean delete(int id){
            pedidoRepository.deleteById(id);
            return true;
        }


}
