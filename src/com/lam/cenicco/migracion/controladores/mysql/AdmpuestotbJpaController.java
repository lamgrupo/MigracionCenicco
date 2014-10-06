/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.controladores.mysql;

import com.lam.cenicco.migracion.controladores.mysql.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.lam.cenicco.migracion.entidades.mysql.Admcompaniatb;
import com.lam.cenicco.migracion.entidades.mysql.Admpuestotb;
import com.lam.cenicco.migracion.entidades.mysql.Rhrelacionlaboralposiciontb;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class AdmpuestotbJpaController implements Serializable {

    public AdmpuestotbJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Admpuestotb admpuestotb) {
        if (admpuestotb.getRhrelacionlaboralposiciontbList() == null) {
            admpuestotb.setRhrelacionlaboralposiciontbList(new ArrayList<Rhrelacionlaboralposiciontb>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Admcompaniatb idcompania = admpuestotb.getIdcompania();
            if (idcompania != null) {
                idcompania = em.getReference(idcompania.getClass(), idcompania.getIdcompania());
                admpuestotb.setIdcompania(idcompania);
            }
            List<Rhrelacionlaboralposiciontb> attachedRhrelacionlaboralposiciontbList = new ArrayList<Rhrelacionlaboralposiciontb>();
            for (Rhrelacionlaboralposiciontb rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontbToAttach : admpuestotb.getRhrelacionlaboralposiciontbList()) {
                rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontbToAttach = em.getReference(rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontbToAttach.getClass(), rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontbToAttach.getIdrelacionlaboralposicion());
                attachedRhrelacionlaboralposiciontbList.add(rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontbToAttach);
            }
            admpuestotb.setRhrelacionlaboralposiciontbList(attachedRhrelacionlaboralposiciontbList);
            em.persist(admpuestotb);
            if (idcompania != null) {
                idcompania.getAdmpuestotbList().add(admpuestotb);
                idcompania = em.merge(idcompania);
            }
            for (Rhrelacionlaboralposiciontb rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb : admpuestotb.getRhrelacionlaboralposiciontbList()) {
                Admpuestotb oldIdpuestoOfRhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb = rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb.getIdpuesto();
                rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb.setIdpuesto(admpuestotb);
                rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb = em.merge(rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb);
                if (oldIdpuestoOfRhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb != null) {
                    oldIdpuestoOfRhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb.getRhrelacionlaboralposiciontbList().remove(rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb);
                    oldIdpuestoOfRhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb = em.merge(oldIdpuestoOfRhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Admpuestotb admpuestotb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Admpuestotb persistentAdmpuestotb = em.find(Admpuestotb.class, admpuestotb.getIdpuesto());
            Admcompaniatb idcompaniaOld = persistentAdmpuestotb.getIdcompania();
            Admcompaniatb idcompaniaNew = admpuestotb.getIdcompania();
            List<Rhrelacionlaboralposiciontb> rhrelacionlaboralposiciontbListOld = persistentAdmpuestotb.getRhrelacionlaboralposiciontbList();
            List<Rhrelacionlaboralposiciontb> rhrelacionlaboralposiciontbListNew = admpuestotb.getRhrelacionlaboralposiciontbList();
            if (idcompaniaNew != null) {
                idcompaniaNew = em.getReference(idcompaniaNew.getClass(), idcompaniaNew.getIdcompania());
                admpuestotb.setIdcompania(idcompaniaNew);
            }
            List<Rhrelacionlaboralposiciontb> attachedRhrelacionlaboralposiciontbListNew = new ArrayList<Rhrelacionlaboralposiciontb>();
            for (Rhrelacionlaboralposiciontb rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontbToAttach : rhrelacionlaboralposiciontbListNew) {
                rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontbToAttach = em.getReference(rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontbToAttach.getClass(), rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontbToAttach.getIdrelacionlaboralposicion());
                attachedRhrelacionlaboralposiciontbListNew.add(rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontbToAttach);
            }
            rhrelacionlaboralposiciontbListNew = attachedRhrelacionlaboralposiciontbListNew;
            admpuestotb.setRhrelacionlaboralposiciontbList(rhrelacionlaboralposiciontbListNew);
            admpuestotb = em.merge(admpuestotb);
            if (idcompaniaOld != null && !idcompaniaOld.equals(idcompaniaNew)) {
                idcompaniaOld.getAdmpuestotbList().remove(admpuestotb);
                idcompaniaOld = em.merge(idcompaniaOld);
            }
            if (idcompaniaNew != null && !idcompaniaNew.equals(idcompaniaOld)) {
                idcompaniaNew.getAdmpuestotbList().add(admpuestotb);
                idcompaniaNew = em.merge(idcompaniaNew);
            }
            for (Rhrelacionlaboralposiciontb rhrelacionlaboralposiciontbListOldRhrelacionlaboralposiciontb : rhrelacionlaboralposiciontbListOld) {
                if (!rhrelacionlaboralposiciontbListNew.contains(rhrelacionlaboralposiciontbListOldRhrelacionlaboralposiciontb)) {
                    rhrelacionlaboralposiciontbListOldRhrelacionlaboralposiciontb.setIdpuesto(null);
                    rhrelacionlaboralposiciontbListOldRhrelacionlaboralposiciontb = em.merge(rhrelacionlaboralposiciontbListOldRhrelacionlaboralposiciontb);
                }
            }
            for (Rhrelacionlaboralposiciontb rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb : rhrelacionlaboralposiciontbListNew) {
                if (!rhrelacionlaboralposiciontbListOld.contains(rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb)) {
                    Admpuestotb oldIdpuestoOfRhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb = rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb.getIdpuesto();
                    rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb.setIdpuesto(admpuestotb);
                    rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb = em.merge(rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb);
                    if (oldIdpuestoOfRhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb != null && !oldIdpuestoOfRhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb.equals(admpuestotb)) {
                        oldIdpuestoOfRhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb.getRhrelacionlaboralposiciontbList().remove(rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb);
                        oldIdpuestoOfRhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb = em.merge(oldIdpuestoOfRhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = admpuestotb.getIdpuesto();
                if (findAdmpuestotb(id) == null) {
                    throw new NonexistentEntityException("The admpuestotb with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Admpuestotb admpuestotb;
            try {
                admpuestotb = em.getReference(Admpuestotb.class, id);
                admpuestotb.getIdpuesto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The admpuestotb with id " + id + " no longer exists.", enfe);
            }
            Admcompaniatb idcompania = admpuestotb.getIdcompania();
            if (idcompania != null) {
                idcompania.getAdmpuestotbList().remove(admpuestotb);
                idcompania = em.merge(idcompania);
            }
            List<Rhrelacionlaboralposiciontb> rhrelacionlaboralposiciontbList = admpuestotb.getRhrelacionlaboralposiciontbList();
            for (Rhrelacionlaboralposiciontb rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb : rhrelacionlaboralposiciontbList) {
                rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb.setIdpuesto(null);
                rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb = em.merge(rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb);
            }
            em.remove(admpuestotb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Admpuestotb> findAdmpuestotbEntities() {
        return findAdmpuestotbEntities(true, -1, -1);
    }

    public List<Admpuestotb> findAdmpuestotbEntities(int maxResults, int firstResult) {
        return findAdmpuestotbEntities(false, maxResults, firstResult);
    }

    private List<Admpuestotb> findAdmpuestotbEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Admpuestotb.class));
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

    public Admpuestotb findAdmpuestotb(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Admpuestotb.class, id);
        } finally {
            em.close();
        }
    }

    public int getAdmpuestotbCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Admpuestotb> rt = cq.from(Admpuestotb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
