package com.example.PotroNet.dao.impl;

import com.example.PotroNet.dao.interfaces.IMatchDAO;
import com.example.PotroNet.domain.Match;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class MatchDAO implements IMatchDAO {
    private EntityManager em;

    public MatchDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public void crear(Match match) {
        em.persist(match);
    }

    @Override
    public Match actualizar(Match match) {
        return em.merge(match);
    }

    @Override
    public void eliminar(Long id) {
        Match match = buscarPorId(id);
        if (match != null) {
            em.remove(match);
        }
    }

    @Override
    public int contar() {
        Long count = em.createQuery("SELECT COUNT(m) FROM Match m", Long.class)
                .getSingleResult();
        return count.intValue();
    }

    @Override
    public Match buscarPorId(Long id) {
        return em.find(Match.class, id);
    }

    @Override
    public List<Match> buscarTodos() {
        TypedQuery<Match> query = em.createQuery("SELECT m FROM Match m", Match.class);
        return query.getResultList();
    }

    @Override
    public Match findMatchEntreUsuarios(Long usuario1Id, Long usuario2Id) {
        TypedQuery<Match> query = em.createQuery(
                "SELECT m FROM Match m WHERE " +
                        "(m.usuario1.id = :u1Id AND m.usuario2.id = :u2Id) OR " +
                        "(m.usuario1.id = :u2Id AND m.usuario2.id = :u1Id)",
                Match.class
        );
        query.setParameter("u1Id", usuario1Id);
        query.setParameter("u2Id", usuario2Id);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}