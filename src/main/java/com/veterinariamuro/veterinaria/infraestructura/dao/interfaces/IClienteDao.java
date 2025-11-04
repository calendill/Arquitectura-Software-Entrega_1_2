package com.veterinariamuro.veterinaria.infraestructura.dao.interfaces;

import java.util.List;
import java.util.Optional;
import com.veterinariamuro.veterinaria.dominio.model.Cliente;

public interface IClienteDao {

     public void GuardarCliente(Cliente cliente);
     List<Cliente> obtenerTodos();
     Optional<Cliente> obtenerPorId(long cedula);
     public void actualizar(Cliente cliente);
     public void eliminarCliente(Cliente cliente);
    
}
