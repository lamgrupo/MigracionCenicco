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
import com.lam.cenicco.migracion.entidades.oracle.CompaniaOracle;
import com.lam.cenicco.migracion.entidades.oracle.RegistroPatronalOracle;
import com.lam.cenicco.migracion.entidades.oracle.RegistroPatronalOraclePK;
import com.lam.cenicco.migracion.entidades.oracle.RelacionLaboralOracle;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class RegistroPatronalOracleJpaController implements Serializable {

    public RegistroPatronalOracleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RegistroPatronalOracle registroPatronalOracle) throws PreexistingEntityException, Exception {
        if (registroPatronalOracle.getRegistroPatronalOraclePK() == null) {
            registroPatronalOracle.setRegistroPatronalOraclePK(new RegistroPatronalOraclePK());
        }
        if (registroPatronalOracle.getRelacionLaboralOracleList() == null) {
            registroPatronalOracle.setRelacionLaboralOracleList(new ArrayList<RelacionLaboralOracle>());
        }
        registroPatronalOracle.getRegistroPatronalOraclePK().setIdcompania(registroPatronalOracle.getCompaniaOracle().getIdcompania());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CompaniaOracle companiaOracle = registroPatronalOracle.getCompaniaOracle();
            if (companiaOracle != null) {
                companiaOracle = em.getReference(companiaOracle.getClass(), companiaOracle.getIdcompania());
                registroPatronalOracle.setCompaniaOracle(companiaOracle);
            }
            List<RelacionLaboralOracle> attachedRelacionLaboralOracleList = new ArrayList<RelacionLaboralOracle>();
            for (RelacionLaboralOracle relacionLaboralOracleListRelacionLaboralOracleToAttach : registroPatronalOracle.getRelacionLaboralOracleList()) {
                relacionLaboralOracleListRelacionLaboralOracleToAttach = em.getReference(relacionLaboralOracleListRelacionLaboralOracleToAttach.getClass(), relacionLaboralOracleListRelacionLaboralOracleToAttach.getIdrellab());
                attachedRelacionLaboralOracleList.add(relacionLaboralOracleListRelacionLaboralOracleToAttach);
            }
            registroPatronalOracle.setRelacionLaboralOracleList(attachedRelacionLaboralOracleList);
            em.persist(registroPatronalOracle);
            if (companiaOracle != null) {
                companiaOracle.getRegistroPatronalOracleList().add(registroPatronalOracle);
                companiaOracle = em.merge(companiaOracle);
            }
            for (RelacionLaboralOracle relacionLaboralOracleListRelacionLaboralOracle : registroPatronalOracle.getRelacionLaboralOracleList()) {
                RegistroPatronalOracle oldRegistroPatronalOracleOfRelacionLaboralOracleListRelacionLaboralOracle = relacionLaboralOracleListRelacionLaboralOracle.getRegistroPatronalOracle();
                relacionLaboralOracleListRelacionLaboralOracle.setRegistroPatronalOracle(registroPatronalOracle);
                relacionLaboralOracleListRelacionLaboralOracle = em.merge(relacionLaboralOracleListRelacionLaboralOracle);
                if (oldRegistroPatronalOracleOfRelacionLaboralOracleListRelacionLaboralOracle != null) {
                    oldRegistroPatronalOracleOfRelacionLaboralOracleListRelacionLaboralOracle.getRelacionLaboralOracleList().remove(relacionLaboralOracleListRelacionLaboralOracle);
                    oldRegistroPatronalOracleOfRelacionLaboralOracleListRelacionLaboralOracle = em.merge(oldRegistroPatronalOracleOfRelacionLaboralOracleListRelacionLaboralOracle);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRegistroPatronalOracle(registroPatronalOracle.getRegistroPatronalOraclePK()) != null) {
                throw new PreexistingEntityException("RegistroPatronalOracle " + registroPatronalOracle + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RegistroPatronalOracle registroPatronalOracle) throws IllegalOrphanException, NonexistentEntityException, Exception {
        registroPatronalOracle.getRegistroPatronalOraclePK().setIdcompania(registroPatronalOracle.getCompaniaOracle().getIdcompania());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RegistroPatronalOracle persistentRegistroPatronalOracle = em.find(RegistroPatronalOracle.class, registroPatronalOracle.getRegistroPatronalOraclePK());
            CompaniaOracle companiaOracleOld = persistentRegistroPatronalOracle.getCompaniaOracle();
            CompaniaOracle companiaOracleNew = registroPatronalOracle.getCompaniaOracle();
            List<RelacionLaboralOracle> relacionLaboralOracleListOld = persistentRegistroPatronalOracle.getRelacionLaboralOracleList();
            List<RelacionLaboralOracle> relacionLaboralOracleListNew = registroPatronalOracle.getRelacionLaboralOracleList();
            List<String> illegalOrphanMessages = null;
            for (RelacionLaboralOracle relacionLaboralOracleListOldRelacionLaboralOracle : relacionLaboralOracleListOld) {
                if (!relacionLaboralOracleListNew.contains(relacionLaboralOracleListOldRelacionLaboralOracle)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RelacionLaboralOracle " + relacionLaboralOracleListOldRelacionLaboralOracle + " since its registroPatronalOracle field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companiaOracleNew != null) {
                companiaOracleNew = em.getReference(companiaOracleNew.getClass(), companiaOracleNew.getIdcompania());
                registroPatronalOracle.setCompaniaOracle(companiaOracleNew);
            }
            List<RelacionLaboralOracle> attachedRelacionLaboralOracleListNew = new ArrayList<RelacionLaboralOracle>();
            for (RelacionLaboralOracle relacionLaboralOracleListNewRelacionLaboralOracleToAttach : relacionLaboralOracleListNew) {
                relacionLaboralOracleListNewRelacionLaboralOracleToAttach = em.getReference(relacionLaboralOracleListNewRelacionLaboralOracleToAttach.getClass(), relacionLaboralOracleListNewRelacionLaboralOracleToAttach.getIdrellab());
                attachedRelacionLaboralOracleListNew.add(relacionLaboralOracleListNewRelacionLaboralOracleToAttach);
            }
            relacionLaboralOracleListNew = attachedRelacionLaboralOracleListNew;
            registroPatronalOracle.setRelacionLaboralOracleList(relacionLaboralOracleListNew);
            registroPatronalOracle = em.merge(registroPatronalOracle);
            if (companiaOracleOld != null && !companiaOracleOld.equals(companiaOracleNew)) {
                companiaOracleOld.getRegistroPatronalOracleList().remove(registroPatronalOracle);
                companiaOracleOld = em.merge(companiaOracleOld);
            }
            if (companiaOracleNew != null && !companiaOracleNew.equals(companiaOracleOld)) {
                companiaOracleNew.getRegistroPatronalOracleList().add(registroPatronalOracle);
                companiaOracleNew = em.merge(companiaOracleNew);
            }
            for (RelacionLaboralOracle relacionLaboralOracleListNewRelacionLaboralOracle : relacionLaboralOracleListNew) {
                if (!relacionLaboralOracleListOld.contains(relacionLaboralOracleListNewRelacionLaboralOracle)) {
                    RegistroPatronalOracle oldRegistroPatronalOracleOfRelacionLaboralOracleListNewRelacionLaboralOracle = relacionLaboralOracleListNewRelacionLaboralOracle.getRegistroPatronalOracle();
                    relacionLaboralOracleListNewRelacionLaboralOracle.setRegistroPatronalOracle(registroPatronalOracle);
                    relacionLaboralOracleListNewRelacionLaboralOracle = em.merge(relacionLaboralOracleListNewRelacionLaboralOracle);
                    if (oldRegistroPatronalOracleOfRelacionLaboralOracleListNewRelacionLaboralOracle != null && !oldRegistroPatronalOracleOfRelacionLaboralOracleListNewRelacionLaboralOracle.equals(registroPatronalOracle)) {
                        oldRegistroPatronalOracleOfRelacionLaboralOracleListNewRelacionLaboralOracle.getRelacionLaboralOracleList().remove(relacionLaboralOracleListNewRelacionLaboralOracle);
                        oldRegistroPatronalOracleOfRelacionLaboralOracleListNewRelacionLaboralOracle = em.merge(oldRegistroPatronalOracleOfRelacionLaboralOracleListNewRelacionLaboralOracle);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                RegistroPatronalOraclePK id = registroPatronalOracle.getRegistroPatronalOraclePK();
                if (findRegistroPatronalOracle(id) == null) {
                    throw new NonexistentEntityException("The registroPatronalOracle with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RegistroPatronalOraclePK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RegistroPatronalOracle registroPatronalOracle;
            try {
                registroPatronalOracle = em.getReference(RegistroPatronalOracle.class, id);
                registroPatronalOracle.getRegistroPatronalOraclePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registroPatronalOracle with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<RelacionLaboralOracle> relacionLaboralOracleListOrphanCheck = registroPatronalOracle.getRelacionLaboralOracleList();
            for (RelacionLaboralOracle relacionLaboralOracleListOrphanCheckRelacionLaboralOracle : relacionLaboralOracleListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This RegistroPatronalOracle (" + registroPatronalOracle + ") cannot be destroyed since the RelacionLaboralOracle " + relacionLaboralOracleListOrphanCheckRelacionLaboralOracle + " in its relacionLaboralOracleList field has a non-nullable registroPatronalOracle field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CompaniaOracle companiaOracle = registroPatronalOracle.getCompaniaOracle();
            if (companiaOracle != null) {
                companiaOracle.getRegistroPatronalOracleList().remove(registroPatronalOracle);
                companiaOracle = em.merge(companiaOracle);
            }
            em.remove(registroPatronalOracle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RegistroPatronalOracle> findRegistroPatronalOracleEntities() {
        return findRegistroPatronalOracleEntities(true, -1, -1);
    }

    public List<RegistroPatronalOracle> findRegistroPatronalOracleEntities(int maxResults, int firstResult) {
        return findRegistroPatronalOracleEntities(false, maxResults, firstResult);
    }

    private List<RegistroPatronalOracle> findRegistroPatronalOracleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RegistroPatronalOracle.class));
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

    public RegistroPatronalOracle findRegistroPatronalOracle(RegistroPatronalOraclePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RegistroPatronalOracle.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistroPatronalOracleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RegistroPatronalOracle> rt = cq.from(RegistroPatronalOracle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
