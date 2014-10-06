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
import com.lam.cenicco.migracion.entidades.mysql.Rhrelacionlaboralposiciontb;
import java.util.ArrayList;
import java.util.List;
import com.lam.cenicco.migracion.entidades.mysql.Rhposicionsueldotb;
import com.lam.cenicco.migracion.entidades.mysql.Rhposicionsueldohtb;
import com.lam.cenicco.migracion.entidades.mysql.Rhtiposueldotb;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class RhtiposueldotbJpaController implements Serializable {

    public RhtiposueldotbJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rhtiposueldotb rhtiposueldotb) {
        if (rhtiposueldotb.getRhrelacionlaboralposiciontbList() == null) {
            rhtiposueldotb.setRhrelacionlaboralposiciontbList(new ArrayList<Rhrelacionlaboralposiciontb>());
        }
        if (rhtiposueldotb.getRhposicionsueldotbList() == null) {
            rhtiposueldotb.setRhposicionsueldotbList(new ArrayList<Rhposicionsueldotb>());
        }
        if (rhtiposueldotb.getRhposicionsueldohtbList() == null) {
            rhtiposueldotb.setRhposicionsueldohtbList(new ArrayList<Rhposicionsueldohtb>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Rhrelacionlaboralposiciontb> attachedRhrelacionlaboralposiciontbList = new ArrayList<Rhrelacionlaboralposiciontb>();
            for (Rhrelacionlaboralposiciontb rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontbToAttach : rhtiposueldotb.getRhrelacionlaboralposiciontbList()) {
                rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontbToAttach = em.getReference(rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontbToAttach.getClass(), rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontbToAttach.getIdrelacionlaboralposicion());
                attachedRhrelacionlaboralposiciontbList.add(rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontbToAttach);
            }
            rhtiposueldotb.setRhrelacionlaboralposiciontbList(attachedRhrelacionlaboralposiciontbList);
            List<Rhposicionsueldotb> attachedRhposicionsueldotbList = new ArrayList<Rhposicionsueldotb>();
            for (Rhposicionsueldotb rhposicionsueldotbListRhposicionsueldotbToAttach : rhtiposueldotb.getRhposicionsueldotbList()) {
                rhposicionsueldotbListRhposicionsueldotbToAttach = em.getReference(rhposicionsueldotbListRhposicionsueldotbToAttach.getClass(), rhposicionsueldotbListRhposicionsueldotbToAttach.getIdposicionsueldo());
                attachedRhposicionsueldotbList.add(rhposicionsueldotbListRhposicionsueldotbToAttach);
            }
            rhtiposueldotb.setRhposicionsueldotbList(attachedRhposicionsueldotbList);
            List<Rhposicionsueldohtb> attachedRhposicionsueldohtbList = new ArrayList<Rhposicionsueldohtb>();
            for (Rhposicionsueldohtb rhposicionsueldohtbListRhposicionsueldohtbToAttach : rhtiposueldotb.getRhposicionsueldohtbList()) {
                rhposicionsueldohtbListRhposicionsueldohtbToAttach = em.getReference(rhposicionsueldohtbListRhposicionsueldohtbToAttach.getClass(), rhposicionsueldohtbListRhposicionsueldohtbToAttach.getIdposicionsueldo());
                attachedRhposicionsueldohtbList.add(rhposicionsueldohtbListRhposicionsueldohtbToAttach);
            }
            rhtiposueldotb.setRhposicionsueldohtbList(attachedRhposicionsueldohtbList);
            em.persist(rhtiposueldotb);
            for (Rhrelacionlaboralposiciontb rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb : rhtiposueldotb.getRhrelacionlaboralposiciontbList()) {
                Rhtiposueldotb oldIdtiposueldoOfRhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb = rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb.getIdtiposueldo();
                rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb.setIdtiposueldo(rhtiposueldotb);
                rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb = em.merge(rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb);
                if (oldIdtiposueldoOfRhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb != null) {
                    oldIdtiposueldoOfRhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb.getRhrelacionlaboralposiciontbList().remove(rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb);
                    oldIdtiposueldoOfRhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb = em.merge(oldIdtiposueldoOfRhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb);
                }
            }
            for (Rhposicionsueldotb rhposicionsueldotbListRhposicionsueldotb : rhtiposueldotb.getRhposicionsueldotbList()) {
                Rhtiposueldotb oldIdtiposueldoOfRhposicionsueldotbListRhposicionsueldotb = rhposicionsueldotbListRhposicionsueldotb.getIdtiposueldo();
                rhposicionsueldotbListRhposicionsueldotb.setIdtiposueldo(rhtiposueldotb);
                rhposicionsueldotbListRhposicionsueldotb = em.merge(rhposicionsueldotbListRhposicionsueldotb);
                if (oldIdtiposueldoOfRhposicionsueldotbListRhposicionsueldotb != null) {
                    oldIdtiposueldoOfRhposicionsueldotbListRhposicionsueldotb.getRhposicionsueldotbList().remove(rhposicionsueldotbListRhposicionsueldotb);
                    oldIdtiposueldoOfRhposicionsueldotbListRhposicionsueldotb = em.merge(oldIdtiposueldoOfRhposicionsueldotbListRhposicionsueldotb);
                }
            }
            for (Rhposicionsueldohtb rhposicionsueldohtbListRhposicionsueldohtb : rhtiposueldotb.getRhposicionsueldohtbList()) {
                Rhtiposueldotb oldIdtiposueldoOfRhposicionsueldohtbListRhposicionsueldohtb = rhposicionsueldohtbListRhposicionsueldohtb.getIdtiposueldo();
                rhposicionsueldohtbListRhposicionsueldohtb.setIdtiposueldo(rhtiposueldotb);
                rhposicionsueldohtbListRhposicionsueldohtb = em.merge(rhposicionsueldohtbListRhposicionsueldohtb);
                if (oldIdtiposueldoOfRhposicionsueldohtbListRhposicionsueldohtb != null) {
                    oldIdtiposueldoOfRhposicionsueldohtbListRhposicionsueldohtb.getRhposicionsueldohtbList().remove(rhposicionsueldohtbListRhposicionsueldohtb);
                    oldIdtiposueldoOfRhposicionsueldohtbListRhposicionsueldohtb = em.merge(oldIdtiposueldoOfRhposicionsueldohtbListRhposicionsueldohtb);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rhtiposueldotb rhtiposueldotb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rhtiposueldotb persistentRhtiposueldotb = em.find(Rhtiposueldotb.class, rhtiposueldotb.getIdtiposueldo());
            List<Rhrelacionlaboralposiciontb> rhrelacionlaboralposiciontbListOld = persistentRhtiposueldotb.getRhrelacionlaboralposiciontbList();
            List<Rhrelacionlaboralposiciontb> rhrelacionlaboralposiciontbListNew = rhtiposueldotb.getRhrelacionlaboralposiciontbList();
            List<Rhposicionsueldotb> rhposicionsueldotbListOld = persistentRhtiposueldotb.getRhposicionsueldotbList();
            List<Rhposicionsueldotb> rhposicionsueldotbListNew = rhtiposueldotb.getRhposicionsueldotbList();
            List<Rhposicionsueldohtb> rhposicionsueldohtbListOld = persistentRhtiposueldotb.getRhposicionsueldohtbList();
            List<Rhposicionsueldohtb> rhposicionsueldohtbListNew = rhtiposueldotb.getRhposicionsueldohtbList();
            List<Rhrelacionlaboralposiciontb> attachedRhrelacionlaboralposiciontbListNew = new ArrayList<Rhrelacionlaboralposiciontb>();
            for (Rhrelacionlaboralposiciontb rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontbToAttach : rhrelacionlaboralposiciontbListNew) {
                rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontbToAttach = em.getReference(rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontbToAttach.getClass(), rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontbToAttach.getIdrelacionlaboralposicion());
                attachedRhrelacionlaboralposiciontbListNew.add(rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontbToAttach);
            }
            rhrelacionlaboralposiciontbListNew = attachedRhrelacionlaboralposiciontbListNew;
            rhtiposueldotb.setRhrelacionlaboralposiciontbList(rhrelacionlaboralposiciontbListNew);
            List<Rhposicionsueldotb> attachedRhposicionsueldotbListNew = new ArrayList<Rhposicionsueldotb>();
            for (Rhposicionsueldotb rhposicionsueldotbListNewRhposicionsueldotbToAttach : rhposicionsueldotbListNew) {
                rhposicionsueldotbListNewRhposicionsueldotbToAttach = em.getReference(rhposicionsueldotbListNewRhposicionsueldotbToAttach.getClass(), rhposicionsueldotbListNewRhposicionsueldotbToAttach.getIdposicionsueldo());
                attachedRhposicionsueldotbListNew.add(rhposicionsueldotbListNewRhposicionsueldotbToAttach);
            }
            rhposicionsueldotbListNew = attachedRhposicionsueldotbListNew;
            rhtiposueldotb.setRhposicionsueldotbList(rhposicionsueldotbListNew);
            List<Rhposicionsueldohtb> attachedRhposicionsueldohtbListNew = new ArrayList<Rhposicionsueldohtb>();
            for (Rhposicionsueldohtb rhposicionsueldohtbListNewRhposicionsueldohtbToAttach : rhposicionsueldohtbListNew) {
                rhposicionsueldohtbListNewRhposicionsueldohtbToAttach = em.getReference(rhposicionsueldohtbListNewRhposicionsueldohtbToAttach.getClass(), rhposicionsueldohtbListNewRhposicionsueldohtbToAttach.getIdposicionsueldo());
                attachedRhposicionsueldohtbListNew.add(rhposicionsueldohtbListNewRhposicionsueldohtbToAttach);
            }
            rhposicionsueldohtbListNew = attachedRhposicionsueldohtbListNew;
            rhtiposueldotb.setRhposicionsueldohtbList(rhposicionsueldohtbListNew);
            rhtiposueldotb = em.merge(rhtiposueldotb);
            for (Rhrelacionlaboralposiciontb rhrelacionlaboralposiciontbListOldRhrelacionlaboralposiciontb : rhrelacionlaboralposiciontbListOld) {
                if (!rhrelacionlaboralposiciontbListNew.contains(rhrelacionlaboralposiciontbListOldRhrelacionlaboralposiciontb)) {
                    rhrelacionlaboralposiciontbListOldRhrelacionlaboralposiciontb.setIdtiposueldo(null);
                    rhrelacionlaboralposiciontbListOldRhrelacionlaboralposiciontb = em.merge(rhrelacionlaboralposiciontbListOldRhrelacionlaboralposiciontb);
                }
            }
            for (Rhrelacionlaboralposiciontb rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb : rhrelacionlaboralposiciontbListNew) {
                if (!rhrelacionlaboralposiciontbListOld.contains(rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb)) {
                    Rhtiposueldotb oldIdtiposueldoOfRhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb = rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb.getIdtiposueldo();
                    rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb.setIdtiposueldo(rhtiposueldotb);
                    rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb = em.merge(rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb);
                    if (oldIdtiposueldoOfRhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb != null && !oldIdtiposueldoOfRhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb.equals(rhtiposueldotb)) {
                        oldIdtiposueldoOfRhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb.getRhrelacionlaboralposiciontbList().remove(rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb);
                        oldIdtiposueldoOfRhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb = em.merge(oldIdtiposueldoOfRhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb);
                    }
                }
            }
            for (Rhposicionsueldotb rhposicionsueldotbListOldRhposicionsueldotb : rhposicionsueldotbListOld) {
                if (!rhposicionsueldotbListNew.contains(rhposicionsueldotbListOldRhposicionsueldotb)) {
                    rhposicionsueldotbListOldRhposicionsueldotb.setIdtiposueldo(null);
                    rhposicionsueldotbListOldRhposicionsueldotb = em.merge(rhposicionsueldotbListOldRhposicionsueldotb);
                }
            }
            for (Rhposicionsueldotb rhposicionsueldotbListNewRhposicionsueldotb : rhposicionsueldotbListNew) {
                if (!rhposicionsueldotbListOld.contains(rhposicionsueldotbListNewRhposicionsueldotb)) {
                    Rhtiposueldotb oldIdtiposueldoOfRhposicionsueldotbListNewRhposicionsueldotb = rhposicionsueldotbListNewRhposicionsueldotb.getIdtiposueldo();
                    rhposicionsueldotbListNewRhposicionsueldotb.setIdtiposueldo(rhtiposueldotb);
                    rhposicionsueldotbListNewRhposicionsueldotb = em.merge(rhposicionsueldotbListNewRhposicionsueldotb);
                    if (oldIdtiposueldoOfRhposicionsueldotbListNewRhposicionsueldotb != null && !oldIdtiposueldoOfRhposicionsueldotbListNewRhposicionsueldotb.equals(rhtiposueldotb)) {
                        oldIdtiposueldoOfRhposicionsueldotbListNewRhposicionsueldotb.getRhposicionsueldotbList().remove(rhposicionsueldotbListNewRhposicionsueldotb);
                        oldIdtiposueldoOfRhposicionsueldotbListNewRhposicionsueldotb = em.merge(oldIdtiposueldoOfRhposicionsueldotbListNewRhposicionsueldotb);
                    }
                }
            }
            for (Rhposicionsueldohtb rhposicionsueldohtbListOldRhposicionsueldohtb : rhposicionsueldohtbListOld) {
                if (!rhposicionsueldohtbListNew.contains(rhposicionsueldohtbListOldRhposicionsueldohtb)) {
                    rhposicionsueldohtbListOldRhposicionsueldohtb.setIdtiposueldo(null);
                    rhposicionsueldohtbListOldRhposicionsueldohtb = em.merge(rhposicionsueldohtbListOldRhposicionsueldohtb);
                }
            }
            for (Rhposicionsueldohtb rhposicionsueldohtbListNewRhposicionsueldohtb : rhposicionsueldohtbListNew) {
                if (!rhposicionsueldohtbListOld.contains(rhposicionsueldohtbListNewRhposicionsueldohtb)) {
                    Rhtiposueldotb oldIdtiposueldoOfRhposicionsueldohtbListNewRhposicionsueldohtb = rhposicionsueldohtbListNewRhposicionsueldohtb.getIdtiposueldo();
                    rhposicionsueldohtbListNewRhposicionsueldohtb.setIdtiposueldo(rhtiposueldotb);
                    rhposicionsueldohtbListNewRhposicionsueldohtb = em.merge(rhposicionsueldohtbListNewRhposicionsueldohtb);
                    if (oldIdtiposueldoOfRhposicionsueldohtbListNewRhposicionsueldohtb != null && !oldIdtiposueldoOfRhposicionsueldohtbListNewRhposicionsueldohtb.equals(rhtiposueldotb)) {
                        oldIdtiposueldoOfRhposicionsueldohtbListNewRhposicionsueldohtb.getRhposicionsueldohtbList().remove(rhposicionsueldohtbListNewRhposicionsueldohtb);
                        oldIdtiposueldoOfRhposicionsueldohtbListNewRhposicionsueldohtb = em.merge(oldIdtiposueldoOfRhposicionsueldohtbListNewRhposicionsueldohtb);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rhtiposueldotb.getIdtiposueldo();
                if (findRhtiposueldotb(id) == null) {
                    throw new NonexistentEntityException("The rhtiposueldotb with id " + id + " no longer exists.");
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
            Rhtiposueldotb rhtiposueldotb;
            try {
                rhtiposueldotb = em.getReference(Rhtiposueldotb.class, id);
                rhtiposueldotb.getIdtiposueldo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rhtiposueldotb with id " + id + " no longer exists.", enfe);
            }
            List<Rhrelacionlaboralposiciontb> rhrelacionlaboralposiciontbList = rhtiposueldotb.getRhrelacionlaboralposiciontbList();
            for (Rhrelacionlaboralposiciontb rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb : rhrelacionlaboralposiciontbList) {
                rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb.setIdtiposueldo(null);
                rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb = em.merge(rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb);
            }
            List<Rhposicionsueldotb> rhposicionsueldotbList = rhtiposueldotb.getRhposicionsueldotbList();
            for (Rhposicionsueldotb rhposicionsueldotbListRhposicionsueldotb : rhposicionsueldotbList) {
                rhposicionsueldotbListRhposicionsueldotb.setIdtiposueldo(null);
                rhposicionsueldotbListRhposicionsueldotb = em.merge(rhposicionsueldotbListRhposicionsueldotb);
            }
            List<Rhposicionsueldohtb> rhposicionsueldohtbList = rhtiposueldotb.getRhposicionsueldohtbList();
            for (Rhposicionsueldohtb rhposicionsueldohtbListRhposicionsueldohtb : rhposicionsueldohtbList) {
                rhposicionsueldohtbListRhposicionsueldohtb.setIdtiposueldo(null);
                rhposicionsueldohtbListRhposicionsueldohtb = em.merge(rhposicionsueldohtbListRhposicionsueldohtb);
            }
            em.remove(rhtiposueldotb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rhtiposueldotb> findRhtiposueldotbEntities() {
        return findRhtiposueldotbEntities(true, -1, -1);
    }

    public List<Rhtiposueldotb> findRhtiposueldotbEntities(int maxResults, int firstResult) {
        return findRhtiposueldotbEntities(false, maxResults, firstResult);
    }

    private List<Rhtiposueldotb> findRhtiposueldotbEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rhtiposueldotb.class));
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

    public Rhtiposueldotb findRhtiposueldotb(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rhtiposueldotb.class, id);
        } finally {
            em.close();
        }
    }

    public int getRhtiposueldotbCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rhtiposueldotb> rt = cq.from(Rhtiposueldotb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
