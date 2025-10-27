package com.example.TecMatch.dao.impl;

import com.example.TecMatch.dao.interfaces.IHobbieUsuarioDAO;
import com.example.TecMatch.domain.HobbieUsuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class HobbieUsuarioDAO implements IHobbieUsuarioDAO {

    private final EntityManager em;

    public HobbieUsuarioDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public void crear(HobbieUsuario hobbieUsuario) {
        em.persist(hobbieUsuario);
    }

    @Override
    public HobbieUsuario buscarPorId(Long id) {
        return em.find(HobbieUsuario.class, id);
    }

    @Override
    public List<HobbieUsuario> listar(int limite) {
        int limiteValidado = Math.min(limite, 100);
        TypedQuery<HobbieUsuario> query = em.createQuery("SELECT hu FROM HobbieUsuario hu", HobbieUsuario.class);
        query.setMaxResults(limiteValidado);
        return query.getResultList();
    }


    @Override
    public void eliminar(Long id) {
        HobbieUsuario hu = buscarPorId(id);
        if (hu != null) {
            em.remove(hu);
        }
    }

    @Override
    public List<HobbieUsuario> buscarPorUsuarioId(Long usuarioId) {
        TypedQuery<HobbieUsuario> query = em.createQuery(
                "SELECT hu FROM HobbieUsuario hu WHERE hu.usuario.id = :usuarioId",
                HobbieUsuario.class
        );
        query.setParameter("usuarioId", usuarioId);
        return query.getResultList();
    }

    @Override
    public List<HobbieUsuario> buscarPorHobbieId(Long hobbieId) {
        TypedQuery<HobbieUsuario> query = em.createQuery(
                "SELECT hu FROM HobbieUsuario hu WHERE hu.hobbie.id = :hobbieId",
                HobbieUsuario.class
        );
        query.setParameter("hobbieId", hobbieId);
        return query.getResultList();
    }

    @Override
    public HobbieUsuario buscarPorUsuarioEHobbie(Long usuarioId, Long hobbieId) {
        try {
            TypedQuery<HobbieUsuario> query = em.createQuery(
                    "SELECT hu FROM HobbieUsuario hu WHERE hu.usuario.id = :usuarioId AND hu.hobbie.id = :hobbieId",
                    HobbieUsuario.class
            );
            query.setParameter("usuarioId", usuarioId);
            query.setParameter("hobbieId", hobbieId);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}