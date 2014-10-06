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
import com.lam.cenicco.migracion.entidades.mysql.Catestadotb;
import com.lam.cenicco.migracion.entidades.mysql.Catpaistb;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class CatpaistbJpaController implements Serializable {

    public CatpaistbJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Catpaistb catpaistb) {
        if (catpaistb.getCatestadotbList() == null) {
            catpaistb.setCatestadotbList(new ArrayList<Catestadotb>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Catestadotb> attachedCatestadotbList = new ArrayList<Catestadotb>();
            for (Catestadotb catestadotbListCatestadotbToAttach : catpaistb.getCatestadotbList()) {
                catestadotbListCatestadotbToAttach = em.getReference(catestadotbListCatestadotbToAttach.getClass(), catestadotbListCatestadotbToAttach.getIdestado());
                attachedCatestadotbList.add(catestadotbListCatestadotbToAttach);
            }
            catpaistb.setCatestadotbList(attachedCatestadotbList);
            em.persist(catpaistb);
            for (Catestadotb catestadotbListCatestadotb : catpaistb.getCatestadotbList()) {
                Catpaistb oldIdpaisOfCatestadotbListCatestadotb = catestadotbListCatestadotb.getIdpais();
                catestadotbListCatestadotb.setIdpais(catpaistb);
                catestadotbListCatestadotb = em.merge(catestadotbListCatestadotb);
                if (oldIdpaisOfCatestadotbListCatestadotb != null) {
                    oldIdpaisOfCatestadotbListCatestadotb.getCatestadotbList().remove(catestadotbListCatestadotb);
                    oldIdpaisOfCatestadotbListCatestadotb = em.merge(oldIdpaisOfCatestadotbListCatestadotb);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Catpaistb catpaistb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Catpaistb persistentCatpaistb = em.find(Catpaistb.class, catpaistb.getIdpais());
            List<Catestadotb> catestadotbListOld = persistentCatpaistb.getCatestadotbList();
            List<Catestadotb> catestadotbListNew = catpaistb.getCatestadotbList();
            List<Catestadotb> attachedCatestadotbListNew = new ArrayList<Catestadotb>();
            for (Catestadotb catestadotbListNewCatestadotbToAttach : catestadotbListNew) {
                catestadotbListNewCatestadotbToAttach = em.getReference(catestadotbListNewCatestadotbToAttach.getClass(), catestadotbListNewCatestadotbToAttach.getIdestado());
                attachedCatestadotbListNew.add(catestadotbListNewCatestadotbToAttach);
            }
            catestadotbListNew = attachedCatestadotbListNew;
            catpaistb.setCatestadotbList(catestadotbListNew);
            catpaistb = em.merge(catpaistb);
            for (Catestadotb catestadotbListOldCatestadotb : catestadotbListOld) {
                if (!catestadotbListNew.contains(catestadotbListOldCatestadotb)) {
                    catestadotbListOldCatestadotb.setIdpais(null);
                    catestadotbListOldCatestadotb = em.merge(catestadotbListOldCatestadotb);
                }
            }
            for (Catestadotb catestadotbListNewCatestadotb : catestadotbListNew) {
                if (!catestadotbListOld.contains(catestadotbListNewCatestadotb)) {
                    Catpaistb oldIdpaisOfCatestadotbListNewCatestadotb = catestadotbListNewCatestadotb.getIdpais();
                    catestadotbListNewCatestadotb.setIdpais(catpaistb);
                    catestadotbListNewCatestadotb = em.merge(catestadotbListNewCatestadotb);
                    if (oldIdpaisOfCatestadotbListNewCatestadotb != null && !oldIdpaisOfCatestadotbListNewCatestadotb.equals(catpaistb)) {
                        oldIdpaisOfCatestadotbListNewCatestadotb.getCatestadotbList().remove(catestadotbListNewCatestadotb);
                        oldIdpaisOfCatestadotbListNewCatestadotb = em.merge(oldIdpaisOfCatestadotbListNewCatestadotb);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = catpaistb.getIdpais();
                if (findCatpaistb(id) == null) {
                    throw new NonexistentEntityException("The catpaistb with id " + id + " no longer exists.");
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
            Catpaistb catpaistb;
            try {
                catpaistb = em.getReference(Catpaistb.class, id);
                catpaistb.getIdpais();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The catpaistb with id " + id + " no longer exists.", enfe);
            }
            List<Catestadotb> catestadotbList = catpaistb.getCatestadotbList();
            for (Catestadotb catestadotbListCatestadotb : catestadotbList) {
                catestadotbListCatestadotb.setIdpais(null);
                catestadotbListCatestadotb = em.merge(catestadotbListCatestadotb);
            }
            em.remove(catpaistb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Catpaistb> findCatpaistbEntities() {
        return findCatpaistbEntities(true, -1, -1);
    }

    public List<Catpaistb> findCatpaistbEntities(int maxResults, int firstResult) {
        return findCatpaistbEntities(false, maxResults, firstResult);
    }

    private List<Catpaistb> findCatpaistbEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Catpaistb.class));
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

    public Catpaistb findCatpaistb(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Catpaistb.class, id);
        } finally {
            em.close();
        }
    }

    public int getCatpaistbCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Catpaistb> rt = cq.from(Catpaistb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
