package com.veterinariamuro.veterinaria.dominio.factory;

import com.veterinariamuro.veterinaria.aplicacion.Dto.MascotaDto;
import com.veterinariamuro.veterinaria.dominio.model.Cliente;
import com.veterinariamuro.veterinaria.dominio.model.Mascota;

public class MascotaFactory {

    public static Mascota crearDesdeDto(MascotaDto dto, Cliente cliente) {
        if ("PERRO".equalsIgnoreCase(dto.getTipoAnimal())) {
            return new Perro(
                    dto.getNombre(),
                    dto.getFechaNacimiento(),
                    dto.getSexo(),
                    dto.getDescripcion(),
                    dto.getProximaFechaVacunacion(),
                    cliente,
                    dto.getRaza()
            );
        } else if ("GATO".equalsIgnoreCase(dto.getTipoAnimal())) {
            return new Gato(
                    dto.getNombre(),
                    dto.getFechaNacimiento(),
                    dto.getSexo(),
                    dto.getDescripcion(),
                    dto.getProximaFechaVacunacion(),
                    cliente,
                    dto.getColorPelaje()
            );
        } else {
            throw new IllegalArgumentException("Tipo de mascota desconocido: " + dto.getTipoAnimal());
        }
    }

    public static MascotaDto crearDtoDesdeEntidad(Mascota mascota) {
        MascotaDto dto = new MascotaDto();
        dto.setId(mascota.getId());
        dto.setNombre(mascota.getNombre());
        dto.setFechaNacimiento(mascota.getFechaNacimiento());
        dto.setSexo(mascota.getSexo());
        dto.setDescripcion(mascota.getDescripcion());
        dto.setProximaFechaVacunacion(mascota.getProximaFechaVacunacion());

        // ✅ Corregido: solo el ID del cliente
        if (mascota.getCliente() != null) {
            dto.setClienteId(mascota.getCliente().getCedula());
        }

        switch (mascota) {
            case Perro perro -> {
                dto.setTipoAnimal("PERRO");
                dto.setRaza(perro.getRaza());
            }
            case Gato gato -> {
                dto.setTipoAnimal("GATO");
                dto.setColorPelaje(gato.getColorPelaje());
            }
            default -> {
            }
        }

        return dto;
    }
}
