package com.example.PotroNet.service.impl;

import com.example.PotroNet.dao.impl.ChatDAO;
import com.example.PotroNet.dao.impl.ChatUsuarioDAO;
import com.example.PotroNet.dao.impl.UsuarioDAO;
import com.example.PotroNet.dao.interfaces.IChatDAO;
import com.example.PotroNet.dao.interfaces.IChatUsuarioDAO;
import com.example.PotroNet.dao.interfaces.IUsuarioDAO;
import com.example.PotroNet.domain.Chat;
import com.example.PotroNet.domain.ChatUsuario;
import com.example.PotroNet.domain.Usuario;
import com.example.PotroNet.dto.ChatDTO;
import com.example.PotroNet.mapper.ChatMapper;
import com.example.PotroNet.service.interfaces.IChatService;
import jakarta.persistence.EntityManager;
import com.example.PotroNet.domain.enums.Tipo;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatService implements IChatService {
    private final EntityManagerFactory emf;

    public ChatService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public ChatDTO crearChat(ChatDTO chatDTO) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            IChatDAO chatDAO = new ChatDAO(em);
            IUsuarioDAO usuarioDAO = new UsuarioDAO(em);
            IChatUsuarioDAO chatUsuarioDAO = new ChatUsuarioDAO(em);
            if (chatDTO.getTipo() == Tipo.PRIVADO) {
                throw new Exception("Los chats privados solo pueden ser creados por un Match.");
            }
            em.getTransaction().begin();
            Chat nuevoChat = new Chat();
            nuevoChat.setFecha_creacion(LocalDateTime.now());
            nuevoChat.setTipo(chatDTO.getTipo());
            chatDAO.crear(nuevoChat);
            if (chatDTO.getUsuarioIds() != null) {
                for (Long usuarioId : chatDTO.getUsuarioIds()) {
                    Usuario u = usuarioDAO.buscarPorId(usuarioId);
                    if (u == null) {
                        throw new Exception("Usuario con ID " + usuarioId + " no existe.");
                    }
                    ChatUsuario cu = new ChatUsuario();
                    cu.setChat(nuevoChat);
                    cu.setUsuario(u);
                    chatUsuarioDAO.crear(cu);
                }
            }
            em.getTransaction().commit();
            return ChatMapper.mapToDTO(nuevoChat);

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Error al crear el chat: " + e.getMessage(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public ChatDTO buscarPorId(Long id) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            Chat chat = new ChatDAO(em).buscarPorId(id);
            if (chat == null) {
                throw new Exception("Chat no encontrado con ID: " + id);
            }
            return ChatMapper.mapToDTO(chat);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<ChatDTO> listarChats(int limite) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            List<Chat> chats = new ChatDAO(em).listar(limite);
            return chats.stream()
                    .map(ChatMapper::mapToDTO)
                    .collect(Collectors.toList());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    @Override
    public void eliminarChat(Long id) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            IChatDAO chatDAO = new ChatDAO(em);

            em.getTransaction().begin();
            Chat chat = chatDAO.buscarPorId(id);
            if (chat == null) {
                throw new Exception("Chat no encontrado con ID: " + id);
            }
            chatDAO.eliminar(id);
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Error al eliminar el chat: " + e.getMessage(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<ChatDTO> buscarChatsPorUsuarioId(Long usuarioId) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            List<Chat> chats = new ChatDAO(em).buscarPorUsuarioId(usuarioId);
            return chats.stream()
                    .map(ChatMapper::mapToDTO)
                    .collect(Collectors.toList());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void agregarUsuarioAChat(Long chatId, Long usuarioId) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            IChatDAO chatDAO = new ChatDAO(em);
            IUsuarioDAO usuarioDAO = new UsuarioDAO(em);
            IChatUsuarioDAO chatUsuarioDAO = new ChatUsuarioDAO(em);

            em.getTransaction().begin();

            Chat chat = chatDAO.buscarPorId(chatId);
            if (chat == null) {
                throw new Exception("Chat no encontrado con ID: " + chatId);
            }
            Usuario usuario = usuarioDAO.buscarPorId(usuarioId);
            if (usuario == null) {
                throw new Exception("Usuario no encontrado con ID: " + usuarioId);
            }
            if (chat.getTipo() == Tipo.PRIVADO) {
                throw new Exception("No se pueden agregar usuarios a un chat privado (de Match).");
            }
            boolean yaExiste = chat.getChatUsuarios().stream()
                    .anyMatch(cu -> cu.getUsuario().getId().equals(usuarioId));
            if (yaExiste) {
                throw new Exception("El usuario " + usuarioId + " ya pertenece a este chat.");
            }

            ChatUsuario cu = new ChatUsuario();
            cu.setChat(chat);
            cu.setUsuario(usuario);
            chatUsuarioDAO.crear(cu);

            em.getTransaction().commit();

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
    public void eliminarUsuarioDeChat(Long chatId, Long usuarioId) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            IChatDAO chatDAO = new ChatDAO(em);
            IChatUsuarioDAO chatUsuarioDAO = new ChatUsuarioDAO(em);

            em.getTransaction().begin();
            Chat chat = chatDAO.buscarPorId(chatId);
            if (chat == null) {
                throw new Exception("Chat no encontrado con ID: " + chatId);
            }
            Optional<ChatUsuario> cuOptional = chat.getChatUsuarios().stream()
                    .filter(cu -> cu.getUsuario().getId().equals(usuarioId))
                    .findFirst();

            if (cuOptional.isEmpty()) {
                throw new Exception("El usuario " + usuarioId + " no pertenece a este chat.");
            }
            ChatUsuario cuParaEliminar = cuOptional.get();
            chat.getChatUsuarios().remove(cuParaEliminar);
            chatUsuarioDAO.eliminar(cuParaEliminar.getId());
            chatDAO.actualizar(chat);

            em.getTransaction().commit();
            System.out.println("ÉXITO: Usuario " + usuarioId + " eliminado del Chat " + chatId + " (con sincronización).");

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
}
