/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.controladores.oracle;

import com.cenicco.migracion.util.JpaUtil;
import com.lam.cenicco.migracion.controladores.oracle.exceptions.NonexistentEntityException;
import com.lam.cenicco.migracion.controladores.oracle.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.lam.cenicco.migracion.entidades.oracle.PosicionOracle;
import com.lam.cenicco.migracion.entidades.oracle.PosicionSueldoOracle;
import com.lam.cenicco.migracion.entidades.oracle.PosicionSueldoOraclePK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class PosicionSueldoOracleJpaController implements Serializable {

    public PosicionSueldoOracleJpaController() {
        this.emf = JpaUtil.getInstance().createEntityManagerCeniccoOracle().getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PosicionSueldoOracle posicionSueldoOracle) throws PreexistingEntityException, Exception {
        if (posicionSueldoOracle.getPosicionSueldoOraclePK() == null) {
            posicionSueldoOracle.setPosicionSueldoOraclePK(new PosicionSueldoOraclePK());
        }
        posicionSueldoOracle.getPosicionSueldoOraclePK().setSec(posicionSueldoOracle.getPosicionOracle().getPosicionOraclePK().getSec());
        posicionSueldoOracle.getPosicionSueldoOraclePK().setIdrellab(posicionSueldoOracle.getPosicionOracle().getPosicionOraclePK().getIdrellab());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PosicionOracle posicionOracle = posicionSueldoOracle.getPosicionOracle();
            if (posicionOracle != null) {
                posicionOracle = em.getReference(posicionOracle.getClass(), posicionOracle.getPosicionOraclePK());
                posicionSueldoOracle.setPosicionOracle(posicionOracle);
            }
            em.persist(posicionSueldoOracle);
            if (posicionOracle != null) {
                posicionOracle.getPosicionSueldoOracleList().add(posicionSueldoOracle);
                posicionOracle = em.merge(posicionOracle);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPosicionSueldoOracle(posicionSueldoOracle.getPosicionSueldoOraclePK()) != null) {
                throw new PreexistingEntityException("PosicionSueldoOracle " + posicionSueldoOracle + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PosicionSueldoOracle posicionSueldoOracle) throws NonexistentEntityException, Exception {
        posicionSueldoOracle.getPosicionSueldoOraclePK().setSec(posicionSueldoOracle.getPosicionOracle().getPosicionOraclePK().getSec());
        posicionSueldoOracle.getPosicionSueldoOraclePK().setIdrellab(posicionSueldoOracle.getPosicionOracle().getPosicionOraclePK().getIdrellab());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PosicionSueldoOracle persistentPosicionSueldoOracle = em.find(PosicionSueldoOracle.class, posicionSueldoOracle.getPosicionSueldoOraclePK());
            PosicionOracle posicionOracleOld = persistentPosicionSueldoOracle.getPosicionOracle();
            PosicionOracle posicionOracleNew = posicionSueldoOracle.getPosicionOracle();
            if (posicionOracleNew != null) {
                posicionOracleNew = em.getReference(posicionOracleNew.getClass(), posicionOracleNew.getPosicionOraclePK());
                posicionSueldoOracle.setPosicionOracle(posicionOracleNew);
            }
            posicionSueldoOracle = em.merge(posicionSueldoOracle);
            if (posicionOracleOld != null && !posicionOracleOld.equals(posicionOracleNew)) {
                posicionOracleOld.getPosicionSueldoOracleList().remove(posicionSueldoOracle);
                posicionOracleOld = em.merge(posicionOracleOld);
            }
            if (posicionOracleNew != null && !posicionOracleNew.equals(posicionOracleOld)) {
                posicionOracleNew.getPosicionSueldoOracleList().add(posicionSueldoOracle);
                posicionOracleNew = em.merge(posicionOracleNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PosicionSueldoOraclePK id = posicionSueldoOracle.getPosicionSueldoOraclePK();
                if (findPosicionSueldoOracle(id) == null) {
                    throw new NonexistentEntityException("The posicionSueldoOracle with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PosicionSueldoOraclePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PosicionSueldoOracle posicionSueldoOracle;
            try {
                posicionSueldoOracle = em.getReference(PosicionSueldoOracle.class, id);
                posicionSueldoOracle.getPosicionSueldoOraclePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The posicionSueldoOracle with id " + id + " no longer exists.", enfe);
            }
            PosicionOracle posicionOracle = posicionSueldoOracle.getPosicionOracle();
            if (posicionOracle != null) {
                posicionOracle.getPosicionSueldoOracleList().remove(posicionSueldoOracle);
                posicionOracle = em.merge(posicionOracle);
            }
            em.remove(posicionSueldoOracle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PosicionSueldoOracle> findPosicionSueldoOracleEntities() {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Query q = em.createNamedQuery("PosicionSueldoOracle.findAll");
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JpaUtil.getInstance().closeEntityManager(em);
        }
    }

    public List<PosicionSueldoOracle> findPosicionSueldoOracleEntities(int maxResults, int firstResult) {
        return findPosicionSueldoOracleEntities(false, maxResults, firstResult);
    }

    private List<PosicionSueldoOracle> findPosicionSueldoOracleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PosicionSueldoOracle.class));
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

    public PosicionSueldoOracle findPosicionSueldoOracle(PosicionSueldoOraclePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PosicionSueldoOracle.class, id);
        } finally {
            em.close();
        }
    }

    public int getPosicionSueldoOracleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PosicionSueldoOracle> rt = cq.from(PosicionSueldoOracle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
