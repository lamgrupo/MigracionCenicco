/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.controladores.oracle;

import com.cenicco.migracion.util.JpaUtil;
import com.lam.cenicco.migracion.controladores.oracle.exceptions.NonexistentEntityException;
import com.lam.cenicco.migracion.controladores.oracle.exceptions.PreexistingEntityException;
import com.lam.cenicco.migracion.entidades.oracle.EstadoOracle;
import com.lam.cenicco.migracion.entidades.oracle.EstadoOraclePK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.lam.cenicco.migracion.entidades.oracle.PaisOracle;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class EstadoOracleJpaController implements Serializable {

    public EstadoOracleJpaController() {
        this.emf = JpaUtil.getInstance().createEntityManagerCeniccoOracle().getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EstadoOracle estadoOracle) throws PreexistingEntityException, Exception {
        if (estadoOracle.getEstadoOraclePK() == null) {
            estadoOracle.setEstadoOraclePK(new EstadoOraclePK());
        }
        estadoOracle.getEstadoOraclePK().setIdpais(estadoOracle.getPaisOracle().getIdpais());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PaisOracle paisOracle = estadoOracle.getPaisOracle();
            if (paisOracle != null) {
                paisOracle = em.getReference(paisOracle.getClass(), paisOracle.getIdpais());
                estadoOracle.setPaisOracle(paisOracle);
            }
            em.persist(estadoOracle);
            if (paisOracle != null) {
                paisOracle.getEstadoOracleList().add(estadoOracle);
                paisOracle = em.merge(paisOracle);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadoOracle(estadoOracle.getEstadoOraclePK()) != null) {
                throw new PreexistingEntityException("EstadoOracle " + estadoOracle + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EstadoOracle estadoOracle) throws NonexistentEntityException, Exception {
        estadoOracle.getEstadoOraclePK().setIdpais(estadoOracle.getPaisOracle().getIdpais());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EstadoOracle persistentEstadoOracle = em.find(EstadoOracle.class, estadoOracle.getEstadoOraclePK());
            PaisOracle paisOracleOld = persistentEstadoOracle.getPaisOracle();
            PaisOracle paisOracleNew = estadoOracle.getPaisOracle();
            if (paisOracleNew != null) {
                paisOracleNew = em.getReference(paisOracleNew.getClass(), paisOracleNew.getIdpais());
                estadoOracle.setPaisOracle(paisOracleNew);
            }
            estadoOracle = em.merge(estadoOracle);
            if (paisOracleOld != null && !paisOracleOld.equals(paisOracleNew)) {
                paisOracleOld.getEstadoOracleList().remove(estadoOracle);
                paisOracleOld = em.merge(paisOracleOld);
            }
            if (paisOracleNew != null && !paisOracleNew.equals(paisOracleOld)) {
                paisOracleNew.getEstadoOracleList().add(estadoOracle);
                paisOracleNew = em.merge(paisOracleNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                EstadoOraclePK id = estadoOracle.getEstadoOraclePK();
                if (findEstadoOracle(id) == null) {
                    throw new NonexistentEntityException("The estadoOracle with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(EstadoOraclePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EstadoOracle estadoOracle;
            try {
                estadoOracle = em.getReference(EstadoOracle.class, id);
                estadoOracle.getEstadoOraclePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadoOracle with id " + id + " no longer exists.", enfe);
            }
            PaisOracle paisOracle = estadoOracle.getPaisOracle();
            if (paisOracle != null) {
                paisOracle.getEstadoOracleList().remove(estadoOracle);
                paisOracle = em.merge(paisOracle);
            }
            em.remove(estadoOracle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EstadoOracle> findEstadoOracleEntities() {
        return findEstadoOracleEntities(true, -1, -1);
    }

    public List<EstadoOracle> findEstadoOracleEntities(int maxResults, int firstResult) {
        return findEstadoOracleEntities(false, maxResults, firstResult);
    }

    private List<EstadoOracle> findEstadoOracleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EstadoOracle.class));
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

    public EstadoOracle findEstadoOracle(EstadoOraclePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EstadoOracle.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoOracleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EstadoOracle> rt = cq.from(EstadoOracle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
