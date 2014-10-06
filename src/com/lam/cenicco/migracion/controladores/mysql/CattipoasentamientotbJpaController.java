/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.controladores.mysql;

import com.cenicco.migracion.util.JpaUtil;
import com.lam.cenicco.migracion.controladores.mysql.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.lam.cenicco.migracion.entidades.mysql.Catasentamientotb;
import com.lam.cenicco.migracion.entidades.mysql.Cattipoasentamientotb;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class CattipoasentamientotbJpaController implements Serializable {

    public CattipoasentamientotbJpaController() {
        this.emf = JpaUtil.getInstance().createEntityManagerCeniccoMysql().getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cattipoasentamientotb cattipoasentamientotb) {
        if (cattipoasentamientotb.getCatasentamientotbList() == null) {
            cattipoasentamientotb.setCatasentamientotbList(new ArrayList<Catasentamientotb>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Catasentamientotb> attachedCatasentamientotbList = new ArrayList<Catasentamientotb>();
            for (Catasentamientotb catasentamientotbListCatasentamientotbToAttach : cattipoasentamientotb.getCatasentamientotbList()) {
                catasentamientotbListCatasentamientotbToAttach = em.getReference(catasentamientotbListCatasentamientotbToAttach.getClass(), catasentamientotbListCatasentamientotbToAttach.getIdasentamiento());
                attachedCatasentamientotbList.add(catasentamientotbListCatasentamientotbToAttach);
            }
            cattipoasentamientotb.setCatasentamientotbList(attachedCatasentamientotbList);
            em.persist(cattipoasentamientotb);
            for (Catasentamientotb catasentamientotbListCatasentamientotb : cattipoasentamientotb.getCatasentamientotbList()) {
                Cattipoasentamientotb oldIdtipoasentamientoOfCatasentamientotbListCatasentamientotb = catasentamientotbListCatasentamientotb.getIdtipoasentamiento();
                catasentamientotbListCatasentamientotb.setIdtipoasentamiento(cattipoasentamientotb);
                catasentamientotbListCatasentamientotb = em.merge(catasentamientotbListCatasentamientotb);
                if (oldIdtipoasentamientoOfCatasentamientotbListCatasentamientotb != null) {
                    oldIdtipoasentamientoOfCatasentamientotbListCatasentamientotb.getCatasentamientotbList().remove(catasentamientotbListCatasentamientotb);
                    oldIdtipoasentamientoOfCatasentamientotbListCatasentamientotb = em.merge(oldIdtipoasentamientoOfCatasentamientotbListCatasentamientotb);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cattipoasentamientotb cattipoasentamientotb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cattipoasentamientotb persistentCattipoasentamientotb = em.find(Cattipoasentamientotb.class, cattipoasentamientotb.getIdtipoasentamiento());
            List<Catasentamientotb> catasentamientotbListOld = persistentCattipoasentamientotb.getCatasentamientotbList();
            List<Catasentamientotb> catasentamientotbListNew = cattipoasentamientotb.getCatasentamientotbList();
            List<Catasentamientotb> attachedCatasentamientotbListNew = new ArrayList<Catasentamientotb>();
            for (Catasentamientotb catasentamientotbListNewCatasentamientotbToAttach : catasentamientotbListNew) {
                catasentamientotbListNewCatasentamientotbToAttach = em.getReference(catasentamientotbListNewCatasentamientotbToAttach.getClass(), catasentamientotbListNewCatasentamientotbToAttach.getIdasentamiento());
                attachedCatasentamientotbListNew.add(catasentamientotbListNewCatasentamientotbToAttach);
            }
            catasentamientotbListNew = attachedCatasentamientotbListNew;
            cattipoasentamientotb.setCatasentamientotbList(catasentamientotbListNew);
            cattipoasentamientotb = em.merge(cattipoasentamientotb);
            for (Catasentamientotb catasentamientotbListOldCatasentamientotb : catasentamientotbListOld) {
                if (!catasentamientotbListNew.contains(catasentamientotbListOldCatasentamientotb)) {
                    catasentamientotbListOldCatasentamientotb.setIdtipoasentamiento(null);
                    catasentamientotbListOldCatasentamientotb = em.merge(catasentamientotbListOldCatasentamientotb);
                }
            }
            for (Catasentamientotb catasentamientotbListNewCatasentamientotb : catasentamientotbListNew) {
                if (!catasentamientotbListOld.contains(catasentamientotbListNewCatasentamientotb)) {
                    Cattipoasentamientotb oldIdtipoasentamientoOfCatasentamientotbListNewCatasentamientotb = catasentamientotbListNewCatasentamientotb.getIdtipoasentamiento();
                    catasentamientotbListNewCatasentamientotb.setIdtipoasentamiento(cattipoasentamientotb);
                    catasentamientotbListNewCatasentamientotb = em.merge(catasentamientotbListNewCatasentamientotb);
                    if (oldIdtipoasentamientoOfCatasentamientotbListNewCatasentamientotb != null && !oldIdtipoasentamientoOfCatasentamientotbListNewCatasentamientotb.equals(cattipoasentamientotb)) {
                        oldIdtipoasentamientoOfCatasentamientotbListNewCatasentamientotb.getCatasentamientotbList().remove(catasentamientotbListNewCatasentamientotb);
                        oldIdtipoasentamientoOfCatasentamientotbListNewCatasentamientotb = em.merge(oldIdtipoasentamientoOfCatasentamientotbListNewCatasentamientotb);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cattipoasentamientotb.getIdtipoasentamiento();
                if (findCattipoasentamientotb(id) == null) {
                    throw new NonexistentEntityException("The cattipoasentamientotb with id " + id + " no longer exists.");
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
            Cattipoasentamientotb cattipoasentamientotb;
            try {
                cattipoasentamientotb = em.getReference(Cattipoasentamientotb.class, id);
                cattipoasentamientotb.getIdtipoasentamiento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cattipoasentamientotb with id " + id + " no longer exists.", enfe);
            }
            List<Catasentamientotb> catasentamientotbList = cattipoasentamientotb.getCatasentamientotbList();
            for (Catasentamientotb catasentamientotbListCatasentamientotb : catasentamientotbList) {
                catasentamientotbListCatasentamientotb.setIdtipoasentamiento(null);
                catasentamientotbListCatasentamientotb = em.merge(catasentamientotbListCatasentamientotb);
            }
            em.remove(cattipoasentamientotb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cattipoasentamientotb> findCattipoasentamientotbEntities() {
        return findCattipoasentamientotbEntities(true, -1, -1);
    }

    public List<Cattipoasentamientotb> findCattipoasentamientotbEntities(int maxResults, int firstResult) {
        return findCattipoasentamientotbEntities(false, maxResults, firstResult);
    }

    private List<Cattipoasentamientotb> findCattipoasentamientotbEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cattipoasentamientotb.class));
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

    public Cattipoasentamientotb findCattipoasentamientotb(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cattipoasentamientotb.class, id);
        } finally {
            em.close();
        }
    }

    public int getCattipoasentamientotbCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cattipoasentamientotb> rt = cq.from(Cattipoasentamientotb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Map<String, Cattipoasentamientotb> findByNombre() {
        EntityManager em = null;
        Map<String, Cattipoasentamientotb> mapa = new HashMap<>();
        List<Cattipoasentamientotb> lista;
        try {
            em = getEntityManager();
            Query q = em.createNamedQuery("Cattipoasentamientotb.findAll");
            lista = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JpaUtil.getInstance().closeEntityManager(em);
        }
        for (Cattipoasentamientotb t : lista) {
            mapa.put(t.getNombre(), t);
        }
        return mapa;
    }
}
