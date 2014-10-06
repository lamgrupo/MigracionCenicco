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
import com.lam.cenicco.migracion.entidades.mysql.Admdepartamentotb;
import com.lam.cenicco.migracion.entidades.mysql.Rhrelacionlaboralposiciontb;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class AdmdepartamentotbJpaController implements Serializable {

    public AdmdepartamentotbJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Admdepartamentotb admdepartamentotb) {
        if (admdepartamentotb.getRhrelacionlaboralposiciontbList() == null) {
            admdepartamentotb.setRhrelacionlaboralposiciontbList(new ArrayList<Rhrelacionlaboralposiciontb>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Admcompaniatb idcompania = admdepartamentotb.getIdcompania();
            if (idcompania != null) {
                idcompania = em.getReference(idcompania.getClass(), idcompania.getIdcompania());
                admdepartamentotb.setIdcompania(idcompania);
            }
            List<Rhrelacionlaboralposiciontb> attachedRhrelacionlaboralposiciontbList = new ArrayList<Rhrelacionlaboralposiciontb>();
            for (Rhrelacionlaboralposiciontb rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontbToAttach : admdepartamentotb.getRhrelacionlaboralposiciontbList()) {
                rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontbToAttach = em.getReference(rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontbToAttach.getClass(), rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontbToAttach.getIdrelacionlaboralposicion());
                attachedRhrelacionlaboralposiciontbList.add(rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontbToAttach);
            }
            admdepartamentotb.setRhrelacionlaboralposiciontbList(attachedRhrelacionlaboralposiciontbList);
            em.persist(admdepartamentotb);
            if (idcompania != null) {
                idcompania.getAdmdepartamentotbList().add(admdepartamentotb);
                idcompania = em.merge(idcompania);
            }
            for (Rhrelacionlaboralposiciontb rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb : admdepartamentotb.getRhrelacionlaboralposiciontbList()) {
                Admdepartamentotb oldIddepartamentoOfRhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb = rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb.getIddepartamento();
                rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb.setIddepartamento(admdepartamentotb);
                rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb = em.merge(rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb);
                if (oldIddepartamentoOfRhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb != null) {
                    oldIddepartamentoOfRhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb.getRhrelacionlaboralposiciontbList().remove(rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb);
                    oldIddepartamentoOfRhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb = em.merge(oldIddepartamentoOfRhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Admdepartamentotb admdepartamentotb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Admdepartamentotb persistentAdmdepartamentotb = em.find(Admdepartamentotb.class, admdepartamentotb.getIddepartamento());
            Admcompaniatb idcompaniaOld = persistentAdmdepartamentotb.getIdcompania();
            Admcompaniatb idcompaniaNew = admdepartamentotb.getIdcompania();
            List<Rhrelacionlaboralposiciontb> rhrelacionlaboralposiciontbListOld = persistentAdmdepartamentotb.getRhrelacionlaboralposiciontbList();
            List<Rhrelacionlaboralposiciontb> rhrelacionlaboralposiciontbListNew = admdepartamentotb.getRhrelacionlaboralposiciontbList();
            if (idcompaniaNew != null) {
                idcompaniaNew = em.getReference(idcompaniaNew.getClass(), idcompaniaNew.getIdcompania());
                admdepartamentotb.setIdcompania(idcompaniaNew);
            }
            List<Rhrelacionlaboralposiciontb> attachedRhrelacionlaboralposiciontbListNew = new ArrayList<Rhrelacionlaboralposiciontb>();
            for (Rhrelacionlaboralposiciontb rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontbToAttach : rhrelacionlaboralposiciontbListNew) {
                rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontbToAttach = em.getReference(rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontbToAttach.getClass(), rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontbToAttach.getIdrelacionlaboralposicion());
                attachedRhrelacionlaboralposiciontbListNew.add(rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontbToAttach);
            }
            rhrelacionlaboralposiciontbListNew = attachedRhrelacionlaboralposiciontbListNew;
            admdepartamentotb.setRhrelacionlaboralposiciontbList(rhrelacionlaboralposiciontbListNew);
            admdepartamentotb = em.merge(admdepartamentotb);
            if (idcompaniaOld != null && !idcompaniaOld.equals(idcompaniaNew)) {
                idcompaniaOld.getAdmdepartamentotbList().remove(admdepartamentotb);
                idcompaniaOld = em.merge(idcompaniaOld);
            }
            if (idcompaniaNew != null && !idcompaniaNew.equals(idcompaniaOld)) {
                idcompaniaNew.getAdmdepartamentotbList().add(admdepartamentotb);
                idcompaniaNew = em.merge(idcompaniaNew);
            }
            for (Rhrelacionlaboralposiciontb rhrelacionlaboralposiciontbListOldRhrelacionlaboralposiciontb : rhrelacionlaboralposiciontbListOld) {
                if (!rhrelacionlaboralposiciontbListNew.contains(rhrelacionlaboralposiciontbListOldRhrelacionlaboralposiciontb)) {
                    rhrelacionlaboralposiciontbListOldRhrelacionlaboralposiciontb.setIddepartamento(null);
                    rhrelacionlaboralposiciontbListOldRhrelacionlaboralposiciontb = em.merge(rhrelacionlaboralposiciontbListOldRhrelacionlaboralposiciontb);
                }
            }
            for (Rhrelacionlaboralposiciontb rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb : rhrelacionlaboralposiciontbListNew) {
                if (!rhrelacionlaboralposiciontbListOld.contains(rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb)) {
                    Admdepartamentotb oldIddepartamentoOfRhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb = rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb.getIddepartamento();
                    rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb.setIddepartamento(admdepartamentotb);
                    rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb = em.merge(rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb);
                    if (oldIddepartamentoOfRhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb != null && !oldIddepartamentoOfRhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb.equals(admdepartamentotb)) {
                        oldIddepartamentoOfRhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb.getRhrelacionlaboralposiciontbList().remove(rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb);
                        oldIddepartamentoOfRhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb = em.merge(oldIddepartamentoOfRhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = admdepartamentotb.getIddepartamento();
                if (findAdmdepartamentotb(id) == null) {
                    throw new NonexistentEntityException("The admdepartamentotb with id " + id + " no longer exists.");
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
            Admdepartamentotb admdepartamentotb;
            try {
                admdepartamentotb = em.getReference(Admdepartamentotb.class, id);
                admdepartamentotb.getIddepartamento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The admdepartamentotb with id " + id + " no longer exists.", enfe);
            }
            Admcompaniatb idcompania = admdepartamentotb.getIdcompania();
            if (idcompania != null) {
                idcompania.getAdmdepartamentotbList().remove(admdepartamentotb);
                idcompania = em.merge(idcompania);
            }
            List<Rhrelacionlaboralposiciontb> rhrelacionlaboralposiciontbList = admdepartamentotb.getRhrelacionlaboralposiciontbList();
            for (Rhrelacionlaboralposiciontb rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb : rhrelacionlaboralposiciontbList) {
                rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb.setIddepartamento(null);
                rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb = em.merge(rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb);
            }
            em.remove(admdepartamentotb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Admdepartamentotb> findAdmdepartamentotbEntities() {
        return findAdmdepartamentotbEntities(true, -1, -1);
    }

    public List<Admdepartamentotb> findAdmdepartamentotbEntities(int maxResults, int firstResult) {
        return findAdmdepartamentotbEntities(false, maxResults, firstResult);
    }

    private List<Admdepartamentotb> findAdmdepartamentotbEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Admdepartamentotb.class));
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

    public Admdepartamentotb findAdmdepartamentotb(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Admdepartamentotb.class, id);
        } finally {
            em.close();
        }
    }

    public int getAdmdepartamentotbCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Admdepartamentotb> rt = cq.from(Admdepartamentotb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
