/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.controladores.mysql;

import com.lam.cenicco.migracion.controladores.mysql.exceptions.NonexistentEntityException;
import com.lam.cenicco.migracion.entidades.mysql.Rhempleadocontactotb;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.lam.cenicco.migracion.entidades.mysql.Rhtipocontactotb;
import com.lam.cenicco.migracion.entidades.mysql.Rhempleadotb;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class RhempleadocontactotbJpaController implements Serializable {

    public RhempleadocontactotbJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rhempleadocontactotb rhempleadocontactotb) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rhtipocontactotb idtipocontacto = rhempleadocontactotb.getIdtipocontacto();
            if (idtipocontacto != null) {
                idtipocontacto = em.getReference(idtipocontacto.getClass(), idtipocontacto.getIdtipocontacto());
                rhempleadocontactotb.setIdtipocontacto(idtipocontacto);
            }
            Rhempleadotb idempleado = rhempleadocontactotb.getIdempleado();
            if (idempleado != null) {
                idempleado = em.getReference(idempleado.getClass(), idempleado.getIdempleado());
                rhempleadocontactotb.setIdempleado(idempleado);
            }
            em.persist(rhempleadocontactotb);
            if (idtipocontacto != null) {
                idtipocontacto.getRhempleadocontactotbList().add(rhempleadocontactotb);
                idtipocontacto = em.merge(idtipocontacto);
            }
            if (idempleado != null) {
                idempleado.getRhempleadocontactotbList().add(rhempleadocontactotb);
                idempleado = em.merge(idempleado);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rhempleadocontactotb rhempleadocontactotb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rhempleadocontactotb persistentRhempleadocontactotb = em.find(Rhempleadocontactotb.class, rhempleadocontactotb.getIdcontacto());
            Rhtipocontactotb idtipocontactoOld = persistentRhempleadocontactotb.getIdtipocontacto();
            Rhtipocontactotb idtipocontactoNew = rhempleadocontactotb.getIdtipocontacto();
            Rhempleadotb idempleadoOld = persistentRhempleadocontactotb.getIdempleado();
            Rhempleadotb idempleadoNew = rhempleadocontactotb.getIdempleado();
            if (idtipocontactoNew != null) {
                idtipocontactoNew = em.getReference(idtipocontactoNew.getClass(), idtipocontactoNew.getIdtipocontacto());
                rhempleadocontactotb.setIdtipocontacto(idtipocontactoNew);
            }
            if (idempleadoNew != null) {
                idempleadoNew = em.getReference(idempleadoNew.getClass(), idempleadoNew.getIdempleado());
                rhempleadocontactotb.setIdempleado(idempleadoNew);
            }
            rhempleadocontactotb = em.merge(rhempleadocontactotb);
            if (idtipocontactoOld != null && !idtipocontactoOld.equals(idtipocontactoNew)) {
                idtipocontactoOld.getRhempleadocontactotbList().remove(rhempleadocontactotb);
                idtipocontactoOld = em.merge(idtipocontactoOld);
            }
            if (idtipocontactoNew != null && !idtipocontactoNew.equals(idtipocontactoOld)) {
                idtipocontactoNew.getRhempleadocontactotbList().add(rhempleadocontactotb);
                idtipocontactoNew = em.merge(idtipocontactoNew);
            }
            if (idempleadoOld != null && !idempleadoOld.equals(idempleadoNew)) {
                idempleadoOld.getRhempleadocontactotbList().remove(rhempleadocontactotb);
                idempleadoOld = em.merge(idempleadoOld);
            }
            if (idempleadoNew != null && !idempleadoNew.equals(idempleadoOld)) {
                idempleadoNew.getRhempleadocontactotbList().add(rhempleadocontactotb);
                idempleadoNew = em.merge(idempleadoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rhempleadocontactotb.getIdcontacto();
                if (findRhempleadocontactotb(id) == null) {
                    throw new NonexistentEntityException("The rhempleadocontactotb with id " + id + " no longer exists.");
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
            Rhempleadocontactotb rhempleadocontactotb;
            try {
                rhempleadocontactotb = em.getReference(Rhempleadocontactotb.class, id);
                rhempleadocontactotb.getIdcontacto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rhempleadocontactotb with id " + id + " no longer exists.", enfe);
            }
            Rhtipocontactotb idtipocontacto = rhempleadocontactotb.getIdtipocontacto();
            if (idtipocontacto != null) {
                idtipocontacto.getRhempleadocontactotbList().remove(rhempleadocontactotb);
                idtipocontacto = em.merge(idtipocontacto);
            }
            Rhempleadotb idempleado = rhempleadocontactotb.getIdempleado();
            if (idempleado != null) {
                idempleado.getRhempleadocontactotbList().remove(rhempleadocontactotb);
                idempleado = em.merge(idempleado);
            }
            em.remove(rhempleadocontactotb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rhempleadocontactotb> findRhempleadocontactotbEntities() {
        return findRhempleadocontactotbEntities(true, -1, -1);
    }

    public List<Rhempleadocontactotb> findRhempleadocontactotbEntities(int maxResults, int firstResult) {
        return findRhempleadocontactotbEntities(false, maxResults, firstResult);
    }

    private List<Rhempleadocontactotb> findRhempleadocontactotbEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rhempleadocontactotb.class));
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

    public Rhempleadocontactotb findRhempleadocontactotb(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rhempleadocontactotb.class, id);
        } finally {
            em.close();
        }
    }

    public int getRhempleadocontactotbCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rhempleadocontactotb> rt = cq.from(Rhempleadocontactotb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
