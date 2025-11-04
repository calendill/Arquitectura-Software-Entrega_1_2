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
import com.veterinariamuro.veterinaria.aplicacion.Dto.MascotaDto;
import com.veterinariamuro.veterinaria.aplicacion.interfaces.IClienteServicio;
import com.veterinariamuro.veterinaria.aplicacion.interfaces.IMascotaServicio;

@Controller
@RequestMapping("/mascota")
public class MascotaControlador {

    @Autowired
    private IMascotaServicio mascotaServicio;

    @Autowired
    private IClienteServicio clienteServicio;

    // ✅ Lista todas las mascotas
    @GetMapping("/listar")
    public String listarMascotas(Model model) {
        List<MascotaDto> mascotas = mascotaServicio.obtenerTodas();
        model.addAttribute("mascotas", mascotas);
        return "/listar"; // resources/templates/mascota/lista.html
    }

    // ✅ Mostrar formulario para nueva mascota
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("mascota", new MascotaDto());
        
        // ✅ Aquí obtenemos la lista de clientes y la enviamos al formulario
        List<ClienteDto> clientes = clienteServicio.obtenerTodos();
        model.addAttribute("clientes", clientes);

        return "formulario-mascota";
}

    // ✅ Guardar mascota
    @PostMapping("/guardar")
    public String guardarMascota(@ModelAttribute("mascota") MascotaDto mascotaDto) {
        mascotaServicio.crearMascota(mascotaDto);
        return "redirect:/listar";
    }

    

    // ✅ Mostrar formulario para editar
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        MascotaDto mascota = mascotaServicio.obtenerPorId(id);
        model.addAttribute("mascota", mascota);
        return "mascota/formulario";
    }

    // ✅ Actualizar mascota
    @PostMapping("/actualizar/{id}")
    public String actualizarMascota(@ModelAttribute("mascota") MascotaDto mascotaDto, @PathVariable Long id) {
        mascotaServicio.actualizarMascota(id, mascotaDto);
        return "redirect:/mascota/listar";
    }

    

    // ✅ Eliminar mascota
    @GetMapping("/eliminar/{id}")
    public String eliminarMascota(@PathVariable Long id) {
        mascotaServicio.eliminarMascota(id);
        return "redirect:/mascota/listar";
    }
}
