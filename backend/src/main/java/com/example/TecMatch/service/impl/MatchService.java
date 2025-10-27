package com.example.TecMatch.service.impl;

import com.example.TecMatch.dao.impl.ChatDAO;
import com.example.TecMatch.dao.impl.ChatUsuarioDAO;
import com.example.TecMatch.dao.impl.UsuarioDAO;
import com.example.TecMatch.dao.interfaces.*;
import com.example.TecMatch.dao.impl.MatchDAO;
import com.example.TecMatch.domain.Match;
import com.example.TecMatch.domain.Usuario;
import com.example.TecMatch.domain.Chat;
import com.example.TecMatch.domain.ChatUsuario;
import com.example.TecMatch.domain.enums.Tipo;
import com.example.TecMatch.dto.MatchDTO;
import com.example.TecMatch.mapper.MatchMapper;
import com.example.TecMatch.config.JpaUtil;
import com.example.TecMatch.service.interfaces.IMatchService;
import jakarta.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MatchService implements IMatchService {

    @Override
    public MatchDTO crearMatch(MatchDTO matchDTO) throws Exception {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            IMatchDAO matchDAO = new MatchDAO(em);
            IUsuarioDAO usuarioDAO = new UsuarioDAO(em);
            IChatDAO chatDAO = new ChatDAO(em);
            IChatUsuarioDAO chatUsuarioDAO = new ChatUsuarioDAO(em);
            em.getTransaction().begin();
            if (matchDTO.getUsuario1Id() == null || matchDTO.getUsuario2Id() == null) {
                throw new Exception("Se requieren dos IDs de usuario para crear un match.");
            }
            if (matchDTO.getUsuario1Id().equals(matchDTO.getUsuario2Id())) {
                throw new Exception("Un usuario no puede hacer match consigo mismo.");
            }
            Usuario u1 = usuarioDAO.buscarPorId(matchDTO.getUsuario1Id());
            Usuario u2 = usuarioDAO.buscarPorId(matchDTO.getUsuario2Id());

            if (u1 == null || u2 == null) {
                throw new Exception("Uno o ambos usuarios (ID: " + matchDTO.getUsuario1Id() + ", " + matchDTO.getUsuario2Id() + ") no existen.");
            }
            Match existingMatch = matchDAO.findMatchEntreUsuarios(u1.getId(), u2.getId());
            if (existingMatch != null) {
                throw new Exception("Ya existe un match entre estos dos usuarios.");
            }
            Match newMatch = new Match(u1, u2);
            matchDAO.crear(newMatch);
            Chat chat = new Chat();
            chat.setFecha_creacion(LocalDateTime.now());
            chat.setTipo(Tipo.PRIVADO);
            chatDAO.crear(chat);

            ChatUsuario chatUsuario1 = new ChatUsuario();
            chatUsuario1.setChat(chat);
            chatUsuario1.setUsuario(u1);
            chatUsuarioDAO.crear(chatUsuario1);

            ChatUsuario chatUsuario2 = new ChatUsuario();
            chatUsuario2.setChat(chat);
            chatUsuario2.setUsuario(u2);
            chatUsuarioDAO.crear(chatUsuario2);

            em.getTransaction().commit();
            return MatchMapper.mapToDTO(newMatch);

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Error al crear el match: " + e.getMessage(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public MatchDTO buscarMatchPorId(Long id) throws Exception {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            IMatchDAO matchDAO = new MatchDAO(em);
            Match match = matchDAO.buscarPorId(id);
            if (match == null) {
                throw new Exception("Match no encontrado con ID: " + id);
            }
            return MatchMapper.mapToDTO(match);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<MatchDTO> buscarTodosLosMatches() {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            IMatchDAO matchDAO = new MatchDAO(em);
            List<Match> matches = matchDAO.buscarTodos();
            return matches.stream()
                    .map(MatchMapper::mapToDTO)
                    .collect(Collectors.toList());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public MatchDTO getMatchEntreUsuarios(Long usuario1Id, Long usuario2Id) {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            IMatchDAO matchDAO = new MatchDAO(em);
            Match match = matchDAO.findMatchEntreUsuarios(usuario1Id, usuario2Id);
            return MatchMapper.mapToDTO(match);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void eliminarMatch(Long id) throws Exception {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            IMatchDAO matchDAO = new MatchDAO(em);
            em.getTransaction().begin();
            Match match = matchDAO.buscarPorId(id);
            if (match == null) {
                em.getTransaction().rollback();
                throw new Exception("No se puede eliminar, Match no encontrado con ID: " + id);
            }
            matchDAO.eliminar(id);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Error al eliminar el match: " + e.getMessage(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}