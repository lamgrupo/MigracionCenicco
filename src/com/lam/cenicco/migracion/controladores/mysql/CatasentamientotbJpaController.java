/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.controladores.mysql;

import com.cenicco.migracion.util.JpaUtil;
import com.lam.cenicco.migracion.controladores.mysql.exceptions.NonexistentEntityException;
import com.lam.cenicco.migracion.entidades.mysql.Catasentamientotb;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.lam.cenicco.migracion.entidades.mysql.Catciudadtb;
import com.lam.cenicco.migracion.entidades.mysql.Cattipoasentamientotb;
import com.lam.cenicco.migracion.entidades.mysql.Rhempleadodomiciliotb;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class CatasentamientotbJpaController implements Serializable {

    public CatasentamientotbJpaController() {
        this.emf = JpaUtil.getInstance().createEntityManagerCeniccoMysql().getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Catasentamientotb catasentamientotb) {
        if (catasentamientotb.getRhempleadodomiciliotbList() == null) {
            catasentamientotb.setRhempleadodomiciliotbList(new ArrayList<Rhempleadodomiciliotb>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Catciudadtb idciudad = catasentamientotb.getIdciudad();
            if (idciudad != null) {
                idciudad = em.getReference(idciudad.getClass(), idciudad.getIdciudad());
                catasentamientotb.setIdciudad(idciudad);
            }
            Cattipoasentamientotb idtipoasentamiento = catasentamientotb.getIdtipoasentamiento();
            if (idtipoasentamiento != null) {
                idtipoasentamiento = em.getReference(idtipoasentamiento.getClass(), idtipoasentamiento.getIdtipoasentamiento());
                catasentamientotb.setIdtipoasentamiento(idtipoasentamiento);
            }
            List<Rhempleadodomiciliotb> attachedRhempleadodomiciliotbList = new ArrayList<Rhempleadodomiciliotb>();
            for (Rhempleadodomiciliotb rhempleadodomiciliotbListRhempleadodomiciliotbToAttach : catasentamientotb.getRhempleadodomiciliotbList()) {
                rhempleadodomiciliotbListRhempleadodomiciliotbToAttach = em.getReference(rhempleadodomiciliotbListRhempleadodomiciliotbToAttach.getClass(), rhempleadodomiciliotbListRhempleadodomiciliotbToAttach.getIddomicilio());
                attachedRhempleadodomiciliotbList.add(rhempleadodomiciliotbListRhempleadodomiciliotbToAttach);
            }
            catasentamientotb.setRhempleadodomiciliotbList(attachedRhempleadodomiciliotbList);
            em.persist(catasentamientotb);
            if (idciudad != null) {
                idciudad.getCatasentamientotbList().add(catasentamientotb);
                idciudad = em.merge(idciudad);
            }
            if (idtipoasentamiento != null) {
                idtipoasentamiento.getCatasentamientotbList().add(catasentamientotb);
                idtipoasentamiento = em.merge(idtipoasentamiento);
            }
            for (Rhempleadodomiciliotb rhempleadodomiciliotbListRhempleadodomiciliotb : catasentamientotb.getRhempleadodomiciliotbList()) {
                Catasentamientotb oldIdasentamientoOfRhempleadodomiciliotbListRhempleadodomiciliotb = rhempleadodomiciliotbListRhempleadodomiciliotb.getIdasentamiento();
                rhempleadodomiciliotbListRhempleadodomiciliotb.setIdasentamiento(catasentamientotb);
                rhempleadodomiciliotbListRhempleadodomiciliotb = em.merge(rhempleadodomiciliotbListRhempleadodomiciliotb);
                if (oldIdasentamientoOfRhempleadodomiciliotbListRhempleadodomiciliotb != null) {
                    oldIdasentamientoOfRhempleadodomiciliotbListRhempleadodomiciliotb.getRhempleadodomiciliotbList().remove(rhempleadodomiciliotbListRhempleadodomiciliotb);
                    oldIdasentamientoOfRhempleadodomiciliotbListRhempleadodomiciliotb = em.merge(oldIdasentamientoOfRhempleadodomiciliotbListRhempleadodomiciliotb);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Catasentamientotb catasentamientotb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Catasentamientotb persistentCatasentamientotb = em.find(Catasentamientotb.class, catasentamientotb.getIdasentamiento());
            Catciudadtb idciudadOld = persistentCatasentamientotb.getIdciudad();
            Catciudadtb idciudadNew = catasentamientotb.getIdciudad();
            Cattipoasentamientotb idtipoasentamientoOld = persistentCatasentamientotb.getIdtipoasentamiento();
            Cattipoasentamientotb idtipoasentamientoNew = catasentamientotb.getIdtipoasentamiento();
            List<Rhempleadodomiciliotb> rhempleadodomiciliotbListOld = persistentCatasentamientotb.getRhempleadodomiciliotbList();
            List<Rhempleadodomiciliotb> rhempleadodomiciliotbListNew = catasentamientotb.getRhempleadodomiciliotbList();
            if (idciudadNew != null) {
                idciudadNew = em.getReference(idciudadNew.getClass(), idciudadNew.getIdciudad());
                catasentamientotb.setIdciudad(idciudadNew);
            }
            if (idtipoasentamientoNew != null) {
                idtipoasentamientoNew = em.getReference(idtipoasentamientoNew.getClass(), idtipoasentamientoNew.getIdtipoasentamiento());
                catasentamientotb.setIdtipoasentamiento(idtipoasentamientoNew);
            }
            List<Rhempleadodomiciliotb> attachedRhempleadodomiciliotbListNew = new ArrayList<Rhempleadodomiciliotb>();
            for (Rhempleadodomiciliotb rhempleadodomiciliotbListNewRhempleadodomiciliotbToAttach : rhempleadodomiciliotbListNew) {
                rhempleadodomiciliotbListNewRhempleadodomiciliotbToAttach = em.getReference(rhempleadodomiciliotbListNewRhempleadodomiciliotbToAttach.getClass(), rhempleadodomiciliotbListNewRhempleadodomiciliotbToAttach.getIddomicilio());
                attachedRhempleadodomiciliotbListNew.add(rhempleadodomiciliotbListNewRhempleadodomiciliotbToAttach);
            }
            rhempleadodomiciliotbListNew = attachedRhempleadodomiciliotbListNew;
            catasentamientotb.setRhempleadodomiciliotbList(rhempleadodomiciliotbListNew);
            catasentamientotb = em.merge(catasentamientotb);
            if (idciudadOld != null && !idciudadOld.equals(idciudadNew)) {
                idciudadOld.getCatasentamientotbList().remove(catasentamientotb);
                idciudadOld = em.merge(idciudadOld);
            }
            if (idciudadNew != null && !idciudadNew.equals(idciudadOld)) {
                idciudadNew.getCatasentamientotbList().add(catasentamientotb);
                idciudadNew = em.merge(idciudadNew);
            }
            if (idtipoasentamientoOld != null && !idtipoasentamientoOld.equals(idtipoasentamientoNew)) {
                idtipoasentamientoOld.getCatasentamientotbList().remove(catasentamientotb);
                idtipoasentamientoOld = em.merge(idtipoasentamientoOld);
            }
            if (idtipoasentamientoNew != null && !idtipoasentamientoNew.equals(idtipoasentamientoOld)) {
                idtipoasentamientoNew.getCatasentamientotbList().add(catasentamientotb);
                idtipoasentamientoNew = em.merge(idtipoasentamientoNew);
            }
            for (Rhempleadodomiciliotb rhempleadodomiciliotbListOldRhempleadodomiciliotb : rhempleadodomiciliotbListOld) {
                if (!rhempleadodomiciliotbListNew.contains(rhempleadodomiciliotbListOldRhempleadodomiciliotb)) {
                    rhempleadodomiciliotbListOldRhempleadodomiciliotb.setIdasentamiento(null);
                    rhempleadodomiciliotbListOldRhempleadodomiciliotb = em.merge(rhempleadodomiciliotbListOldRhempleadodomiciliotb);
                }
            }
            for (Rhempleadodomiciliotb rhempleadodomiciliotbListNewRhempleadodomiciliotb : rhempleadodomiciliotbListNew) {
                if (!rhempleadodomiciliotbListOld.contains(rhempleadodomiciliotbListNewRhempleadodomiciliotb)) {
                    Catasentamientotb oldIdasentamientoOfRhempleadodomiciliotbListNewRhempleadodomiciliotb = rhempleadodomiciliotbListNewRhempleadodomiciliotb.getIdasentamiento();
                    rhempleadodomiciliotbListNewRhempleadodomiciliotb.setIdasentamiento(catasentamientotb);
                    rhempleadodomiciliotbListNewRhempleadodomiciliotb = em.merge(rhempleadodomiciliotbListNewRhempleadodomiciliotb);
                    if (oldIdasentamientoOfRhempleadodomiciliotbListNewRhempleadodomiciliotb != null && !oldIdasentamientoOfRhempleadodomiciliotbListNewRhempleadodomiciliotb.equals(catasentamientotb)) {
                        oldIdasentamientoOfRhempleadodomiciliotbListNewRhempleadodomiciliotb.getRhempleadodomiciliotbList().remove(rhempleadodomiciliotbListNewRhempleadodomiciliotb);
                        oldIdasentamientoOfRhempleadodomiciliotbListNewRhempleadodomiciliotb = em.merge(oldIdasentamientoOfRhempleadodomiciliotbListNewRhempleadodomiciliotb);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = catasentamientotb.getIdasentamiento();
                if (findCatasentamientotb(id) == null) {
                    throw new NonexistentEntityException("The catasentamientotb with id " + id + " no longer exists.");
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
            Catasentamientotb catasentamientotb;
            try {
                catasentamientotb = em.getReference(Catasentamientotb.class, id);
                catasentamientotb.getIdasentamiento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The catasentamientotb with id " + id + " no longer exists.", enfe);
            }
            Catciudadtb idciudad = catasentamientotb.getIdciudad();
            if (idciudad != null) {
                idciudad.getCatasentamientotbList().remove(catasentamientotb);
                idciudad = em.merge(idciudad);
            }
            Cattipoasentamientotb idtipoasentamiento = catasentamientotb.getIdtipoasentamiento();
            if (idtipoasentamiento != null) {
                idtipoasentamiento.getCatasentamientotbList().remove(catasentamientotb);
                idtipoasentamiento = em.merge(idtipoasentamiento);
            }
            List<Rhempleadodomiciliotb> rhempleadodomiciliotbList = catasentamientotb.getRhempleadodomiciliotbList();
            for (Rhempleadodomiciliotb rhempleadodomiciliotbListRhempleadodomiciliotb : rhempleadodomiciliotbList) {
                rhempleadodomiciliotbListRhempleadodomiciliotb.setIdasentamiento(null);
                rhempleadodomiciliotbListRhempleadodomiciliotb = em.merge(rhempleadodomiciliotbListRhempleadodomiciliotb);
            }
            em.remove(catasentamientotb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Catasentamientotb> findCatasentamientotbEntities() {
        return findCatasentamientotbEntities(true, -1, -1);
    }

    public List<Catasentamientotb> findCatasentamientotbEntities(int maxResults, int firstResult) {
        return findCatasentamientotbEntities(false, maxResults, firstResult);
    }

    private List<Catasentamientotb> findCatasentamientotbEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Catasentamientotb.class));
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

    public Catasentamientotb findCatasentamientotb(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Catasentamientotb.class, id);
        } finally {
            em.close();
        }
    }

    public int getCatasentamientotbCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Catasentamientotb> rt = cq.from(Catasentamientotb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
