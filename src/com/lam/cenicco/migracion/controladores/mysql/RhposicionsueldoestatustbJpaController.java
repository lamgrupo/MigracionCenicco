/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.controladores.mysql;

import com.lam.cenicco.migracion.controladores.mysql.exceptions.NonexistentEntityException;
import com.lam.cenicco.migracion.entidades.mysql.Rhposicionsueldoestatustb;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.lam.cenicco.migracion.entidades.mysql.Rhposicionsueldotb;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class RhposicionsueldoestatustbJpaController implements Serializable {

    public RhposicionsueldoestatustbJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rhposicionsueldoestatustb rhposicionsueldoestatustb) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rhposicionsueldotb idposicionsueldo = rhposicionsueldoestatustb.getIdposicionsueldo();
            if (idposicionsueldo != null) {
                idposicionsueldo = em.getReference(idposicionsueldo.getClass(), idposicionsueldo.getIdposicionsueldo());
                rhposicionsueldoestatustb.setIdposicionsueldo(idposicionsueldo);
            }
            em.persist(rhposicionsueldoestatustb);
            if (idposicionsueldo != null) {
                idposicionsueldo.getRhposicionsueldoestatustbList().add(rhposicionsueldoestatustb);
                idposicionsueldo = em.merge(idposicionsueldo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rhposicionsueldoestatustb rhposicionsueldoestatustb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rhposicionsueldoestatustb persistentRhposicionsueldoestatustb = em.find(Rhposicionsueldoestatustb.class, rhposicionsueldoestatustb.getIdposicionsueldoestatus());
            Rhposicionsueldotb idposicionsueldoOld = persistentRhposicionsueldoestatustb.getIdposicionsueldo();
            Rhposicionsueldotb idposicionsueldoNew = rhposicionsueldoestatustb.getIdposicionsueldo();
            if (idposicionsueldoNew != null) {
                idposicionsueldoNew = em.getReference(idposicionsueldoNew.getClass(), idposicionsueldoNew.getIdposicionsueldo());
                rhposicionsueldoestatustb.setIdposicionsueldo(idposicionsueldoNew);
            }
            rhposicionsueldoestatustb = em.merge(rhposicionsueldoestatustb);
            if (idposicionsueldoOld != null && !idposicionsueldoOld.equals(idposicionsueldoNew)) {
                idposicionsueldoOld.getRhposicionsueldoestatustbList().remove(rhposicionsueldoestatustb);
                idposicionsueldoOld = em.merge(idposicionsueldoOld);
            }
            if (idposicionsueldoNew != null && !idposicionsueldoNew.equals(idposicionsueldoOld)) {
                idposicionsueldoNew.getRhposicionsueldoestatustbList().add(rhposicionsueldoestatustb);
                idposicionsueldoNew = em.merge(idposicionsueldoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rhposicionsueldoestatustb.getIdposicionsueldoestatus();
                if (findRhposicionsueldoestatustb(id) == null) {
                    throw new NonexistentEntityException("The rhposicionsueldoestatustb with id " + id + " no longer exists.");
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
            Rhposicionsueldoestatustb rhposicionsueldoestatustb;
            try {
                rhposicionsueldoestatustb = em.getReference(Rhposicionsueldoestatustb.class, id);
                rhposicionsueldoestatustb.getIdposicionsueldoestatus();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rhposicionsueldoestatustb with id " + id + " no longer exists.", enfe);
            }
            Rhposicionsueldotb idposicionsueldo = rhposicionsueldoestatustb.getIdposicionsueldo();
            if (idposicionsueldo != null) {
                idposicionsueldo.getRhposicionsueldoestatustbList().remove(rhposicionsueldoestatustb);
                idposicionsueldo = em.merge(idposicionsueldo);
            }
            em.remove(rhposicionsueldoestatustb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rhposicionsueldoestatustb> findRhposicionsueldoestatustbEntities() {
        return findRhposicionsueldoestatustbEntities(true, -1, -1);
    }

    public List<Rhposicionsueldoestatustb> findRhposicionsueldoestatustbEntities(int maxResults, int firstResult) {
        return findRhposicionsueldoestatustbEntities(false, maxResults, firstResult);
    }

    private List<Rhposicionsueldoestatustb> findRhposicionsueldoestatustbEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rhposicionsueldoestatustb.class));
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

    public Rhposicionsueldoestatustb findRhposicionsueldoestatustb(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rhposicionsueldoestatustb.class, id);
        } finally {
            em.close();
        }
    }

    public int getRhposicionsueldoestatustbCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rhposicionsueldoestatustb> rt = cq.from(Rhposicionsueldoestatustb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
