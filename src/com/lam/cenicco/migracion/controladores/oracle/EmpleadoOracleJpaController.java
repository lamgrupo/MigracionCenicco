/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.controladores.oracle;

import com.cenicco.migracion.util.JpaUtil;
import com.lam.cenicco.migracion.controladores.oracle.exceptions.NonexistentEntityException;
import com.lam.cenicco.migracion.controladores.oracle.exceptions.PreexistingEntityException;
import com.lam.cenicco.migracion.entidades.oracle.EmpleadoOracle;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.lam.cenicco.migracion.entidades.oracle.EstadoOracle;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class EmpleadoOracleJpaController implements Serializable {

    public EmpleadoOracleJpaController() {
        this.emf = JpaUtil.getInstance().createEntityManagerCeniccoOracle().getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EmpleadoOracle empleadoOracle) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EstadoOracle estadoOracle = empleadoOracle.getEstadoOracle();
            if (estadoOracle != null) {
                estadoOracle = em.getReference(estadoOracle.getClass(), estadoOracle.getEstadoOraclePK());
                empleadoOracle.setEstadoOracle(estadoOracle);
            }
            em.persist(empleadoOracle);
            if (estadoOracle != null) {
                estadoOracle.getEmpeladoOracleList().add(empleadoOracle);
                estadoOracle = em.merge(estadoOracle);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEmpleadoOracle(empleadoOracle.getIdempleado()) != null) {
                throw new PreexistingEntityException("EmpleadoOracle " + empleadoOracle + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EmpleadoOracle empleadoOracle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EmpleadoOracle persistentEmpleadoOracle = em.find(EmpleadoOracle.class, empleadoOracle.getIdempleado());
            EstadoOracle estadoOracleOld = persistentEmpleadoOracle.getEstadoOracle();
            EstadoOracle estadoOracleNew = empleadoOracle.getEstadoOracle();
            if (estadoOracleNew != null) {
                estadoOracleNew = em.getReference(estadoOracleNew.getClass(), estadoOracleNew.getEstadoOraclePK());
                empleadoOracle.setEstadoOracle(estadoOracleNew);
            }
            empleadoOracle = em.merge(empleadoOracle);
            if (estadoOracleOld != null && !estadoOracleOld.equals(estadoOracleNew)) {
                estadoOracleOld.getEmpeladoOracleList().remove(empleadoOracle);
                estadoOracleOld = em.merge(estadoOracleOld);
            }
            if (estadoOracleNew != null && !estadoOracleNew.equals(estadoOracleOld)) {
                estadoOracleNew.getEmpeladoOracleList().add(empleadoOracle);
                estadoOracleNew = em.merge(estadoOracleNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = empleadoOracle.getIdempleado();
                if (findEmpleadoOracle(id) == null) {
                    throw new NonexistentEntityException("The empleadoOracle with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EmpleadoOracle empleadoOracle;
            try {
                empleadoOracle = em.getReference(EmpleadoOracle.class, id);
                empleadoOracle.getIdempleado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleadoOracle with id " + id + " no longer exists.", enfe);
            }
            EstadoOracle estadoOracle = empleadoOracle.getEstadoOracle();
            if (estadoOracle != null) {
                estadoOracle.getEmpeladoOracleList().remove(empleadoOracle);
                estadoOracle = em.merge(estadoOracle);
            }
            em.remove(empleadoOracle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EmpleadoOracle> findEmpleadoOracleEntities() {
        return findEmpleadoOracleEntities(true, -1, -1);
    }

    public List<EmpleadoOracle> findEmpleadoOracleEntities(int maxResults, int firstResult) {
        return findEmpleadoOracleEntities(false, maxResults, firstResult);
    }

    private List<EmpleadoOracle> findEmpleadoOracleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EmpleadoOracle.class));
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

    public EmpleadoOracle findEmpleadoOracle(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EmpleadoOracle.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadoOracleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EmpleadoOracle> rt = cq.from(EmpleadoOracle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
