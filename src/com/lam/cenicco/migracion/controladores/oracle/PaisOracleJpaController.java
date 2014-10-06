/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.controladores.oracle;

import com.cenicco.migracion.util.JpaUtil;
import com.lam.cenicco.migracion.controladores.oracle.exceptions.IllegalOrphanException;
import com.lam.cenicco.migracion.controladores.oracle.exceptions.NonexistentEntityException;
import com.lam.cenicco.migracion.controladores.oracle.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.lam.cenicco.migracion.entidades.oracle.EstadoOracle;
import com.lam.cenicco.migracion.entidades.oracle.PaisOracle;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class PaisOracleJpaController implements Serializable {

    public PaisOracleJpaController() {
        this.emf = JpaUtil.getInstance().createEntityManagerCeniccoOracle().getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PaisOracle paisOracle) throws PreexistingEntityException, Exception {
        if (paisOracle.getEstadoOracleList() == null) {
            paisOracle.setEstadoOracleList(new ArrayList<EstadoOracle>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<EstadoOracle> attachedEstadoOracleList = new ArrayList<EstadoOracle>();
            for (EstadoOracle estadoOracleListEstadoOracleToAttach : paisOracle.getEstadoOracleList()) {
                estadoOracleListEstadoOracleToAttach = em.getReference(estadoOracleListEstadoOracleToAttach.getClass(), estadoOracleListEstadoOracleToAttach.getEstadoOraclePK());
                attachedEstadoOracleList.add(estadoOracleListEstadoOracleToAttach);
            }
            paisOracle.setEstadoOracleList(attachedEstadoOracleList);
            em.persist(paisOracle);
            for (EstadoOracle estadoOracleListEstadoOracle : paisOracle.getEstadoOracleList()) {
                PaisOracle oldPaisOracleOfEstadoOracleListEstadoOracle = estadoOracleListEstadoOracle.getPaisOracle();
                estadoOracleListEstadoOracle.setPaisOracle(paisOracle);
                estadoOracleListEstadoOracle = em.merge(estadoOracleListEstadoOracle);
                if (oldPaisOracleOfEstadoOracleListEstadoOracle != null) {
                    oldPaisOracleOfEstadoOracleListEstadoOracle.getEstadoOracleList().remove(estadoOracleListEstadoOracle);
                    oldPaisOracleOfEstadoOracleListEstadoOracle = em.merge(oldPaisOracleOfEstadoOracleListEstadoOracle);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPaisOracle(paisOracle.getIdpais()) != null) {
                throw new PreexistingEntityException("PaisOracle " + paisOracle + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PaisOracle paisOracle) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PaisOracle persistentPaisOracle = em.find(PaisOracle.class, paisOracle.getIdpais());
            List<EstadoOracle> estadoOracleListOld = persistentPaisOracle.getEstadoOracleList();
            List<EstadoOracle> estadoOracleListNew = paisOracle.getEstadoOracleList();
            List<String> illegalOrphanMessages = null;
            for (EstadoOracle estadoOracleListOldEstadoOracle : estadoOracleListOld) {
                if (!estadoOracleListNew.contains(estadoOracleListOldEstadoOracle)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EstadoOracle " + estadoOracleListOldEstadoOracle + " since its paisOracle field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<EstadoOracle> attachedEstadoOracleListNew = new ArrayList<EstadoOracle>();
            for (EstadoOracle estadoOracleListNewEstadoOracleToAttach : estadoOracleListNew) {
                estadoOracleListNewEstadoOracleToAttach = em.getReference(estadoOracleListNewEstadoOracleToAttach.getClass(), estadoOracleListNewEstadoOracleToAttach.getEstadoOraclePK());
                attachedEstadoOracleListNew.add(estadoOracleListNewEstadoOracleToAttach);
            }
            estadoOracleListNew = attachedEstadoOracleListNew;
            paisOracle.setEstadoOracleList(estadoOracleListNew);
            paisOracle = em.merge(paisOracle);
            for (EstadoOracle estadoOracleListNewEstadoOracle : estadoOracleListNew) {
                if (!estadoOracleListOld.contains(estadoOracleListNewEstadoOracle)) {
                    PaisOracle oldPaisOracleOfEstadoOracleListNewEstadoOracle = estadoOracleListNewEstadoOracle.getPaisOracle();
                    estadoOracleListNewEstadoOracle.setPaisOracle(paisOracle);
                    estadoOracleListNewEstadoOracle = em.merge(estadoOracleListNewEstadoOracle);
                    if (oldPaisOracleOfEstadoOracleListNewEstadoOracle != null && !oldPaisOracleOfEstadoOracleListNewEstadoOracle.equals(paisOracle)) {
                        oldPaisOracleOfEstadoOracleListNewEstadoOracle.getEstadoOracleList().remove(estadoOracleListNewEstadoOracle);
                        oldPaisOracleOfEstadoOracleListNewEstadoOracle = em.merge(oldPaisOracleOfEstadoOracleListNewEstadoOracle);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = paisOracle.getIdpais();
                if (findPaisOracle(id) == null) {
                    throw new NonexistentEntityException("The paisOracle with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PaisOracle paisOracle;
            try {
                paisOracle = em.getReference(PaisOracle.class, id);
                paisOracle.getIdpais();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paisOracle with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<EstadoOracle> estadoOracleListOrphanCheck = paisOracle.getEstadoOracleList();
            for (EstadoOracle estadoOracleListOrphanCheckEstadoOracle : estadoOracleListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PaisOracle (" + paisOracle + ") cannot be destroyed since the EstadoOracle " + estadoOracleListOrphanCheckEstadoOracle + " in its estadoOracleList field has a non-nullable paisOracle field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(paisOracle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PaisOracle> findPaisOracleEntities() {
        return findPaisOracleEntities(true, -1, -1);
    }

    public List<PaisOracle> findPaisOracleEntities(int maxResults, int firstResult) {
        return findPaisOracleEntities(false, maxResults, firstResult);
    }

    private List<PaisOracle> findPaisOracleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PaisOracle.class));
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

    public PaisOracle findPaisOracle(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PaisOracle.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaisOracleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PaisOracle> rt = cq.from(PaisOracle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
