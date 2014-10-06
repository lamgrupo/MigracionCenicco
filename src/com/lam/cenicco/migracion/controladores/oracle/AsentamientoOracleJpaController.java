/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.controladores.oracle;

import com.cenicco.migracion.util.JpaUtil;
import com.lam.cenicco.migracion.controladores.oracle.exceptions.NonexistentEntityException;
import com.lam.cenicco.migracion.controladores.oracle.exceptions.PreexistingEntityException;
import com.lam.cenicco.migracion.entidades.oracle.AsentamientoOracle;
import com.lam.cenicco.migracion.entidades.oracle.AsentamientoOraclePK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.lam.cenicco.migracion.entidades.oracle.CiudadOracle;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class AsentamientoOracleJpaController implements Serializable {

    public AsentamientoOracleJpaController() {
        this.emf = JpaUtil.getInstance().createEntityManagerCeniccoOracle().getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsentamientoOracle asentamientoOracle) throws PreexistingEntityException, Exception {
        if (asentamientoOracle.getAsentamientoOraclePK() == null) {
            asentamientoOracle.setAsentamientoOraclePK(new AsentamientoOraclePK());
        }
        asentamientoOracle.getAsentamientoOraclePK().setIdestado(asentamientoOracle.getCiudadOracle().getCiudadOraclePK().getIdestado());
        asentamientoOracle.getAsentamientoOraclePK().setIdmunicipio(asentamientoOracle.getCiudadOracle().getCiudadOraclePK().getIdmunicipio());
        asentamientoOracle.getAsentamientoOraclePK().setIdpais(asentamientoOracle.getCiudadOracle().getCiudadOraclePK().getIdpais());
        asentamientoOracle.getAsentamientoOraclePK().setIdciudad(asentamientoOracle.getCiudadOracle().getCiudadOraclePK().getIdciudad());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CiudadOracle ciudadOracle = asentamientoOracle.getCiudadOracle();
            if (ciudadOracle != null) {
                ciudadOracle = em.getReference(ciudadOracle.getClass(), ciudadOracle.getCiudadOraclePK());
                asentamientoOracle.setCiudadOracle(ciudadOracle);
            }
            em.persist(asentamientoOracle);
            if (ciudadOracle != null) {
                ciudadOracle.getAsentamientoOracleList().add(asentamientoOracle);
                ciudadOracle = em.merge(ciudadOracle);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAsentamientoOracle(asentamientoOracle.getAsentamientoOraclePK()) != null) {
                throw new PreexistingEntityException("AsentamientoOracle " + asentamientoOracle + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsentamientoOracle asentamientoOracle) throws NonexistentEntityException, Exception {
        asentamientoOracle.getAsentamientoOraclePK().setIdestado(asentamientoOracle.getCiudadOracle().getCiudadOraclePK().getIdestado());
        asentamientoOracle.getAsentamientoOraclePK().setIdmunicipio(asentamientoOracle.getCiudadOracle().getCiudadOraclePK().getIdmunicipio());
        asentamientoOracle.getAsentamientoOraclePK().setIdpais(asentamientoOracle.getCiudadOracle().getCiudadOraclePK().getIdpais());
        asentamientoOracle.getAsentamientoOraclePK().setIdciudad(asentamientoOracle.getCiudadOracle().getCiudadOraclePK().getIdciudad());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsentamientoOracle persistentAsentamientoOracle = em.find(AsentamientoOracle.class, asentamientoOracle.getAsentamientoOraclePK());
            CiudadOracle ciudadOracleOld = persistentAsentamientoOracle.getCiudadOracle();
            CiudadOracle ciudadOracleNew = asentamientoOracle.getCiudadOracle();
            if (ciudadOracleNew != null) {
                ciudadOracleNew = em.getReference(ciudadOracleNew.getClass(), ciudadOracleNew.getCiudadOraclePK());
                asentamientoOracle.setCiudadOracle(ciudadOracleNew);
            }
            asentamientoOracle = em.merge(asentamientoOracle);
            if (ciudadOracleOld != null && !ciudadOracleOld.equals(ciudadOracleNew)) {
                ciudadOracleOld.getAsentamientoOracleList().remove(asentamientoOracle);
                ciudadOracleOld = em.merge(ciudadOracleOld);
            }
            if (ciudadOracleNew != null && !ciudadOracleNew.equals(ciudadOracleOld)) {
                ciudadOracleNew.getAsentamientoOracleList().add(asentamientoOracle);
                ciudadOracleNew = em.merge(ciudadOracleNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AsentamientoOraclePK id = asentamientoOracle.getAsentamientoOraclePK();
                if (findAsentamientoOracle(id) == null) {
                    throw new NonexistentEntityException("The asentamientoOracle with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AsentamientoOraclePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsentamientoOracle asentamientoOracle;
            try {
                asentamientoOracle = em.getReference(AsentamientoOracle.class, id);
                asentamientoOracle.getAsentamientoOraclePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asentamientoOracle with id " + id + " no longer exists.", enfe);
            }
            CiudadOracle ciudadOracle = asentamientoOracle.getCiudadOracle();
            if (ciudadOracle != null) {
                ciudadOracle.getAsentamientoOracleList().remove(asentamientoOracle);
                ciudadOracle = em.merge(ciudadOracle);
            }
            em.remove(asentamientoOracle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsentamientoOracle> findAsentamientoOracleEntities() {
        return findAsentamientoOracleEntities(true, -1, -1);
    }

    public List<AsentamientoOracle> findAsentamientoOracleEntities(int maxResults, int firstResult) {
        return findAsentamientoOracleEntities(false, maxResults, firstResult);
    }

    private List<AsentamientoOracle> findAsentamientoOracleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AsentamientoOracle.class));
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

    public AsentamientoOracle findAsentamientoOracle(AsentamientoOraclePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsentamientoOracle.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsentamientoOracleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AsentamientoOracle> rt = cq.from(AsentamientoOracle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
