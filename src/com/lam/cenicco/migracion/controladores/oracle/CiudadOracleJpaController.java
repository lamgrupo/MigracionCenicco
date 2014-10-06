/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.controladores.oracle;

import com.cenicco.migracion.util.JpaUtil;
import com.lam.cenicco.migracion.controladores.oracle.exceptions.NonexistentEntityException;
import com.lam.cenicco.migracion.controladores.oracle.exceptions.PreexistingEntityException;
import com.lam.cenicco.migracion.entidades.oracle.CiudadOracle;
import com.lam.cenicco.migracion.entidades.oracle.CiudadOraclePK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.lam.cenicco.migracion.entidades.oracle.MunicipioOracle;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class CiudadOracleJpaController implements Serializable {

    public CiudadOracleJpaController() {
        this.emf = JpaUtil.getInstance().createEntityManagerCeniccoOracle().getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CiudadOracle ciudadOracle) throws PreexistingEntityException, Exception {
        if (ciudadOracle.getCiudadOraclePK() == null) {
            ciudadOracle.setCiudadOraclePK(new CiudadOraclePK());
        }
        ciudadOracle.getCiudadOraclePK().setIdestado(ciudadOracle.getMunicipioOracle().getMunicipioOraclePK().getIdestado());
        ciudadOracle.getCiudadOraclePK().setIdpais(ciudadOracle.getMunicipioOracle().getMunicipioOraclePK().getIdpais());
        ciudadOracle.getCiudadOraclePK().setIdmunicipio(ciudadOracle.getMunicipioOracle().getMunicipioOraclePK().getIdmunicipio());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MunicipioOracle municipioOracle = ciudadOracle.getMunicipioOracle();
            if (municipioOracle != null) {
                municipioOracle = em.getReference(municipioOracle.getClass(), municipioOracle.getMunicipioOraclePK());
                ciudadOracle.setMunicipioOracle(municipioOracle);
            }
            em.persist(ciudadOracle);
            if (municipioOracle != null) {
                municipioOracle.getCiudadOracleList().add(ciudadOracle);
                municipioOracle = em.merge(municipioOracle);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCiudadOracle(ciudadOracle.getCiudadOraclePK()) != null) {
                throw new PreexistingEntityException("CiudadOracle " + ciudadOracle + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CiudadOracle ciudadOracle) throws NonexistentEntityException, Exception {
        ciudadOracle.getCiudadOraclePK().setIdestado(ciudadOracle.getMunicipioOracle().getMunicipioOraclePK().getIdestado());
        ciudadOracle.getCiudadOraclePK().setIdpais(ciudadOracle.getMunicipioOracle().getMunicipioOraclePK().getIdpais());
        ciudadOracle.getCiudadOraclePK().setIdmunicipio(ciudadOracle.getMunicipioOracle().getMunicipioOraclePK().getIdmunicipio());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CiudadOracle persistentCiudadOracle = em.find(CiudadOracle.class, ciudadOracle.getCiudadOraclePK());
            MunicipioOracle municipioOracleOld = persistentCiudadOracle.getMunicipioOracle();
            MunicipioOracle municipioOracleNew = ciudadOracle.getMunicipioOracle();
            if (municipioOracleNew != null) {
                municipioOracleNew = em.getReference(municipioOracleNew.getClass(), municipioOracleNew.getMunicipioOraclePK());
                ciudadOracle.setMunicipioOracle(municipioOracleNew);
            }
            ciudadOracle = em.merge(ciudadOracle);
            if (municipioOracleOld != null && !municipioOracleOld.equals(municipioOracleNew)) {
                municipioOracleOld.getCiudadOracleList().remove(ciudadOracle);
                municipioOracleOld = em.merge(municipioOracleOld);
            }
            if (municipioOracleNew != null && !municipioOracleNew.equals(municipioOracleOld)) {
                municipioOracleNew.getCiudadOracleList().add(ciudadOracle);
                municipioOracleNew = em.merge(municipioOracleNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CiudadOraclePK id = ciudadOracle.getCiudadOraclePK();
                if (findCiudadOracle(id) == null) {
                    throw new NonexistentEntityException("The ciudadOracle with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CiudadOraclePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CiudadOracle ciudadOracle;
            try {
                ciudadOracle = em.getReference(CiudadOracle.class, id);
                ciudadOracle.getCiudadOraclePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ciudadOracle with id " + id + " no longer exists.", enfe);
            }
            MunicipioOracle municipioOracle = ciudadOracle.getMunicipioOracle();
            if (municipioOracle != null) {
                municipioOracle.getCiudadOracleList().remove(ciudadOracle);
                municipioOracle = em.merge(municipioOracle);
            }
            em.remove(ciudadOracle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CiudadOracle> findCiudadOracleEntities() {
        return findCiudadOracleEntities(true, -1, -1);
    }

    public List<CiudadOracle> findCiudadOracleEntities(int maxResults, int firstResult) {
        return findCiudadOracleEntities(false, maxResults, firstResult);
    }

    private List<CiudadOracle> findCiudadOracleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CiudadOracle.class));
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

    public CiudadOracle findCiudadOracle(CiudadOraclePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CiudadOracle.class, id);
        } finally {
            em.close();
        }
    }

    public int getCiudadOracleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CiudadOracle> rt = cq.from(CiudadOracle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
