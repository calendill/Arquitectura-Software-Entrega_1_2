package com.veterinariamuro.veterinaria.infraestructura.dao.implementacion;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.veterinariamuro.veterinaria.dominio.model.Cliente;
import com.veterinariamuro.veterinaria.infraestructura.dao.interfaces.IClienteDao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ClienteDaoJpaImpl implements IClienteDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public void  GuardarCliente(Cliente cliente) {
       this.em.persist(cliente);
       this.em.flush();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> obtenerTodos() {
        return this.em.createQuery("SELECT u FROM Cliente u ").getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> obtenerPorId(long cedula) {
        return Optional.ofNullable(this.em.find(Cliente.class, cedula));
    }

    @Override
    @Transactional
    public void actualizar(Cliente cliente) {
        this.em.merge(cliente);
    }

    @Override
    @Transactional
    public void eliminarCliente(Cliente cliente) {
       this.em.remove(cliente);
    }
    
}
