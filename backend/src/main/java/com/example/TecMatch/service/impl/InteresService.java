package com.example.TecMatch.service.impl;

import com.example.TecMatch.config.JpaUtil;
import com.example.TecMatch.dao.impl.InteresDAO;
import com.example.TecMatch.dao.interfaces.IInteresDAO;
import com.example.TecMatch.domain.Interes;
import com.example.TecMatch.dto.InteresDTO;
import com.example.TecMatch.mapper.InteresMapper;
import com.example.TecMatch.service.interfaces.IInteresService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class InteresService implements IInteresService {
    private final EntityManagerFactory emf;

    public InteresService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public InteresDTO crearInteres(InteresDTO interesDTO) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            IInteresDAO dao = new InteresDAO(em);
            if (interesDTO.getDescripcion() == null || interesDTO.getDescripcion().isBlank()) {
                throw new Exception("La descripción del interés no puede estar vacía.");
            }
            if (dao.buscarPorDescripcion(interesDTO.getDescripcion()) != null) {
                throw new Exception("El interés '" + interesDTO.getDescripcion() + "' ya existe.");
            }

            Interes entity = InteresMapper.mapToEntity(interesDTO);

            em.getTransaction().begin();
            dao.crear(entity);
            em.getTransaction().commit();

            return InteresMapper.mapToDTO(entity);

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Error al crear el interés: " + e.getMessage(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public InteresDTO buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Interes entity = new InteresDAO(em).buscarPorId(id);
            return InteresMapper.mapToDTO(entity);
        } finally {
            em.close();
        }
    }

    @Override
    public InteresDTO buscarPorDescripcion(String descripcion) {
        EntityManager em = emf.createEntityManager();
        try {
            Interes entity = new InteresDAO(em).buscarPorDescripcion(descripcion);
            return InteresMapper.mapToDTO(entity);
        } finally {
            em.close();
        }
    }

    @Override
    public List<InteresDTO> listarIntereses(int limite) {
        EntityManager em = emf.createEntityManager();
        try {
            int limiteValidado = Math.min(limite, 100);
            List<Interes> lista = new InteresDAO(em).listar(limiteValidado);
            return lista.stream()
                    .map(InteresMapper::mapToDTO)
                    .collect(Collectors.toList());
        } finally {
            em.close();
        }
    }

    @Override
    public InteresDTO actualizarInteres(Long id, InteresDTO interesDTO) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            IInteresDAO dao = new InteresDAO(em);

            em.getTransaction().begin();

            Interes entity = dao.buscarPorId(id);
            if (entity == null) {
                throw new Exception("No se encontró el interés con ID: " + id);
            }
            Interes existente = dao.buscarPorDescripcion(interesDTO.getDescripcion());
            if (existente != null && !existente.getId().equals(id)) {
                throw new Exception("La descripción '" + interesDTO.getDescripcion() + "' ya está en uso por otro interés.");
            }

            entity.setDescripcion(interesDTO.getDescripcion());
            Interes actualizado = dao.actualizar(entity);

            em.getTransaction().commit();
            return InteresMapper.mapToDTO(actualizado);

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Error al actualizar el interés: " + e.getMessage(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void eliminarInteres(Long id) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            IInteresDAO dao = new InteresDAO(em);
            if (dao.buscarPorId(id) == null) {
                throw new Exception("No se encontró el interés con ID: " + id);
            }
            em.getTransaction().begin();
            dao.eliminar(id);
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
