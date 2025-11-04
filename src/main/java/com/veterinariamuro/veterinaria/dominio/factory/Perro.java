package com.veterinariamuro.veterinaria.dominio.factory;
import java.time.LocalDate;

import com.veterinariamuro.veterinaria.dominio.model.Cliente;
import com.veterinariamuro.veterinaria.dominio.model.Mascota;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("PERRO")
public class Perro extends Mascota {

    @Column(length = 50)
    private String raza;


    public Perro() {
        // Constructor vacío (requerido por JPA)
    }

   public Perro(String nombre, LocalDate fechaNacimiento, String sexo, String descripcion,
             LocalDate proximaFechaVacunacion, Cliente cliente, String raza) {
    super(null, nombre, fechaNacimiento, sexo, descripcion, proximaFechaVacunacion, cliente);
    this.raza = raza;
    }


    // Getters y Setters
    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

}
