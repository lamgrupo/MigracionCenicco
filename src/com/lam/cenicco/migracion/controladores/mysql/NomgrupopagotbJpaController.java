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
import com.lam.cenicco.migracion.entidades.mysql.Nomgrupopagotb;
import com.lam.cenicco.migracion.entidades.mysql.Rhrelacionlaboraltb;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class NomgrupopagotbJpaController implements Serializable {

    public NomgrupopagotbJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Nomgrupopagotb nomgrupopagotb) {
        if (nomgrupopagotb.getRhrelacionlaboraltbList() == null) {
            nomgrupopagotb.setRhrelacionlaboraltbList(new ArrayList<Rhrelacionlaboraltb>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Admcompaniatb idcompania = nomgrupopagotb.getIdcompania();
            if (idcompania != null) {
                idcompania = em.getReference(idcompania.getClass(), idcompania.getIdcompania());
                nomgrupopagotb.setIdcompania(idcompania);
            }
            List<Rhrelacionlaboraltb> attachedRhrelacionlaboraltbList = new ArrayList<Rhrelacionlaboraltb>();
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListRhrelacionlaboraltbToAttach : nomgrupopagotb.getRhrelacionlaboraltbList()) {
                rhrelacionlaboraltbListRhrelacionlaboraltbToAttach = em.getReference(rhrelacionlaboraltbListRhrelacionlaboraltbToAttach.getClass(), rhrelacionlaboraltbListRhrelacionlaboraltbToAttach.getIdrellab());
                attachedRhrelacionlaboraltbList.add(rhrelacionlaboraltbListRhrelacionlaboraltbToAttach);
            }
            nomgrupopagotb.setRhrelacionlaboraltbList(attachedRhrelacionlaboraltbList);
            em.persist(nomgrupopagotb);
            if (idcompania != null) {
                idcompania.getNomgrupopagotbList().add(nomgrupopagotb);
                idcompania = em.merge(idcompania);
            }
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListRhrelacionlaboraltb : nomgrupopagotb.getRhrelacionlaboraltbList()) {
                Nomgrupopagotb oldIdgrupopagoOfRhrelacionlaboraltbListRhrelacionlaboraltb = rhrelacionlaboraltbListRhrelacionlaboraltb.getIdgrupopago();
                rhrelacionlaboraltbListRhrelacionlaboraltb.setIdgrupopago(nomgrupopagotb);
                rhrelacionlaboraltbListRhrelacionlaboraltb = em.merge(rhrelacionlaboraltbListRhrelacionlaboraltb);
                if (oldIdgrupopagoOfRhrelacionlaboraltbListRhrelacionlaboraltb != null) {
                    oldIdgrupopagoOfRhrelacionlaboraltbListRhrelacionlaboraltb.getRhrelacionlaboraltbList().remove(rhrelacionlaboraltbListRhrelacionlaboraltb);
                    oldIdgrupopagoOfRhrelacionlaboraltbListRhrelacionlaboraltb = em.merge(oldIdgrupopagoOfRhrelacionlaboraltbListRhrelacionlaboraltb);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Nomgrupopagotb nomgrupopagotb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Nomgrupopagotb persistentNomgrupopagotb = em.find(Nomgrupopagotb.class, nomgrupopagotb.getIdgrupopago());
            Admcompaniatb idcompaniaOld = persistentNomgrupopagotb.getIdcompania();
            Admcompaniatb idcompaniaNew = nomgrupopagotb.getIdcompania();
            List<Rhrelacionlaboraltb> rhrelacionlaboraltbListOld = persistentNomgrupopagotb.getRhrelacionlaboraltbList();
            List<Rhrelacionlaboraltb> rhrelacionlaboraltbListNew = nomgrupopagotb.getRhrelacionlaboraltbList();
            if (idcompaniaNew != null) {
                idcompaniaNew = em.getReference(idcompaniaNew.getClass(), idcompaniaNew.getIdcompania());
                nomgrupopagotb.setIdcompania(idcompaniaNew);
            }
            List<Rhrelacionlaboraltb> attachedRhrelacionlaboraltbListNew = new ArrayList<Rhrelacionlaboraltb>();
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach : rhrelacionlaboraltbListNew) {
                rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach = em.getReference(rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach.getClass(), rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach.getIdrellab());
                attachedRhrelacionlaboraltbListNew.add(rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach);
            }
            rhrelacionlaboraltbListNew = attachedRhrelacionlaboraltbListNew;
            nomgrupopagotb.setRhrelacionlaboraltbList(rhrelacionlaboraltbListNew);
            nomgrupopagotb = em.merge(nomgrupopagotb);
            if (idcompaniaOld != null && !idcompaniaOld.equals(idcompaniaNew)) {
                idcompaniaOld.getNomgrupopagotbList().remove(nomgrupopagotb);
                idcompaniaOld = em.merge(idcompaniaOld);
            }
            if (idcompaniaNew != null && !idcompaniaNew.equals(idcompaniaOld)) {
                idcompaniaNew.getNomgrupopagotbList().add(nomgrupopagotb);
                idcompaniaNew = em.merge(idcompaniaNew);
            }
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListOldRhrelacionlaboraltb : rhrelacionlaboraltbListOld) {
                if (!rhrelacionlaboraltbListNew.contains(rhrelacionlaboraltbListOldRhrelacionlaboraltb)) {
                    rhrelacionlaboraltbListOldRhrelacionlaboraltb.setIdgrupopago(null);
                    rhrelacionlaboraltbListOldRhrelacionlaboraltb = em.merge(rhrelacionlaboraltbListOldRhrelacionlaboraltb);
                }
            }
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListNewRhrelacionlaboraltb : rhrelacionlaboraltbListNew) {
                if (!rhrelacionlaboraltbListOld.contains(rhrelacionlaboraltbListNewRhrelacionlaboraltb)) {
                    Nomgrupopagotb oldIdgrupopagoOfRhrelacionlaboraltbListNewRhrelacionlaboraltb = rhrelacionlaboraltbListNewRhrelacionlaboraltb.getIdgrupopago();
                    rhrelacionlaboraltbListNewRhrelacionlaboraltb.setIdgrupopago(nomgrupopagotb);
                    rhrelacionlaboraltbListNewRhrelacionlaboraltb = em.merge(rhrelacionlaboraltbListNewRhrelacionlaboraltb);
                    if (oldIdgrupopagoOfRhrelacionlaboraltbListNewRhrelacionlaboraltb != null && !oldIdgrupopagoOfRhrelacionlaboraltbListNewRhrelacionlaboraltb.equals(nomgrupopagotb)) {
                        oldIdgrupopagoOfRhrelacionlaboraltbListNewRhrelacionlaboraltb.getRhrelacionlaboraltbList().remove(rhrelacionlaboraltbListNewRhrelacionlaboraltb);
                        oldIdgrupopagoOfRhrelacionlaboraltbListNewRhrelacionlaboraltb = em.merge(oldIdgrupopagoOfRhrelacionlaboraltbListNewRhrelacionlaboraltb);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nomgrupopagotb.getIdgrupopago();
                if (findNomgrupopagotb(id) == null) {
                    throw new NonexistentEntityException("The nomgrupopagotb with id " + id + " no longer exists.");
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
            Nomgrupopagotb nomgrupopagotb;
            try {
                nomgrupopagotb = em.getReference(Nomgrupopagotb.class, id);
                nomgrupopagotb.getIdgrupopago();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nomgrupopagotb with id " + id + " no longer exists.", enfe);
            }
            Admcompaniatb idcompania = nomgrupopagotb.getIdcompania();
            if (idcompania != null) {
                idcompania.getNomgrupopagotbList().remove(nomgrupopagotb);
                idcompania = em.merge(idcompania);
            }
            List<Rhrelacionlaboraltb> rhrelacionlaboraltbList = nomgrupopagotb.getRhrelacionlaboraltbList();
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListRhrelacionlaboraltb : rhrelacionlaboraltbList) {
                rhrelacionlaboraltbListRhrelacionlaboraltb.setIdgrupopago(null);
                rhrelacionlaboraltbListRhrelacionlaboraltb = em.merge(rhrelacionlaboraltbListRhrelacionlaboraltb);
            }
            em.remove(nomgrupopagotb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Nomgrupopagotb> findNomgrupopagotbEntities() {
        return findNomgrupopagotbEntities(true, -1, -1);
    }

    public List<Nomgrupopagotb> findNomgrupopagotbEntities(int maxResults, int firstResult) {
        return findNomgrupopagotbEntities(false, maxResults, firstResult);
    }

    private List<Nomgrupopagotb> findNomgrupopagotbEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Nomgrupopagotb.class));
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

    public Nomgrupopagotb findNomgrupopagotb(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Nomgrupopagotb.class, id);
        } finally {
            em.close();
        }
    }

    public int getNomgrupopagotbCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Nomgrupopagotb> rt = cq.from(Nomgrupopagotb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
