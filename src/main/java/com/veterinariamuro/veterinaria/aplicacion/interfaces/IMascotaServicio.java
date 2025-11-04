package com.veterinariamuro.veterinaria.aplicacion.interfaces;

import java.util.List;

import com.veterinariamuro.veterinaria.aplicacion.Dto.MascotaDto;

public interface IMascotaServicio {

    List<MascotaDto> obtenerTodas();

    MascotaDto obtenerPorId(Long id);

    MascotaDto crearMascota(MascotaDto dto);

    MascotaDto actualizarMascota(Long id, MascotaDto dto);

    String eliminarMascota(Long id);
}
