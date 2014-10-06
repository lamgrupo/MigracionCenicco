/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.controladores.mysql;

import com.lam.cenicco.migracion.controladores.mysql.exceptions.NonexistentEntityException;
import com.lam.cenicco.migracion.entidades.mysql.Rhposicionsueldohtb;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.lam.cenicco.migracion.entidades.mysql.Rhtiposueldotb;
import com.lam.cenicco.migracion.entidades.mysql.Rhrelacionlaboraltb;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class RhposicionsueldohtbJpaController implements Serializable {

    public RhposicionsueldohtbJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rhposicionsueldohtb rhposicionsueldohtb) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rhtiposueldotb idtiposueldo = rhposicionsueldohtb.getIdtiposueldo();
            if (idtiposueldo != null) {
                idtiposueldo = em.getReference(idtiposueldo.getClass(), idtiposueldo.getIdtiposueldo());
                rhposicionsueldohtb.setIdtiposueldo(idtiposueldo);
            }
            Rhrelacionlaboraltb idrellab = rhposicionsueldohtb.getIdrellab();
            if (idrellab != null) {
                idrellab = em.getReference(idrellab.getClass(), idrellab.getIdrellab());
                rhposicionsueldohtb.setIdrellab(idrellab);
            }
            em.persist(rhposicionsueldohtb);
            if (idtiposueldo != null) {
                idtiposueldo.getRhposicionsueldohtbList().add(rhposicionsueldohtb);
                idtiposueldo = em.merge(idtiposueldo);
            }
            if (idrellab != null) {
                idrellab.getRhposicionsueldohtbList().add(rhposicionsueldohtb);
                idrellab = em.merge(idrellab);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rhposicionsueldohtb rhposicionsueldohtb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rhposicionsueldohtb persistentRhposicionsueldohtb = em.find(Rhposicionsueldohtb.class, rhposicionsueldohtb.getIdposicionsueldo());
            Rhtiposueldotb idtiposueldoOld = persistentRhposicionsueldohtb.getIdtiposueldo();
            Rhtiposueldotb idtiposueldoNew = rhposicionsueldohtb.getIdtiposueldo();
            Rhrelacionlaboraltb idrellabOld = persistentRhposicionsueldohtb.getIdrellab();
            Rhrelacionlaboraltb idrellabNew = rhposicionsueldohtb.getIdrellab();
            if (idtiposueldoNew != null) {
                idtiposueldoNew = em.getReference(idtiposueldoNew.getClass(), idtiposueldoNew.getIdtiposueldo());
                rhposicionsueldohtb.setIdtiposueldo(idtiposueldoNew);
            }
            if (idrellabNew != null) {
                idrellabNew = em.getReference(idrellabNew.getClass(), idrellabNew.getIdrellab());
                rhposicionsueldohtb.setIdrellab(idrellabNew);
            }
            rhposicionsueldohtb = em.merge(rhposicionsueldohtb);
            if (idtiposueldoOld != null && !idtiposueldoOld.equals(idtiposueldoNew)) {
                idtiposueldoOld.getRhposicionsueldohtbList().remove(rhposicionsueldohtb);
                idtiposueldoOld = em.merge(idtiposueldoOld);
            }
            if (idtiposueldoNew != null && !idtiposueldoNew.equals(idtiposueldoOld)) {
                idtiposueldoNew.getRhposicionsueldohtbList().add(rhposicionsueldohtb);
                idtiposueldoNew = em.merge(idtiposueldoNew);
            }
            if (idrellabOld != null && !idrellabOld.equals(idrellabNew)) {
                idrellabOld.getRhposicionsueldohtbList().remove(rhposicionsueldohtb);
                idrellabOld = em.merge(idrellabOld);
            }
            if (idrellabNew != null && !idrellabNew.equals(idrellabOld)) {
                idrellabNew.getRhposicionsueldohtbList().add(rhposicionsueldohtb);
                idrellabNew = em.merge(idrellabNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rhposicionsueldohtb.getIdposicionsueldo();
                if (findRhposicionsueldohtb(id) == null) {
                    throw new NonexistentEntityException("The rhposicionsueldohtb with id " + id + " no longer exists.");
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
            Rhposicionsueldohtb rhposicionsueldohtb;
            try {
                rhposicionsueldohtb = em.getReference(Rhposicionsueldohtb.class, id);
                rhposicionsueldohtb.getIdposicionsueldo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rhposicionsueldohtb with id " + id + " no longer exists.", enfe);
            }
            Rhtiposueldotb idtiposueldo = rhposicionsueldohtb.getIdtiposueldo();
            if (idtiposueldo != null) {
                idtiposueldo.getRhposicionsueldohtbList().remove(rhposicionsueldohtb);
                idtiposueldo = em.merge(idtiposueldo);
            }
            Rhrelacionlaboraltb idrellab = rhposicionsueldohtb.getIdrellab();
            if (idrellab != null) {
                idrellab.getRhposicionsueldohtbList().remove(rhposicionsueldohtb);
                idrellab = em.merge(idrellab);
            }
            em.remove(rhposicionsueldohtb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rhposicionsueldohtb> findRhposicionsueldohtbEntities() {
        return findRhposicionsueldohtbEntities(true, -1, -1);
    }

    public List<Rhposicionsueldohtb> findRhposicionsueldohtbEntities(int maxResults, int firstResult) {
        return findRhposicionsueldohtbEntities(false, maxResults, firstResult);
    }

    private List<Rhposicionsueldohtb> findRhposicionsueldohtbEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rhposicionsueldohtb.class));
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

    public Rhposicionsueldohtb findRhposicionsueldohtb(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rhposicionsueldohtb.class, id);
        } finally {
            em.close();
        }
    }

    public int getRhposicionsueldohtbCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rhposicionsueldohtb> rt = cq.from(Rhposicionsueldohtb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
