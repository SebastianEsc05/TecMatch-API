package com.example.TecMatch.dao.impl;

import com.example.TecMatch.config.JpaUtil;
import com.example.TecMatch.dao.interfaces.IMensajeDAO;
import com.example.TecMatch.domain.Mensaje;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class MensajeDAO implements IMensajeDAO {
    @Override
    public boolean crear(Mensaje mensaje) {
        EntityManager em = JpaUtil.getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(mensaje);
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
    public Mensaje buscarPorId(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        Mensaje mensaje = null;
        try{
            mensaje = em.find(Mensaje.class, id);
        }catch(Exception exception){
            em.getTransaction().rollback();
            exception.printStackTrace();
        }finally {
            em.close();
        }
        return mensaje;    }

    @Override
    public List<Mensaje> listar(int limite) {
        EntityManager em = JpaUtil.getEntityManager();
        List<Mensaje> lista = null;
        try{
            TypedQuery<Mensaje> query = em.createQuery("SELECT m FROM Mensaje m", Mensaje.class);
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
    public boolean actualizar(Mensaje mensaje) {
        EntityManager em = JpaUtil.getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(mensaje);
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
            Mensaje mensaje = em.find(Mensaje.class, id);
            if(mensaje != null){
                em.getTransaction().begin();
                em.remove(mensaje);
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
}
