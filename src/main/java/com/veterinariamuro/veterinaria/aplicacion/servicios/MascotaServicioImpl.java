package com.veterinariamuro.veterinaria.aplicacion.servicios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.veterinariamuro.veterinaria.aplicacion.Dto.MascotaDto;
import com.veterinariamuro.veterinaria.aplicacion.interfaces.IMascotaServicio;
import com.veterinariamuro.veterinaria.dominio.factory.MascotaFactory;
import com.veterinariamuro.veterinaria.dominio.model.Cliente;
import com.veterinariamuro.veterinaria.dominio.model.Mascota;
import com.veterinariamuro.veterinaria.infraestructura.dao.interfaces.IClienteDao;
import com.veterinariamuro.veterinaria.infraestructura.dao.interfaces.IMascotaDao;

@Service
public class MascotaServicioImpl implements IMascotaServicio {
   @Autowired
    private IMascotaDao mascotaDao;

    @Autowired
    private IClienteDao clienteDao;


    @Override
    public List<MascotaDto> obtenerTodas() {
        return mascotaDao.obtenerTodas()
                .stream()
                .map(MascotaFactory::crearDtoDesdeEntidad)
                .collect(Collectors.toList());
    }

    @Override
    public MascotaDto obtenerPorId(Long id) {
        return mascotaDao.obtenerPorId(id)
                .map(MascotaFactory::crearDtoDesdeEntidad)
                .orElse(null);
    }

  @Override
public MascotaDto crearMascota(MascotaDto mascotaDto) {
    Cliente cliente = clienteDao.obtenerPorId(mascotaDto.getClienteId())
            .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

    Mascota mascota = MascotaFactory.crearDesdeDto(mascotaDto, cliente);
    mascotaDao.guardar(mascota);

    return MascotaFactory.crearDtoDesdeEntidad(mascota);
}


    @Override
    public MascotaDto actualizarMascota(Long id, MascotaDto dto) {
        Optional<Mascota> optMascota = mascotaDao.obtenerPorId(id);
        if (optMascota.isEmpty()) {
            throw new RuntimeException("Mascota no encontrada");
        }

        Mascota mascota = optMascota.get();
        mascota.setNombre(dto.getNombre());
        mascota.setDescripcion(dto.getDescripcion());
        mascota.setSexo(dto.getSexo());
        mascota.setFechaNacimiento(dto.getFechaNacimiento());

        // Los atributos específicos de perro/gato los manejaría el Factory si es necesario
        mascotaDao.actualizar(mascota);
        return MascotaFactory.crearDtoDesdeEntidad(mascota);
    }

    @Override
    public String eliminarMascota(Long id) {
        Optional<Mascota> mascota = mascotaDao.obtenerPorId(id);
        if (mascota.isPresent()) {
            mascotaDao.eliminar(mascota.get());
            return "Mascota eliminada correctamente";
        }
        return "Mascota no encontrada";
    }
}
