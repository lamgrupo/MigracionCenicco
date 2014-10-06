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
import com.lam.cenicco.migracion.entidades.mysql.Rhempleadocontactotb;
import java.util.ArrayList;
import java.util.List;
import com.lam.cenicco.migracion.entidades.mysql.Rhrelacionlaboraltb;
import com.lam.cenicco.migracion.entidades.mysql.Rhempleadodomiciliotb;
import com.lam.cenicco.migracion.entidades.mysql.Rhempleadotb;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class RhempleadotbJpaController implements Serializable {

    public RhempleadotbJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rhempleadotb rhempleadotb) {
        if (rhempleadotb.getRhempleadocontactotbList() == null) {
            rhempleadotb.setRhempleadocontactotbList(new ArrayList<Rhempleadocontactotb>());
        }
        if (rhempleadotb.getRhrelacionlaboraltbList() == null) {
            rhempleadotb.setRhrelacionlaboraltbList(new ArrayList<Rhrelacionlaboraltb>());
        }
        if (rhempleadotb.getRhempleadodomiciliotbList() == null) {
            rhempleadotb.setRhempleadodomiciliotbList(new ArrayList<Rhempleadodomiciliotb>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Catestadotb idestadonacimiento = rhempleadotb.getIdestadonacimiento();
            if (idestadonacimiento != null) {
                idestadonacimiento = em.getReference(idestadonacimiento.getClass(), idestadonacimiento.getIdestado());
                rhempleadotb.setIdestadonacimiento(idestadonacimiento);
            }
            List<Rhempleadocontactotb> attachedRhempleadocontactotbList = new ArrayList<Rhempleadocontactotb>();
            for (Rhempleadocontactotb rhempleadocontactotbListRhempleadocontactotbToAttach : rhempleadotb.getRhempleadocontactotbList()) {
                rhempleadocontactotbListRhempleadocontactotbToAttach = em.getReference(rhempleadocontactotbListRhempleadocontactotbToAttach.getClass(), rhempleadocontactotbListRhempleadocontactotbToAttach.getIdcontacto());
                attachedRhempleadocontactotbList.add(rhempleadocontactotbListRhempleadocontactotbToAttach);
            }
            rhempleadotb.setRhempleadocontactotbList(attachedRhempleadocontactotbList);
            List<Rhrelacionlaboraltb> attachedRhrelacionlaboraltbList = new ArrayList<Rhrelacionlaboraltb>();
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListRhrelacionlaboraltbToAttach : rhempleadotb.getRhrelacionlaboraltbList()) {
                rhrelacionlaboraltbListRhrelacionlaboraltbToAttach = em.getReference(rhrelacionlaboraltbListRhrelacionlaboraltbToAttach.getClass(), rhrelacionlaboraltbListRhrelacionlaboraltbToAttach.getIdrellab());
                attachedRhrelacionlaboraltbList.add(rhrelacionlaboraltbListRhrelacionlaboraltbToAttach);
            }
            rhempleadotb.setRhrelacionlaboraltbList(attachedRhrelacionlaboraltbList);
            List<Rhempleadodomiciliotb> attachedRhempleadodomiciliotbList = new ArrayList<Rhempleadodomiciliotb>();
            for (Rhempleadodomiciliotb rhempleadodomiciliotbListRhempleadodomiciliotbToAttach : rhempleadotb.getRhempleadodomiciliotbList()) {
                rhempleadodomiciliotbListRhempleadodomiciliotbToAttach = em.getReference(rhempleadodomiciliotbListRhempleadodomiciliotbToAttach.getClass(), rhempleadodomiciliotbListRhempleadodomiciliotbToAttach.getIddomicilio());
                attachedRhempleadodomiciliotbList.add(rhempleadodomiciliotbListRhempleadodomiciliotbToAttach);
            }
            rhempleadotb.setRhempleadodomiciliotbList(attachedRhempleadodomiciliotbList);
            em.persist(rhempleadotb);
            if (idestadonacimiento != null) {
                idestadonacimiento.getRhempleadotbList().add(rhempleadotb);
                idestadonacimiento = em.merge(idestadonacimiento);
            }
            for (Rhempleadocontactotb rhempleadocontactotbListRhempleadocontactotb : rhempleadotb.getRhempleadocontactotbList()) {
                Rhempleadotb oldIdempleadoOfRhempleadocontactotbListRhempleadocontactotb = rhempleadocontactotbListRhempleadocontactotb.getIdempleado();
                rhempleadocontactotbListRhempleadocontactotb.setIdempleado(rhempleadotb);
                rhempleadocontactotbListRhempleadocontactotb = em.merge(rhempleadocontactotbListRhempleadocontactotb);
                if (oldIdempleadoOfRhempleadocontactotbListRhempleadocontactotb != null) {
                    oldIdempleadoOfRhempleadocontactotbListRhempleadocontactotb.getRhempleadocontactotbList().remove(rhempleadocontactotbListRhempleadocontactotb);
                    oldIdempleadoOfRhempleadocontactotbListRhempleadocontactotb = em.merge(oldIdempleadoOfRhempleadocontactotbListRhempleadocontactotb);
                }
            }
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListRhrelacionlaboraltb : rhempleadotb.getRhrelacionlaboraltbList()) {
                Rhempleadotb oldIdempleadoOfRhrelacionlaboraltbListRhrelacionlaboraltb = rhrelacionlaboraltbListRhrelacionlaboraltb.getIdempleado();
                rhrelacionlaboraltbListRhrelacionlaboraltb.setIdempleado(rhempleadotb);
                rhrelacionlaboraltbListRhrelacionlaboraltb = em.merge(rhrelacionlaboraltbListRhrelacionlaboraltb);
                if (oldIdempleadoOfRhrelacionlaboraltbListRhrelacionlaboraltb != null) {
                    oldIdempleadoOfRhrelacionlaboraltbListRhrelacionlaboraltb.getRhrelacionlaboraltbList().remove(rhrelacionlaboraltbListRhrelacionlaboraltb);
                    oldIdempleadoOfRhrelacionlaboraltbListRhrelacionlaboraltb = em.merge(oldIdempleadoOfRhrelacionlaboraltbListRhrelacionlaboraltb);
                }
            }
            for (Rhempleadodomiciliotb rhempleadodomiciliotbListRhempleadodomiciliotb : rhempleadotb.getRhempleadodomiciliotbList()) {
                Rhempleadotb oldIdempleadoOfRhempleadodomiciliotbListRhempleadodomiciliotb = rhempleadodomiciliotbListRhempleadodomiciliotb.getIdempleado();
                rhempleadodomiciliotbListRhempleadodomiciliotb.setIdempleado(rhempleadotb);
                rhempleadodomiciliotbListRhempleadodomiciliotb = em.merge(rhempleadodomiciliotbListRhempleadodomiciliotb);
                if (oldIdempleadoOfRhempleadodomiciliotbListRhempleadodomiciliotb != null) {
                    oldIdempleadoOfRhempleadodomiciliotbListRhempleadodomiciliotb.getRhempleadodomiciliotbList().remove(rhempleadodomiciliotbListRhempleadodomiciliotb);
                    oldIdempleadoOfRhempleadodomiciliotbListRhempleadodomiciliotb = em.merge(oldIdempleadoOfRhempleadodomiciliotbListRhempleadodomiciliotb);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rhempleadotb rhempleadotb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rhempleadotb persistentRhempleadotb = em.find(Rhempleadotb.class, rhempleadotb.getIdempleado());
            Catestadotb idestadonacimientoOld = persistentRhempleadotb.getIdestadonacimiento();
            Catestadotb idestadonacimientoNew = rhempleadotb.getIdestadonacimiento();
            List<Rhempleadocontactotb> rhempleadocontactotbListOld = persistentRhempleadotb.getRhempleadocontactotbList();
            List<Rhempleadocontactotb> rhempleadocontactotbListNew = rhempleadotb.getRhempleadocontactotbList();
            List<Rhrelacionlaboraltb> rhrelacionlaboraltbListOld = persistentRhempleadotb.getRhrelacionlaboraltbList();
            List<Rhrelacionlaboraltb> rhrelacionlaboraltbListNew = rhempleadotb.getRhrelacionlaboraltbList();
            List<Rhempleadodomiciliotb> rhempleadodomiciliotbListOld = persistentRhempleadotb.getRhempleadodomiciliotbList();
            List<Rhempleadodomiciliotb> rhempleadodomiciliotbListNew = rhempleadotb.getRhempleadodomiciliotbList();
            if (idestadonacimientoNew != null) {
                idestadonacimientoNew = em.getReference(idestadonacimientoNew.getClass(), idestadonacimientoNew.getIdestado());
                rhempleadotb.setIdestadonacimiento(idestadonacimientoNew);
            }
            List<Rhempleadocontactotb> attachedRhempleadocontactotbListNew = new ArrayList<Rhempleadocontactotb>();
            for (Rhempleadocontactotb rhempleadocontactotbListNewRhempleadocontactotbToAttach : rhempleadocontactotbListNew) {
                rhempleadocontactotbListNewRhempleadocontactotbToAttach = em.getReference(rhempleadocontactotbListNewRhempleadocontactotbToAttach.getClass(), rhempleadocontactotbListNewRhempleadocontactotbToAttach.getIdcontacto());
                attachedRhempleadocontactotbListNew.add(rhempleadocontactotbListNewRhempleadocontactotbToAttach);
            }
            rhempleadocontactotbListNew = attachedRhempleadocontactotbListNew;
            rhempleadotb.setRhempleadocontactotbList(rhempleadocontactotbListNew);
            List<Rhrelacionlaboraltb> attachedRhrelacionlaboraltbListNew = new ArrayList<Rhrelacionlaboraltb>();
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach : rhrelacionlaboraltbListNew) {
                rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach = em.getReference(rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach.getClass(), rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach.getIdrellab());
                attachedRhrelacionlaboraltbListNew.add(rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach);
            }
            rhrelacionlaboraltbListNew = attachedRhrelacionlaboraltbListNew;
            rhempleadotb.setRhrelacionlaboraltbList(rhrelacionlaboraltbListNew);
            List<Rhempleadodomiciliotb> attachedRhempleadodomiciliotbListNew = new ArrayList<Rhempleadodomiciliotb>();
            for (Rhempleadodomiciliotb rhempleadodomiciliotbListNewRhempleadodomiciliotbToAttach : rhempleadodomiciliotbListNew) {
                rhempleadodomiciliotbListNewRhempleadodomiciliotbToAttach = em.getReference(rhempleadodomiciliotbListNewRhempleadodomiciliotbToAttach.getClass(), rhempleadodomiciliotbListNewRhempleadodomiciliotbToAttach.getIddomicilio());
                attachedRhempleadodomiciliotbListNew.add(rhempleadodomiciliotbListNewRhempleadodomiciliotbToAttach);
            }
            rhempleadodomiciliotbListNew = attachedRhempleadodomiciliotbListNew;
            rhempleadotb.setRhempleadodomiciliotbList(rhempleadodomiciliotbListNew);
            rhempleadotb = em.merge(rhempleadotb);
            if (idestadonacimientoOld != null && !idestadonacimientoOld.equals(idestadonacimientoNew)) {
                idestadonacimientoOld.getRhempleadotbList().remove(rhempleadotb);
                idestadonacimientoOld = em.merge(idestadonacimientoOld);
            }
            if (idestadonacimientoNew != null && !idestadonacimientoNew.equals(idestadonacimientoOld)) {
                idestadonacimientoNew.getRhempleadotbList().add(rhempleadotb);
                idestadonacimientoNew = em.merge(idestadonacimientoNew);
            }
            for (Rhempleadocontactotb rhempleadocontactotbListOldRhempleadocontactotb : rhempleadocontactotbListOld) {
                if (!rhempleadocontactotbListNew.contains(rhempleadocontactotbListOldRhempleadocontactotb)) {
                    rhempleadocontactotbListOldRhempleadocontactotb.setIdempleado(null);
                    rhempleadocontactotbListOldRhempleadocontactotb = em.merge(rhempleadocontactotbListOldRhempleadocontactotb);
                }
            }
            for (Rhempleadocontactotb rhempleadocontactotbListNewRhempleadocontactotb : rhempleadocontactotbListNew) {
                if (!rhempleadocontactotbListOld.contains(rhempleadocontactotbListNewRhempleadocontactotb)) {
                    Rhempleadotb oldIdempleadoOfRhempleadocontactotbListNewRhempleadocontactotb = rhempleadocontactotbListNewRhempleadocontactotb.getIdempleado();
                    rhempleadocontactotbListNewRhempleadocontactotb.setIdempleado(rhempleadotb);
                    rhempleadocontactotbListNewRhempleadocontactotb = em.merge(rhempleadocontactotbListNewRhempleadocontactotb);
                    if (oldIdempleadoOfRhempleadocontactotbListNewRhempleadocontactotb != null && !oldIdempleadoOfRhempleadocontactotbListNewRhempleadocontactotb.equals(rhempleadotb)) {
                        oldIdempleadoOfRhempleadocontactotbListNewRhempleadocontactotb.getRhempleadocontactotbList().remove(rhempleadocontactotbListNewRhempleadocontactotb);
                        oldIdempleadoOfRhempleadocontactotbListNewRhempleadocontactotb = em.merge(oldIdempleadoOfRhempleadocontactotbListNewRhempleadocontactotb);
                    }
                }
            }
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListOldRhrelacionlaboraltb : rhrelacionlaboraltbListOld) {
                if (!rhrelacionlaboraltbListNew.contains(rhrelacionlaboraltbListOldRhrelacionlaboraltb)) {
                    rhrelacionlaboraltbListOldRhrelacionlaboraltb.setIdempleado(null);
                    rhrelacionlaboraltbListOldRhrelacionlaboraltb = em.merge(rhrelacionlaboraltbListOldRhrelacionlaboraltb);
                }
            }
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListNewRhrelacionlaboraltb : rhrelacionlaboraltbListNew) {
                if (!rhrelacionlaboraltbListOld.contains(rhrelacionlaboraltbListNewRhrelacionlaboraltb)) {
                    Rhempleadotb oldIdempleadoOfRhrelacionlaboraltbListNewRhrelacionlaboraltb = rhrelacionlaboraltbListNewRhrelacionlaboraltb.getIdempleado();
                    rhrelacionlaboraltbListNewRhrelacionlaboraltb.setIdempleado(rhempleadotb);
                    rhrelacionlaboraltbListNewRhrelacionlaboraltb = em.merge(rhrelacionlaboraltbListNewRhrelacionlaboraltb);
                    if (oldIdempleadoOfRhrelacionlaboraltbListNewRhrelacionlaboraltb != null && !oldIdempleadoOfRhrelacionlaboraltbListNewRhrelacionlaboraltb.equals(rhempleadotb)) {
                        oldIdempleadoOfRhrelacionlaboraltbListNewRhrelacionlaboraltb.getRhrelacionlaboraltbList().remove(rhrelacionlaboraltbListNewRhrelacionlaboraltb);
                        oldIdempleadoOfRhrelacionlaboraltbListNewRhrelacionlaboraltb = em.merge(oldIdempleadoOfRhrelacionlaboraltbListNewRhrelacionlaboraltb);
                    }
                }
            }
            for (Rhempleadodomiciliotb rhempleadodomiciliotbListOldRhempleadodomiciliotb : rhempleadodomiciliotbListOld) {
                if (!rhempleadodomiciliotbListNew.contains(rhempleadodomiciliotbListOldRhempleadodomiciliotb)) {
                    rhempleadodomiciliotbListOldRhempleadodomiciliotb.setIdempleado(null);
                    rhempleadodomiciliotbListOldRhempleadodomiciliotb = em.merge(rhempleadodomiciliotbListOldRhempleadodomiciliotb);
                }
            }
            for (Rhempleadodomiciliotb rhempleadodomiciliotbListNewRhempleadodomiciliotb : rhempleadodomiciliotbListNew) {
                if (!rhempleadodomiciliotbListOld.contains(rhempleadodomiciliotbListNewRhempleadodomiciliotb)) {
                    Rhempleadotb oldIdempleadoOfRhempleadodomiciliotbListNewRhempleadodomiciliotb = rhempleadodomiciliotbListNewRhempleadodomiciliotb.getIdempleado();
                    rhempleadodomiciliotbListNewRhempleadodomiciliotb.setIdempleado(rhempleadotb);
                    rhempleadodomiciliotbListNewRhempleadodomiciliotb = em.merge(rhempleadodomiciliotbListNewRhempleadodomiciliotb);
                    if (oldIdempleadoOfRhempleadodomiciliotbListNewRhempleadodomiciliotb != null && !oldIdempleadoOfRhempleadodomiciliotbListNewRhempleadodomiciliotb.equals(rhempleadotb)) {
                        oldIdempleadoOfRhempleadodomiciliotbListNewRhempleadodomiciliotb.getRhempleadodomiciliotbList().remove(rhempleadodomiciliotbListNewRhempleadodomiciliotb);
                        oldIdempleadoOfRhempleadodomiciliotbListNewRhempleadodomiciliotb = em.merge(oldIdempleadoOfRhempleadodomiciliotbListNewRhempleadodomiciliotb);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rhempleadotb.getIdempleado();
                if (findRhempleadotb(id) == null) {
                    throw new NonexistentEntityException("The rhempleadotb with id " + id + " no longer exists.");
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
            Rhempleadotb rhempleadotb;
            try {
                rhempleadotb = em.getReference(Rhempleadotb.class, id);
                rhempleadotb.getIdempleado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rhempleadotb with id " + id + " no longer exists.", enfe);
            }
            Catestadotb idestadonacimiento = rhempleadotb.getIdestadonacimiento();
            if (idestadonacimiento != null) {
                idestadonacimiento.getRhempleadotbList().remove(rhempleadotb);
                idestadonacimiento = em.merge(idestadonacimiento);
            }
            List<Rhempleadocontactotb> rhempleadocontactotbList = rhempleadotb.getRhempleadocontactotbList();
            for (Rhempleadocontactotb rhempleadocontactotbListRhempleadocontactotb : rhempleadocontactotbList) {
                rhempleadocontactotbListRhempleadocontactotb.setIdempleado(null);
                rhempleadocontactotbListRhempleadocontactotb = em.merge(rhempleadocontactotbListRhempleadocontactotb);
            }
            List<Rhrelacionlaboraltb> rhrelacionlaboraltbList = rhempleadotb.getRhrelacionlaboraltbList();
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListRhrelacionlaboraltb : rhrelacionlaboraltbList) {
                rhrelacionlaboraltbListRhrelacionlaboraltb.setIdempleado(null);
                rhrelacionlaboraltbListRhrelacionlaboraltb = em.merge(rhrelacionlaboraltbListRhrelacionlaboraltb);
            }
            List<Rhempleadodomiciliotb> rhempleadodomiciliotbList = rhempleadotb.getRhempleadodomiciliotbList();
            for (Rhempleadodomiciliotb rhempleadodomiciliotbListRhempleadodomiciliotb : rhempleadodomiciliotbList) {
                rhempleadodomiciliotbListRhempleadodomiciliotb.setIdempleado(null);
                rhempleadodomiciliotbListRhempleadodomiciliotb = em.merge(rhempleadodomiciliotbListRhempleadodomiciliotb);
            }
            em.remove(rhempleadotb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rhempleadotb> findRhempleadotbEntities() {
        return findRhempleadotbEntities(true, -1, -1);
    }

    public List<Rhempleadotb> findRhempleadotbEntities(int maxResults, int firstResult) {
        return findRhempleadotbEntities(false, maxResults, firstResult);
    }

    private List<Rhempleadotb> findRhempleadotbEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rhempleadotb.class));
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

    public Rhempleadotb findRhempleadotb(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rhempleadotb.class, id);
        } finally {
            em.close();
        }
    }

    public int getRhempleadotbCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rhempleadotb> rt = cq.from(Rhempleadotb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
