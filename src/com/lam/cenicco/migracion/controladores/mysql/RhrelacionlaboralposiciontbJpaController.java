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
import com.lam.cenicco.migracion.entidades.mysql.Taturnotb;
import com.lam.cenicco.migracion.entidades.mysql.Rhtiposueldotb;
import com.lam.cenicco.migracion.entidades.mysql.Admpuestotb;
import com.lam.cenicco.migracion.entidades.mysql.Admdepartamentotb;
import com.lam.cenicco.migracion.entidades.mysql.Rhrelacionlaboralposiciontb;
import com.lam.cenicco.migracion.entidades.mysql.Rhrelacionlaboraltb;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class RhrelacionlaboralposiciontbJpaController implements Serializable {

    public RhrelacionlaboralposiciontbJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rhrelacionlaboralposiciontb rhrelacionlaboralposiciontb) {
        if (rhrelacionlaboralposiciontb.getRhrelacionlaboraltbList() == null) {
            rhrelacionlaboralposiciontb.setRhrelacionlaboraltbList(new ArrayList<Rhrelacionlaboraltb>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Taturnotb idturno = rhrelacionlaboralposiciontb.getIdturno();
            if (idturno != null) {
                idturno = em.getReference(idturno.getClass(), idturno.getIdturno());
                rhrelacionlaboralposiciontb.setIdturno(idturno);
            }
            Rhtiposueldotb idtiposueldo = rhrelacionlaboralposiciontb.getIdtiposueldo();
            if (idtiposueldo != null) {
                idtiposueldo = em.getReference(idtiposueldo.getClass(), idtiposueldo.getIdtiposueldo());
                rhrelacionlaboralposiciontb.setIdtiposueldo(idtiposueldo);
            }
            Admpuestotb idpuesto = rhrelacionlaboralposiciontb.getIdpuesto();
            if (idpuesto != null) {
                idpuesto = em.getReference(idpuesto.getClass(), idpuesto.getIdpuesto());
                rhrelacionlaboralposiciontb.setIdpuesto(idpuesto);
            }
            Admdepartamentotb iddepartamento = rhrelacionlaboralposiciontb.getIddepartamento();
            if (iddepartamento != null) {
                iddepartamento = em.getReference(iddepartamento.getClass(), iddepartamento.getIddepartamento());
                rhrelacionlaboralposiciontb.setIddepartamento(iddepartamento);
            }
            List<Rhrelacionlaboraltb> attachedRhrelacionlaboraltbList = new ArrayList<Rhrelacionlaboraltb>();
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListRhrelacionlaboraltbToAttach : rhrelacionlaboralposiciontb.getRhrelacionlaboraltbList()) {
                rhrelacionlaboraltbListRhrelacionlaboraltbToAttach = em.getReference(rhrelacionlaboraltbListRhrelacionlaboraltbToAttach.getClass(), rhrelacionlaboraltbListRhrelacionlaboraltbToAttach.getIdrellab());
                attachedRhrelacionlaboraltbList.add(rhrelacionlaboraltbListRhrelacionlaboraltbToAttach);
            }
            rhrelacionlaboralposiciontb.setRhrelacionlaboraltbList(attachedRhrelacionlaboraltbList);
            em.persist(rhrelacionlaboralposiciontb);
            if (idturno != null) {
                idturno.getRhrelacionlaboralposiciontbList().add(rhrelacionlaboralposiciontb);
                idturno = em.merge(idturno);
            }
            if (idtiposueldo != null) {
                idtiposueldo.getRhrelacionlaboralposiciontbList().add(rhrelacionlaboralposiciontb);
                idtiposueldo = em.merge(idtiposueldo);
            }
            if (idpuesto != null) {
                idpuesto.getRhrelacionlaboralposiciontbList().add(rhrelacionlaboralposiciontb);
                idpuesto = em.merge(idpuesto);
            }
            if (iddepartamento != null) {
                iddepartamento.getRhrelacionlaboralposiciontbList().add(rhrelacionlaboralposiciontb);
                iddepartamento = em.merge(iddepartamento);
            }
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListRhrelacionlaboraltb : rhrelacionlaboralposiciontb.getRhrelacionlaboraltbList()) {
                Rhrelacionlaboralposiciontb oldIdrelacionlaboralposicionOfRhrelacionlaboraltbListRhrelacionlaboraltb = rhrelacionlaboraltbListRhrelacionlaboraltb.getIdrelacionlaboralposicion();
                rhrelacionlaboraltbListRhrelacionlaboraltb.setIdrelacionlaboralposicion(rhrelacionlaboralposiciontb);
                rhrelacionlaboraltbListRhrelacionlaboraltb = em.merge(rhrelacionlaboraltbListRhrelacionlaboraltb);
                if (oldIdrelacionlaboralposicionOfRhrelacionlaboraltbListRhrelacionlaboraltb != null) {
                    oldIdrelacionlaboralposicionOfRhrelacionlaboraltbListRhrelacionlaboraltb.getRhrelacionlaboraltbList().remove(rhrelacionlaboraltbListRhrelacionlaboraltb);
                    oldIdrelacionlaboralposicionOfRhrelacionlaboraltbListRhrelacionlaboraltb = em.merge(oldIdrelacionlaboralposicionOfRhrelacionlaboraltbListRhrelacionlaboraltb);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rhrelacionlaboralposiciontb rhrelacionlaboralposiciontb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rhrelacionlaboralposiciontb persistentRhrelacionlaboralposiciontb = em.find(Rhrelacionlaboralposiciontb.class, rhrelacionlaboralposiciontb.getIdrelacionlaboralposicion());
            Taturnotb idturnoOld = persistentRhrelacionlaboralposiciontb.getIdturno();
            Taturnotb idturnoNew = rhrelacionlaboralposiciontb.getIdturno();
            Rhtiposueldotb idtiposueldoOld = persistentRhrelacionlaboralposiciontb.getIdtiposueldo();
            Rhtiposueldotb idtiposueldoNew = rhrelacionlaboralposiciontb.getIdtiposueldo();
            Admpuestotb idpuestoOld = persistentRhrelacionlaboralposiciontb.getIdpuesto();
            Admpuestotb idpuestoNew = rhrelacionlaboralposiciontb.getIdpuesto();
            Admdepartamentotb iddepartamentoOld = persistentRhrelacionlaboralposiciontb.getIddepartamento();
            Admdepartamentotb iddepartamentoNew = rhrelacionlaboralposiciontb.getIddepartamento();
            List<Rhrelacionlaboraltb> rhrelacionlaboraltbListOld = persistentRhrelacionlaboralposiciontb.getRhrelacionlaboraltbList();
            List<Rhrelacionlaboraltb> rhrelacionlaboraltbListNew = rhrelacionlaboralposiciontb.getRhrelacionlaboraltbList();
            if (idturnoNew != null) {
                idturnoNew = em.getReference(idturnoNew.getClass(), idturnoNew.getIdturno());
                rhrelacionlaboralposiciontb.setIdturno(idturnoNew);
            }
            if (idtiposueldoNew != null) {
                idtiposueldoNew = em.getReference(idtiposueldoNew.getClass(), idtiposueldoNew.getIdtiposueldo());
                rhrelacionlaboralposiciontb.setIdtiposueldo(idtiposueldoNew);
            }
            if (idpuestoNew != null) {
                idpuestoNew = em.getReference(idpuestoNew.getClass(), idpuestoNew.getIdpuesto());
                rhrelacionlaboralposiciontb.setIdpuesto(idpuestoNew);
            }
            if (iddepartamentoNew != null) {
                iddepartamentoNew = em.getReference(iddepartamentoNew.getClass(), iddepartamentoNew.getIddepartamento());
                rhrelacionlaboralposiciontb.setIddepartamento(iddepartamentoNew);
            }
            List<Rhrelacionlaboraltb> attachedRhrelacionlaboraltbListNew = new ArrayList<Rhrelacionlaboraltb>();
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach : rhrelacionlaboraltbListNew) {
                rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach = em.getReference(rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach.getClass(), rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach.getIdrellab());
                attachedRhrelacionlaboraltbListNew.add(rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach);
            }
            rhrelacionlaboraltbListNew = attachedRhrelacionlaboraltbListNew;
            rhrelacionlaboralposiciontb.setRhrelacionlaboraltbList(rhrelacionlaboraltbListNew);
            rhrelacionlaboralposiciontb = em.merge(rhrelacionlaboralposiciontb);
            if (idturnoOld != null && !idturnoOld.equals(idturnoNew)) {
                idturnoOld.getRhrelacionlaboralposiciontbList().remove(rhrelacionlaboralposiciontb);
                idturnoOld = em.merge(idturnoOld);
            }
            if (idturnoNew != null && !idturnoNew.equals(idturnoOld)) {
                idturnoNew.getRhrelacionlaboralposiciontbList().add(rhrelacionlaboralposiciontb);
                idturnoNew = em.merge(idturnoNew);
            }
            if (idtiposueldoOld != null && !idtiposueldoOld.equals(idtiposueldoNew)) {
                idtiposueldoOld.getRhrelacionlaboralposiciontbList().remove(rhrelacionlaboralposiciontb);
                idtiposueldoOld = em.merge(idtiposueldoOld);
            }
            if (idtiposueldoNew != null && !idtiposueldoNew.equals(idtiposueldoOld)) {
                idtiposueldoNew.getRhrelacionlaboralposiciontbList().add(rhrelacionlaboralposiciontb);
                idtiposueldoNew = em.merge(idtiposueldoNew);
            }
            if (idpuestoOld != null && !idpuestoOld.equals(idpuestoNew)) {
                idpuestoOld.getRhrelacionlaboralposiciontbList().remove(rhrelacionlaboralposiciontb);
                idpuestoOld = em.merge(idpuestoOld);
            }
            if (idpuestoNew != null && !idpuestoNew.equals(idpuestoOld)) {
                idpuestoNew.getRhrelacionlaboralposiciontbList().add(rhrelacionlaboralposiciontb);
                idpuestoNew = em.merge(idpuestoNew);
            }
            if (iddepartamentoOld != null && !iddepartamentoOld.equals(iddepartamentoNew)) {
                iddepartamentoOld.getRhrelacionlaboralposiciontbList().remove(rhrelacionlaboralposiciontb);
                iddepartamentoOld = em.merge(iddepartamentoOld);
            }
            if (iddepartamentoNew != null && !iddepartamentoNew.equals(iddepartamentoOld)) {
                iddepartamentoNew.getRhrelacionlaboralposiciontbList().add(rhrelacionlaboralposiciontb);
                iddepartamentoNew = em.merge(iddepartamentoNew);
            }
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListOldRhrelacionlaboraltb : rhrelacionlaboraltbListOld) {
                if (!rhrelacionlaboraltbListNew.contains(rhrelacionlaboraltbListOldRhrelacionlaboraltb)) {
                    rhrelacionlaboraltbListOldRhrelacionlaboraltb.setIdrelacionlaboralposicion(null);
                    rhrelacionlaboraltbListOldRhrelacionlaboraltb = em.merge(rhrelacionlaboraltbListOldRhrelacionlaboraltb);
                }
            }
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListNewRhrelacionlaboraltb : rhrelacionlaboraltbListNew) {
                if (!rhrelacionlaboraltbListOld.contains(rhrelacionlaboraltbListNewRhrelacionlaboraltb)) {
                    Rhrelacionlaboralposiciontb oldIdrelacionlaboralposicionOfRhrelacionlaboraltbListNewRhrelacionlaboraltb = rhrelacionlaboraltbListNewRhrelacionlaboraltb.getIdrelacionlaboralposicion();
                    rhrelacionlaboraltbListNewRhrelacionlaboraltb.setIdrelacionlaboralposicion(rhrelacionlaboralposiciontb);
                    rhrelacionlaboraltbListNewRhrelacionlaboraltb = em.merge(rhrelacionlaboraltbListNewRhrelacionlaboraltb);
                    if (oldIdrelacionlaboralposicionOfRhrelacionlaboraltbListNewRhrelacionlaboraltb != null && !oldIdrelacionlaboralposicionOfRhrelacionlaboraltbListNewRhrelacionlaboraltb.equals(rhrelacionlaboralposiciontb)) {
                        oldIdrelacionlaboralposicionOfRhrelacionlaboraltbListNewRhrelacionlaboraltb.getRhrelacionlaboraltbList().remove(rhrelacionlaboraltbListNewRhrelacionlaboraltb);
                        oldIdrelacionlaboralposicionOfRhrelacionlaboraltbListNewRhrelacionlaboraltb = em.merge(oldIdrelacionlaboralposicionOfRhrelacionlaboraltbListNewRhrelacionlaboraltb);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rhrelacionlaboralposiciontb.getIdrelacionlaboralposicion();
                if (findRhrelacionlaboralposiciontb(id) == null) {
                    throw new NonexistentEntityException("The rhrelacionlaboralposiciontb with id " + id + " no longer exists.");
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
            Rhrelacionlaboralposiciontb rhrelacionlaboralposiciontb;
            try {
                rhrelacionlaboralposiciontb = em.getReference(Rhrelacionlaboralposiciontb.class, id);
                rhrelacionlaboralposiciontb.getIdrelacionlaboralposicion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rhrelacionlaboralposiciontb with id " + id + " no longer exists.", enfe);
            }
            Taturnotb idturno = rhrelacionlaboralposiciontb.getIdturno();
            if (idturno != null) {
                idturno.getRhrelacionlaboralposiciontbList().remove(rhrelacionlaboralposiciontb);
                idturno = em.merge(idturno);
            }
            Rhtiposueldotb idtiposueldo = rhrelacionlaboralposiciontb.getIdtiposueldo();
            if (idtiposueldo != null) {
                idtiposueldo.getRhrelacionlaboralposiciontbList().remove(rhrelacionlaboralposiciontb);
                idtiposueldo = em.merge(idtiposueldo);
            }
            Admpuestotb idpuesto = rhrelacionlaboralposiciontb.getIdpuesto();
            if (idpuesto != null) {
                idpuesto.getRhrelacionlaboralposiciontbList().remove(rhrelacionlaboralposiciontb);
                idpuesto = em.merge(idpuesto);
            }
            Admdepartamentotb iddepartamento = rhrelacionlaboralposiciontb.getIddepartamento();
            if (iddepartamento != null) {
                iddepartamento.getRhrelacionlaboralposiciontbList().remove(rhrelacionlaboralposiciontb);
                iddepartamento = em.merge(iddepartamento);
            }
            List<Rhrelacionlaboraltb> rhrelacionlaboraltbList = rhrelacionlaboralposiciontb.getRhrelacionlaboraltbList();
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListRhrelacionlaboraltb : rhrelacionlaboraltbList) {
                rhrelacionlaboraltbListRhrelacionlaboraltb.setIdrelacionlaboralposicion(null);
                rhrelacionlaboraltbListRhrelacionlaboraltb = em.merge(rhrelacionlaboraltbListRhrelacionlaboraltb);
            }
            em.remove(rhrelacionlaboralposiciontb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rhrelacionlaboralposiciontb> findRhrelacionlaboralposiciontbEntities() {
        return findRhrelacionlaboralposiciontbEntities(true, -1, -1);
    }

    public List<Rhrelacionlaboralposiciontb> findRhrelacionlaboralposiciontbEntities(int maxResults, int firstResult) {
        return findRhrelacionlaboralposiciontbEntities(false, maxResults, firstResult);
    }

    private List<Rhrelacionlaboralposiciontb> findRhrelacionlaboralposiciontbEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rhrelacionlaboralposiciontb.class));
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

    public Rhrelacionlaboralposiciontb findRhrelacionlaboralposiciontb(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rhrelacionlaboralposiciontb.class, id);
        } finally {
            em.close();
        }
    }

    public int getRhrelacionlaboralposiciontbCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rhrelacionlaboralposiciontb> rt = cq.from(Rhrelacionlaboralposiciontb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
