package com.example.TecMatch.service.impl;

import com.example.TecMatch.config.JpaUtil;
import com.example.TecMatch.dao.impl.HobbieDAO;
import com.example.TecMatch.dao.interfaces.IHobbieDAO;
import com.example.TecMatch.domain.Hobbie;
import com.example.TecMatch.dto.HobbieDTO;
import com.example.TecMatch.mapper.HobbieMapper;
import com.example.TecMatch.service.interfaces.IHobbieService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class HobbieService implements IHobbieService {
    private final EntityManagerFactory emf;

    public HobbieService(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public HobbieDTO crearHobbie(HobbieDTO hobbieDTO) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            IHobbieDAO dao = new HobbieDAO(em);

            if (hobbieDTO.getDescripcion() == null || hobbieDTO.getDescripcion().isBlank()) {
                throw new Exception("La descripción del hobbie no puede estar vacía.");
            }

            if (dao.buscarPorDescripcion(hobbieDTO.getDescripcion()) != null) {
                throw new Exception("El hobbie '" + hobbieDTO.getDescripcion() + "' ya existe.");
            }

            Hobbie entity = HobbieMapper.mapToEntity(hobbieDTO);

            em.getTransaction().begin();
            dao.crear(entity);
            em.getTransaction().commit();

            return HobbieMapper.mapToDTO(entity);

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Error al crear el hobbie: " + e.getMessage(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public HobbieDTO buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Hobbie entity = new HobbieDAO(em).buscarPorId(id);
            return HobbieMapper.mapToDTO(entity);
        } finally {
            em.close();
        }
    }

    @Override
    public HobbieDTO buscarPorDescripcion(String descripcion) {
        EntityManager em = emf.createEntityManager();
        try {
            Hobbie entity = new HobbieDAO(em).buscarPorDescripcion(descripcion);
            return HobbieMapper.mapToDTO(entity);
        } finally {
            em.close();
        }
    }

    @Override
    public List<HobbieDTO> listarHobbies(int limite) {
        EntityManager em = emf.createEntityManager();
        try {
            int limiteValidado = Math.min(limite, 100);
            List<Hobbie> lista = new HobbieDAO(em).listar(limiteValidado);
            return lista.stream()
                    .map(HobbieMapper::mapToDTO)
                    .collect(Collectors.toList());
        } finally {
            em.close();
        }
    }

    @Override
    public HobbieDTO actualizarHobbie(Long id, HobbieDTO hobbieDTO) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            IHobbieDAO dao = new HobbieDAO(em);

            em.getTransaction().begin();

            Hobbie entity = dao.buscarPorId(id);
            if (entity == null) {
                throw new Exception("No se encontró el hobbie con ID: " + id);
            }

            Hobbie existente = dao.buscarPorDescripcion(hobbieDTO.getDescripcion());
            if (existente != null && !existente.getId().equals(id)) {
                throw new Exception("La descripción '" + hobbieDTO.getDescripcion() + "' ya está en uso por otro hobbie.");
            }

            entity.setDescripcion(hobbieDTO.getDescripcion());
            Hobbie actualizado = dao.actualizar(entity);

            em.getTransaction().commit();
            return HobbieMapper.mapToDTO(actualizado);

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("Error al actualizar el hobbie: " + e.getMessage(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void eliminarHobbie(Long id) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            IHobbieDAO dao = new HobbieDAO(em);

            if (dao.buscarPorId(id) == null) {
                throw new Exception("No se encontró el hobbie con ID: " + id);
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
