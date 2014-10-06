/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.controladores.oracle;

import com.cenicco.migracion.util.JpaUtil;
import com.lam.cenicco.migracion.controladores.oracle.exceptions.NonexistentEntityException;
import com.lam.cenicco.migracion.controladores.oracle.exceptions.PreexistingEntityException;
import com.lam.cenicco.migracion.entidades.oracle.CatalogoGeneralOracle;
import com.lam.cenicco.migracion.entidades.oracle.CatalogoGeneralOraclePK;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class CatalogoGeneralOracleJpaController implements Serializable {

    public CatalogoGeneralOracleJpaController() {
        this.emf = JpaUtil.getInstance().createEntityManagerCeniccoOracle().getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CatalogoGeneralOracle catalogoGeneralOracle) throws PreexistingEntityException, Exception {
        if (catalogoGeneralOracle.getCatalogoGeneralOraclePK() == null) {
            catalogoGeneralOracle.setCatalogoGeneralOraclePK(new CatalogoGeneralOraclePK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(catalogoGeneralOracle);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCatalogoGeneralOracle(catalogoGeneralOracle.getCatalogoGeneralOraclePK()) != null) {
                throw new PreexistingEntityException("CatalogoGeneralOracle " + catalogoGeneralOracle + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CatalogoGeneralOracle catalogoGeneralOracle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            catalogoGeneralOracle = em.merge(catalogoGeneralOracle);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                CatalogoGeneralOraclePK id = catalogoGeneralOracle.getCatalogoGeneralOraclePK();
                if (findCatalogoGeneralOracle(id) == null) {
                    throw new NonexistentEntityException("The catalogoGeneralOracle with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CatalogoGeneralOraclePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CatalogoGeneralOracle catalogoGeneralOracle;
            try {
                catalogoGeneralOracle = em.getReference(CatalogoGeneralOracle.class, id);
                catalogoGeneralOracle.getCatalogoGeneralOraclePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The catalogoGeneralOracle with id " + id + " no longer exists.", enfe);
            }
            em.remove(catalogoGeneralOracle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CatalogoGeneralOracle> findCatalogoGeneralOracleEntities() {
        return findCatalogoGeneralOracleEntities(true, -1, -1);
    }

    public List<CatalogoGeneralOracle> findCatalogoGeneralOracleEntities(int maxResults, int firstResult) {
        return findCatalogoGeneralOracleEntities(false, maxResults, firstResult);
    }

    private List<CatalogoGeneralOracle> findCatalogoGeneralOracleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CatalogoGeneralOracle.class));
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

    public CatalogoGeneralOracle findCatalogoGeneralOracle(CatalogoGeneralOraclePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CatalogoGeneralOracle.class, id);
        } finally {
            em.close();
        }
    }

    public int getCatalogoGeneralOracleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CatalogoGeneralOracle> rt = cq.from(CatalogoGeneralOracle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<CatalogoGeneralOracle> findByTipoAsentamientos() {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Query q = em.createNamedQuery("CatalogoGeneralOracle.findByTabla");
            q.setParameter("tabla", "CATASENTAMIENTOSTB");
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JpaUtil.getInstance().closeEntityManager(em);
        }
    }

    public Map<String, String> findByAsentamientoNombre() {
        EntityManager em = null;
        Map<String, String> mapa = new HashMap<>();
        List<CatalogoGeneralOracle> catalogo;
        try {
            em = getEntityManager();
            Query q = em.createNamedQuery("CatalogoGeneralOracle.findByNombre");
            q.setParameter("tabla", "CATASENTAMIENTOSTB");
            q.setParameter("columna", "IDTIPOASENTAMIENTO");
            catalogo = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JpaUtil.getInstance().closeEntityManager(em);
        }
        for (CatalogoGeneralOracle c : catalogo) {
            mapa.put(c.getCatalogoGeneralOraclePK().getValor(), c.getNombre());
        }
        return mapa;
    }
}
