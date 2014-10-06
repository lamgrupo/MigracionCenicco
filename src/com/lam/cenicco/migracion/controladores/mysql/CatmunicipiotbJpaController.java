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
import com.lam.cenicco.migracion.entidades.mysql.Catestadotb;
import com.lam.cenicco.migracion.entidades.mysql.Catciudadtb;
import com.lam.cenicco.migracion.entidades.mysql.Catmunicipiotb;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class CatmunicipiotbJpaController implements Serializable {

    public CatmunicipiotbJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Catmunicipiotb catmunicipiotb) {
        if (catmunicipiotb.getCatciudadtbList() == null) {
            catmunicipiotb.setCatciudadtbList(new ArrayList<Catciudadtb>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Catestadotb idestado = catmunicipiotb.getIdestado();
            if (idestado != null) {
                idestado = em.getReference(idestado.getClass(), idestado.getIdestado());
                catmunicipiotb.setIdestado(idestado);
            }
            List<Catciudadtb> attachedCatciudadtbList = new ArrayList<Catciudadtb>();
            for (Catciudadtb catciudadtbListCatciudadtbToAttach : catmunicipiotb.getCatciudadtbList()) {
                catciudadtbListCatciudadtbToAttach = em.getReference(catciudadtbListCatciudadtbToAttach.getClass(), catciudadtbListCatciudadtbToAttach.getIdciudad());
                attachedCatciudadtbList.add(catciudadtbListCatciudadtbToAttach);
            }
            catmunicipiotb.setCatciudadtbList(attachedCatciudadtbList);
            em.persist(catmunicipiotb);
            if (idestado != null) {
                idestado.getCatmunicipiotbList().add(catmunicipiotb);
                idestado = em.merge(idestado);
            }
            for (Catciudadtb catciudadtbListCatciudadtb : catmunicipiotb.getCatciudadtbList()) {
                Catmunicipiotb oldIdmunicipioOfCatciudadtbListCatciudadtb = catciudadtbListCatciudadtb.getIdmunicipio();
                catciudadtbListCatciudadtb.setIdmunicipio(catmunicipiotb);
                catciudadtbListCatciudadtb = em.merge(catciudadtbListCatciudadtb);
                if (oldIdmunicipioOfCatciudadtbListCatciudadtb != null) {
                    oldIdmunicipioOfCatciudadtbListCatciudadtb.getCatciudadtbList().remove(catciudadtbListCatciudadtb);
                    oldIdmunicipioOfCatciudadtbListCatciudadtb = em.merge(oldIdmunicipioOfCatciudadtbListCatciudadtb);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Catmunicipiotb catmunicipiotb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Catmunicipiotb persistentCatmunicipiotb = em.find(Catmunicipiotb.class, catmunicipiotb.getIdmunicipio());
            Catestadotb idestadoOld = persistentCatmunicipiotb.getIdestado();
            Catestadotb idestadoNew = catmunicipiotb.getIdestado();
            List<Catciudadtb> catciudadtbListOld = persistentCatmunicipiotb.getCatciudadtbList();
            List<Catciudadtb> catciudadtbListNew = catmunicipiotb.getCatciudadtbList();
            if (idestadoNew != null) {
                idestadoNew = em.getReference(idestadoNew.getClass(), idestadoNew.getIdestado());
                catmunicipiotb.setIdestado(idestadoNew);
            }
            List<Catciudadtb> attachedCatciudadtbListNew = new ArrayList<Catciudadtb>();
            for (Catciudadtb catciudadtbListNewCatciudadtbToAttach : catciudadtbListNew) {
                catciudadtbListNewCatciudadtbToAttach = em.getReference(catciudadtbListNewCatciudadtbToAttach.getClass(), catciudadtbListNewCatciudadtbToAttach.getIdciudad());
                attachedCatciudadtbListNew.add(catciudadtbListNewCatciudadtbToAttach);
            }
            catciudadtbListNew = attachedCatciudadtbListNew;
            catmunicipiotb.setCatciudadtbList(catciudadtbListNew);
            catmunicipiotb = em.merge(catmunicipiotb);
            if (idestadoOld != null && !idestadoOld.equals(idestadoNew)) {
                idestadoOld.getCatmunicipiotbList().remove(catmunicipiotb);
                idestadoOld = em.merge(idestadoOld);
            }
            if (idestadoNew != null && !idestadoNew.equals(idestadoOld)) {
                idestadoNew.getCatmunicipiotbList().add(catmunicipiotb);
                idestadoNew = em.merge(idestadoNew);
            }
            for (Catciudadtb catciudadtbListOldCatciudadtb : catciudadtbListOld) {
                if (!catciudadtbListNew.contains(catciudadtbListOldCatciudadtb)) {
                    catciudadtbListOldCatciudadtb.setIdmunicipio(null);
                    catciudadtbListOldCatciudadtb = em.merge(catciudadtbListOldCatciudadtb);
                }
            }
            for (Catciudadtb catciudadtbListNewCatciudadtb : catciudadtbListNew) {
                if (!catciudadtbListOld.contains(catciudadtbListNewCatciudadtb)) {
                    Catmunicipiotb oldIdmunicipioOfCatciudadtbListNewCatciudadtb = catciudadtbListNewCatciudadtb.getIdmunicipio();
                    catciudadtbListNewCatciudadtb.setIdmunicipio(catmunicipiotb);
                    catciudadtbListNewCatciudadtb = em.merge(catciudadtbListNewCatciudadtb);
                    if (oldIdmunicipioOfCatciudadtbListNewCatciudadtb != null && !oldIdmunicipioOfCatciudadtbListNewCatciudadtb.equals(catmunicipiotb)) {
                        oldIdmunicipioOfCatciudadtbListNewCatciudadtb.getCatciudadtbList().remove(catciudadtbListNewCatciudadtb);
                        oldIdmunicipioOfCatciudadtbListNewCatciudadtb = em.merge(oldIdmunicipioOfCatciudadtbListNewCatciudadtb);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = catmunicipiotb.getIdmunicipio();
                if (findCatmunicipiotb(id) == null) {
                    throw new NonexistentEntityException("The catmunicipiotb with id " + id + " no longer exists.");
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
            Catmunicipiotb catmunicipiotb;
            try {
                catmunicipiotb = em.getReference(Catmunicipiotb.class, id);
                catmunicipiotb.getIdmunicipio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The catmunicipiotb with id " + id + " no longer exists.", enfe);
            }
            Catestadotb idestado = catmunicipiotb.getIdestado();
            if (idestado != null) {
                idestado.getCatmunicipiotbList().remove(catmunicipiotb);
                idestado = em.merge(idestado);
            }
            List<Catciudadtb> catciudadtbList = catmunicipiotb.getCatciudadtbList();
            for (Catciudadtb catciudadtbListCatciudadtb : catciudadtbList) {
                catciudadtbListCatciudadtb.setIdmunicipio(null);
                catciudadtbListCatciudadtb = em.merge(catciudadtbListCatciudadtb);
            }
            em.remove(catmunicipiotb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Catmunicipiotb> findCatmunicipiotbEntities() {
        return findCatmunicipiotbEntities(true, -1, -1);
    }

    public List<Catmunicipiotb> findCatmunicipiotbEntities(int maxResults, int firstResult) {
        return findCatmunicipiotbEntities(false, maxResults, firstResult);
    }

    private List<Catmunicipiotb> findCatmunicipiotbEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Catmunicipiotb.class));
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

    public Catmunicipiotb findCatmunicipiotb(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Catmunicipiotb.class, id);
        } finally {
            em.close();
        }
    }

    public int getCatmunicipiotbCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Catmunicipiotb> rt = cq.from(Catmunicipiotb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
