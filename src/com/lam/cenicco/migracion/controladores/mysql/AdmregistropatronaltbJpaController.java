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
import com.lam.cenicco.migracion.entidades.mysql.Admregistropatronaltb;
import com.lam.cenicco.migracion.entidades.mysql.Rhrelacionlaboraltb;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class AdmregistropatronaltbJpaController implements Serializable {

    public AdmregistropatronaltbJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Admregistropatronaltb admregistropatronaltb) {
        if (admregistropatronaltb.getRhrelacionlaboraltbList() == null) {
            admregistropatronaltb.setRhrelacionlaboraltbList(new ArrayList<Rhrelacionlaboraltb>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Admcompaniatb idcompania = admregistropatronaltb.getIdcompania();
            if (idcompania != null) {
                idcompania = em.getReference(idcompania.getClass(), idcompania.getIdcompania());
                admregistropatronaltb.setIdcompania(idcompania);
            }
            List<Rhrelacionlaboraltb> attachedRhrelacionlaboraltbList = new ArrayList<Rhrelacionlaboraltb>();
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListRhrelacionlaboraltbToAttach : admregistropatronaltb.getRhrelacionlaboraltbList()) {
                rhrelacionlaboraltbListRhrelacionlaboraltbToAttach = em.getReference(rhrelacionlaboraltbListRhrelacionlaboraltbToAttach.getClass(), rhrelacionlaboraltbListRhrelacionlaboraltbToAttach.getIdrellab());
                attachedRhrelacionlaboraltbList.add(rhrelacionlaboraltbListRhrelacionlaboraltbToAttach);
            }
            admregistropatronaltb.setRhrelacionlaboraltbList(attachedRhrelacionlaboraltbList);
            em.persist(admregistropatronaltb);
            if (idcompania != null) {
                idcompania.getAdmregistropatronaltbList().add(admregistropatronaltb);
                idcompania = em.merge(idcompania);
            }
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListRhrelacionlaboraltb : admregistropatronaltb.getRhrelacionlaboraltbList()) {
                Admregistropatronaltb oldIdregistropatronalOfRhrelacionlaboraltbListRhrelacionlaboraltb = rhrelacionlaboraltbListRhrelacionlaboraltb.getIdregistropatronal();
                rhrelacionlaboraltbListRhrelacionlaboraltb.setIdregistropatronal(admregistropatronaltb);
                rhrelacionlaboraltbListRhrelacionlaboraltb = em.merge(rhrelacionlaboraltbListRhrelacionlaboraltb);
                if (oldIdregistropatronalOfRhrelacionlaboraltbListRhrelacionlaboraltb != null) {
                    oldIdregistropatronalOfRhrelacionlaboraltbListRhrelacionlaboraltb.getRhrelacionlaboraltbList().remove(rhrelacionlaboraltbListRhrelacionlaboraltb);
                    oldIdregistropatronalOfRhrelacionlaboraltbListRhrelacionlaboraltb = em.merge(oldIdregistropatronalOfRhrelacionlaboraltbListRhrelacionlaboraltb);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Admregistropatronaltb admregistropatronaltb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Admregistropatronaltb persistentAdmregistropatronaltb = em.find(Admregistropatronaltb.class, admregistropatronaltb.getIdregistropatronal());
            Admcompaniatb idcompaniaOld = persistentAdmregistropatronaltb.getIdcompania();
            Admcompaniatb idcompaniaNew = admregistropatronaltb.getIdcompania();
            List<Rhrelacionlaboraltb> rhrelacionlaboraltbListOld = persistentAdmregistropatronaltb.getRhrelacionlaboraltbList();
            List<Rhrelacionlaboraltb> rhrelacionlaboraltbListNew = admregistropatronaltb.getRhrelacionlaboraltbList();
            if (idcompaniaNew != null) {
                idcompaniaNew = em.getReference(idcompaniaNew.getClass(), idcompaniaNew.getIdcompania());
                admregistropatronaltb.setIdcompania(idcompaniaNew);
            }
            List<Rhrelacionlaboraltb> attachedRhrelacionlaboraltbListNew = new ArrayList<Rhrelacionlaboraltb>();
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach : rhrelacionlaboraltbListNew) {
                rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach = em.getReference(rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach.getClass(), rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach.getIdrellab());
                attachedRhrelacionlaboraltbListNew.add(rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach);
            }
            rhrelacionlaboraltbListNew = attachedRhrelacionlaboraltbListNew;
            admregistropatronaltb.setRhrelacionlaboraltbList(rhrelacionlaboraltbListNew);
            admregistropatronaltb = em.merge(admregistropatronaltb);
            if (idcompaniaOld != null && !idcompaniaOld.equals(idcompaniaNew)) {
                idcompaniaOld.getAdmregistropatronaltbList().remove(admregistropatronaltb);
                idcompaniaOld = em.merge(idcompaniaOld);
            }
            if (idcompaniaNew != null && !idcompaniaNew.equals(idcompaniaOld)) {
                idcompaniaNew.getAdmregistropatronaltbList().add(admregistropatronaltb);
                idcompaniaNew = em.merge(idcompaniaNew);
            }
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListOldRhrelacionlaboraltb : rhrelacionlaboraltbListOld) {
                if (!rhrelacionlaboraltbListNew.contains(rhrelacionlaboraltbListOldRhrelacionlaboraltb)) {
                    rhrelacionlaboraltbListOldRhrelacionlaboraltb.setIdregistropatronal(null);
                    rhrelacionlaboraltbListOldRhrelacionlaboraltb = em.merge(rhrelacionlaboraltbListOldRhrelacionlaboraltb);
                }
            }
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListNewRhrelacionlaboraltb : rhrelacionlaboraltbListNew) {
                if (!rhrelacionlaboraltbListOld.contains(rhrelacionlaboraltbListNewRhrelacionlaboraltb)) {
                    Admregistropatronaltb oldIdregistropatronalOfRhrelacionlaboraltbListNewRhrelacionlaboraltb = rhrelacionlaboraltbListNewRhrelacionlaboraltb.getIdregistropatronal();
                    rhrelacionlaboraltbListNewRhrelacionlaboraltb.setIdregistropatronal(admregistropatronaltb);
                    rhrelacionlaboraltbListNewRhrelacionlaboraltb = em.merge(rhrelacionlaboraltbListNewRhrelacionlaboraltb);
                    if (oldIdregistropatronalOfRhrelacionlaboraltbListNewRhrelacionlaboraltb != null && !oldIdregistropatronalOfRhrelacionlaboraltbListNewRhrelacionlaboraltb.equals(admregistropatronaltb)) {
                        oldIdregistropatronalOfRhrelacionlaboraltbListNewRhrelacionlaboraltb.getRhrelacionlaboraltbList().remove(rhrelacionlaboraltbListNewRhrelacionlaboraltb);
                        oldIdregistropatronalOfRhrelacionlaboraltbListNewRhrelacionlaboraltb = em.merge(oldIdregistropatronalOfRhrelacionlaboraltbListNewRhrelacionlaboraltb);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = admregistropatronaltb.getIdregistropatronal();
                if (findAdmregistropatronaltb(id) == null) {
                    throw new NonexistentEntityException("The admregistropatronaltb with id " + id + " no longer exists.");
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
            Admregistropatronaltb admregistropatronaltb;
            try {
                admregistropatronaltb = em.getReference(Admregistropatronaltb.class, id);
                admregistropatronaltb.getIdregistropatronal();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The admregistropatronaltb with id " + id + " no longer exists.", enfe);
            }
            Admcompaniatb idcompania = admregistropatronaltb.getIdcompania();
            if (idcompania != null) {
                idcompania.getAdmregistropatronaltbList().remove(admregistropatronaltb);
                idcompania = em.merge(idcompania);
            }
            List<Rhrelacionlaboraltb> rhrelacionlaboraltbList = admregistropatronaltb.getRhrelacionlaboraltbList();
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListRhrelacionlaboraltb : rhrelacionlaboraltbList) {
                rhrelacionlaboraltbListRhrelacionlaboraltb.setIdregistropatronal(null);
                rhrelacionlaboraltbListRhrelacionlaboraltb = em.merge(rhrelacionlaboraltbListRhrelacionlaboraltb);
            }
            em.remove(admregistropatronaltb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Admregistropatronaltb> findAdmregistropatronaltbEntities() {
        return findAdmregistropatronaltbEntities(true, -1, -1);
    }

    public List<Admregistropatronaltb> findAdmregistropatronaltbEntities(int maxResults, int firstResult) {
        return findAdmregistropatronaltbEntities(false, maxResults, firstResult);
    }

    private List<Admregistropatronaltb> findAdmregistropatronaltbEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Admregistropatronaltb.class));
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

    public Admregistropatronaltb findAdmregistropatronaltb(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Admregistropatronaltb.class, id);
        } finally {
            em.close();
        }
    }

    public int getAdmregistropatronaltbCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Admregistropatronaltb> rt = cq.from(Admregistropatronaltb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
