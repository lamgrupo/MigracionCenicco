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
import com.lam.cenicco.migracion.entidades.oracle.CompaniaOracle;
import com.lam.cenicco.migracion.entidades.oracle.PuestoOracle;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class PuestoOracleJpaController implements Serializable {

    public PuestoOracleJpaController() {
        this.emf = JpaUtil.getInstance().createEntityManagerCeniccoOracle().getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PuestoOracle puestoOracle) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CompaniaOracle idcompania = puestoOracle.getIdcompania();
            if (idcompania != null) {
                idcompania = em.getReference(idcompania.getClass(), idcompania.getIdcompania());
                puestoOracle.setIdcompania(idcompania);
            }
            em.persist(puestoOracle);
            if (idcompania != null) {
                idcompania.getPuestoOracleList().add(puestoOracle);
                idcompania = em.merge(idcompania);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPuestoOracle(puestoOracle.getIdpuesto()) != null) {
                throw new PreexistingEntityException("PuestoOracle " + puestoOracle + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PuestoOracle puestoOracle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PuestoOracle persistentPuestoOracle = em.find(PuestoOracle.class, puestoOracle.getIdpuesto());
            CompaniaOracle idcompaniaOld = persistentPuestoOracle.getIdcompania();
            CompaniaOracle idcompaniaNew = puestoOracle.getIdcompania();
            if (idcompaniaNew != null) {
                idcompaniaNew = em.getReference(idcompaniaNew.getClass(), idcompaniaNew.getIdcompania());
                puestoOracle.setIdcompania(idcompaniaNew);
            }
            puestoOracle = em.merge(puestoOracle);
            if (idcompaniaOld != null && !idcompaniaOld.equals(idcompaniaNew)) {
                idcompaniaOld.getPuestoOracleList().remove(puestoOracle);
                idcompaniaOld = em.merge(idcompaniaOld);
            }
            if (idcompaniaNew != null && !idcompaniaNew.equals(idcompaniaOld)) {
                idcompaniaNew.getPuestoOracleList().add(puestoOracle);
                idcompaniaNew = em.merge(idcompaniaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = puestoOracle.getIdpuesto();
                if (findPuestoOracle(id) == null) {
                    throw new NonexistentEntityException("The puestoOracle with id " + id + " no longer exists.");
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
            PuestoOracle puestoOracle;
            try {
                puestoOracle = em.getReference(PuestoOracle.class, id);
                puestoOracle.getIdpuesto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The puestoOracle with id " + id + " no longer exists.", enfe);
            }
            CompaniaOracle idcompania = puestoOracle.getIdcompania();
            if (idcompania != null) {
                idcompania.getPuestoOracleList().remove(puestoOracle);
                idcompania = em.merge(idcompania);
            }
            em.remove(puestoOracle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PuestoOracle> findPuestoOracleEntities() {
        return findPuestoOracleEntities(true, -1, -1);
    }

    public List<PuestoOracle> findPuestoOracleEntities(int maxResults, int firstResult) {
        return findPuestoOracleEntities(false, maxResults, firstResult);
    }

    private List<PuestoOracle> findPuestoOracleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PuestoOracle.class));
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

    public PuestoOracle findPuestoOracle(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PuestoOracle.class, id);
        } finally {
            em.close();
        }
    }

    public int getPuestoOracleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PuestoOracle> rt = cq.from(PuestoOracle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
