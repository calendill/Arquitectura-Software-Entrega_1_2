package com.veterinariamuro.veterinaria.aplicacion.Dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MascotaDto implements Serializable{

    private Long id;
    private String nombre;
    private LocalDate fechaNacimiento;
    private String sexo;
    private String descripcion;
    private LocalDate proximaFechaVacunacion;

    // 🔹 Tipo de mascota (PERRO o GATO)
    private String tipoAnimal;

    // 🔹 Relación con cliente (opcional para el formulario)
    private Long clienteId;

    // 🔹 Campos específicos de perro
    private String raza;

    // 🔹 Campos específicos de gato
    private String colorPelaje;
}