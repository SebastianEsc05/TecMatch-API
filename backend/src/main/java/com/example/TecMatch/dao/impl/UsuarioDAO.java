package com.example.TecMatch.dao.impl;

import com.example.TecMatch.config.JpaUtil;
import com.example.TecMatch.dao.interfaces.IUsuarioDAO;
import com.example.TecMatch.domain.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UsuarioDAO implements IUsuarioDAO {
    private final EntityManager em;

    public UsuarioDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public void crear(Usuario usuario) {
        em.persist(usuario);
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return em.find(Usuario.class,id);
    }

    @Override
    public List<Usuario> listar(int limite) {
        TypedQuery<Usuario> query = em.createQuery("SELECT e FROM Usuario e", Usuario.class);
        query.setMaxResults(limite);
        return query.getResultList();
    }

    @Override
    public Usuario actualizar(Usuario usuario) {
        return em.merge(usuario);
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = buscarPorId(id);
        if (usuario != null) {
            em.remove(usuario);
        }
    }

    @Override
    public Usuario buscarPorCorreo(String correo) {
        try {
            return em.createQuery(
                            "SELECT u FROM Usuario u WHERE u.correo = :correo", Usuario.class)
                    .setParameter("correo", correo)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
