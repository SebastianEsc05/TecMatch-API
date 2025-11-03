package com.example.PotroNet.service.impl;

import com.example.PotroNet.config.JpaUtil;
import com.example.PotroNet.dao.impl.InteresDAO;
import com.example.PotroNet.dao.impl.InteresUsuarioDAO;
import com.example.PotroNet.dao.impl.UsuarioDAO;
import com.example.PotroNet.dao.interfaces.IInteresDAO;
import com.example.PotroNet.dao.interfaces.IInteresUsuarioDAO;
import com.example.PotroNet.dao.interfaces.IUsuarioDAO;
import com.example.PotroNet.domain.Interes;
import com.example.PotroNet.domain.InteresUsuario;
import com.example.PotroNet.domain.Usuario;
import com.example.PotroNet.dto.InteresUsuarioDTO;
import com.example.PotroNet.mapper.InteresUsuarioMapper;
import com.example.PotroNet.service.interfaces.IInteresUsuarioService;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.stream.Collectors;

public class InteresUsuarioService implements IInteresUsuarioService {

    @Override
    public InteresUsuarioDTO agregarInteresAUsuario(InteresUsuarioDTO dto) throws Exception {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            IUsuarioDAO usuarioDAO = new UsuarioDAO(em);
            IInteresDAO interesDAO = new InteresDAO(em);
            IInteresUsuarioDAO iuDAO = new InteresUsuarioDAO(em);

            if (dto.getUsuarioId() == null || dto.getInteresId() == null) {
                throw new Exception("Se requiere un ID de usuario y un ID de interés.");
            }

            em.getTransaction().begin();

            Usuario usuario = usuarioDAO.buscarPorId(dto.getUsuarioId());
            Interes interes = interesDAO.buscarPorId(dto.getInteresId());

            if (usuario == null) {
                throw new Exception("Usuario no encontrado con ID: " + dto.getUsuarioId());
            }
            if (interes == null) {
                if (dto.getInteresDescripcion() == null || dto.getInteresDescripcion().isBlank()) {
                    throw new Exception("Interés no encontrado con ID: " + dto.getInteresId());
                }
                interes = interesDAO.buscarOCrearPorDescripcion(dto.getInteresDescripcion());
            }

            if (iuDAO.buscarPorUsuarioEInteres(usuario.getId(), interes.getId()) != null) {
                throw new Exception("El usuario ya tiene este interés asignado.");
            }

            InteresUsuario nuevaRelacion = new InteresUsuario(usuario, interes);
            iuDAO.crear(nuevaRelacion);

            em.getTransaction().commit();
            return InteresUsuarioMapper.mapToDTO(nuevaRelacion);

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Error al agregar interés al usuario: " + e.getMessage(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void eliminarInteresDeUsuario(Long idRelacion) throws Exception {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            IInteresUsuarioDAO iuDAO = new InteresUsuarioDAO(em);

            em.getTransaction().begin();
            InteresUsuario iu = iuDAO.buscarPorId(idRelacion);
            if (iu == null) {
                throw new Exception("No se encontró la relación con ID: " + idRelacion);
            }
            iuDAO.eliminar(idRelacion);
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Error al eliminar el interés del usuario: " + e.getMessage(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<InteresUsuarioDTO> listarInteresesDeUsuario(Long usuarioId) throws Exception {
        try (EntityManager em = JpaUtil.getEntityManager()) {
            IInteresUsuarioDAO iuDAO = new InteresUsuarioDAO(em);
            List<InteresUsuario> lista = iuDAO.buscarPorUsuarioId(usuarioId);
            return lista.stream()
                    .map(InteresUsuarioMapper::mapToDTO)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<InteresUsuarioDTO> listarUsuariosPorInteres(Long interesId) throws Exception {
        try (EntityManager em = JpaUtil.getEntityManager()) {
            IInteresUsuarioDAO iuDAO = new InteresUsuarioDAO(em);
            List<InteresUsuario> lista = iuDAO.buscarPorInteresId(interesId);
            return lista.stream()
                    .map(InteresUsuarioMapper::mapToDTO)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public InteresUsuarioDTO buscarPorId(Long id) throws Exception {
        try (EntityManager em = JpaUtil.getEntityManager()) {
            InteresUsuario iu = new InteresUsuarioDAO(em).buscarPorId(id);
            if (iu == null) {
                throw new Exception("No se encontró la relación con ID: " + id);
            }
            return InteresUsuarioMapper.mapToDTO(iu);
        }
    }

    @Override
    public List<InteresUsuarioDTO> listar(int limite) throws Exception {
        try (EntityManager em = JpaUtil.getEntityManager()) {
            List<InteresUsuario> lista = new InteresUsuarioDAO(em).listar(limite);
            return lista.stream()
                    .map(InteresUsuarioMapper::mapToDTO)
                    .collect(Collectors.toList());
        }
    }

}