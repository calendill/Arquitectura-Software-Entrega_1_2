package com.veterinariamuro.veterinaria.aplicacion.servicios;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veterinariamuro.veterinaria.aplicacion.Dto.ClienteDto;
import com.veterinariamuro.veterinaria.aplicacion.interfaces.IClienteServicio;
import com.veterinariamuro.veterinaria.dominio.model.Cliente;
import com.veterinariamuro.veterinaria.infraestructura.dao.interfaces.IClienteDao;

@Service
public class ClienteServicioImpl implements IClienteServicio {

    @Autowired
    private IClienteDao clienteDao;

    @Override
    public List<ClienteDto> obtenerTodos() {
        return clienteDao.obtenerTodos()
                .stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDto obtenerPorId(long cedula) {
        return clienteDao.obtenerPorId(cedula)
                .map(this::convertirADto)
                .orElse(null);
    }

    @Override
    public ClienteDto CrearCliente(ClienteDto clienteDto) {
        Cliente cliente = convertirAEntidad(clienteDto);
        clienteDao.GuardarCliente(cliente);
        return clienteDto;
    }

    @Override
    public ClienteDto actualizar(ClienteDto clienteDto, Long cedula) {
        Optional<Cliente> optCliente = clienteDao.obtenerPorId(cedula);

        if (optCliente.isEmpty()) {
            throw new RuntimeException("El cliente no existe");
        }

        Cliente cliente = optCliente.get();
        cliente.setNombre(clienteDto.getNombre());
        cliente.setApellido1(clienteDto.getApellido1());
        cliente.setApellido2(clienteDto.getApellido2());
        cliente.setTelefono(clienteDto.getTelefono());
        cliente.setCorreo(clienteDto.getCorreo());
        cliente.setDireccion(clienteDto.getDireccion());

        clienteDao.actualizar(cliente);
        return convertirADto(cliente);
    }

    @Override
    public String eliminarCliente(long cedula) {
        Optional<Cliente> cliente = clienteDao.obtenerPorId(cedula);
        if (cliente.isPresent()) {
            clienteDao.eliminarCliente(cliente.get());
            return "Cliente con cédula " + cedula + " eliminado";
        }
        return "Cliente con cédula " + cedula + " no encontrado";
    }

    // ✅ Métodos privados de conversión usando Builder
    private ClienteDto convertirADto(Cliente cliente) {
        return ClienteDto.builder()
                .cedula(cliente.getCedula())
                .nombre(cliente.getNombre())
                .apellido1(cliente.getApellido1())
                .apellido2(cliente.getApellido2())
                .telefono(cliente.getTelefono())
                .correo(cliente.getCorreo())
                .direccion(cliente.getDireccion())
                .build();
    }

    private Cliente convertirAEntidad(ClienteDto dto) {
        return Cliente.builder()
                .cedula(dto.getCedula())
                .nombre(dto.getNombre())
                .apellido1(dto.getApellido1())
                .apellido2(dto.getApellido2())
                .telefono(dto.getTelefono())
                .correo(dto.getCorreo())
                .direccion(dto.getDireccion())
                .build();
    }
}
