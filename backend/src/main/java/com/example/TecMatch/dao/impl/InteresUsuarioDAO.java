package com.example.TecMatch.dao.impl;

import com.example.TecMatch.dao.interfaces.IInteresUsuarioDAO;
import com.example.TecMatch.domain.InteresUsuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class InteresUsuarioDAO implements IInteresUsuarioDAO {

    private final EntityManager em;

    public InteresUsuarioDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public void crear(InteresUsuario interesUsuario) {
        em.persist(interesUsuario);
    }

    @Override
    public InteresUsuario buscarPorId(Long id) {
        return em.find(InteresUsuario.class, id);
    }

    @Override
    public List<InteresUsuario> listar(int limite) {
        int limiteValidado = Math.min(limite, 100);
        TypedQuery<InteresUsuario> query = em.createQuery("SELECT iu FROM InteresUsuario iu", InteresUsuario.class);
        query.setMaxResults(limiteValidado);
        return query.getResultList();
    }

    @Override
    public void eliminar(Long id) {
        InteresUsuario iu = buscarPorId(id);
        if (iu != null) {
            em.remove(iu);
        }
    }

    @Override
    public List<InteresUsuario> buscarPorUsuarioId(Long usuarioId) {
        TypedQuery<InteresUsuario> query = em.createQuery(
                "SELECT iu FROM InteresUsuario iu WHERE iu.usuario.id = :usuarioId",
                InteresUsuario.class
        );
        query.setParameter("usuarioId", usuarioId);
        return query.getResultList();
    }

    @Override
    public List<InteresUsuario> buscarPorInteresId(Long interesId) {
        TypedQuery<InteresUsuario> query = em.createQuery(
                "SELECT iu FROM InteresUsuario iu WHERE iu.interes.id = :interesId",
                InteresUsuario.class
        );
        query.setParameter("interesId", interesId);
        return query.getResultList();
    }

    @Override
    public InteresUsuario buscarPorUsuarioEInteres(Long usuarioId, Long interesId) {
        try {
            TypedQuery<InteresUsuario> query = em.createQuery(
                    "SELECT iu FROM InteresUsuario iu WHERE iu.usuario.id = :usuarioId AND iu.interes.id = :interesId",
                    InteresUsuario.class
            );
            query.setParameter("usuarioId", usuarioId);
            query.setParameter("interesId", interesId);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}