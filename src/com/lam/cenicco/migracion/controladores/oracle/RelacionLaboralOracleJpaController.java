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
import com.lam.cenicco.migracion.entidades.oracle.TipoRelacionLaboralOracle;
import com.lam.cenicco.migracion.entidades.oracle.EmpleadoOracle;
import com.lam.cenicco.migracion.entidades.oracle.GrupoPagoOracle;
import com.lam.cenicco.migracion.entidades.oracle.CompaniaOracle;
import com.lam.cenicco.migracion.entidades.oracle.RegistroPatronalOracle;
import com.lam.cenicco.migracion.entidades.oracle.RelacionLaboralOracle;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class RelacionLaboralOracleJpaController implements Serializable {

    public RelacionLaboralOracleJpaController() {
        this.emf = JpaUtil.getInstance().createEntityManagerCeniccoOracle().getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RelacionLaboralOracle relacionLaboralOracle) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoRelacionLaboralOracle tiporellab = relacionLaboralOracle.getTiporellab();
            if (tiporellab != null) {
                tiporellab = em.getReference(tiporellab.getClass(), tiporellab.getTiporellab());
                relacionLaboralOracle.setTiporellab(tiporellab);
            }
            EmpleadoOracle idempleado = relacionLaboralOracle.getIdempleado();
            if (idempleado != null) {
                idempleado = em.getReference(idempleado.getClass(), idempleado.getIdempleado());
                relacionLaboralOracle.setIdempleado(idempleado);
            }
            GrupoPagoOracle idgrupopago = relacionLaboralOracle.getIdgrupopago();
            if (idgrupopago != null) {
                idgrupopago = em.getReference(idgrupopago.getClass(), idgrupopago.getIdgrupopago());
                relacionLaboralOracle.setIdgrupopago(idgrupopago);
            }
            CompaniaOracle idcompania = relacionLaboralOracle.getIdcompania();
            if (idcompania != null) {
                idcompania = em.getReference(idcompania.getClass(), idcompania.getIdcompania());
                relacionLaboralOracle.setIdcompania(idcompania);
            }
            RegistroPatronalOracle registroPatronalOracle = relacionLaboralOracle.getRegistroPatronalOracle();
            if (registroPatronalOracle != null) {
                registroPatronalOracle = em.getReference(registroPatronalOracle.getClass(), registroPatronalOracle.getRegistroPatronalOraclePK());
                relacionLaboralOracle.setRegistroPatronalOracle(registroPatronalOracle);
            }
            em.persist(relacionLaboralOracle);
            if (tiporellab != null) {
                tiporellab.getRelacionLaboralOracleList().add(relacionLaboralOracle);
                tiporellab = em.merge(tiporellab);
            }
            if (idempleado != null) {
                idempleado.getRelacionLaboralOracleList().add(relacionLaboralOracle);
                idempleado = em.merge(idempleado);
            }
            if (idgrupopago != null) {
                idgrupopago.getRelacionLaboralOracleList().add(relacionLaboralOracle);
                idgrupopago = em.merge(idgrupopago);
            }
            if (idcompania != null) {
                idcompania.getRelacionLaboralOracleList().add(relacionLaboralOracle);
                idcompania = em.merge(idcompania);
            }
            if (registroPatronalOracle != null) {
                registroPatronalOracle.getRelacionLaboralOracleList().add(relacionLaboralOracle);
                registroPatronalOracle = em.merge(registroPatronalOracle);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRelacionLaboralOracle(relacionLaboralOracle.getIdrellab()) != null) {
                throw new PreexistingEntityException("RelacionLaboralOracle " + relacionLaboralOracle + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RelacionLaboralOracle relacionLaboralOracle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RelacionLaboralOracle persistentRelacionLaboralOracle = em.find(RelacionLaboralOracle.class, relacionLaboralOracle.getIdrellab());
            TipoRelacionLaboralOracle tiporellabOld = persistentRelacionLaboralOracle.getTiporellab();
            TipoRelacionLaboralOracle tiporellabNew = relacionLaboralOracle.getTiporellab();
            EmpleadoOracle idempleadoOld = persistentRelacionLaboralOracle.getIdempleado();
            EmpleadoOracle idempleadoNew = relacionLaboralOracle.getIdempleado();
            GrupoPagoOracle idgrupopagoOld = persistentRelacionLaboralOracle.getIdgrupopago();
            GrupoPagoOracle idgrupopagoNew = relacionLaboralOracle.getIdgrupopago();
            CompaniaOracle idcompaniaOld = persistentRelacionLaboralOracle.getIdcompania();
            CompaniaOracle idcompaniaNew = relacionLaboralOracle.getIdcompania();
            RegistroPatronalOracle registroPatronalOracleOld = persistentRelacionLaboralOracle.getRegistroPatronalOracle();
            RegistroPatronalOracle registroPatronalOracleNew = relacionLaboralOracle.getRegistroPatronalOracle();
            if (tiporellabNew != null) {
                tiporellabNew = em.getReference(tiporellabNew.getClass(), tiporellabNew.getTiporellab());
                relacionLaboralOracle.setTiporellab(tiporellabNew);
            }
            if (idempleadoNew != null) {
                idempleadoNew = em.getReference(idempleadoNew.getClass(), idempleadoNew.getIdempleado());
                relacionLaboralOracle.setIdempleado(idempleadoNew);
            }
            if (idgrupopagoNew != null) {
                idgrupopagoNew = em.getReference(idgrupopagoNew.getClass(), idgrupopagoNew.getIdgrupopago());
                relacionLaboralOracle.setIdgrupopago(idgrupopagoNew);
            }
            if (idcompaniaNew != null) {
                idcompaniaNew = em.getReference(idcompaniaNew.getClass(), idcompaniaNew.getIdcompania());
                relacionLaboralOracle.setIdcompania(idcompaniaNew);
            }
            if (registroPatronalOracleNew != null) {
                registroPatronalOracleNew = em.getReference(registroPatronalOracleNew.getClass(), registroPatronalOracleNew.getRegistroPatronalOraclePK());
                relacionLaboralOracle.setRegistroPatronalOracle(registroPatronalOracleNew);
            }
            relacionLaboralOracle = em.merge(relacionLaboralOracle);
            if (tiporellabOld != null && !tiporellabOld.equals(tiporellabNew)) {
                tiporellabOld.getRelacionLaboralOracleList().remove(relacionLaboralOracle);
                tiporellabOld = em.merge(tiporellabOld);
            }
            if (tiporellabNew != null && !tiporellabNew.equals(tiporellabOld)) {
                tiporellabNew.getRelacionLaboralOracleList().add(relacionLaboralOracle);
                tiporellabNew = em.merge(tiporellabNew);
            }
            if (idempleadoOld != null && !idempleadoOld.equals(idempleadoNew)) {
                idempleadoOld.getRelacionLaboralOracleList().remove(relacionLaboralOracle);
                idempleadoOld = em.merge(idempleadoOld);
            }
            if (idempleadoNew != null && !idempleadoNew.equals(idempleadoOld)) {
                idempleadoNew.getRelacionLaboralOracleList().add(relacionLaboralOracle);
                idempleadoNew = em.merge(idempleadoNew);
            }
            if (idgrupopagoOld != null && !idgrupopagoOld.equals(idgrupopagoNew)) {
                idgrupopagoOld.getRelacionLaboralOracleList().remove(relacionLaboralOracle);
                idgrupopagoOld = em.merge(idgrupopagoOld);
            }
            if (idgrupopagoNew != null && !idgrupopagoNew.equals(idgrupopagoOld)) {
                idgrupopagoNew.getRelacionLaboralOracleList().add(relacionLaboralOracle);
                idgrupopagoNew = em.merge(idgrupopagoNew);
            }
            if (idcompaniaOld != null && !idcompaniaOld.equals(idcompaniaNew)) {
                idcompaniaOld.getRelacionLaboralOracleList().remove(relacionLaboralOracle);
                idcompaniaOld = em.merge(idcompaniaOld);
            }
            if (idcompaniaNew != null && !idcompaniaNew.equals(idcompaniaOld)) {
                idcompaniaNew.getRelacionLaboralOracleList().add(relacionLaboralOracle);
                idcompaniaNew = em.merge(idcompaniaNew);
            }
            if (registroPatronalOracleOld != null && !registroPatronalOracleOld.equals(registroPatronalOracleNew)) {
                registroPatronalOracleOld.getRelacionLaboralOracleList().remove(relacionLaboralOracle);
                registroPatronalOracleOld = em.merge(registroPatronalOracleOld);
            }
            if (registroPatronalOracleNew != null && !registroPatronalOracleNew.equals(registroPatronalOracleOld)) {
                registroPatronalOracleNew.getRelacionLaboralOracleList().add(relacionLaboralOracle);
                registroPatronalOracleNew = em.merge(registroPatronalOracleNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = relacionLaboralOracle.getIdrellab();
                if (findRelacionLaboralOracle(id) == null) {
                    throw new NonexistentEntityException("The relacionLaboralOracle with id " + id + " no longer exists.");
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
            RelacionLaboralOracle relacionLaboralOracle;
            try {
                relacionLaboralOracle = em.getReference(RelacionLaboralOracle.class, id);
                relacionLaboralOracle.getIdrellab();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The relacionLaboralOracle with id " + id + " no longer exists.", enfe);
            }
            TipoRelacionLaboralOracle tiporellab = relacionLaboralOracle.getTiporellab();
            if (tiporellab != null) {
                tiporellab.getRelacionLaboralOracleList().remove(relacionLaboralOracle);
                tiporellab = em.merge(tiporellab);
            }
            EmpleadoOracle idempleado = relacionLaboralOracle.getIdempleado();
            if (idempleado != null) {
                idempleado.getRelacionLaboralOracleList().remove(relacionLaboralOracle);
                idempleado = em.merge(idempleado);
            }
            GrupoPagoOracle idgrupopago = relacionLaboralOracle.getIdgrupopago();
            if (idgrupopago != null) {
                idgrupopago.getRelacionLaboralOracleList().remove(relacionLaboralOracle);
                idgrupopago = em.merge(idgrupopago);
            }
            CompaniaOracle idcompania = relacionLaboralOracle.getIdcompania();
            if (idcompania != null) {
                idcompania.getRelacionLaboralOracleList().remove(relacionLaboralOracle);
                idcompania = em.merge(idcompania);
            }
            RegistroPatronalOracle registroPatronalOracle = relacionLaboralOracle.getRegistroPatronalOracle();
            if (registroPatronalOracle != null) {
                registroPatronalOracle.getRelacionLaboralOracleList().remove(relacionLaboralOracle);
                registroPatronalOracle = em.merge(registroPatronalOracle);
            }
            em.remove(relacionLaboralOracle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RelacionLaboralOracle> findRelacionLaboralOracleEntities() {
        return findRelacionLaboralOracleEntities(true, -1, -1);
    }

    public List<RelacionLaboralOracle> findRelacionLaboralOracleEntities(int maxResults, int firstResult) {
        return findRelacionLaboralOracleEntities(false, maxResults, firstResult);
    }

    private List<RelacionLaboralOracle> findRelacionLaboralOracleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RelacionLaboralOracle.class));
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

    public RelacionLaboralOracle findRelacionLaboralOracle(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RelacionLaboralOracle.class, id);
        } finally {
            em.close();
        }
    }

    public int getRelacionLaboralOracleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RelacionLaboralOracle> rt = cq.from(RelacionLaboralOracle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
