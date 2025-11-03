package com.example.PotroNet.dao.impl;

import com.example.PotroNet.dao.interfaces.IHobbieDAO;
import com.example.PotroNet.domain.Hobbie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class HobbieDAO implements IHobbieDAO {

    private final EntityManager em;

    public HobbieDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public void crear(Hobbie hobbie) {
        em.persist(hobbie);
    }

    @Override
    public Hobbie buscarPorId(Long id) {
        return em.find(Hobbie.class, id);
    }

    @Override
    public List<Hobbie> listar(int limite) {
        TypedQuery<Hobbie> query = em.createQuery("SELECT h FROM Hobbie h", Hobbie.class);
        query.setMaxResults(limite);
        return query.getResultList();
    }

    @Override
    public Hobbie actualizar(Hobbie hobbie) {
        return em.merge(hobbie);
    }

    @Override
    public void eliminar(Long id) {
        Hobbie hobbie = buscarPorId(id);
        if (hobbie != null) {
            em.remove(hobbie);
        }
    }

    @Override
    public Hobbie buscarPorDescripcion(String descripcion) {
        try {
            return em.createQuery(
                            "SELECT h FROM Hobbie h WHERE h.descripcion = :descripcion", Hobbie.class)
                    .setParameter("descripcion", descripcion)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Hobbie buscarOCrearPorDescripcion(String descripcion) {
        Hobbie hobbie = buscarPorDescripcion(descripcion);
        if (hobbie == null) {
            hobbie = new Hobbie();
            hobbie.setDescripcion(descripcion);
            em.persist(hobbie);
        }
        return hobbie;
    }
}