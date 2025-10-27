package com.example.TecMatch.service.impl;

import com.example.TecMatch.config.JpaUtil;
import com.example.TecMatch.dao.impl.HobbieDAO;
import com.example.TecMatch.dao.impl.HobbieUsuarioDAO;
import com.example.TecMatch.dao.impl.UsuarioDAO;
import com.example.TecMatch.dao.interfaces.IHobbieDAO;
import com.example.TecMatch.dao.interfaces.IHobbieUsuarioDAO;
import com.example.TecMatch.dao.interfaces.IUsuarioDAO;
import com.example.TecMatch.domain.Hobbie;
import com.example.TecMatch.domain.HobbieUsuario;
import com.example.TecMatch.domain.Usuario;
import com.example.TecMatch.dto.HobbieUsuarioDTO;
import com.example.TecMatch.mapper.HobbieUsuarioMapper;
import com.example.TecMatch.service.interfaces.IHobbieUsuarioService;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.stream.Collectors;

public class HobbieUsuarioService implements IHobbieUsuarioService {

    @Override
    public HobbieUsuarioDTO agregarHobbieAUsuario(HobbieUsuarioDTO dto) throws Exception {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            IUsuarioDAO usuarioDAO = new UsuarioDAO(em);
            IHobbieDAO hobbieDAO = new HobbieDAO(em);
            IHobbieUsuarioDAO huDAO = new HobbieUsuarioDAO(em);

            if (dto.getUsuarioId() == null || dto.getHobbieId() == null) {
                if (dto.getUsuarioId() == null || (dto.getHobbieId() == null && dto.getHobbieDescripcion() == null)) {
                    throw new Exception("Se requiere un ID de usuario y un ID de hobbie (o descripción).");
                }
            }

            em.getTransaction().begin();

            Usuario usuario = usuarioDAO.buscarPorId(dto.getUsuarioId());
            if (usuario == null) {
                throw new Exception("Usuario no encontrado con ID: " + dto.getUsuarioId());
            }

            Hobbie hobbie = null;
            if (dto.getHobbieId() != null) {
                hobbie = hobbieDAO.buscarPorId(dto.getHobbieId());
            }

            if (hobbie == null && dto.getHobbieDescripcion() != null && !dto.getHobbieDescripcion().isBlank()) {
                hobbie = hobbieDAO.buscarOCrearPorDescripcion(dto.getHobbieDescripcion());
            }

            if (hobbie == null) {
                throw new Exception("Hobbie no encontrado o no se pudo crear.");
            }

            if (huDAO.buscarPorUsuarioEHobbie(usuario.getId(), hobbie.getId()) != null) {
                throw new Exception("El usuario ya tiene este hobbie asignado.");
            }
            HobbieUsuario nuevaRelacion = new HobbieUsuario(usuario, hobbie);
            huDAO.crear(nuevaRelacion);

            em.getTransaction().commit();
            return HobbieUsuarioMapper.mapToDTO(nuevaRelacion);
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Error al agregar hobbie al usuario: " + e.getMessage(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void eliminarHobbieDeUsuario(Long idRelacion) throws Exception {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            IHobbieUsuarioDAO huDAO = new HobbieUsuarioDAO(em);

            em.getTransaction().begin();
            HobbieUsuario hu = huDAO.buscarPorId(idRelacion);
            if (hu == null) {
                throw new Exception("No se encontró la relación con ID: " + idRelacion);
            }
            huDAO.eliminar(idRelacion);
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Error al eliminar el hobbie del usuario: " + e.getMessage(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<HobbieUsuarioDTO> listarHobbiesDeUsuario(Long usuarioId) throws Exception {
        try (EntityManager em = JpaUtil.getEntityManager()) {
            IHobbieUsuarioDAO huDAO = new HobbieUsuarioDAO(em);
            List<HobbieUsuario> lista = huDAO.buscarPorUsuarioId(usuarioId);
            return lista.stream()
                    .map(HobbieUsuarioMapper::mapToDTO)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<HobbieUsuarioDTO> listarUsuariosPorHobbie(Long hobbieId) throws Exception {
        try (EntityManager em = JpaUtil.getEntityManager()) {
            IHobbieUsuarioDAO huDAO = new HobbieUsuarioDAO(em);
            List<HobbieUsuario> lista = huDAO.buscarPorHobbieId(hobbieId);
            return lista.stream()
                    .map(HobbieUsuarioMapper::mapToDTO)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public HobbieUsuarioDTO buscarPorId(Long id) throws Exception {
        try (EntityManager em = JpaUtil.getEntityManager()) {
            HobbieUsuario hu = new HobbieUsuarioDAO(em).buscarPorId(id);
            if (hu == null) {
                throw new Exception("No se encontró la relación con ID: " + id);
            }
            return HobbieUsuarioMapper.mapToDTO(hu);
        }
    }

    @Override
    public List<HobbieUsuarioDTO> listar(int limite) throws Exception {
        try (EntityManager em = JpaUtil.getEntityManager()) {
            List<HobbieUsuario> lista = new HobbieUsuarioDAO(em).listar(limite);
            return lista.stream()
                    .map(HobbieUsuarioMapper::mapToDTO)
                    .collect(Collectors.toList());
        }
    }

}