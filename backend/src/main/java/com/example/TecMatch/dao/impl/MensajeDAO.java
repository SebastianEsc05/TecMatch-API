package com.example.TecMatch.dao.impl;

import com.example.TecMatch.dao.interfaces.IMensajeDAO;
import com.example.TecMatch.domain.Mensaje;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class MensajeDAO implements IMensajeDAO {
    private final EntityManager em;

    public MensajeDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public void crear(Mensaje mensaje) {
        em.persist(mensaje);
    }

    @Override
    public Mensaje buscarPorId(Long id) {
        return em.find(Mensaje.class,id);
    }

    @Override
    public List<Mensaje> listar(int limite) {
        TypedQuery<Mensaje> query = em.createQuery("SELECT m FROM Mensaje m", Mensaje.class);
        query.setMaxResults(limite);
        return query.getResultList();
    }

    @Override
    public Mensaje actualizar(Mensaje mensaje) {
        return em.merge(mensaje);
    }

    @Override
    public void eliminar(Long id) {
        Mensaje mensaje = buscarPorId(id);
        if (mensaje != null) {
            em.remove(mensaje);
        }
    }

    @Override
    public List<Mensaje> listarPorChatId(Long chatId, int limite) {
        TypedQuery<Mensaje> query = em.createQuery(
                "SELECT m FROM Mensaje m WHERE m.chat.id = :chatId ORDER BY m.fecha_hora DESC",
                Mensaje.class
        );
        query.setParameter("chatId", chatId);
        query.setMaxResults(limite);

        return query.getResultList();
    }
}
