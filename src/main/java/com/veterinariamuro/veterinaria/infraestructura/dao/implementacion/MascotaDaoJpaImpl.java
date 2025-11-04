package com.veterinariamuro.veterinaria.infraestructura.dao.implementacion;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.veterinariamuro.veterinaria.dominio.model.Mascota;
import com.veterinariamuro.veterinaria.infraestructura.dao.interfaces.IMascotaDao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class MascotaDaoJpaImpl implements IMascotaDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void guardar(Mascota mascota) {
        this.em.persist(mascota);
        this.em.flush();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Mascota> obtenerTodas() {
        return this.em.createQuery("SELECT m FROM Mascota m", Mascota.class)
                      .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Mascota> obtenerPorId(Long id) {
        return Optional.ofNullable(this.em.find(Mascota.class, id));
    }

    @Override
    @Transactional
    public void actualizar(Mascota mascota) {
        this.em.merge(mascota);
    }

    @Override
    @Transactional
    public void eliminar(Mascota mascota) {
        this.em.remove(mascota);
    }
}
