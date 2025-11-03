package com.example.PotroNet.service.impl;

import com.example.PotroNet.config.JpaUtil;
import com.example.PotroNet.dao.impl.ChatDAO;
import com.example.PotroNet.dao.impl.MensajeDAO;
import com.example.PotroNet.dao.impl.UsuarioDAO;
import com.example.PotroNet.dao.interfaces.IChatDAO;
import com.example.PotroNet.dao.interfaces.IMensajeDAO;
import com.example.PotroNet.dao.interfaces.IUsuarioDAO;
import com.example.PotroNet.domain.Chat;
import com.example.PotroNet.domain.Mensaje;
import com.example.PotroNet.domain.Usuario;
import com.example.PotroNet.dto.MensajeDTO;
import com.example.PotroNet.mapper.MensajeMapper;
import com.example.PotroNet.service.interfaces.IMensajeService;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MensajeService implements IMensajeService {

    @Override
    public MensajeDTO crearMensaje(MensajeDTO mensajeDTO) throws Exception {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            IMensajeDAO mensajeDAO = new MensajeDAO(em);
            IChatDAO chatDAO = new ChatDAO(em);
            IUsuarioDAO usuarioDAO = new UsuarioDAO(em);

            if (mensajeDTO.getTexto() == null || mensajeDTO.getTexto().isBlank()) {
                throw new Exception("El texto del mensaje no puede estar vacío.");
            }
            if (mensajeDTO.getChatId() == null || mensajeDTO.getEmisorId() == null) {
                throw new Exception("Se requieren un Chat ID y un Emisor ID.");
            }
            em.getTransaction().begin();

            Chat chat = chatDAO.buscarPorId(mensajeDTO.getChatId());
            Usuario emisor = usuarioDAO.buscarPorId(mensajeDTO.getEmisorId());

            if (chat == null) {
                throw new Exception("El chat (ID: " + mensajeDTO.getChatId() + ") no existe.");
            }
            if (emisor == null) {
                throw new Exception("El usuario emisor (ID: " + mensajeDTO.getEmisorId() + ") no existe.");
            }
            Long emisorId = emisor.getId();
            boolean emisorEstaEnElChat = chat.getChatUsuarios()
                    .stream()
                    .anyMatch(cu -> cu.getUsuario().getId().equals(emisorId));
            if (!emisorEstaEnElChat) {
                throw new Exception("El usuario (ID: " + emisorId + ") no pertenece a este chat.");
            }
            Mensaje nuevoMensaje = MensajeMapper.mapToEntity(mensajeDTO);
            nuevoMensaje.setChat(chat);
            nuevoMensaje.setEmisor(emisor);
            nuevoMensaje.setFecha_hora(LocalDateTime.now());
            nuevoMensaje.setVisto(false);
            mensajeDAO.crear(nuevoMensaje);
            em.getTransaction().commit();
            return MensajeMapper.mapToDTO(nuevoMensaje);

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Error al crear el mensaje: " + e.getMessage(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<MensajeDTO> listarMensajesPorChat(Long chatId, int limite) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            int limiteValidado = Math.min(limite, 100);
            IMensajeDAO mensajeDAO = new MensajeDAO(em);
            List<Mensaje> mensajes = mensajeDAO.listarPorChatId(chatId, limiteValidado);
            return mensajes.stream()
                    .map(MensajeMapper::mapToDTO)
                    .collect(Collectors.toList());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public MensajeDTO marcarComoVisto(Long mensajeId) throws Exception {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            IMensajeDAO mensajeDAO = new MensajeDAO(em);
            em.getTransaction().begin();
            Mensaje mensaje = mensajeDAO.buscarPorId(mensajeId);
            if (mensaje == null) {
                throw new Exception("Mensaje no encontrado con ID: " + mensajeId);
            }
            mensaje.setVisto(true);
            Mensaje mensajeActualizado = mensajeDAO.actualizar(mensaje);
            em.getTransaction().commit();
            return MensajeMapper.mapToDTO(mensajeActualizado);
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Error al marcar mensaje como visto: " + e.getMessage(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void eliminarMensaje(Long mensajeId) throws Exception {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            IMensajeDAO mensajeDAO = new MensajeDAO(em);
            em.getTransaction().begin();
            Mensaje mensaje = mensajeDAO.buscarPorId(mensajeId);
            if (mensaje == null) {
                throw new Exception("No se encontró el mensaje con ID: " + mensajeId + " para eliminar.");
            }
            mensajeDAO.eliminar(mensajeId);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Error al eliminar el mensaje: " + e.getMessage(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
