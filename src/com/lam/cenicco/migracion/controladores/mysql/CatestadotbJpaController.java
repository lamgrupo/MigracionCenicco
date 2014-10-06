/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.controladores.mysql;

import com.lam.cenicco.migracion.controladores.mysql.exceptions.NonexistentEntityException;
import com.lam.cenicco.migracion.entidades.mysql.Catestadotb;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.lam.cenicco.migracion.entidades.mysql.Catpaistb;
import com.lam.cenicco.migracion.entidades.mysql.Catmunicipiotb;
import java.util.ArrayList;
import java.util.List;
import com.lam.cenicco.migracion.entidades.mysql.Rhempleadotb;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class CatestadotbJpaController implements Serializable {

    public CatestadotbJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Catestadotb catestadotb) {
        if (catestadotb.getCatmunicipiotbList() == null) {
            catestadotb.setCatmunicipiotbList(new ArrayList<Catmunicipiotb>());
        }
        if (catestadotb.getRhempleadotbList() == null) {
            catestadotb.setRhempleadotbList(new ArrayList<Rhempleadotb>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Catpaistb idpais = catestadotb.getIdpais();
            if (idpais != null) {
                idpais = em.getReference(idpais.getClass(), idpais.getIdpais());
                catestadotb.setIdpais(idpais);
            }
            List<Catmunicipiotb> attachedCatmunicipiotbList = new ArrayList<Catmunicipiotb>();
            for (Catmunicipiotb catmunicipiotbListCatmunicipiotbToAttach : catestadotb.getCatmunicipiotbList()) {
                catmunicipiotbListCatmunicipiotbToAttach = em.getReference(catmunicipiotbListCatmunicipiotbToAttach.getClass(), catmunicipiotbListCatmunicipiotbToAttach.getIdmunicipio());
                attachedCatmunicipiotbList.add(catmunicipiotbListCatmunicipiotbToAttach);
            }
            catestadotb.setCatmunicipiotbList(attachedCatmunicipiotbList);
            List<Rhempleadotb> attachedRhempleadotbList = new ArrayList<Rhempleadotb>();
            for (Rhempleadotb rhempleadotbListRhempleadotbToAttach : catestadotb.getRhempleadotbList()) {
                rhempleadotbListRhempleadotbToAttach = em.getReference(rhempleadotbListRhempleadotbToAttach.getClass(), rhempleadotbListRhempleadotbToAttach.getIdempleado());
                attachedRhempleadotbList.add(rhempleadotbListRhempleadotbToAttach);
            }
            catestadotb.setRhempleadotbList(attachedRhempleadotbList);
            em.persist(catestadotb);
            if (idpais != null) {
                idpais.getCatestadotbList().add(catestadotb);
                idpais = em.merge(idpais);
            }
            for (Catmunicipiotb catmunicipiotbListCatmunicipiotb : catestadotb.getCatmunicipiotbList()) {
                Catestadotb oldIdestadoOfCatmunicipiotbListCatmunicipiotb = catmunicipiotbListCatmunicipiotb.getIdestado();
                catmunicipiotbListCatmunicipiotb.setIdestado(catestadotb);
                catmunicipiotbListCatmunicipiotb = em.merge(catmunicipiotbListCatmunicipiotb);
                if (oldIdestadoOfCatmunicipiotbListCatmunicipiotb != null) {
                    oldIdestadoOfCatmunicipiotbListCatmunicipiotb.getCatmunicipiotbList().remove(catmunicipiotbListCatmunicipiotb);
                    oldIdestadoOfCatmunicipiotbListCatmunicipiotb = em.merge(oldIdestadoOfCatmunicipiotbListCatmunicipiotb);
                }
            }
            for (Rhempleadotb rhempleadotbListRhempleadotb : catestadotb.getRhempleadotbList()) {
                Catestadotb oldIdestadonacimientoOfRhempleadotbListRhempleadotb = rhempleadotbListRhempleadotb.getIdestadonacimiento();
                rhempleadotbListRhempleadotb.setIdestadonacimiento(catestadotb);
                rhempleadotbListRhempleadotb = em.merge(rhempleadotbListRhempleadotb);
                if (oldIdestadonacimientoOfRhempleadotbListRhempleadotb != null) {
                    oldIdestadonacimientoOfRhempleadotbListRhempleadotb.getRhempleadotbList().remove(rhempleadotbListRhempleadotb);
                    oldIdestadonacimientoOfRhempleadotbListRhempleadotb = em.merge(oldIdestadonacimientoOfRhempleadotbListRhempleadotb);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Catestadotb catestadotb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Catestadotb persistentCatestadotb = em.find(Catestadotb.class, catestadotb.getIdestado());
            Catpaistb idpaisOld = persistentCatestadotb.getIdpais();
            Catpaistb idpaisNew = catestadotb.getIdpais();
            List<Catmunicipiotb> catmunicipiotbListOld = persistentCatestadotb.getCatmunicipiotbList();
            List<Catmunicipiotb> catmunicipiotbListNew = catestadotb.getCatmunicipiotbList();
            List<Rhempleadotb> rhempleadotbListOld = persistentCatestadotb.getRhempleadotbList();
            List<Rhempleadotb> rhempleadotbListNew = catestadotb.getRhempleadotbList();
            if (idpaisNew != null) {
                idpaisNew = em.getReference(idpaisNew.getClass(), idpaisNew.getIdpais());
                catestadotb.setIdpais(idpaisNew);
            }
            List<Catmunicipiotb> attachedCatmunicipiotbListNew = new ArrayList<Catmunicipiotb>();
            for (Catmunicipiotb catmunicipiotbListNewCatmunicipiotbToAttach : catmunicipiotbListNew) {
                catmunicipiotbListNewCatmunicipiotbToAttach = em.getReference(catmunicipiotbListNewCatmunicipiotbToAttach.getClass(), catmunicipiotbListNewCatmunicipiotbToAttach.getIdmunicipio());
                attachedCatmunicipiotbListNew.add(catmunicipiotbListNewCatmunicipiotbToAttach);
            }
            catmunicipiotbListNew = attachedCatmunicipiotbListNew;
            catestadotb.setCatmunicipiotbList(catmunicipiotbListNew);
            List<Rhempleadotb> attachedRhempleadotbListNew = new ArrayList<Rhempleadotb>();
            for (Rhempleadotb rhempleadotbListNewRhempleadotbToAttach : rhempleadotbListNew) {
                rhempleadotbListNewRhempleadotbToAttach = em.getReference(rhempleadotbListNewRhempleadotbToAttach.getClass(), rhempleadotbListNewRhempleadotbToAttach.getIdempleado());
                attachedRhempleadotbListNew.add(rhempleadotbListNewRhempleadotbToAttach);
            }
            rhempleadotbListNew = attachedRhempleadotbListNew;
            catestadotb.setRhempleadotbList(rhempleadotbListNew);
            catestadotb = em.merge(catestadotb);
            if (idpaisOld != null && !idpaisOld.equals(idpaisNew)) {
                idpaisOld.getCatestadotbList().remove(catestadotb);
                idpaisOld = em.merge(idpaisOld);
            }
            if (idpaisNew != null && !idpaisNew.equals(idpaisOld)) {
                idpaisNew.getCatestadotbList().add(catestadotb);
                idpaisNew = em.merge(idpaisNew);
            }
            for (Catmunicipiotb catmunicipiotbListOldCatmunicipiotb : catmunicipiotbListOld) {
                if (!catmunicipiotbListNew.contains(catmunicipiotbListOldCatmunicipiotb)) {
                    catmunicipiotbListOldCatmunicipiotb.setIdestado(null);
                    catmunicipiotbListOldCatmunicipiotb = em.merge(catmunicipiotbListOldCatmunicipiotb);
                }
            }
            for (Catmunicipiotb catmunicipiotbListNewCatmunicipiotb : catmunicipiotbListNew) {
                if (!catmunicipiotbListOld.contains(catmunicipiotbListNewCatmunicipiotb)) {
                    Catestadotb oldIdestadoOfCatmunicipiotbListNewCatmunicipiotb = catmunicipiotbListNewCatmunicipiotb.getIdestado();
                    catmunicipiotbListNewCatmunicipiotb.setIdestado(catestadotb);
                    catmunicipiotbListNewCatmunicipiotb = em.merge(catmunicipiotbListNewCatmunicipiotb);
                    if (oldIdestadoOfCatmunicipiotbListNewCatmunicipiotb != null && !oldIdestadoOfCatmunicipiotbListNewCatmunicipiotb.equals(catestadotb)) {
                        oldIdestadoOfCatmunicipiotbListNewCatmunicipiotb.getCatmunicipiotbList().remove(catmunicipiotbListNewCatmunicipiotb);
                        oldIdestadoOfCatmunicipiotbListNewCatmunicipiotb = em.merge(oldIdestadoOfCatmunicipiotbListNewCatmunicipiotb);
                    }
                }
            }
            for (Rhempleadotb rhempleadotbListOldRhempleadotb : rhempleadotbListOld) {
                if (!rhempleadotbListNew.contains(rhempleadotbListOldRhempleadotb)) {
                    rhempleadotbListOldRhempleadotb.setIdestadonacimiento(null);
                    rhempleadotbListOldRhempleadotb = em.merge(rhempleadotbListOldRhempleadotb);
                }
            }
            for (Rhempleadotb rhempleadotbListNewRhempleadotb : rhempleadotbListNew) {
                if (!rhempleadotbListOld.contains(rhempleadotbListNewRhempleadotb)) {
                    Catestadotb oldIdestadonacimientoOfRhempleadotbListNewRhempleadotb = rhempleadotbListNewRhempleadotb.getIdestadonacimiento();
                    rhempleadotbListNewRhempleadotb.setIdestadonacimiento(catestadotb);
                    rhempleadotbListNewRhempleadotb = em.merge(rhempleadotbListNewRhempleadotb);
                    if (oldIdestadonacimientoOfRhempleadotbListNewRhempleadotb != null && !oldIdestadonacimientoOfRhempleadotbListNewRhempleadotb.equals(catestadotb)) {
                        oldIdestadonacimientoOfRhempleadotbListNewRhempleadotb.getRhempleadotbList().remove(rhempleadotbListNewRhempleadotb);
                        oldIdestadonacimientoOfRhempleadotbListNewRhempleadotb = em.merge(oldIdestadonacimientoOfRhempleadotbListNewRhempleadotb);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = catestadotb.getIdestado();
                if (findCatestadotb(id) == null) {
                    throw new NonexistentEntityException("The catestadotb with id " + id + " no longer exists.");
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
            Catestadotb catestadotb;
            try {
                catestadotb = em.getReference(Catestadotb.class, id);
                catestadotb.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The catestadotb with id " + id + " no longer exists.", enfe);
            }
            Catpaistb idpais = catestadotb.getIdpais();
            if (idpais != null) {
                idpais.getCatestadotbList().remove(catestadotb);
                idpais = em.merge(idpais);
            }
            List<Catmunicipiotb> catmunicipiotbList = catestadotb.getCatmunicipiotbList();
            for (Catmunicipiotb catmunicipiotbListCatmunicipiotb : catmunicipiotbList) {
                catmunicipiotbListCatmunicipiotb.setIdestado(null);
                catmunicipiotbListCatmunicipiotb = em.merge(catmunicipiotbListCatmunicipiotb);
            }
            List<Rhempleadotb> rhempleadotbList = catestadotb.getRhempleadotbList();
            for (Rhempleadotb rhempleadotbListRhempleadotb : rhempleadotbList) {
                rhempleadotbListRhempleadotb.setIdestadonacimiento(null);
                rhempleadotbListRhempleadotb = em.merge(rhempleadotbListRhempleadotb);
            }
            em.remove(catestadotb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Catestadotb> findCatestadotbEntities() {
        return findCatestadotbEntities(true, -1, -1);
    }

    public List<Catestadotb> findCatestadotbEntities(int maxResults, int firstResult) {
        return findCatestadotbEntities(false, maxResults, firstResult);
    }

    private List<Catestadotb> findCatestadotbEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Catestadotb.class));
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

    public Catestadotb findCatestadotb(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Catestadotb.class, id);
        } finally {
            em.close();
        }
    }

    public int getCatestadotbCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Catestadotb> rt = cq.from(Catestadotb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
