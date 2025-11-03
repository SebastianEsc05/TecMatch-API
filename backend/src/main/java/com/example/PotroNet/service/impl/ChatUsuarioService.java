package com.example.PotroNet.service.impl;

import com.example.PotroNet.config.JpaUtil;
import com.example.PotroNet.dao.impl.ChatDAO;
import com.example.PotroNet.dao.impl.ChatUsuarioDAO;
import com.example.PotroNet.dao.impl.UsuarioDAO;
import com.example.PotroNet.dao.interfaces.IChatDAO;
import com.example.PotroNet.dao.interfaces.IChatUsuarioDAO;
import com.example.PotroNet.dao.interfaces.IUsuarioDAO;
import com.example.PotroNet.domain.Chat;
import com.example.PotroNet.domain.ChatUsuario;
import com.example.PotroNet.domain.Usuario;
import com.example.PotroNet.dto.ChatUsuarioDTO;
import com.example.PotroNet.mapper.ChatUsuarioMapper;
import com.example.PotroNet.service.interfaces.IChatUsuarioService;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.stream.Collectors;

public class ChatUsuarioService implements IChatUsuarioService {
    @Override
    public ChatUsuarioDTO agregarUsuarioAChat(ChatUsuarioDTO dto) throws Exception {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            IChatUsuarioDAO chatUsuarioDAO = new ChatUsuarioDAO(em);
            IUsuarioDAO usuarioDAO = new UsuarioDAO(em);
            IChatDAO chatDAO = new ChatDAO(em);
            em.getTransaction().begin();
            Usuario usuario = usuarioDAO.buscarPorId(dto.getUsuarioId());
            Chat chat = chatDAO.buscarPorId(dto.getChatId());

            if (usuario == null) {
                throw new Exception("Usuario no encontrado con ID: " + dto.getUsuarioId());
            }
            if (chat == null) {
                throw new Exception("Chat no encontrado con ID: " + dto.getChatId());
            }

            ChatUsuario nuevoChatUsuario = new ChatUsuario();
            nuevoChatUsuario.setUsuario(usuario);
            nuevoChatUsuario.setChat(chat);

            chatUsuarioDAO.crear(nuevoChatUsuario);

            em.getTransaction().commit();
            return ChatUsuarioMapper.mapToDTO(nuevoChatUsuario);

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Error al agregar usuario al chat: " + e.getMessage(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void eliminarUsuarioDeChat(Long chatUsuarioId) throws Exception {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            IChatUsuarioDAO chatUsuarioDAO = new ChatUsuarioDAO(em);

            em.getTransaction().begin();

            ChatUsuario cu = chatUsuarioDAO.buscarPorId(chatUsuarioId);
            if (cu == null) {
                throw new Exception("No se encontró la relación Chat-Usuario con ID: " + chatUsuarioId);
            }

            chatUsuarioDAO.eliminar(chatUsuarioId);
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Error al eliminar usuario del chat: " + e.getMessage(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<ChatUsuarioDTO> buscarUsuariosPorChatId(Long chatId) throws Exception {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            IChatUsuarioDAO chatUsuarioDAO = new ChatUsuarioDAO(em);
            List<ChatUsuario> relaciones = chatUsuarioDAO.buscarPorChatId(chatId);
            return relaciones.stream()
                    .map(ChatUsuarioMapper::mapToDTO)
                    .collect(Collectors.toList());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<ChatUsuarioDTO> buscarChatsPorUsuarioId(Long usuarioId) throws Exception {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            IChatUsuarioDAO chatUsuarioDAO = new ChatUsuarioDAO(em);
            List<ChatUsuario> relaciones = chatUsuarioDAO.buscarPorUsuarioId(usuarioId);
            return relaciones.stream()
                    .map(ChatUsuarioMapper::mapToDTO)
                    .collect(Collectors.toList());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public ChatUsuarioDTO buscarPorId(Long id) throws Exception {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            IChatUsuarioDAO chatUsuarioDAO = new ChatUsuarioDAO(em);
            ChatUsuario cu = chatUsuarioDAO.buscarPorId(id);
            if (cu == null) {
                throw new Exception("No se encontró la relación Chat-Usuario con ID: " + id);
            }
            return ChatUsuarioMapper.mapToDTO(cu);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
