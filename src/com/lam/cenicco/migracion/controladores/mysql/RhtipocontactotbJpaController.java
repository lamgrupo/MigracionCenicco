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
import com.lam.cenicco.migracion.entidades.mysql.Rhempleadocontactotb;
import com.lam.cenicco.migracion.entidades.mysql.Rhtipocontactotb;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class RhtipocontactotbJpaController implements Serializable {

    public RhtipocontactotbJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rhtipocontactotb rhtipocontactotb) {
        if (rhtipocontactotb.getRhempleadocontactotbList() == null) {
            rhtipocontactotb.setRhempleadocontactotbList(new ArrayList<Rhempleadocontactotb>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Rhempleadocontactotb> attachedRhempleadocontactotbList = new ArrayList<Rhempleadocontactotb>();
            for (Rhempleadocontactotb rhempleadocontactotbListRhempleadocontactotbToAttach : rhtipocontactotb.getRhempleadocontactotbList()) {
                rhempleadocontactotbListRhempleadocontactotbToAttach = em.getReference(rhempleadocontactotbListRhempleadocontactotbToAttach.getClass(), rhempleadocontactotbListRhempleadocontactotbToAttach.getIdcontacto());
                attachedRhempleadocontactotbList.add(rhempleadocontactotbListRhempleadocontactotbToAttach);
            }
            rhtipocontactotb.setRhempleadocontactotbList(attachedRhempleadocontactotbList);
            em.persist(rhtipocontactotb);
            for (Rhempleadocontactotb rhempleadocontactotbListRhempleadocontactotb : rhtipocontactotb.getRhempleadocontactotbList()) {
                Rhtipocontactotb oldIdtipocontactoOfRhempleadocontactotbListRhempleadocontactotb = rhempleadocontactotbListRhempleadocontactotb.getIdtipocontacto();
                rhempleadocontactotbListRhempleadocontactotb.setIdtipocontacto(rhtipocontactotb);
                rhempleadocontactotbListRhempleadocontactotb = em.merge(rhempleadocontactotbListRhempleadocontactotb);
                if (oldIdtipocontactoOfRhempleadocontactotbListRhempleadocontactotb != null) {
                    oldIdtipocontactoOfRhempleadocontactotbListRhempleadocontactotb.getRhempleadocontactotbList().remove(rhempleadocontactotbListRhempleadocontactotb);
                    oldIdtipocontactoOfRhempleadocontactotbListRhempleadocontactotb = em.merge(oldIdtipocontactoOfRhempleadocontactotbListRhempleadocontactotb);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rhtipocontactotb rhtipocontactotb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rhtipocontactotb persistentRhtipocontactotb = em.find(Rhtipocontactotb.class, rhtipocontactotb.getIdtipocontacto());
            List<Rhempleadocontactotb> rhempleadocontactotbListOld = persistentRhtipocontactotb.getRhempleadocontactotbList();
            List<Rhempleadocontactotb> rhempleadocontactotbListNew = rhtipocontactotb.getRhempleadocontactotbList();
            List<Rhempleadocontactotb> attachedRhempleadocontactotbListNew = new ArrayList<Rhempleadocontactotb>();
            for (Rhempleadocontactotb rhempleadocontactotbListNewRhempleadocontactotbToAttach : rhempleadocontactotbListNew) {
                rhempleadocontactotbListNewRhempleadocontactotbToAttach = em.getReference(rhempleadocontactotbListNewRhempleadocontactotbToAttach.getClass(), rhempleadocontactotbListNewRhempleadocontactotbToAttach.getIdcontacto());
                attachedRhempleadocontactotbListNew.add(rhempleadocontactotbListNewRhempleadocontactotbToAttach);
            }
            rhempleadocontactotbListNew = attachedRhempleadocontactotbListNew;
            rhtipocontactotb.setRhempleadocontactotbList(rhempleadocontactotbListNew);
            rhtipocontactotb = em.merge(rhtipocontactotb);
            for (Rhempleadocontactotb rhempleadocontactotbListOldRhempleadocontactotb : rhempleadocontactotbListOld) {
                if (!rhempleadocontactotbListNew.contains(rhempleadocontactotbListOldRhempleadocontactotb)) {
                    rhempleadocontactotbListOldRhempleadocontactotb.setIdtipocontacto(null);
                    rhempleadocontactotbListOldRhempleadocontactotb = em.merge(rhempleadocontactotbListOldRhempleadocontactotb);
                }
            }
            for (Rhempleadocontactotb rhempleadocontactotbListNewRhempleadocontactotb : rhempleadocontactotbListNew) {
                if (!rhempleadocontactotbListOld.contains(rhempleadocontactotbListNewRhempleadocontactotb)) {
                    Rhtipocontactotb oldIdtipocontactoOfRhempleadocontactotbListNewRhempleadocontactotb = rhempleadocontactotbListNewRhempleadocontactotb.getIdtipocontacto();
                    rhempleadocontactotbListNewRhempleadocontactotb.setIdtipocontacto(rhtipocontactotb);
                    rhempleadocontactotbListNewRhempleadocontactotb = em.merge(rhempleadocontactotbListNewRhempleadocontactotb);
                    if (oldIdtipocontactoOfRhempleadocontactotbListNewRhempleadocontactotb != null && !oldIdtipocontactoOfRhempleadocontactotbListNewRhempleadocontactotb.equals(rhtipocontactotb)) {
                        oldIdtipocontactoOfRhempleadocontactotbListNewRhempleadocontactotb.getRhempleadocontactotbList().remove(rhempleadocontactotbListNewRhempleadocontactotb);
                        oldIdtipocontactoOfRhempleadocontactotbListNewRhempleadocontactotb = em.merge(oldIdtipocontactoOfRhempleadocontactotbListNewRhempleadocontactotb);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rhtipocontactotb.getIdtipocontacto();
                if (findRhtipocontactotb(id) == null) {
                    throw new NonexistentEntityException("The rhtipocontactotb with id " + id + " no longer exists.");
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
            Rhtipocontactotb rhtipocontactotb;
            try {
                rhtipocontactotb = em.getReference(Rhtipocontactotb.class, id);
                rhtipocontactotb.getIdtipocontacto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rhtipocontactotb with id " + id + " no longer exists.", enfe);
            }
            List<Rhempleadocontactotb> rhempleadocontactotbList = rhtipocontactotb.getRhempleadocontactotbList();
            for (Rhempleadocontactotb rhempleadocontactotbListRhempleadocontactotb : rhempleadocontactotbList) {
                rhempleadocontactotbListRhempleadocontactotb.setIdtipocontacto(null);
                rhempleadocontactotbListRhempleadocontactotb = em.merge(rhempleadocontactotbListRhempleadocontactotb);
            }
            em.remove(rhtipocontactotb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rhtipocontactotb> findRhtipocontactotbEntities() {
        return findRhtipocontactotbEntities(true, -1, -1);
    }

    public List<Rhtipocontactotb> findRhtipocontactotbEntities(int maxResults, int firstResult) {
        return findRhtipocontactotbEntities(false, maxResults, firstResult);
    }

    private List<Rhtipocontactotb> findRhtipocontactotbEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rhtipocontactotb.class));
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

    public Rhtipocontactotb findRhtipocontactotb(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rhtipocontactotb.class, id);
        } finally {
            em.close();
        }
    }

    public int getRhtipocontactotbCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rhtipocontactotb> rt = cq.from(Rhtipocontactotb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
