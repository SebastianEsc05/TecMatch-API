package com.example.PotroNet.dao.impl;

import com.example.PotroNet.dao.interfaces.IInteresDAO;
import com.example.PotroNet.domain.Interes;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class InteresDAO implements IInteresDAO {

    private final EntityManager em;

    public InteresDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public void crear(Interes interes) {
        em.persist(interes);
    }

    @Override
    public Interes buscarPorId(Long id) {
        return em.find(Interes.class, id);
    }

    @Override
    public List<Interes> listar(int limite) {
        TypedQuery<Interes> query = em.createQuery("SELECT i FROM Interes i", Interes.class);
        query.setMaxResults(limite);
        return query.getResultList();
    }

    @Override
    public int contar() {
        Long count = em.createQuery("SELECT COUNT(i) FROM Interes i", Long.class)
                .getSingleResult();
        return count.intValue();
    }

    @Override
    public Interes actualizar(Interes interes) {
        return em.merge(interes);
    }

    @Override
    public void eliminar(Long id) {
        Interes interes = buscarPorId(id);
        if (interes != null) {
            em.remove(interes);
        }
    }

    @Override
    public Interes buscarPorDescripcion(String descripcion) {
        try {
            return em.createQuery(
                            "SELECT i FROM Interes i WHERE i.descripcion = :descripcion", Interes.class)
                    .setParameter("descripcion", descripcion)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Interes buscarOCrearPorDescripcion(String descripcion) {
        Interes interes = buscarPorDescripcion(descripcion);
        if (interes == null) {
            interes = new Interes();
            interes.setDescripcion(descripcion);
            em.persist(interes);
        }
        return interes;
    }
}