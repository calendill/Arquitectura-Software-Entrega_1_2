package com.veterinariamuro.veterinaria.dominio.factory;

import java.time.LocalDate;

import com.veterinariamuro.veterinaria.dominio.model.Cliente;
import com.veterinariamuro.veterinaria.dominio.model.Mascota;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("GATO")
public class Gato extends Mascota {

    @Column(name = "color_pelaje", length = 50)
    private String colorPelaje;


        public Gato() {
        // Constructor vacío requerido por JPA
    }

        public Gato(String nombre, LocalDate fechaNacimiento, String sexo, String descripcion,
                LocalDate proximaFechaVacunacion, Cliente cliente, String colorPelaje) {
        super(null, nombre, fechaNacimiento, sexo, descripcion, proximaFechaVacunacion, cliente);
        this.colorPelaje = colorPelaje;
    }

    public String getColorPelaje() {
        return colorPelaje;
    }

    public void setColorPelaje(String colorPelaje) {
        this.colorPelaje = colorPelaje;
    }
}