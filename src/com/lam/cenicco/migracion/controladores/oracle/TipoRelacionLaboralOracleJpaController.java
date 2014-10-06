/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.controladores.oracle;

import com.lam.cenicco.migracion.controladores.oracle.exceptions.IllegalOrphanException;
import com.lam.cenicco.migracion.controladores.oracle.exceptions.NonexistentEntityException;
import com.lam.cenicco.migracion.controladores.oracle.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.lam.cenicco.migracion.entidades.oracle.RelacionLaboralOracle;
import com.lam.cenicco.migracion.entidades.oracle.TipoRelacionLaboralOracle;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class TipoRelacionLaboralOracleJpaController implements Serializable {

    public TipoRelacionLaboralOracleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoRelacionLaboralOracle tipoRelacionLaboralOracle) throws PreexistingEntityException, Exception {
        if (tipoRelacionLaboralOracle.getRelacionLaboralOracleList() == null) {
            tipoRelacionLaboralOracle.setRelacionLaboralOracleList(new ArrayList<RelacionLaboralOracle>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<RelacionLaboralOracle> attachedRelacionLaboralOracleList = new ArrayList<RelacionLaboralOracle>();
            for (RelacionLaboralOracle relacionLaboralOracleListRelacionLaboralOracleToAttach : tipoRelacionLaboralOracle.getRelacionLaboralOracleList()) {
                relacionLaboralOracleListRelacionLaboralOracleToAttach = em.getReference(relacionLaboralOracleListRelacionLaboralOracleToAttach.getClass(), relacionLaboralOracleListRelacionLaboralOracleToAttach.getIdrellab());
                attachedRelacionLaboralOracleList.add(relacionLaboralOracleListRelacionLaboralOracleToAttach);
            }
            tipoRelacionLaboralOracle.setRelacionLaboralOracleList(attachedRelacionLaboralOracleList);
            em.persist(tipoRelacionLaboralOracle);
            for (RelacionLaboralOracle relacionLaboralOracleListRelacionLaboralOracle : tipoRelacionLaboralOracle.getRelacionLaboralOracleList()) {
                TipoRelacionLaboralOracle oldTiporellabOfRelacionLaboralOracleListRelacionLaboralOracle = relacionLaboralOracleListRelacionLaboralOracle.getTiporellab();
                relacionLaboralOracleListRelacionLaboralOracle.setTiporellab(tipoRelacionLaboralOracle);
                relacionLaboralOracleListRelacionLaboralOracle = em.merge(relacionLaboralOracleListRelacionLaboralOracle);
                if (oldTiporellabOfRelacionLaboralOracleListRelacionLaboralOracle != null) {
                    oldTiporellabOfRelacionLaboralOracleListRelacionLaboralOracle.getRelacionLaboralOracleList().remove(relacionLaboralOracleListRelacionLaboralOracle);
                    oldTiporellabOfRelacionLaboralOracleListRelacionLaboralOracle = em.merge(oldTiporellabOfRelacionLaboralOracleListRelacionLaboralOracle);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipoRelacionLaboralOracle(tipoRelacionLaboralOracle.getTiporellab()) != null) {
                throw new PreexistingEntityException("TipoRelacionLaboralOracle " + tipoRelacionLaboralOracle + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoRelacionLaboralOracle tipoRelacionLaboralOracle) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoRelacionLaboralOracle persistentTipoRelacionLaboralOracle = em.find(TipoRelacionLaboralOracle.class, tipoRelacionLaboralOracle.getTiporellab());
            List<RelacionLaboralOracle> relacionLaboralOracleListOld = persistentTipoRelacionLaboralOracle.getRelacionLaboralOracleList();
            List<RelacionLaboralOracle> relacionLaboralOracleListNew = tipoRelacionLaboralOracle.getRelacionLaboralOracleList();
            List<String> illegalOrphanMessages = null;
            for (RelacionLaboralOracle relacionLaboralOracleListOldRelacionLaboralOracle : relacionLaboralOracleListOld) {
                if (!relacionLaboralOracleListNew.contains(relacionLaboralOracleListOldRelacionLaboralOracle)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RelacionLaboralOracle " + relacionLaboralOracleListOldRelacionLaboralOracle + " since its tiporellab field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<RelacionLaboralOracle> attachedRelacionLaboralOracleListNew = new ArrayList<RelacionLaboralOracle>();
            for (RelacionLaboralOracle relacionLaboralOracleListNewRelacionLaboralOracleToAttach : relacionLaboralOracleListNew) {
                relacionLaboralOracleListNewRelacionLaboralOracleToAttach = em.getReference(relacionLaboralOracleListNewRelacionLaboralOracleToAttach.getClass(), relacionLaboralOracleListNewRelacionLaboralOracleToAttach.getIdrellab());
                attachedRelacionLaboralOracleListNew.add(relacionLaboralOracleListNewRelacionLaboralOracleToAttach);
            }
            relacionLaboralOracleListNew = attachedRelacionLaboralOracleListNew;
            tipoRelacionLaboralOracle.setRelacionLaboralOracleList(relacionLaboralOracleListNew);
            tipoRelacionLaboralOracle = em.merge(tipoRelacionLaboralOracle);
            for (RelacionLaboralOracle relacionLaboralOracleListNewRelacionLaboralOracle : relacionLaboralOracleListNew) {
                if (!relacionLaboralOracleListOld.contains(relacionLaboralOracleListNewRelacionLaboralOracle)) {
                    TipoRelacionLaboralOracle oldTiporellabOfRelacionLaboralOracleListNewRelacionLaboralOracle = relacionLaboralOracleListNewRelacionLaboralOracle.getTiporellab();
                    relacionLaboralOracleListNewRelacionLaboralOracle.setTiporellab(tipoRelacionLaboralOracle);
                    relacionLaboralOracleListNewRelacionLaboralOracle = em.merge(relacionLaboralOracleListNewRelacionLaboralOracle);
                    if (oldTiporellabOfRelacionLaboralOracleListNewRelacionLaboralOracle != null && !oldTiporellabOfRelacionLaboralOracleListNewRelacionLaboralOracle.equals(tipoRelacionLaboralOracle)) {
                        oldTiporellabOfRelacionLaboralOracleListNewRelacionLaboralOracle.getRelacionLaboralOracleList().remove(relacionLaboralOracleListNewRelacionLaboralOracle);
                        oldTiporellabOfRelacionLaboralOracleListNewRelacionLaboralOracle = em.merge(oldTiporellabOfRelacionLaboralOracleListNewRelacionLaboralOracle);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tipoRelacionLaboralOracle.getTiporellab();
                if (findTipoRelacionLaboralOracle(id) == null) {
                    throw new NonexistentEntityException("The tipoRelacionLaboralOracle with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoRelacionLaboralOracle tipoRelacionLaboralOracle;
            try {
                tipoRelacionLaboralOracle = em.getReference(TipoRelacionLaboralOracle.class, id);
                tipoRelacionLaboralOracle.getTiporellab();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoRelacionLaboralOracle with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<RelacionLaboralOracle> relacionLaboralOracleListOrphanCheck = tipoRelacionLaboralOracle.getRelacionLaboralOracleList();
            for (RelacionLaboralOracle relacionLaboralOracleListOrphanCheckRelacionLaboralOracle : relacionLaboralOracleListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TipoRelacionLaboralOracle (" + tipoRelacionLaboralOracle + ") cannot be destroyed since the RelacionLaboralOracle " + relacionLaboralOracleListOrphanCheckRelacionLaboralOracle + " in its relacionLaboralOracleList field has a non-nullable tiporellab field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tipoRelacionLaboralOracle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoRelacionLaboralOracle> findTipoRelacionLaboralOracleEntities() {
        return findTipoRelacionLaboralOracleEntities(true, -1, -1);
    }

    public List<TipoRelacionLaboralOracle> findTipoRelacionLaboralOracleEntities(int maxResults, int firstResult) {
        return findTipoRelacionLaboralOracleEntities(false, maxResults, firstResult);
    }

    private List<TipoRelacionLaboralOracle> findTipoRelacionLaboralOracleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoRelacionLaboralOracle.class));
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

    public TipoRelacionLaboralOracle findTipoRelacionLaboralOracle(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoRelacionLaboralOracle.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoRelacionLaboralOracleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoRelacionLaboralOracle> rt = cq.from(TipoRelacionLaboralOracle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
