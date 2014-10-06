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
import com.lam.cenicco.migracion.entidades.mysql.Rhrelacionlaboraltb;
import com.lam.cenicco.migracion.entidades.mysql.Rhtiporelacionlaboraltb;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class RhtiporelacionlaboraltbJpaController implements Serializable {

    public RhtiporelacionlaboraltbJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rhtiporelacionlaboraltb rhtiporelacionlaboraltb) {
        if (rhtiporelacionlaboraltb.getRhrelacionlaboraltbList() == null) {
            rhtiporelacionlaboraltb.setRhrelacionlaboraltbList(new ArrayList<Rhrelacionlaboraltb>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Rhrelacionlaboraltb> attachedRhrelacionlaboraltbList = new ArrayList<Rhrelacionlaboraltb>();
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListRhrelacionlaboraltbToAttach : rhtiporelacionlaboraltb.getRhrelacionlaboraltbList()) {
                rhrelacionlaboraltbListRhrelacionlaboraltbToAttach = em.getReference(rhrelacionlaboraltbListRhrelacionlaboraltbToAttach.getClass(), rhrelacionlaboraltbListRhrelacionlaboraltbToAttach.getIdrellab());
                attachedRhrelacionlaboraltbList.add(rhrelacionlaboraltbListRhrelacionlaboraltbToAttach);
            }
            rhtiporelacionlaboraltb.setRhrelacionlaboraltbList(attachedRhrelacionlaboraltbList);
            em.persist(rhtiporelacionlaboraltb);
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListRhrelacionlaboraltb : rhtiporelacionlaboraltb.getRhrelacionlaboraltbList()) {
                Rhtiporelacionlaboraltb oldIdtiporellabOfRhrelacionlaboraltbListRhrelacionlaboraltb = rhrelacionlaboraltbListRhrelacionlaboraltb.getIdtiporellab();
                rhrelacionlaboraltbListRhrelacionlaboraltb.setIdtiporellab(rhtiporelacionlaboraltb);
                rhrelacionlaboraltbListRhrelacionlaboraltb = em.merge(rhrelacionlaboraltbListRhrelacionlaboraltb);
                if (oldIdtiporellabOfRhrelacionlaboraltbListRhrelacionlaboraltb != null) {
                    oldIdtiporellabOfRhrelacionlaboraltbListRhrelacionlaboraltb.getRhrelacionlaboraltbList().remove(rhrelacionlaboraltbListRhrelacionlaboraltb);
                    oldIdtiporellabOfRhrelacionlaboraltbListRhrelacionlaboraltb = em.merge(oldIdtiporellabOfRhrelacionlaboraltbListRhrelacionlaboraltb);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rhtiporelacionlaboraltb rhtiporelacionlaboraltb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rhtiporelacionlaboraltb persistentRhtiporelacionlaboraltb = em.find(Rhtiporelacionlaboraltb.class, rhtiporelacionlaboraltb.getIdtiporelacionlaboral());
            List<Rhrelacionlaboraltb> rhrelacionlaboraltbListOld = persistentRhtiporelacionlaboraltb.getRhrelacionlaboraltbList();
            List<Rhrelacionlaboraltb> rhrelacionlaboraltbListNew = rhtiporelacionlaboraltb.getRhrelacionlaboraltbList();
            List<Rhrelacionlaboraltb> attachedRhrelacionlaboraltbListNew = new ArrayList<Rhrelacionlaboraltb>();
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach : rhrelacionlaboraltbListNew) {
                rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach = em.getReference(rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach.getClass(), rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach.getIdrellab());
                attachedRhrelacionlaboraltbListNew.add(rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach);
            }
            rhrelacionlaboraltbListNew = attachedRhrelacionlaboraltbListNew;
            rhtiporelacionlaboraltb.setRhrelacionlaboraltbList(rhrelacionlaboraltbListNew);
            rhtiporelacionlaboraltb = em.merge(rhtiporelacionlaboraltb);
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListOldRhrelacionlaboraltb : rhrelacionlaboraltbListOld) {
                if (!rhrelacionlaboraltbListNew.contains(rhrelacionlaboraltbListOldRhrelacionlaboraltb)) {
                    rhrelacionlaboraltbListOldRhrelacionlaboraltb.setIdtiporellab(null);
                    rhrelacionlaboraltbListOldRhrelacionlaboraltb = em.merge(rhrelacionlaboraltbListOldRhrelacionlaboraltb);
                }
            }
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListNewRhrelacionlaboraltb : rhrelacionlaboraltbListNew) {
                if (!rhrelacionlaboraltbListOld.contains(rhrelacionlaboraltbListNewRhrelacionlaboraltb)) {
                    Rhtiporelacionlaboraltb oldIdtiporellabOfRhrelacionlaboraltbListNewRhrelacionlaboraltb = rhrelacionlaboraltbListNewRhrelacionlaboraltb.getIdtiporellab();
                    rhrelacionlaboraltbListNewRhrelacionlaboraltb.setIdtiporellab(rhtiporelacionlaboraltb);
                    rhrelacionlaboraltbListNewRhrelacionlaboraltb = em.merge(rhrelacionlaboraltbListNewRhrelacionlaboraltb);
                    if (oldIdtiporellabOfRhrelacionlaboraltbListNewRhrelacionlaboraltb != null && !oldIdtiporellabOfRhrelacionlaboraltbListNewRhrelacionlaboraltb.equals(rhtiporelacionlaboraltb)) {
                        oldIdtiporellabOfRhrelacionlaboraltbListNewRhrelacionlaboraltb.getRhrelacionlaboraltbList().remove(rhrelacionlaboraltbListNewRhrelacionlaboraltb);
                        oldIdtiporellabOfRhrelacionlaboraltbListNewRhrelacionlaboraltb = em.merge(oldIdtiporellabOfRhrelacionlaboraltbListNewRhrelacionlaboraltb);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rhtiporelacionlaboraltb.getIdtiporelacionlaboral();
                if (findRhtiporelacionlaboraltb(id) == null) {
                    throw new NonexistentEntityException("The rhtiporelacionlaboraltb with id " + id + " no longer exists.");
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
            Rhtiporelacionlaboraltb rhtiporelacionlaboraltb;
            try {
                rhtiporelacionlaboraltb = em.getReference(Rhtiporelacionlaboraltb.class, id);
                rhtiporelacionlaboraltb.getIdtiporelacionlaboral();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rhtiporelacionlaboraltb with id " + id + " no longer exists.", enfe);
            }
            List<Rhrelacionlaboraltb> rhrelacionlaboraltbList = rhtiporelacionlaboraltb.getRhrelacionlaboraltbList();
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListRhrelacionlaboraltb : rhrelacionlaboraltbList) {
                rhrelacionlaboraltbListRhrelacionlaboraltb.setIdtiporellab(null);
                rhrelacionlaboraltbListRhrelacionlaboraltb = em.merge(rhrelacionlaboraltbListRhrelacionlaboraltb);
            }
            em.remove(rhtiporelacionlaboraltb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rhtiporelacionlaboraltb> findRhtiporelacionlaboraltbEntities() {
        return findRhtiporelacionlaboraltbEntities(true, -1, -1);
    }

    public List<Rhtiporelacionlaboraltb> findRhtiporelacionlaboraltbEntities(int maxResults, int firstResult) {
        return findRhtiporelacionlaboraltbEntities(false, maxResults, firstResult);
    }

    private List<Rhtiporelacionlaboraltb> findRhtiporelacionlaboraltbEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rhtiporelacionlaboraltb.class));
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

    public Rhtiporelacionlaboraltb findRhtiporelacionlaboraltb(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rhtiporelacionlaboraltb.class, id);
        } finally {
            em.close();
        }
    }

    public int getRhtiporelacionlaboraltbCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rhtiporelacionlaboraltb> rt = cq.from(Rhtiporelacionlaboraltb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
