/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.controladores.oracle;

import com.cenicco.migracion.util.JpaUtil;
import com.lam.cenicco.migracion.controladores.oracle.exceptions.NonexistentEntityException;
import com.lam.cenicco.migracion.controladores.oracle.exceptions.PreexistingEntityException;
import com.lam.cenicco.migracion.entidades.oracle.CompaniaOracle;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author JoséAntonio
 */
public class CompaniaOracleJpaController implements Serializable {

    public CompaniaOracleJpaController() {
        this.emf = JpaUtil.getInstance().createEntityManagerCeniccoOracle().getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CompaniaOracle companiaOracle) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(companiaOracle);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCompaniaOracle(companiaOracle.getIdcompania()) != null) {
                throw new PreexistingEntityException("CompaniaOracle " + companiaOracle + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CompaniaOracle companiaOracle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            companiaOracle = em.merge(companiaOracle);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = companiaOracle.getIdcompania();
                if (findCompaniaOracle(id) == null) {
                    throw new NonexistentEntityException("The companiaOracle with id " + id + " no longer exists.");
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
            CompaniaOracle companiaOracle;
            try {
                companiaOracle = em.getReference(CompaniaOracle.class, id);
                companiaOracle.getIdcompania();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The companiaOracle with id " + id + " no longer exists.", enfe);
            }
            em.remove(companiaOracle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CompaniaOracle> findCompaniaOracleEntities() {
        return findCompaniaOracleEntities(true, -1, -1);
    }

    public List<CompaniaOracle> findCompaniaOracleEntities(int maxResults, int firstResult) {
        return findCompaniaOracleEntities(false, maxResults, firstResult);
    }

    private List<CompaniaOracle> findCompaniaOracleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CompaniaOracle.class));
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

    public CompaniaOracle findCompaniaOracle(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CompaniaOracle.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompaniaOracleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CompaniaOracle> rt = cq.from(CompaniaOracle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
