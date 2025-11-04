package com.veterinariamuro.veterinaria.infraestructura.dao.interfaces;

import java.util.List;
import java.util.Optional;

import com.veterinariamuro.veterinaria.dominio.model.Mascota;

public interface IMascotaDao {

    void guardar(Mascota mascota);

    List<Mascota> obtenerTodas();

    Optional<Mascota> obtenerPorId(Long id);

    void actualizar(Mascota mascota);

    void eliminar(Mascota mascota);
}
