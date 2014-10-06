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
import com.lam.cenicco.migracion.entidades.oracle.GrupoPagoOracle;
import com.lam.cenicco.migracion.entidades.oracle.RelacionLaboralOracle;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class GrupoPagoOracleJpaController implements Serializable {

    public GrupoPagoOracleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(GrupoPagoOracle grupoPagoOracle) throws PreexistingEntityException, Exception {
        if (grupoPagoOracle.getRelacionLaboralOracleList() == null) {
            grupoPagoOracle.setRelacionLaboralOracleList(new ArrayList<RelacionLaboralOracle>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CompaniaOracle idcompania = grupoPagoOracle.getIdcompania();
            if (idcompania != null) {
                idcompania = em.getReference(idcompania.getClass(), idcompania.getIdcompania());
                grupoPagoOracle.setIdcompania(idcompania);
            }
            List<RelacionLaboralOracle> attachedRelacionLaboralOracleList = new ArrayList<RelacionLaboralOracle>();
            for (RelacionLaboralOracle relacionLaboralOracleListRelacionLaboralOracleToAttach : grupoPagoOracle.getRelacionLaboralOracleList()) {
                relacionLaboralOracleListRelacionLaboralOracleToAttach = em.getReference(relacionLaboralOracleListRelacionLaboralOracleToAttach.getClass(), relacionLaboralOracleListRelacionLaboralOracleToAttach.getIdrellab());
                attachedRelacionLaboralOracleList.add(relacionLaboralOracleListRelacionLaboralOracleToAttach);
            }
            grupoPagoOracle.setRelacionLaboralOracleList(attachedRelacionLaboralOracleList);
            em.persist(grupoPagoOracle);
            if (idcompania != null) {
                idcompania.getGrupoPagoOracleList().add(grupoPagoOracle);
                idcompania = em.merge(idcompania);
            }
            for (RelacionLaboralOracle relacionLaboralOracleListRelacionLaboralOracle : grupoPagoOracle.getRelacionLaboralOracleList()) {
                GrupoPagoOracle oldIdgrupopagoOfRelacionLaboralOracleListRelacionLaboralOracle = relacionLaboralOracleListRelacionLaboralOracle.getIdgrupopago();
                relacionLaboralOracleListRelacionLaboralOracle.setIdgrupopago(grupoPagoOracle);
                relacionLaboralOracleListRelacionLaboralOracle = em.merge(relacionLaboralOracleListRelacionLaboralOracle);
                if (oldIdgrupopagoOfRelacionLaboralOracleListRelacionLaboralOracle != null) {
                    oldIdgrupopagoOfRelacionLaboralOracleListRelacionLaboralOracle.getRelacionLaboralOracleList().remove(relacionLaboralOracleListRelacionLaboralOracle);
                    oldIdgrupopagoOfRelacionLaboralOracleListRelacionLaboralOracle = em.merge(oldIdgrupopagoOfRelacionLaboralOracleListRelacionLaboralOracle);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findGrupoPagoOracle(grupoPagoOracle.getIdgrupopago()) != null) {
                throw new PreexistingEntityException("GrupoPagoOracle " + grupoPagoOracle + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(GrupoPagoOracle grupoPagoOracle) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            GrupoPagoOracle persistentGrupoPagoOracle = em.find(GrupoPagoOracle.class, grupoPagoOracle.getIdgrupopago());
            CompaniaOracle idcompaniaOld = persistentGrupoPagoOracle.getIdcompania();
            CompaniaOracle idcompaniaNew = grupoPagoOracle.getIdcompania();
            List<RelacionLaboralOracle> relacionLaboralOracleListOld = persistentGrupoPagoOracle.getRelacionLaboralOracleList();
            List<RelacionLaboralOracle> relacionLaboralOracleListNew = grupoPagoOracle.getRelacionLaboralOracleList();
            List<String> illegalOrphanMessages = null;
            for (RelacionLaboralOracle relacionLaboralOracleListOldRelacionLaboralOracle : relacionLaboralOracleListOld) {
                if (!relacionLaboralOracleListNew.contains(relacionLaboralOracleListOldRelacionLaboralOracle)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RelacionLaboralOracle " + relacionLaboralOracleListOldRelacionLaboralOracle + " since its idgrupopago field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idcompaniaNew != null) {
                idcompaniaNew = em.getReference(idcompaniaNew.getClass(), idcompaniaNew.getIdcompania());
                grupoPagoOracle.setIdcompania(idcompaniaNew);
            }
            List<RelacionLaboralOracle> attachedRelacionLaboralOracleListNew = new ArrayList<RelacionLaboralOracle>();
            for (RelacionLaboralOracle relacionLaboralOracleListNewRelacionLaboralOracleToAttach : relacionLaboralOracleListNew) {
                relacionLaboralOracleListNewRelacionLaboralOracleToAttach = em.getReference(relacionLaboralOracleListNewRelacionLaboralOracleToAttach.getClass(), relacionLaboralOracleListNewRelacionLaboralOracleToAttach.getIdrellab());
                attachedRelacionLaboralOracleListNew.add(relacionLaboralOracleListNewRelacionLaboralOracleToAttach);
            }
            relacionLaboralOracleListNew = attachedRelacionLaboralOracleListNew;
            grupoPagoOracle.setRelacionLaboralOracleList(relacionLaboralOracleListNew);
            grupoPagoOracle = em.merge(grupoPagoOracle);
            if (idcompaniaOld != null && !idcompaniaOld.equals(idcompaniaNew)) {
                idcompaniaOld.getGrupoPagoOracleList().remove(grupoPagoOracle);
                idcompaniaOld = em.merge(idcompaniaOld);
            }
            if (idcompaniaNew != null && !idcompaniaNew.equals(idcompaniaOld)) {
                idcompaniaNew.getGrupoPagoOracleList().add(grupoPagoOracle);
                idcompaniaNew = em.merge(idcompaniaNew);
            }
            for (RelacionLaboralOracle relacionLaboralOracleListNewRelacionLaboralOracle : relacionLaboralOracleListNew) {
                if (!relacionLaboralOracleListOld.contains(relacionLaboralOracleListNewRelacionLaboralOracle)) {
                    GrupoPagoOracle oldIdgrupopagoOfRelacionLaboralOracleListNewRelacionLaboralOracle = relacionLaboralOracleListNewRelacionLaboralOracle.getIdgrupopago();
                    relacionLaboralOracleListNewRelacionLaboralOracle.setIdgrupopago(grupoPagoOracle);
                    relacionLaboralOracleListNewRelacionLaboralOracle = em.merge(relacionLaboralOracleListNewRelacionLaboralOracle);
                    if (oldIdgrupopagoOfRelacionLaboralOracleListNewRelacionLaboralOracle != null && !oldIdgrupopagoOfRelacionLaboralOracleListNewRelacionLaboralOracle.equals(grupoPagoOracle)) {
                        oldIdgrupopagoOfRelacionLaboralOracleListNewRelacionLaboralOracle.getRelacionLaboralOracleList().remove(relacionLaboralOracleListNewRelacionLaboralOracle);
                        oldIdgrupopagoOfRelacionLaboralOracleListNewRelacionLaboralOracle = em.merge(oldIdgrupopagoOfRelacionLaboralOracleListNewRelacionLaboralOracle);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = grupoPagoOracle.getIdgrupopago();
                if (findGrupoPagoOracle(id) == null) {
                    throw new NonexistentEntityException("The grupoPagoOracle with id " + id + " no longer exists.");
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
            GrupoPagoOracle grupoPagoOracle;
            try {
                grupoPagoOracle = em.getReference(GrupoPagoOracle.class, id);
                grupoPagoOracle.getIdgrupopago();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The grupoPagoOracle with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<RelacionLaboralOracle> relacionLaboralOracleListOrphanCheck = grupoPagoOracle.getRelacionLaboralOracleList();
            for (RelacionLaboralOracle relacionLaboralOracleListOrphanCheckRelacionLaboralOracle : relacionLaboralOracleListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This GrupoPagoOracle (" + grupoPagoOracle + ") cannot be destroyed since the RelacionLaboralOracle " + relacionLaboralOracleListOrphanCheckRelacionLaboralOracle + " in its relacionLaboralOracleList field has a non-nullable idgrupopago field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CompaniaOracle idcompania = grupoPagoOracle.getIdcompania();
            if (idcompania != null) {
                idcompania.getGrupoPagoOracleList().remove(grupoPagoOracle);
                idcompania = em.merge(idcompania);
            }
            em.remove(grupoPagoOracle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<GrupoPagoOracle> findGrupoPagoOracleEntities() {
        return findGrupoPagoOracleEntities(true, -1, -1);
    }

    public List<GrupoPagoOracle> findGrupoPagoOracleEntities(int maxResults, int firstResult) {
        return findGrupoPagoOracleEntities(false, maxResults, firstResult);
    }

    private List<GrupoPagoOracle> findGrupoPagoOracleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(GrupoPagoOracle.class));
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

    public GrupoPagoOracle findGrupoPagoOracle(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(GrupoPagoOracle.class, id);
        } finally {
            em.close();
        }
    }

    public int getGrupoPagoOracleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<GrupoPagoOracle> rt = cq.from(GrupoPagoOracle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
