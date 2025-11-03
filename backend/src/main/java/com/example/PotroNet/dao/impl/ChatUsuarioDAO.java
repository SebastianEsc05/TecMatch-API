package com.example.PotroNet.dao.impl;

import com.example.PotroNet.dao.interfaces.IChatUsuarioDAO;
import com.example.PotroNet.domain.ChatUsuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ChatUsuarioDAO implements IChatUsuarioDAO {
    private final EntityManager em;

    public ChatUsuarioDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public void crear(ChatUsuario chatUsuario) {
        em.persist(chatUsuario);
    }

    @Override
    public ChatUsuario actualizar(ChatUsuario chatUsuario) {
        return em.merge(chatUsuario);
    }

    @Override
    public void eliminar(Long id) {
        ChatUsuario chatUsuario = buscarPorId(id);
        if (chatUsuario != null) {
            em.remove(chatUsuario);
        }
    }

    @Override
    public ChatUsuario buscarPorId(Long id) {
        return em.find(ChatUsuario.class, id);
    }

    @Override
    public List<ChatUsuario> listar() {
        TypedQuery<ChatUsuario> query = em.createQuery("SELECT cu FROM ChatUsuario cu", ChatUsuario.class);
        return query.getResultList();
    }

    @Override
    public List<ChatUsuario> buscarPorChatId(Long chatId) {
        TypedQuery<ChatUsuario> query = em.createQuery(
                "SELECT cu FROM ChatUsuario cu WHERE cu.chat.id = :chatId",
                ChatUsuario.class
        );
        query.setParameter("chatId", chatId);
        return query.getResultList();
    }

    @Override
    public List<ChatUsuario> buscarPorUsuarioId(Long usuarioId) {
        TypedQuery<ChatUsuario> query = em.createQuery(
                "SELECT cu FROM ChatUsuario cu WHERE cu.usuario.id = :usuarioId",
                ChatUsuario.class
        );
        query.setParameter("usuarioId", usuarioId);
        return query.getResultList();
    }
}
