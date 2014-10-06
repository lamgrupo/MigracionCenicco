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
import com.lam.cenicco.migracion.entidades.mysql.Catbancotb;
import com.lam.cenicco.migracion.entidades.mysql.Rhrelacionlaboralcuentatb;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class RhrelacionlaboralcuentatbJpaController implements Serializable {

    public RhrelacionlaboralcuentatbJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rhrelacionlaboralcuentatb rhrelacionlaboralcuentatb) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rhrelacionlaboraltb idrellab = rhrelacionlaboralcuentatb.getIdrellab();
            if (idrellab != null) {
                idrellab = em.getReference(idrellab.getClass(), idrellab.getIdrellab());
                rhrelacionlaboralcuentatb.setIdrellab(idrellab);
            }
            Catbancotb idbanco = rhrelacionlaboralcuentatb.getIdbanco();
            if (idbanco != null) {
                idbanco = em.getReference(idbanco.getClass(), idbanco.getIdbanco());
                rhrelacionlaboralcuentatb.setIdbanco(idbanco);
            }
            em.persist(rhrelacionlaboralcuentatb);
            if (idrellab != null) {
                idrellab.getRhrelacionlaboralcuentatbList().add(rhrelacionlaboralcuentatb);
                idrellab = em.merge(idrellab);
            }
            if (idbanco != null) {
                idbanco.getRhrelacionlaboralcuentatbList().add(rhrelacionlaboralcuentatb);
                idbanco = em.merge(idbanco);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rhrelacionlaboralcuentatb rhrelacionlaboralcuentatb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rhrelacionlaboralcuentatb persistentRhrelacionlaboralcuentatb = em.find(Rhrelacionlaboralcuentatb.class, rhrelacionlaboralcuentatb.getIdcuenta());
            Rhrelacionlaboraltb idrellabOld = persistentRhrelacionlaboralcuentatb.getIdrellab();
            Rhrelacionlaboraltb idrellabNew = rhrelacionlaboralcuentatb.getIdrellab();
            Catbancotb idbancoOld = persistentRhrelacionlaboralcuentatb.getIdbanco();
            Catbancotb idbancoNew = rhrelacionlaboralcuentatb.getIdbanco();
            if (idrellabNew != null) {
                idrellabNew = em.getReference(idrellabNew.getClass(), idrellabNew.getIdrellab());
                rhrelacionlaboralcuentatb.setIdrellab(idrellabNew);
            }
            if (idbancoNew != null) {
                idbancoNew = em.getReference(idbancoNew.getClass(), idbancoNew.getIdbanco());
                rhrelacionlaboralcuentatb.setIdbanco(idbancoNew);
            }
            rhrelacionlaboralcuentatb = em.merge(rhrelacionlaboralcuentatb);
            if (idrellabOld != null && !idrellabOld.equals(idrellabNew)) {
                idrellabOld.getRhrelacionlaboralcuentatbList().remove(rhrelacionlaboralcuentatb);
                idrellabOld = em.merge(idrellabOld);
            }
            if (idrellabNew != null && !idrellabNew.equals(idrellabOld)) {
                idrellabNew.getRhrelacionlaboralcuentatbList().add(rhrelacionlaboralcuentatb);
                idrellabNew = em.merge(idrellabNew);
            }
            if (idbancoOld != null && !idbancoOld.equals(idbancoNew)) {
                idbancoOld.getRhrelacionlaboralcuentatbList().remove(rhrelacionlaboralcuentatb);
                idbancoOld = em.merge(idbancoOld);
            }
            if (idbancoNew != null && !idbancoNew.equals(idbancoOld)) {
                idbancoNew.getRhrelacionlaboralcuentatbList().add(rhrelacionlaboralcuentatb);
                idbancoNew = em.merge(idbancoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rhrelacionlaboralcuentatb.getIdcuenta();
                if (findRhrelacionlaboralcuentatb(id) == null) {
                    throw new NonexistentEntityException("The rhrelacionlaboralcuentatb with id " + id + " no longer exists.");
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
            Rhrelacionlaboralcuentatb rhrelacionlaboralcuentatb;
            try {
                rhrelacionlaboralcuentatb = em.getReference(Rhrelacionlaboralcuentatb.class, id);
                rhrelacionlaboralcuentatb.getIdcuenta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rhrelacionlaboralcuentatb with id " + id + " no longer exists.", enfe);
            }
            Rhrelacionlaboraltb idrellab = rhrelacionlaboralcuentatb.getIdrellab();
            if (idrellab != null) {
                idrellab.getRhrelacionlaboralcuentatbList().remove(rhrelacionlaboralcuentatb);
                idrellab = em.merge(idrellab);
            }
            Catbancotb idbanco = rhrelacionlaboralcuentatb.getIdbanco();
            if (idbanco != null) {
                idbanco.getRhrelacionlaboralcuentatbList().remove(rhrelacionlaboralcuentatb);
                idbanco = em.merge(idbanco);
            }
            em.remove(rhrelacionlaboralcuentatb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rhrelacionlaboralcuentatb> findRhrelacionlaboralcuentatbEntities() {
        return findRhrelacionlaboralcuentatbEntities(true, -1, -1);
    }

    public List<Rhrelacionlaboralcuentatb> findRhrelacionlaboralcuentatbEntities(int maxResults, int firstResult) {
        return findRhrelacionlaboralcuentatbEntities(false, maxResults, firstResult);
    }

    private List<Rhrelacionlaboralcuentatb> findRhrelacionlaboralcuentatbEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rhrelacionlaboralcuentatb.class));
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

    public Rhrelacionlaboralcuentatb findRhrelacionlaboralcuentatb(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rhrelacionlaboralcuentatb.class, id);
        } finally {
            em.close();
        }
    }

    public int getRhrelacionlaboralcuentatbCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rhrelacionlaboralcuentatb> rt = cq.from(Rhrelacionlaboralcuentatb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
