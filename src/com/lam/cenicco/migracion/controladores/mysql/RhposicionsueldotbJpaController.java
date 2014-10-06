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
import com.lam.cenicco.migracion.entidades.mysql.Rhtiposueldotb;
import com.lam.cenicco.migracion.entidades.mysql.Rhposicionsueldoestatustb;
import com.lam.cenicco.migracion.entidades.mysql.Rhposicionsueldotb;
import java.util.ArrayList;
import java.util.List;
import com.lam.cenicco.migracion.entidades.mysql.Rhrelacionlaboraltb;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class RhposicionsueldotbJpaController implements Serializable {

    public RhposicionsueldotbJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rhposicionsueldotb rhposicionsueldotb) {
        if (rhposicionsueldotb.getRhposicionsueldoestatustbList() == null) {
            rhposicionsueldotb.setRhposicionsueldoestatustbList(new ArrayList<Rhposicionsueldoestatustb>());
        }
        if (rhposicionsueldotb.getRhrelacionlaboraltbList() == null) {
            rhposicionsueldotb.setRhrelacionlaboraltbList(new ArrayList<Rhrelacionlaboraltb>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rhtiposueldotb idtiposueldo = rhposicionsueldotb.getIdtiposueldo();
            if (idtiposueldo != null) {
                idtiposueldo = em.getReference(idtiposueldo.getClass(), idtiposueldo.getIdtiposueldo());
                rhposicionsueldotb.setIdtiposueldo(idtiposueldo);
            }
            List<Rhposicionsueldoestatustb> attachedRhposicionsueldoestatustbList = new ArrayList<Rhposicionsueldoestatustb>();
            for (Rhposicionsueldoestatustb rhposicionsueldoestatustbListRhposicionsueldoestatustbToAttach : rhposicionsueldotb.getRhposicionsueldoestatustbList()) {
                rhposicionsueldoestatustbListRhposicionsueldoestatustbToAttach = em.getReference(rhposicionsueldoestatustbListRhposicionsueldoestatustbToAttach.getClass(), rhposicionsueldoestatustbListRhposicionsueldoestatustbToAttach.getIdposicionsueldoestatus());
                attachedRhposicionsueldoestatustbList.add(rhposicionsueldoestatustbListRhposicionsueldoestatustbToAttach);
            }
            rhposicionsueldotb.setRhposicionsueldoestatustbList(attachedRhposicionsueldoestatustbList);
            List<Rhrelacionlaboraltb> attachedRhrelacionlaboraltbList = new ArrayList<Rhrelacionlaboraltb>();
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListRhrelacionlaboraltbToAttach : rhposicionsueldotb.getRhrelacionlaboraltbList()) {
                rhrelacionlaboraltbListRhrelacionlaboraltbToAttach = em.getReference(rhrelacionlaboraltbListRhrelacionlaboraltbToAttach.getClass(), rhrelacionlaboraltbListRhrelacionlaboraltbToAttach.getIdrellab());
                attachedRhrelacionlaboraltbList.add(rhrelacionlaboraltbListRhrelacionlaboraltbToAttach);
            }
            rhposicionsueldotb.setRhrelacionlaboraltbList(attachedRhrelacionlaboraltbList);
            em.persist(rhposicionsueldotb);
            if (idtiposueldo != null) {
                idtiposueldo.getRhposicionsueldotbList().add(rhposicionsueldotb);
                idtiposueldo = em.merge(idtiposueldo);
            }
            for (Rhposicionsueldoestatustb rhposicionsueldoestatustbListRhposicionsueldoestatustb : rhposicionsueldotb.getRhposicionsueldoestatustbList()) {
                Rhposicionsueldotb oldIdposicionsueldoOfRhposicionsueldoestatustbListRhposicionsueldoestatustb = rhposicionsueldoestatustbListRhposicionsueldoestatustb.getIdposicionsueldo();
                rhposicionsueldoestatustbListRhposicionsueldoestatustb.setIdposicionsueldo(rhposicionsueldotb);
                rhposicionsueldoestatustbListRhposicionsueldoestatustb = em.merge(rhposicionsueldoestatustbListRhposicionsueldoestatustb);
                if (oldIdposicionsueldoOfRhposicionsueldoestatustbListRhposicionsueldoestatustb != null) {
                    oldIdposicionsueldoOfRhposicionsueldoestatustbListRhposicionsueldoestatustb.getRhposicionsueldoestatustbList().remove(rhposicionsueldoestatustbListRhposicionsueldoestatustb);
                    oldIdposicionsueldoOfRhposicionsueldoestatustbListRhposicionsueldoestatustb = em.merge(oldIdposicionsueldoOfRhposicionsueldoestatustbListRhposicionsueldoestatustb);
                }
            }
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListRhrelacionlaboraltb : rhposicionsueldotb.getRhrelacionlaboraltbList()) {
                Rhposicionsueldotb oldIdposicionsueldoOfRhrelacionlaboraltbListRhrelacionlaboraltb = rhrelacionlaboraltbListRhrelacionlaboraltb.getIdposicionsueldo();
                rhrelacionlaboraltbListRhrelacionlaboraltb.setIdposicionsueldo(rhposicionsueldotb);
                rhrelacionlaboraltbListRhrelacionlaboraltb = em.merge(rhrelacionlaboraltbListRhrelacionlaboraltb);
                if (oldIdposicionsueldoOfRhrelacionlaboraltbListRhrelacionlaboraltb != null) {
                    oldIdposicionsueldoOfRhrelacionlaboraltbListRhrelacionlaboraltb.getRhrelacionlaboraltbList().remove(rhrelacionlaboraltbListRhrelacionlaboraltb);
                    oldIdposicionsueldoOfRhrelacionlaboraltbListRhrelacionlaboraltb = em.merge(oldIdposicionsueldoOfRhrelacionlaboraltbListRhrelacionlaboraltb);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rhposicionsueldotb rhposicionsueldotb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rhposicionsueldotb persistentRhposicionsueldotb = em.find(Rhposicionsueldotb.class, rhposicionsueldotb.getIdposicionsueldo());
            Rhtiposueldotb idtiposueldoOld = persistentRhposicionsueldotb.getIdtiposueldo();
            Rhtiposueldotb idtiposueldoNew = rhposicionsueldotb.getIdtiposueldo();
            List<Rhposicionsueldoestatustb> rhposicionsueldoestatustbListOld = persistentRhposicionsueldotb.getRhposicionsueldoestatustbList();
            List<Rhposicionsueldoestatustb> rhposicionsueldoestatustbListNew = rhposicionsueldotb.getRhposicionsueldoestatustbList();
            List<Rhrelacionlaboraltb> rhrelacionlaboraltbListOld = persistentRhposicionsueldotb.getRhrelacionlaboraltbList();
            List<Rhrelacionlaboraltb> rhrelacionlaboraltbListNew = rhposicionsueldotb.getRhrelacionlaboraltbList();
            if (idtiposueldoNew != null) {
                idtiposueldoNew = em.getReference(idtiposueldoNew.getClass(), idtiposueldoNew.getIdtiposueldo());
                rhposicionsueldotb.setIdtiposueldo(idtiposueldoNew);
            }
            List<Rhposicionsueldoestatustb> attachedRhposicionsueldoestatustbListNew = new ArrayList<Rhposicionsueldoestatustb>();
            for (Rhposicionsueldoestatustb rhposicionsueldoestatustbListNewRhposicionsueldoestatustbToAttach : rhposicionsueldoestatustbListNew) {
                rhposicionsueldoestatustbListNewRhposicionsueldoestatustbToAttach = em.getReference(rhposicionsueldoestatustbListNewRhposicionsueldoestatustbToAttach.getClass(), rhposicionsueldoestatustbListNewRhposicionsueldoestatustbToAttach.getIdposicionsueldoestatus());
                attachedRhposicionsueldoestatustbListNew.add(rhposicionsueldoestatustbListNewRhposicionsueldoestatustbToAttach);
            }
            rhposicionsueldoestatustbListNew = attachedRhposicionsueldoestatustbListNew;
            rhposicionsueldotb.setRhposicionsueldoestatustbList(rhposicionsueldoestatustbListNew);
            List<Rhrelacionlaboraltb> attachedRhrelacionlaboraltbListNew = new ArrayList<Rhrelacionlaboraltb>();
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach : rhrelacionlaboraltbListNew) {
                rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach = em.getReference(rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach.getClass(), rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach.getIdrellab());
                attachedRhrelacionlaboraltbListNew.add(rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach);
            }
            rhrelacionlaboraltbListNew = attachedRhrelacionlaboraltbListNew;
            rhposicionsueldotb.setRhrelacionlaboraltbList(rhrelacionlaboraltbListNew);
            rhposicionsueldotb = em.merge(rhposicionsueldotb);
            if (idtiposueldoOld != null && !idtiposueldoOld.equals(idtiposueldoNew)) {
                idtiposueldoOld.getRhposicionsueldotbList().remove(rhposicionsueldotb);
                idtiposueldoOld = em.merge(idtiposueldoOld);
            }
            if (idtiposueldoNew != null && !idtiposueldoNew.equals(idtiposueldoOld)) {
                idtiposueldoNew.getRhposicionsueldotbList().add(rhposicionsueldotb);
                idtiposueldoNew = em.merge(idtiposueldoNew);
            }
            for (Rhposicionsueldoestatustb rhposicionsueldoestatustbListOldRhposicionsueldoestatustb : rhposicionsueldoestatustbListOld) {
                if (!rhposicionsueldoestatustbListNew.contains(rhposicionsueldoestatustbListOldRhposicionsueldoestatustb)) {
                    rhposicionsueldoestatustbListOldRhposicionsueldoestatustb.setIdposicionsueldo(null);
                    rhposicionsueldoestatustbListOldRhposicionsueldoestatustb = em.merge(rhposicionsueldoestatustbListOldRhposicionsueldoestatustb);
                }
            }
            for (Rhposicionsueldoestatustb rhposicionsueldoestatustbListNewRhposicionsueldoestatustb : rhposicionsueldoestatustbListNew) {
                if (!rhposicionsueldoestatustbListOld.contains(rhposicionsueldoestatustbListNewRhposicionsueldoestatustb)) {
                    Rhposicionsueldotb oldIdposicionsueldoOfRhposicionsueldoestatustbListNewRhposicionsueldoestatustb = rhposicionsueldoestatustbListNewRhposicionsueldoestatustb.getIdposicionsueldo();
                    rhposicionsueldoestatustbListNewRhposicionsueldoestatustb.setIdposicionsueldo(rhposicionsueldotb);
                    rhposicionsueldoestatustbListNewRhposicionsueldoestatustb = em.merge(rhposicionsueldoestatustbListNewRhposicionsueldoestatustb);
                    if (oldIdposicionsueldoOfRhposicionsueldoestatustbListNewRhposicionsueldoestatustb != null && !oldIdposicionsueldoOfRhposicionsueldoestatustbListNewRhposicionsueldoestatustb.equals(rhposicionsueldotb)) {
                        oldIdposicionsueldoOfRhposicionsueldoestatustbListNewRhposicionsueldoestatustb.getRhposicionsueldoestatustbList().remove(rhposicionsueldoestatustbListNewRhposicionsueldoestatustb);
                        oldIdposicionsueldoOfRhposicionsueldoestatustbListNewRhposicionsueldoestatustb = em.merge(oldIdposicionsueldoOfRhposicionsueldoestatustbListNewRhposicionsueldoestatustb);
                    }
                }
            }
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListOldRhrelacionlaboraltb : rhrelacionlaboraltbListOld) {
                if (!rhrelacionlaboraltbListNew.contains(rhrelacionlaboraltbListOldRhrelacionlaboraltb)) {
                    rhrelacionlaboraltbListOldRhrelacionlaboraltb.setIdposicionsueldo(null);
                    rhrelacionlaboraltbListOldRhrelacionlaboraltb = em.merge(rhrelacionlaboraltbListOldRhrelacionlaboraltb);
                }
            }
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListNewRhrelacionlaboraltb : rhrelacionlaboraltbListNew) {
                if (!rhrelacionlaboraltbListOld.contains(rhrelacionlaboraltbListNewRhrelacionlaboraltb)) {
                    Rhposicionsueldotb oldIdposicionsueldoOfRhrelacionlaboraltbListNewRhrelacionlaboraltb = rhrelacionlaboraltbListNewRhrelacionlaboraltb.getIdposicionsueldo();
                    rhrelacionlaboraltbListNewRhrelacionlaboraltb.setIdposicionsueldo(rhposicionsueldotb);
                    rhrelacionlaboraltbListNewRhrelacionlaboraltb = em.merge(rhrelacionlaboraltbListNewRhrelacionlaboraltb);
                    if (oldIdposicionsueldoOfRhrelacionlaboraltbListNewRhrelacionlaboraltb != null && !oldIdposicionsueldoOfRhrelacionlaboraltbListNewRhrelacionlaboraltb.equals(rhposicionsueldotb)) {
                        oldIdposicionsueldoOfRhrelacionlaboraltbListNewRhrelacionlaboraltb.getRhrelacionlaboraltbList().remove(rhrelacionlaboraltbListNewRhrelacionlaboraltb);
                        oldIdposicionsueldoOfRhrelacionlaboraltbListNewRhrelacionlaboraltb = em.merge(oldIdposicionsueldoOfRhrelacionlaboraltbListNewRhrelacionlaboraltb);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rhposicionsueldotb.getIdposicionsueldo();
                if (findRhposicionsueldotb(id) == null) {
                    throw new NonexistentEntityException("The rhposicionsueldotb with id " + id + " no longer exists.");
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
            Rhposicionsueldotb rhposicionsueldotb;
            try {
                rhposicionsueldotb = em.getReference(Rhposicionsueldotb.class, id);
                rhposicionsueldotb.getIdposicionsueldo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rhposicionsueldotb with id " + id + " no longer exists.", enfe);
            }
            Rhtiposueldotb idtiposueldo = rhposicionsueldotb.getIdtiposueldo();
            if (idtiposueldo != null) {
                idtiposueldo.getRhposicionsueldotbList().remove(rhposicionsueldotb);
                idtiposueldo = em.merge(idtiposueldo);
            }
            List<Rhposicionsueldoestatustb> rhposicionsueldoestatustbList = rhposicionsueldotb.getRhposicionsueldoestatustbList();
            for (Rhposicionsueldoestatustb rhposicionsueldoestatustbListRhposicionsueldoestatustb : rhposicionsueldoestatustbList) {
                rhposicionsueldoestatustbListRhposicionsueldoestatustb.setIdposicionsueldo(null);
                rhposicionsueldoestatustbListRhposicionsueldoestatustb = em.merge(rhposicionsueldoestatustbListRhposicionsueldoestatustb);
            }
            List<Rhrelacionlaboraltb> rhrelacionlaboraltbList = rhposicionsueldotb.getRhrelacionlaboraltbList();
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListRhrelacionlaboraltb : rhrelacionlaboraltbList) {
                rhrelacionlaboraltbListRhrelacionlaboraltb.setIdposicionsueldo(null);
                rhrelacionlaboraltbListRhrelacionlaboraltb = em.merge(rhrelacionlaboraltbListRhrelacionlaboraltb);
            }
            em.remove(rhposicionsueldotb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rhposicionsueldotb> findRhposicionsueldotbEntities() {
        return findRhposicionsueldotbEntities(true, -1, -1);
    }

    public List<Rhposicionsueldotb> findRhposicionsueldotbEntities(int maxResults, int firstResult) {
        return findRhposicionsueldotbEntities(false, maxResults, firstResult);
    }

    private List<Rhposicionsueldotb> findRhposicionsueldotbEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rhposicionsueldotb.class));
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

    public Rhposicionsueldotb findRhposicionsueldotb(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rhposicionsueldotb.class, id);
        } finally {
            em.close();
        }
    }

    public int getRhposicionsueldotbCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rhposicionsueldotb> rt = cq.from(Rhposicionsueldotb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
