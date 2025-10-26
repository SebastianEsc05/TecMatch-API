package com.example.TecMatch.dao.impl;

import com.example.TecMatch.config.JpaUtil;
import com.example.TecMatch.dao.interfaces.IUsuarioDAO;
import com.example.TecMatch.domain.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UsuarioDAO implements IUsuarioDAO {

    @Override
    public boolean crear(Usuario usuario) {
        EntityManager em = JpaUtil.getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
            return true;
        }catch(Exception exception){
            if(em.getTransaction().isActive())
                em.getTransaction().rollback();
            exception.printStackTrace();
            return false;
        }finally {
            em.close();
        }
    }

    @Override
    public Usuario buscarPorId(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        Usuario usuario = null;
        try{
          usuario = em.find(Usuario.class, id);
        }catch(Exception exception){
            em.getTransaction().rollback();
            exception.printStackTrace();
        }finally {
            em.close();
        }
        return usuario;
    }

    @Override
    public List<Usuario> listar(int limite) {
        EntityManager em = JpaUtil.getEntityManager();
        List<Usuario> lista = null;
        try{
            TypedQuery<Usuario> query = em.createQuery("SELECT e FROM Usuario e", Usuario.class);
            query.setMaxResults(Math.min(limite,100));
            lista = query.getResultList();
        }catch(Exception exception){
            exception.printStackTrace();
        }finally {
            em.close();
        }
        return lista;
    }

    @Override
    public boolean actualizar(Usuario usuario) {
        EntityManager em = JpaUtil.getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
            return true;
        }catch(Exception exception){
            if(em.getTransaction().isActive())
                em.getTransaction().rollback();
            exception.printStackTrace();
            return false;
        }finally {
            em.close();
        }
    }

    @Override
    public boolean eliminar(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try{
            Usuario usuario = em.find(Usuario.class, id);
            if(usuario != null){
                em.getTransaction().begin();
                em.remove(usuario);
                em.getTransaction().commit();
                return true;
            }
            return false;
        }catch (Exception exception){
            if(em.getTransaction().isActive())
                em.getTransaction().rollback();
            exception.printStackTrace();
            return false;
        }finally {
            em.close();
        }
    }

    @Override
    public Usuario buscarPorCorreo(String correo) {
        EntityManager em = JpaUtil.getEntityManager();
        try{
            return em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.correo = :correo", Usuario.class)
                    .setParameter("correo",correo)
                    .getSingleResult();
        }catch (Exception exception){
            exception.printStackTrace();
            return null;
        }
    }
}
