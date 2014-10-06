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
import com.lam.cenicco.migracion.entidades.oracle.EstadoOracle;
import com.lam.cenicco.migracion.entidades.oracle.MunicipioOracle;
import com.lam.cenicco.migracion.entidades.oracle.MunicipioOraclePK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class MunicipioOracleJpaController implements Serializable {

    public MunicipioOracleJpaController() {
        this.emf = JpaUtil.getInstance().createEntityManagerCeniccoOracle().getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MunicipioOracle municipioOracle) throws PreexistingEntityException, Exception {
        if (municipioOracle.getMunicipioOraclePK() == null) {
            municipioOracle.setMunicipioOraclePK(new MunicipioOraclePK());
        }
        municipioOracle.getMunicipioOraclePK().setIdpais(municipioOracle.getEstadoOracle().getEstadoOraclePK().getIdpais());
        municipioOracle.getMunicipioOraclePK().setIdestado(municipioOracle.getEstadoOracle().getEstadoOraclePK().getIdestado());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EstadoOracle estadoOracle = municipioOracle.getEstadoOracle();
            if (estadoOracle != null) {
                estadoOracle = em.getReference(estadoOracle.getClass(), estadoOracle.getEstadoOraclePK());
                municipioOracle.setEstadoOracle(estadoOracle);
            }
            em.persist(municipioOracle);
            if (estadoOracle != null) {
                estadoOracle.getMunicipioOracleList().add(municipioOracle);
                estadoOracle = em.merge(estadoOracle);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMunicipioOracle(municipioOracle.getMunicipioOraclePK()) != null) {
                throw new PreexistingEntityException("MunicipioOracle " + municipioOracle + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MunicipioOracle municipioOracle) throws NonexistentEntityException, Exception {
        municipioOracle.getMunicipioOraclePK().setIdpais(municipioOracle.getEstadoOracle().getEstadoOraclePK().getIdpais());
        municipioOracle.getMunicipioOraclePK().setIdestado(municipioOracle.getEstadoOracle().getEstadoOraclePK().getIdestado());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MunicipioOracle persistentMunicipioOracle = em.find(MunicipioOracle.class, municipioOracle.getMunicipioOraclePK());
            EstadoOracle estadoOracleOld = persistentMunicipioOracle.getEstadoOracle();
            EstadoOracle estadoOracleNew = municipioOracle.getEstadoOracle();
            if (estadoOracleNew != null) {
                estadoOracleNew = em.getReference(estadoOracleNew.getClass(), estadoOracleNew.getEstadoOraclePK());
                municipioOracle.setEstadoOracle(estadoOracleNew);
            }
            municipioOracle = em.merge(municipioOracle);
            if (estadoOracleOld != null && !estadoOracleOld.equals(estadoOracleNew)) {
                estadoOracleOld.getMunicipioOracleList().remove(municipioOracle);
                estadoOracleOld = em.merge(estadoOracleOld);
            }
            if (estadoOracleNew != null && !estadoOracleNew.equals(estadoOracleOld)) {
                estadoOracleNew.getMunicipioOracleList().add(municipioOracle);
                estadoOracleNew = em.merge(estadoOracleNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                MunicipioOraclePK id = municipioOracle.getMunicipioOraclePK();
                if (findMunicipioOracle(id) == null) {
                    throw new NonexistentEntityException("The municipioOracle with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(MunicipioOraclePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MunicipioOracle municipioOracle;
            try {
                municipioOracle = em.getReference(MunicipioOracle.class, id);
                municipioOracle.getMunicipioOraclePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The municipioOracle with id " + id + " no longer exists.", enfe);
            }
            EstadoOracle estadoOracle = municipioOracle.getEstadoOracle();
            if (estadoOracle != null) {
                estadoOracle.getMunicipioOracleList().remove(municipioOracle);
                estadoOracle = em.merge(estadoOracle);
            }
            em.remove(municipioOracle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MunicipioOracle> findMunicipioOracleEntities() {
        return findMunicipioOracleEntities(true, -1, -1);
    }

    public List<MunicipioOracle> findMunicipioOracleEntities(int maxResults, int firstResult) {
        return findMunicipioOracleEntities(false, maxResults, firstResult);
    }

    private List<MunicipioOracle> findMunicipioOracleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MunicipioOracle.class));
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

    public MunicipioOracle findMunicipioOracle(MunicipioOraclePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MunicipioOracle.class, id);
        } finally {
            em.close();
        }
    }

    public int getMunicipioOracleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MunicipioOracle> rt = cq.from(MunicipioOracle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
