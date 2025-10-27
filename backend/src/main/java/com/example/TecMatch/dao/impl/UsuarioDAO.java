package com.example.TecMatch.dao.impl;

import com.example.TecMatch.config.JpaUtil;
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

    /**
     * Metodo para listar a los usuarios mas populares (con más Likes)
     * @param offset
     * @return
     */
    public List<Usuario> listarPorLikes(int offset){
        StoredProcedureQuery query= em.
                createStoredProcedureQuery("listarPorLikes",Usuario.class);
        query.registerStoredProcedureParameter("p_offset",int.class, ParameterMode.IN);
        query.setParameter("p_offset",offset);
        return query.getResultList();
    }

    /**
     * Metodo para listar a los usuarios mas polemicos (con más dislikes)
     * @param offset
     * @return
     */
    public List<Usuario> listarPorDislikes(int offset){
        StoredProcedureQuery query = em.
                createStoredProcedureQuery("listarPorDislikes",Usuario.class);
        query.registerStoredProcedureParameter("p_offset",int.class, ParameterMode.IN);
        query.setParameter("p_offset",offset);
        return query.getResultList();
    }

    /**
     * Metodo para listar de manera segregada a usuarios con el mismo hobbie
     * @param descripcion
     * @param offset
     * @return
     */
    public List<Usuario> listarPorHobbies(String descripcion,int offset){
        StoredProcedureQuery query = em.
                createStoredProcedureQuery("listarAlumnosPorHobbies",Usuario.class);
        query.registerStoredProcedureParameter("p_descripcion",String.class, ParameterMode.IN);
        query.setParameter("p_descripcion",descripcion);
        query.registerStoredProcedureParameter("p_offset",int.class, ParameterMode.IN);
        query.setParameter("p_offset",offset);

        return query.getResultList();
    }

    /**
     * Metodo para listar de manera segregada a usuarios con el mismo Interes
     * @param descripcion
     * @param offset
     * @return
     */
    public List<Usuario> listarPorIntereses(String descripcion,int offset){
        StoredProcedureQuery query = em.
                createStoredProcedureQuery("listarAlumnosPorIntereses",Usuario.class);
        query.registerStoredProcedureParameter("p_descripcion",String.class, ParameterMode.IN);
        query.setParameter("p_descripcion",descripcion);
        query.registerStoredProcedureParameter("p_offset",int.class, ParameterMode.IN);
        query.setParameter("p_offset",offset);

        return query.getResultList();
    }

    /**
     * Metodo para listar a los usuarios que le dieron like a un usuario en especifico
     * @param id
     * @param offset
     * @return
     */
    public List<Usuario> listarPorLikes(int id, int offset){
        StoredProcedureQuery query = em.
                createStoredProcedureQuery("listarLikes",Usuario.class);
        query.registerStoredProcedureParameter("p_usuarioId",int.class, ParameterMode.IN);
        query.setParameter("p_usuarioId",id);
        query.registerStoredProcedureParameter("p_offset",int.class, ParameterMode.IN);
        query.setParameter("p_offset",offset);

        return query.getResultList();
    }

    /**
     * Metodo para listar a los usuarios que le dieron dislike a un usuario en especifico
     * @param id
     * @param offset
     * @return
     */
    public List<Usuario> listarDislikes(int id, int offset){
        StoredProcedureQuery query = em.
                createStoredProcedureQuery("listarDisikes",Usuario.class);
        query.registerStoredProcedureParameter("p_usuarioId",int.class, ParameterMode.IN);
        query.setParameter("p_usuarioId",id);
        query.registerStoredProcedureParameter("p_offset",int.class, ParameterMode.IN);
        query.setParameter("p_offset",offset);

        return query.getResultList();
    }

    /**
     * Metodo para listar a los usuarios que hicieron Match con un usuario
     * @param id
     * @param offset
     * @return
     */
    public List<Usuario> listarMatches(int id, int offset){
        StoredProcedureQuery query = em.
                createStoredProcedureQuery("listarMatches",Usuario.class);
        query.registerStoredProcedureParameter("p_usuarioId",int.class, ParameterMode.IN);
        query.setParameter("p_usuarioId",id);
        query.registerStoredProcedureParameter("p_offset",int.class, ParameterMode.IN);
        query.setParameter("p_offset",offset);

        return query.getResultList();
    }

    /**
     * Metodo para listar a los usuarios según su hobbie e interes
     * @param descripcionHobbie
     * @param descripcionInteres
     * @param offset
     * @return
     */
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
