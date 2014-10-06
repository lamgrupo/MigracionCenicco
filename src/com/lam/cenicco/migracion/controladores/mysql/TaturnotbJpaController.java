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
import com.lam.cenicco.migracion.entidades.mysql.Rhrelacionlaboralposiciontb;
import com.lam.cenicco.migracion.entidades.mysql.Taturnotb;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class TaturnotbJpaController implements Serializable {

    public TaturnotbJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Taturnotb taturnotb) {
        if (taturnotb.getRhrelacionlaboralposiciontbList() == null) {
            taturnotb.setRhrelacionlaboralposiciontbList(new ArrayList<Rhrelacionlaboralposiciontb>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Admcompaniatb idcompania = taturnotb.getIdcompania();
            if (idcompania != null) {
                idcompania = em.getReference(idcompania.getClass(), idcompania.getIdcompania());
                taturnotb.setIdcompania(idcompania);
            }
            List<Rhrelacionlaboralposiciontb> attachedRhrelacionlaboralposiciontbList = new ArrayList<Rhrelacionlaboralposiciontb>();
            for (Rhrelacionlaboralposiciontb rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontbToAttach : taturnotb.getRhrelacionlaboralposiciontbList()) {
                rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontbToAttach = em.getReference(rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontbToAttach.getClass(), rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontbToAttach.getIdrelacionlaboralposicion());
                attachedRhrelacionlaboralposiciontbList.add(rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontbToAttach);
            }
            taturnotb.setRhrelacionlaboralposiciontbList(attachedRhrelacionlaboralposiciontbList);
            em.persist(taturnotb);
            if (idcompania != null) {
                idcompania.getTaturnotbList().add(taturnotb);
                idcompania = em.merge(idcompania);
            }
            for (Rhrelacionlaboralposiciontb rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb : taturnotb.getRhrelacionlaboralposiciontbList()) {
                Taturnotb oldIdturnoOfRhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb = rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb.getIdturno();
                rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb.setIdturno(taturnotb);
                rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb = em.merge(rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb);
                if (oldIdturnoOfRhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb != null) {
                    oldIdturnoOfRhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb.getRhrelacionlaboralposiciontbList().remove(rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb);
                    oldIdturnoOfRhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb = em.merge(oldIdturnoOfRhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Taturnotb taturnotb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Taturnotb persistentTaturnotb = em.find(Taturnotb.class, taturnotb.getIdturno());
            Admcompaniatb idcompaniaOld = persistentTaturnotb.getIdcompania();
            Admcompaniatb idcompaniaNew = taturnotb.getIdcompania();
            List<Rhrelacionlaboralposiciontb> rhrelacionlaboralposiciontbListOld = persistentTaturnotb.getRhrelacionlaboralposiciontbList();
            List<Rhrelacionlaboralposiciontb> rhrelacionlaboralposiciontbListNew = taturnotb.getRhrelacionlaboralposiciontbList();
            if (idcompaniaNew != null) {
                idcompaniaNew = em.getReference(idcompaniaNew.getClass(), idcompaniaNew.getIdcompania());
                taturnotb.setIdcompania(idcompaniaNew);
            }
            List<Rhrelacionlaboralposiciontb> attachedRhrelacionlaboralposiciontbListNew = new ArrayList<Rhrelacionlaboralposiciontb>();
            for (Rhrelacionlaboralposiciontb rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontbToAttach : rhrelacionlaboralposiciontbListNew) {
                rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontbToAttach = em.getReference(rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontbToAttach.getClass(), rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontbToAttach.getIdrelacionlaboralposicion());
                attachedRhrelacionlaboralposiciontbListNew.add(rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontbToAttach);
            }
            rhrelacionlaboralposiciontbListNew = attachedRhrelacionlaboralposiciontbListNew;
            taturnotb.setRhrelacionlaboralposiciontbList(rhrelacionlaboralposiciontbListNew);
            taturnotb = em.merge(taturnotb);
            if (idcompaniaOld != null && !idcompaniaOld.equals(idcompaniaNew)) {
                idcompaniaOld.getTaturnotbList().remove(taturnotb);
                idcompaniaOld = em.merge(idcompaniaOld);
            }
            if (idcompaniaNew != null && !idcompaniaNew.equals(idcompaniaOld)) {
                idcompaniaNew.getTaturnotbList().add(taturnotb);
                idcompaniaNew = em.merge(idcompaniaNew);
            }
            for (Rhrelacionlaboralposiciontb rhrelacionlaboralposiciontbListOldRhrelacionlaboralposiciontb : rhrelacionlaboralposiciontbListOld) {
                if (!rhrelacionlaboralposiciontbListNew.contains(rhrelacionlaboralposiciontbListOldRhrelacionlaboralposiciontb)) {
                    rhrelacionlaboralposiciontbListOldRhrelacionlaboralposiciontb.setIdturno(null);
                    rhrelacionlaboralposiciontbListOldRhrelacionlaboralposiciontb = em.merge(rhrelacionlaboralposiciontbListOldRhrelacionlaboralposiciontb);
                }
            }
            for (Rhrelacionlaboralposiciontb rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb : rhrelacionlaboralposiciontbListNew) {
                if (!rhrelacionlaboralposiciontbListOld.contains(rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb)) {
                    Taturnotb oldIdturnoOfRhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb = rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb.getIdturno();
                    rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb.setIdturno(taturnotb);
                    rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb = em.merge(rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb);
                    if (oldIdturnoOfRhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb != null && !oldIdturnoOfRhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb.equals(taturnotb)) {
                        oldIdturnoOfRhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb.getRhrelacionlaboralposiciontbList().remove(rhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb);
                        oldIdturnoOfRhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb = em.merge(oldIdturnoOfRhrelacionlaboralposiciontbListNewRhrelacionlaboralposiciontb);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = taturnotb.getIdturno();
                if (findTaturnotb(id) == null) {
                    throw new NonexistentEntityException("The taturnotb with id " + id + " no longer exists.");
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
            Taturnotb taturnotb;
            try {
                taturnotb = em.getReference(Taturnotb.class, id);
                taturnotb.getIdturno();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The taturnotb with id " + id + " no longer exists.", enfe);
            }
            Admcompaniatb idcompania = taturnotb.getIdcompania();
            if (idcompania != null) {
                idcompania.getTaturnotbList().remove(taturnotb);
                idcompania = em.merge(idcompania);
            }
            List<Rhrelacionlaboralposiciontb> rhrelacionlaboralposiciontbList = taturnotb.getRhrelacionlaboralposiciontbList();
            for (Rhrelacionlaboralposiciontb rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb : rhrelacionlaboralposiciontbList) {
                rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb.setIdturno(null);
                rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb = em.merge(rhrelacionlaboralposiciontbListRhrelacionlaboralposiciontb);
            }
            em.remove(taturnotb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Taturnotb> findTaturnotbEntities() {
        return findTaturnotbEntities(true, -1, -1);
    }

    public List<Taturnotb> findTaturnotbEntities(int maxResults, int firstResult) {
        return findTaturnotbEntities(false, maxResults, firstResult);
    }

    private List<Taturnotb> findTaturnotbEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Taturnotb.class));
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

    public Taturnotb findTaturnotb(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Taturnotb.class, id);
        } finally {
            em.close();
        }
    }

    public int getTaturnotbCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Taturnotb> rt = cq.from(Taturnotb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
