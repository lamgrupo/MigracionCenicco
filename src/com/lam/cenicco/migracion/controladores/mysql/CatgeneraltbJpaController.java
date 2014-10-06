/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.controladores.mysql;

import com.lam.cenicco.migracion.controladores.mysql.exceptions.NonexistentEntityException;
import com.lam.cenicco.migracion.entidades.mysql.Catgeneraltb;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author JoséAntonio
 */
public class CatgeneraltbJpaController implements Serializable {

    public CatgeneraltbJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Catgeneraltb catgeneraltb) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(catgeneraltb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Catgeneraltb catgeneraltb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            catgeneraltb = em.merge(catgeneraltb);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = catgeneraltb.getIdcatgeneral();
                if (findCatgeneraltb(id) == null) {
                    throw new NonexistentEntityException("The catgeneraltb with id " + id + " no longer exists.");
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
            Catgeneraltb catgeneraltb;
            try {
                catgeneraltb = em.getReference(Catgeneraltb.class, id);
                catgeneraltb.getIdcatgeneral();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The catgeneraltb with id " + id + " no longer exists.", enfe);
            }
            em.remove(catgeneraltb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Catgeneraltb> findCatgeneraltbEntities() {
        return findCatgeneraltbEntities(true, -1, -1);
    }

    public List<Catgeneraltb> findCatgeneraltbEntities(int maxResults, int firstResult) {
        return findCatgeneraltbEntities(false, maxResults, firstResult);
    }

    private List<Catgeneraltb> findCatgeneraltbEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Catgeneraltb.class));
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

    public Catgeneraltb findCatgeneraltb(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Catgeneraltb.class, id);
        } finally {
            em.close();
        }
    }

    public int getCatgeneraltbCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Catgeneraltb> rt = cq.from(Catgeneraltb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
