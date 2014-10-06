/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.controladores.mysql;

import com.lam.cenicco.migracion.controladores.mysql.exceptions.NonexistentEntityException;
import com.lam.cenicco.migracion.entidades.mysql.Catbancotb;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.lam.cenicco.migracion.entidades.mysql.Rhrelacionlaboralcuentatb;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class CatbancotbJpaController implements Serializable {

    public CatbancotbJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Catbancotb catbancotb) {
        if (catbancotb.getRhrelacionlaboralcuentatbList() == null) {
            catbancotb.setRhrelacionlaboralcuentatbList(new ArrayList<Rhrelacionlaboralcuentatb>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Rhrelacionlaboralcuentatb> attachedRhrelacionlaboralcuentatbList = new ArrayList<Rhrelacionlaboralcuentatb>();
            for (Rhrelacionlaboralcuentatb rhrelacionlaboralcuentatbListRhrelacionlaboralcuentatbToAttach : catbancotb.getRhrelacionlaboralcuentatbList()) {
                rhrelacionlaboralcuentatbListRhrelacionlaboralcuentatbToAttach = em.getReference(rhrelacionlaboralcuentatbListRhrelacionlaboralcuentatbToAttach.getClass(), rhrelacionlaboralcuentatbListRhrelacionlaboralcuentatbToAttach.getIdcuenta());
                attachedRhrelacionlaboralcuentatbList.add(rhrelacionlaboralcuentatbListRhrelacionlaboralcuentatbToAttach);
            }
            catbancotb.setRhrelacionlaboralcuentatbList(attachedRhrelacionlaboralcuentatbList);
            em.persist(catbancotb);
            for (Rhrelacionlaboralcuentatb rhrelacionlaboralcuentatbListRhrelacionlaboralcuentatb : catbancotb.getRhrelacionlaboralcuentatbList()) {
                Catbancotb oldIdbancoOfRhrelacionlaboralcuentatbListRhrelacionlaboralcuentatb = rhrelacionlaboralcuentatbListRhrelacionlaboralcuentatb.getIdbanco();
                rhrelacionlaboralcuentatbListRhrelacionlaboralcuentatb.setIdbanco(catbancotb);
                rhrelacionlaboralcuentatbListRhrelacionlaboralcuentatb = em.merge(rhrelacionlaboralcuentatbListRhrelacionlaboralcuentatb);
                if (oldIdbancoOfRhrelacionlaboralcuentatbListRhrelacionlaboralcuentatb != null) {
                    oldIdbancoOfRhrelacionlaboralcuentatbListRhrelacionlaboralcuentatb.getRhrelacionlaboralcuentatbList().remove(rhrelacionlaboralcuentatbListRhrelacionlaboralcuentatb);
                    oldIdbancoOfRhrelacionlaboralcuentatbListRhrelacionlaboralcuentatb = em.merge(oldIdbancoOfRhrelacionlaboralcuentatbListRhrelacionlaboralcuentatb);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Catbancotb catbancotb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Catbancotb persistentCatbancotb = em.find(Catbancotb.class, catbancotb.getIdbanco());
            List<Rhrelacionlaboralcuentatb> rhrelacionlaboralcuentatbListOld = persistentCatbancotb.getRhrelacionlaboralcuentatbList();
            List<Rhrelacionlaboralcuentatb> rhrelacionlaboralcuentatbListNew = catbancotb.getRhrelacionlaboralcuentatbList();
            List<Rhrelacionlaboralcuentatb> attachedRhrelacionlaboralcuentatbListNew = new ArrayList<Rhrelacionlaboralcuentatb>();
            for (Rhrelacionlaboralcuentatb rhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatbToAttach : rhrelacionlaboralcuentatbListNew) {
                rhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatbToAttach = em.getReference(rhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatbToAttach.getClass(), rhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatbToAttach.getIdcuenta());
                attachedRhrelacionlaboralcuentatbListNew.add(rhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatbToAttach);
            }
            rhrelacionlaboralcuentatbListNew = attachedRhrelacionlaboralcuentatbListNew;
            catbancotb.setRhrelacionlaboralcuentatbList(rhrelacionlaboralcuentatbListNew);
            catbancotb = em.merge(catbancotb);
            for (Rhrelacionlaboralcuentatb rhrelacionlaboralcuentatbListOldRhrelacionlaboralcuentatb : rhrelacionlaboralcuentatbListOld) {
                if (!rhrelacionlaboralcuentatbListNew.contains(rhrelacionlaboralcuentatbListOldRhrelacionlaboralcuentatb)) {
                    rhrelacionlaboralcuentatbListOldRhrelacionlaboralcuentatb.setIdbanco(null);
                    rhrelacionlaboralcuentatbListOldRhrelacionlaboralcuentatb = em.merge(rhrelacionlaboralcuentatbListOldRhrelacionlaboralcuentatb);
                }
            }
            for (Rhrelacionlaboralcuentatb rhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatb : rhrelacionlaboralcuentatbListNew) {
                if (!rhrelacionlaboralcuentatbListOld.contains(rhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatb)) {
                    Catbancotb oldIdbancoOfRhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatb = rhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatb.getIdbanco();
                    rhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatb.setIdbanco(catbancotb);
                    rhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatb = em.merge(rhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatb);
                    if (oldIdbancoOfRhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatb != null && !oldIdbancoOfRhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatb.equals(catbancotb)) {
                        oldIdbancoOfRhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatb.getRhrelacionlaboralcuentatbList().remove(rhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatb);
                        oldIdbancoOfRhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatb = em.merge(oldIdbancoOfRhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatb);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = catbancotb.getIdbanco();
                if (findCatbancotb(id) == null) {
                    throw new NonexistentEntityException("The catbancotb with id " + id + " no longer exists.");
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
            Catbancotb catbancotb;
            try {
                catbancotb = em.getReference(Catbancotb.class, id);
                catbancotb.getIdbanco();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The catbancotb with id " + id + " no longer exists.", enfe);
            }
            List<Rhrelacionlaboralcuentatb> rhrelacionlaboralcuentatbList = catbancotb.getRhrelacionlaboralcuentatbList();
            for (Rhrelacionlaboralcuentatb rhrelacionlaboralcuentatbListRhrelacionlaboralcuentatb : rhrelacionlaboralcuentatbList) {
                rhrelacionlaboralcuentatbListRhrelacionlaboralcuentatb.setIdbanco(null);
                rhrelacionlaboralcuentatbListRhrelacionlaboralcuentatb = em.merge(rhrelacionlaboralcuentatbListRhrelacionlaboralcuentatb);
            }
            em.remove(catbancotb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Catbancotb> findCatbancotbEntities() {
        return findCatbancotbEntities(true, -1, -1);
    }

    public List<Catbancotb> findCatbancotbEntities(int maxResults, int firstResult) {
        return findCatbancotbEntities(false, maxResults, firstResult);
    }

    private List<Catbancotb> findCatbancotbEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Catbancotb.class));
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

    public Catbancotb findCatbancotb(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Catbancotb.class, id);
        } finally {
            em.close();
        }
    }

    public int getCatbancotbCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Catbancotb> rt = cq.from(Catbancotb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
