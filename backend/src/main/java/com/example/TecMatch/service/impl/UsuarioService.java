package com.example.TecMatch.service.impl;

import com.example.TecMatch.config.JpaUtil;
import com.example.TecMatch.dao.impl.HobbieDAO;
import com.example.TecMatch.dao.impl.InteresDAO;
import com.example.TecMatch.dao.impl.UsuarioDAO;
import com.example.TecMatch.dao.interfaces.IHobbieDAO;
import com.example.TecMatch.dao.interfaces.IInteresDAO;
import com.example.TecMatch.dao.interfaces.IUsuarioDAO;
import com.example.TecMatch.domain.*;
import com.example.TecMatch.dto.UsuarioDTO;
import com.example.TecMatch.mapper.UsuarioMapper;
import com.example.TecMatch.service.interfaces.IUsuarioService;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioService implements IUsuarioService {

    @Override
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) throws Exception {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            IUsuarioDAO usuarioDAO = new UsuarioDAO(em);
            IInteresDAO interesDAO = new InteresDAO(em);
            IHobbieDAO hobbieDAO = new HobbieDAO(em);
            if (usuarioDTO.getCorreo() == null || usuarioDTO.getCorreo().isBlank()) {
                throw new Exception("El correo es obligatorio.");
            }
            if (usuarioDAO.buscarPorCorreo(usuarioDTO.getCorreo()) != null) {
                throw new Exception("El correo '" + usuarioDTO.getCorreo() + "' ya está registrado.");
            }
            Usuario usuario = UsuarioMapper.mapToEntity(usuarioDTO);
            em.getTransaction().begin();
            if (usuarioDTO.getIntereses() != null) {
                for (String desc : usuarioDTO.getIntereses()) {
                    Interes interes = interesDAO.buscarOCrearPorDescripcion(desc);
                    InteresUsuario interesUsuario = new InteresUsuario(usuario, interes);
                    usuario.getInteresUsuarios().add(interesUsuario);
                }
            }
            if (usuarioDTO.getHobbies() != null) {
                for (String desc : usuarioDTO.getHobbies()) {
                    Hobbie hobbie = hobbieDAO.buscarOCrearPorDescripcion(desc);
                    HobbieUsuario hobbieUsuario = new HobbieUsuario(usuario, hobbie);
                    usuario.getHobbieUsuarios().add(hobbieUsuario);
                }
            }
            usuarioDAO.crear(usuario);
            em.getTransaction().commit();
            return UsuarioMapper.mapToDTO(usuario);

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Error al crear usuario: " + e.getMessage(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public UsuarioDTO buscarUsuarioPorId(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            IUsuarioDAO dao = new UsuarioDAO(em);
            Usuario usuario = dao.buscarPorId(id);
            return UsuarioMapper.mapToDTO(usuario);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<UsuarioDTO> listarUsuarios(int limite) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            int limiteValidado = Math.min(limite, 100);
            IUsuarioDAO dao = new UsuarioDAO(em);
            List<Usuario> usuarios = dao.listar(limiteValidado);
            return usuarios.stream()
                    .map(UsuarioMapper::mapToDTO)
                    .collect(Collectors.toList());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public UsuarioDTO actualizarUsuario(Long id, UsuarioDTO usuarioDTO) throws Exception {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            IUsuarioDAO usuarioDAO = new UsuarioDAO(em);
            IInteresDAO interesDAO = new InteresDAO(em);
            IHobbieDAO hobbieDAO = new HobbieDAO(em);
            em.getTransaction().begin();
            Usuario usuario = usuarioDAO.buscarPorId(id);
            if (usuario == null) {
                throw new Exception("No se encontró el usuario con ID: " + id);
            }
            if (!usuario.getCorreo().equals(usuarioDTO.getCorreo())) {
                if (usuarioDAO.buscarPorCorreo(usuarioDTO.getCorreo()) != null) {
                    throw new Exception("El nuevo correo '" + usuarioDTO.getCorreo() + "' ya está en uso.");
                }
                usuario.setCorreo(usuarioDTO.getCorreo());
            }
            usuario.setNombre(usuarioDTO.getNombre());
            usuario.setCarrera(usuarioDTO.getCarrera());
            usuario.setDescripcion(usuarioDTO.getDescripcion());
            usuario.setSexo(usuarioDTO.getSexo());
            usuario.setFechaNacimiento(usuarioDTO.getFechaNacimiento());
            if (usuarioDTO.getContrasenia() != null && !usuarioDTO.getContrasenia().isBlank()) {
                usuario.setContrasenia(usuarioDTO.getContrasenia());
            }
            usuario.getHobbieUsuarios().clear();
            if (usuarioDTO.getHobbies() != null) {
                for (String desc : usuarioDTO.getHobbies()) {
                    Hobbie hobbie = hobbieDAO.buscarOCrearPorDescripcion(desc);
                    usuario.getHobbieUsuarios().add(new HobbieUsuario(usuario, hobbie));
                }
            }
            usuario.getInteresUsuarios().clear();
            if (usuarioDTO.getIntereses() != null) {
                for (String desc : usuarioDTO.getIntereses()) {
                    Interes interes = interesDAO.buscarOCrearPorDescripcion(desc);
                    usuario.getInteresUsuarios().add(new InteresUsuario(usuario, interes));
                }
            }
            Usuario usuarioActualizado = usuarioDAO.actualizar(usuario);
            em.getTransaction().commit();
            return UsuarioMapper.mapToDTO(usuarioActualizado);
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Error al actualizar usuario: " + e.getMessage(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void eliminarUsuario(Long id) throws Exception {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            IUsuarioDAO dao = new UsuarioDAO(em);
            if (dao.buscarPorId(id) == null) {
                throw new Exception("No se encontró el usuario con ID: " + id + " para eliminar.");
            }
            em.getTransaction().begin();
            dao.eliminar(id);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Error al eliminar usuario: " + e.getMessage(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public UsuarioDTO buscarUsuarioPorCorreo(String correo) {
        try (EntityManager em = JpaUtil.getEntityManager()) {
            IUsuarioDAO dao = new UsuarioDAO(em);
            Usuario usuario = dao.buscarPorCorreo(correo);
            return UsuarioMapper.mapToDTO(usuario);
        }
    }
}
