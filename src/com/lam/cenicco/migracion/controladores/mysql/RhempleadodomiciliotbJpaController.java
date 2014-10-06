/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.controladores.mysql;

import com.lam.cenicco.migracion.controladores.mysql.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.lam.cenicco.migracion.entidades.mysql.Rhempleadotb;
import com.lam.cenicco.migracion.entidades.mysql.Catasentamientotb;
import com.lam.cenicco.migracion.entidades.mysql.Rhempleadodomiciliotb;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class RhempleadodomiciliotbJpaController implements Serializable {

    public RhempleadodomiciliotbJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rhempleadodomiciliotb rhempleadodomiciliotb) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rhempleadotb idempleado = rhempleadodomiciliotb.getIdempleado();
            if (idempleado != null) {
                idempleado = em.getReference(idempleado.getClass(), idempleado.getIdempleado());
                rhempleadodomiciliotb.setIdempleado(idempleado);
            }
            Catasentamientotb idasentamiento = rhempleadodomiciliotb.getIdasentamiento();
            if (idasentamiento != null) {
                idasentamiento = em.getReference(idasentamiento.getClass(), idasentamiento.getIdasentamiento());
                rhempleadodomiciliotb.setIdasentamiento(idasentamiento);
            }
            em.persist(rhempleadodomiciliotb);
            if (idempleado != null) {
                idempleado.getRhempleadodomiciliotbList().add(rhempleadodomiciliotb);
                idempleado = em.merge(idempleado);
            }
            if (idasentamiento != null) {
                idasentamiento.getRhempleadodomiciliotbList().add(rhempleadodomiciliotb);
                idasentamiento = em.merge(idasentamiento);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rhempleadodomiciliotb rhempleadodomiciliotb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rhempleadodomiciliotb persistentRhempleadodomiciliotb = em.find(Rhempleadodomiciliotb.class, rhempleadodomiciliotb.getIddomicilio());
            Rhempleadotb idempleadoOld = persistentRhempleadodomiciliotb.getIdempleado();
            Rhempleadotb idempleadoNew = rhempleadodomiciliotb.getIdempleado();
            Catasentamientotb idasentamientoOld = persistentRhempleadodomiciliotb.getIdasentamiento();
            Catasentamientotb idasentamientoNew = rhempleadodomiciliotb.getIdasentamiento();
            if (idempleadoNew != null) {
                idempleadoNew = em.getReference(idempleadoNew.getClass(), idempleadoNew.getIdempleado());
                rhempleadodomiciliotb.setIdempleado(idempleadoNew);
            }
            if (idasentamientoNew != null) {
                idasentamientoNew = em.getReference(idasentamientoNew.getClass(), idasentamientoNew.getIdasentamiento());
                rhempleadodomiciliotb.setIdasentamiento(idasentamientoNew);
            }
            rhempleadodomiciliotb = em.merge(rhempleadodomiciliotb);
            if (idempleadoOld != null && !idempleadoOld.equals(idempleadoNew)) {
                idempleadoOld.getRhempleadodomiciliotbList().remove(rhempleadodomiciliotb);
                idempleadoOld = em.merge(idempleadoOld);
            }
            if (idempleadoNew != null && !idempleadoNew.equals(idempleadoOld)) {
                idempleadoNew.getRhempleadodomiciliotbList().add(rhempleadodomiciliotb);
                idempleadoNew = em.merge(idempleadoNew);
            }
            if (idasentamientoOld != null && !idasentamientoOld.equals(idasentamientoNew)) {
                idasentamientoOld.getRhempleadodomiciliotbList().remove(rhempleadodomiciliotb);
                idasentamientoOld = em.merge(idasentamientoOld);
            }
            if (idasentamientoNew != null && !idasentamientoNew.equals(idasentamientoOld)) {
                idasentamientoNew.getRhempleadodomiciliotbList().add(rhempleadodomiciliotb);
                idasentamientoNew = em.merge(idasentamientoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rhempleadodomiciliotb.getIddomicilio();
                if (findRhempleadodomiciliotb(id) == null) {
                    throw new NonexistentEntityException("The rhempleadodomiciliotb with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rhempleadodomiciliotb rhempleadodomiciliotb;
            try {
                rhempleadodomiciliotb = em.getReference(Rhempleadodomiciliotb.class, id);
                rhempleadodomiciliotb.getIddomicilio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rhempleadodomiciliotb with id " + id + " no longer exists.", enfe);
            }
            Rhempleadotb idempleado = rhempleadodomiciliotb.getIdempleado();
            if (idempleado != null) {
                idempleado.getRhempleadodomiciliotbList().remove(rhempleadodomiciliotb);
                idempleado = em.merge(idempleado);
            }
            Catasentamientotb idasentamiento = rhempleadodomiciliotb.getIdasentamiento();
            if (idasentamiento != null) {
                idasentamiento.getRhempleadodomiciliotbList().remove(rhempleadodomiciliotb);
                idasentamiento = em.merge(idasentamiento);
            }
            em.remove(rhempleadodomiciliotb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rhempleadodomiciliotb> findRhempleadodomiciliotbEntities() {
        return findRhempleadodomiciliotbEntities(true, -1, -1);
    }

    public List<Rhempleadodomiciliotb> findRhempleadodomiciliotbEntities(int maxResults, int firstResult) {
        return findRhempleadodomiciliotbEntities(false, maxResults, firstResult);
    }

    private List<Rhempleadodomiciliotb> findRhempleadodomiciliotbEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rhempleadodomiciliotb.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Rhempleadodomiciliotb findRhempleadodomiciliotb(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rhempleadodomiciliotb.class, id);
        } finally {
            em.close();
        }
    }

    public int getRhempleadodomiciliotbCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rhempleadodomiciliotb> rt = cq.from(Rhempleadodomiciliotb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
