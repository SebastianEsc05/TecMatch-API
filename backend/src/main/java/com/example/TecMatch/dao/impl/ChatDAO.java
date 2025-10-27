package com.example.TecMatch.dao.impl;
import com.example.TecMatch.dao.interfaces.IChatDAO;
import com.example.TecMatch.domain.Chat;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ChatDAO implements IChatDAO {
    private final EntityManager em;

    public ChatDAO(EntityManager em) {
        this.em = em;
    }
    @Override
    public void crear(Chat chat) {
        em.persist(chat);
    }

    @Override
    public Chat buscarPorId(Long id) {
        return em.find(Chat.class, id);
    }

    @Override
    public List<Chat> listar(int limite) {
        int limiteValidado = Math.min(limite, 100);
        TypedQuery<Chat> query = em.createQuery("SELECT c FROM Chat c", Chat.class);
        query.setMaxResults(limiteValidado);
        return query.getResultList();
    }

    @Override
    public Chat actualizar(Chat chat) {
        return em.merge(chat);
    }

    @Override
    public void eliminar(Long id) {
        Chat chat = buscarPorId(id);
        if (chat != null) {
            em.remove(chat);
        }
    }

    @Override
    public Chat buscarPorMatchId(Long matchId) {
        // Asumiendo que SÍ agregaste la relación OneToOne
        try {
            TypedQuery<Chat> query = em.createQuery(
                    "SELECT c FROM Chat c WHERE c.match.id = :matchId", Chat.class);
            query.setParameter("matchId", matchId);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Chat> buscarPorUsuarioId(Long usuarioId) {
        // Consulta JPQL que une Chat con ChatUsuario
        TypedQuery<Chat> query = em.createQuery(
                "SELECT c FROM Chat c JOIN c.chatUsuarios cu WHERE cu.usuario.id = :usuarioId",
                Chat.class
        );
        query.setParameter("usuarioId", usuarioId);
        return query.getResultList();
    }


}
