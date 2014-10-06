/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.controladores.mysql;

import com.cenicco.migracion.util.JpaUtil;
import com.lam.cenicco.migracion.controladores.mysql.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.lam.cenicco.migracion.entidades.mysql.Catmunicipiotb;
import com.lam.cenicco.migracion.entidades.mysql.Catasentamientotb;
import com.lam.cenicco.migracion.entidades.mysql.Catciudadtb;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class CatciudadtbJpaController implements Serializable {

    public CatciudadtbJpaController() {
        this.emf = JpaUtil.getInstance().createEntityManagerCeniccoMysql().getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Catciudadtb catciudadtb) {
        if (catciudadtb.getCatasentamientotbList() == null) {
            catciudadtb.setCatasentamientotbList(new ArrayList<Catasentamientotb>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Catmunicipiotb idmunicipio = catciudadtb.getIdmunicipio();
            if (idmunicipio != null) {
                idmunicipio = em.getReference(idmunicipio.getClass(), idmunicipio.getIdmunicipio());
                catciudadtb.setIdmunicipio(idmunicipio);
            }
            List<Catasentamientotb> attachedCatasentamientotbList = new ArrayList<Catasentamientotb>();
            for (Catasentamientotb catasentamientotbListCatasentamientotbToAttach : catciudadtb.getCatasentamientotbList()) {
                catasentamientotbListCatasentamientotbToAttach = em.getReference(catasentamientotbListCatasentamientotbToAttach.getClass(), catasentamientotbListCatasentamientotbToAttach.getIdasentamiento());
                attachedCatasentamientotbList.add(catasentamientotbListCatasentamientotbToAttach);
            }
            catciudadtb.setCatasentamientotbList(attachedCatasentamientotbList);
            em.persist(catciudadtb);
            if (idmunicipio != null) {
                idmunicipio.getCatciudadtbList().add(catciudadtb);
                idmunicipio = em.merge(idmunicipio);
            }
            for (Catasentamientotb catasentamientotbListCatasentamientotb : catciudadtb.getCatasentamientotbList()) {
                Catciudadtb oldIdciudadOfCatasentamientotbListCatasentamientotb = catasentamientotbListCatasentamientotb.getIdciudad();
                catasentamientotbListCatasentamientotb.setIdciudad(catciudadtb);
                catasentamientotbListCatasentamientotb = em.merge(catasentamientotbListCatasentamientotb);
                if (oldIdciudadOfCatasentamientotbListCatasentamientotb != null) {
                    oldIdciudadOfCatasentamientotbListCatasentamientotb.getCatasentamientotbList().remove(catasentamientotbListCatasentamientotb);
                    oldIdciudadOfCatasentamientotbListCatasentamientotb = em.merge(oldIdciudadOfCatasentamientotbListCatasentamientotb);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Catciudadtb catciudadtb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Catciudadtb persistentCatciudadtb = em.find(Catciudadtb.class, catciudadtb.getIdciudad());
            Catmunicipiotb idmunicipioOld = persistentCatciudadtb.getIdmunicipio();
            Catmunicipiotb idmunicipioNew = catciudadtb.getIdmunicipio();
            List<Catasentamientotb> catasentamientotbListOld = persistentCatciudadtb.getCatasentamientotbList();
            List<Catasentamientotb> catasentamientotbListNew = catciudadtb.getCatasentamientotbList();
            if (idmunicipioNew != null) {
                idmunicipioNew = em.getReference(idmunicipioNew.getClass(), idmunicipioNew.getIdmunicipio());
                catciudadtb.setIdmunicipio(idmunicipioNew);
            }
            List<Catasentamientotb> attachedCatasentamientotbListNew = new ArrayList<Catasentamientotb>();
            for (Catasentamientotb catasentamientotbListNewCatasentamientotbToAttach : catasentamientotbListNew) {
                catasentamientotbListNewCatasentamientotbToAttach = em.getReference(catasentamientotbListNewCatasentamientotbToAttach.getClass(), catasentamientotbListNewCatasentamientotbToAttach.getIdasentamiento());
                attachedCatasentamientotbListNew.add(catasentamientotbListNewCatasentamientotbToAttach);
            }
            catasentamientotbListNew = attachedCatasentamientotbListNew;
            catciudadtb.setCatasentamientotbList(catasentamientotbListNew);
            catciudadtb = em.merge(catciudadtb);
            if (idmunicipioOld != null && !idmunicipioOld.equals(idmunicipioNew)) {
                idmunicipioOld.getCatciudadtbList().remove(catciudadtb);
                idmunicipioOld = em.merge(idmunicipioOld);
            }
            if (idmunicipioNew != null && !idmunicipioNew.equals(idmunicipioOld)) {
                idmunicipioNew.getCatciudadtbList().add(catciudadtb);
                idmunicipioNew = em.merge(idmunicipioNew);
            }
            for (Catasentamientotb catasentamientotbListOldCatasentamientotb : catasentamientotbListOld) {
                if (!catasentamientotbListNew.contains(catasentamientotbListOldCatasentamientotb)) {
                    catasentamientotbListOldCatasentamientotb.setIdciudad(null);
                    catasentamientotbListOldCatasentamientotb = em.merge(catasentamientotbListOldCatasentamientotb);
                }
            }
            for (Catasentamientotb catasentamientotbListNewCatasentamientotb : catasentamientotbListNew) {
                if (!catasentamientotbListOld.contains(catasentamientotbListNewCatasentamientotb)) {
                    Catciudadtb oldIdciudadOfCatasentamientotbListNewCatasentamientotb = catasentamientotbListNewCatasentamientotb.getIdciudad();
                    catasentamientotbListNewCatasentamientotb.setIdciudad(catciudadtb);
                    catasentamientotbListNewCatasentamientotb = em.merge(catasentamientotbListNewCatasentamientotb);
                    if (oldIdciudadOfCatasentamientotbListNewCatasentamientotb != null && !oldIdciudadOfCatasentamientotbListNewCatasentamientotb.equals(catciudadtb)) {
                        oldIdciudadOfCatasentamientotbListNewCatasentamientotb.getCatasentamientotbList().remove(catasentamientotbListNewCatasentamientotb);
                        oldIdciudadOfCatasentamientotbListNewCatasentamientotb = em.merge(oldIdciudadOfCatasentamientotbListNewCatasentamientotb);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = catciudadtb.getIdciudad();
                if (findCatciudadtb(id) == null) {
                    throw new NonexistentEntityException("The catciudadtb with id " + id + " no longer exists.");
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
            Catciudadtb catciudadtb;
            try {
                catciudadtb = em.getReference(Catciudadtb.class, id);
                catciudadtb.getIdciudad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The catciudadtb with id " + id + " no longer exists.", enfe);
            }
            Catmunicipiotb idmunicipio = catciudadtb.getIdmunicipio();
            if (idmunicipio != null) {
                idmunicipio.getCatciudadtbList().remove(catciudadtb);
                idmunicipio = em.merge(idmunicipio);
            }
            List<Catasentamientotb> catasentamientotbList = catciudadtb.getCatasentamientotbList();
            for (Catasentamientotb catasentamientotbListCatasentamientotb : catasentamientotbList) {
                catasentamientotbListCatasentamientotb.setIdciudad(null);
                catasentamientotbListCatasentamientotb = em.merge(catasentamientotbListCatasentamientotb);
            }
            em.remove(catciudadtb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Catciudadtb> findCatciudadtbEntities() {
        return findCatciudadtbEntities(true, -1, -1);
    }

    public List<Catciudadtb> findCatciudadtbEntities(int maxResults, int firstResult) {
        return findCatciudadtbEntities(false, maxResults, firstResult);
    }

    private List<Catciudadtb> findCatciudadtbEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Catciudadtb.class));
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

    public Catciudadtb findCatciudadtb(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Catciudadtb.class, id);
        } finally {
            em.close();
        }
    }

    public int getCatciudadtbCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Catciudadtb> rt = cq.from(Catciudadtb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Catciudadtb findByCiudadMunicipioEstado(String codigoCiudad) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Query q = em.createNamedQuery("Catciudadtb.findByCiudad");
            q.setParameter("ciudad", codigoCiudad);
            return (Catciudadtb) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JpaUtil.getInstance().closeEntityManager(em);
        }
    }
}
