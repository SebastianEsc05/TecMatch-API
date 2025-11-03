package com.example.PotroNet.dao.impl;

import com.example.PotroNet.dao.interfaces.ILikeDAO;
import com.example.PotroNet.domain.Like;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;


import java.util.List;

public class LikeDAO implements ILikeDAO {
    private final EntityManager em;

    public LikeDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public void crear(Like like) {
        em.persist(like);
    }

    @Override
    public void eliminar(Long id) {
        Like like = buscarPorId(id);
        if (like != null) {
            em.remove(like);
        }
    }

    @Override
    public Like buscarPorId(Long id) {
        return em.find(Like.class, id);
    }

    @Override
    public List<Like> listar() {
        TypedQuery<Like> query = em.createQuery("SELECT l FROM Like l", Like.class);
        return query.getResultList();
    }

    @Override
    public Like findLike(Long likerId, Long likedId) {
        TypedQuery<Like> query = em.createQuery(
                "SELECT l FROM Like l WHERE l.liker.id = :likerId AND l.liked.id = :likedId",
                Like.class
        );
        query.setParameter("likerId", likerId);
        query.setParameter("likedId", likedId);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Like> findLikesRecibidos(Long likedId) {
        TypedQuery<Like> query = em.createQuery(
                "SELECT l FROM Like l WHERE l.liked.id = :likedId",
                Like.class
        );
        query.setParameter("likedId", likedId);
        return query.getResultList();
    }

    @Override
    public List<Like> findLikesDados(Long likerId) {
        TypedQuery<Like> query = em.createQuery(
                "SELECT l FROM Like l WHERE l.liker.id = :likerId",
                Like.class
        );
        query.setParameter("likerId", likerId);
        return query.getResultList();
    }
}
