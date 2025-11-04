package com.veterinariamuro.veterinaria.presentacion.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.veterinariamuro.veterinaria.aplicacion.Dto.ClienteDto;
import com.veterinariamuro.veterinaria.aplicacion.interfaces.IClienteServicio;

@Controller
@RequestMapping("/cliente")
public class ClienteControlador {


     @Autowired
    private IClienteServicio clienteServicio;

    // ✅ Mostrar lista de clientes en una vista HTML
    @GetMapping("/listar")
    public String listarClientes(Model model) {
        List<ClienteDto> clientes = clienteServicio.obtenerTodos();
        model.addAttribute("clientes", clientes);
        return "cliente/lista"; // → resources/templates/cliente/lista.html
    }

    // ✅ Mostrar formulario de creación
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("cliente", new ClienteDto());
        return "cliente/formulario"; // → resources/templates/cliente/formulario.html
    }

    // ✅ Guardar cliente (al enviar formulario)
    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute("cliente") ClienteDto clienteDto) {
        clienteServicio.CrearCliente(clienteDto);
        return "redirect:/cliente/listar"; // redirige a la lista después de guardar
    }

    // ✅ Mostrar formulario para editar cliente
    @GetMapping("/editar/{cedula}")
    public String mostrarFormularioEditar(@PathVariable Long cedula, Model model) {
        ClienteDto cliente = clienteServicio.obtenerPorId(cedula);
        model.addAttribute("cliente", cliente);
        return "cliente/formulario"; // mismo formulario reutilizado
    }

    // ✅ Actualizar cliente
    @PostMapping("/actualizar/{cedula}")
    public String actualizarCliente(@ModelAttribute("cliente") ClienteDto clienteDto, @PathVariable Long cedula) {
        clienteServicio.actualizar(clienteDto, cedula);
        return "redirect:/cliente/listar";
    }

    // ✅ Eliminar cliente
    @GetMapping("/eliminar/{cedula}")
    public String eliminarCliente(@PathVariable Long cedula) {
        clienteServicio.eliminarCliente(cedula);
        return "redirect:/cliente/listar";
    }
}
    
    

