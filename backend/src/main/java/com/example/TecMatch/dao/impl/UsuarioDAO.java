package com.example.TecMatch.dao.impl;

import com.example.TecMatch.dao.interfaces.IUsuarioDAO;
import com.example.TecMatch.domain.Usuario;
import jakarta.persistence.*;

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

    @Override
    public List<Usuario> listarPorLikes(int offset){
        try {
            StoredProcedureQuery query = em.
                    createStoredProcedureQuery("listarPorLikes", Usuario.class);
            query.registerStoredProcedureParameter("p_offset", int.class, ParameterMode.IN);
            query.setParameter("p_offset", offset);
            return query.getResultList();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return List.of();
    }

    @Override
    public List<Usuario> listarPorDislikes(int offset){
        StoredProcedureQuery query = em.
                createStoredProcedureQuery("listarPorDislikes",Usuario.class);
        query.registerStoredProcedureParameter("p_offset",int.class, ParameterMode.IN);
        query.setParameter("p_offset",offset);
        return query.getResultList();
    }

    @Override
    public List<Usuario> listarPorHobbies(String descripcion,int offset){
        StoredProcedureQuery query = em.
                createStoredProcedureQuery("listarAlumnosPorHobbies",Usuario.class);
        query.registerStoredProcedureParameter("p_descripcion",String.class, ParameterMode.IN);
        query.setParameter("p_descripcion",descripcion);
        query.registerStoredProcedureParameter("p_offset",int.class, ParameterMode.IN);
        query.setParameter("p_offset",offset);

        return query.getResultList();
    }

    @Override
    public List<Usuario> listarPorIntereses(String descripcion,int offset){
        StoredProcedureQuery query = em.
                createStoredProcedureQuery("listarAlumnosPorIntereses",Usuario.class);
        query.registerStoredProcedureParameter("p_descripcion",String.class, ParameterMode.IN);
        query.setParameter("p_descripcion",descripcion);
        query.registerStoredProcedureParameter("p_offset",int.class, ParameterMode.IN);
        query.setParameter("p_offset",offset);

        return query.getResultList();
    }

    @Override
    public List<Usuario> listarPorLikes(int id, int offset){
        StoredProcedureQuery query = em.
                createStoredProcedureQuery("listarLikes",Usuario.class);
        query.registerStoredProcedureParameter("p_usuarioId",int.class, ParameterMode.IN);
        query.setParameter("p_usuarioId",id);
        query.registerStoredProcedureParameter("p_offset",int.class, ParameterMode.IN);
        query.setParameter("p_offset",offset);

        return query.getResultList();
    }

    @Override
    public List<Usuario> listarMatches(int id, int offset){
        StoredProcedureQuery query = em.
                createStoredProcedureQuery("listarMatches",Usuario.class);
        query.registerStoredProcedureParameter("p_usuarioId",int.class, ParameterMode.IN);
        query.setParameter("p_usuarioId",id);
        query.registerStoredProcedureParameter("p_offset",int.class, ParameterMode.IN);
        query.setParameter("p_offset",offset);
        return query.getResultList();
    }

    @Override
    public List<Usuario> listarPorHobbieEInteres(String descripcionHobbie,String descripcionInteres,int offset){
        StoredProcedureQuery query = em.
                createStoredProcedureQuery("listarPorHobbiesEIntereses");
        query.registerStoredProcedureParameter("descripcionHobbie",String.class, ParameterMode.IN);
        query.setParameter("descripcionHobbie",descripcionHobbie);
        query.registerStoredProcedureParameter("descripcionInteres",String.class, ParameterMode.IN);
        query.setParameter("descripcionInteres",descripcionInteres);
        query.registerStoredProcedureParameter("p_offset",int.class, ParameterMode.IN);
        query.setParameter("p_offset",offset);

        return query.getResultList();
    }
}
