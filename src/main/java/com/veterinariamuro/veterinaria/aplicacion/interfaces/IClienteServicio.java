package com.veterinariamuro.veterinaria.aplicacion.interfaces;

import java.util.List;

import com.veterinariamuro.veterinaria.aplicacion.Dto.ClienteDto;

public interface IClienteServicio {


    
     List<ClienteDto> obtenerTodos();
     ClienteDto obtenerPorId(long cedula);
     ClienteDto CrearCliente(ClienteDto clienteDto);
     ClienteDto actualizar(ClienteDto clienteDto, Long cedula);
     String eliminarCliente(long cedula);
    
}
