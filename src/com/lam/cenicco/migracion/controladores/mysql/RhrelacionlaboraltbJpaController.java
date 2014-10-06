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
import com.lam.cenicco.migracion.entidades.mysql.Rhtiporelacionlaboraltb;
import com.lam.cenicco.migracion.entidades.mysql.Rhrelacionlaboralposiciontb;
import com.lam.cenicco.migracion.entidades.mysql.Admregistropatronaltb;
import com.lam.cenicco.migracion.entidades.mysql.Rhposicionsueldotb;
import com.lam.cenicco.migracion.entidades.mysql.Nomgrupopagotb;
import com.lam.cenicco.migracion.entidades.mysql.Rhempleadotb;
import com.lam.cenicco.migracion.entidades.mysql.Admcompaniatb;
import com.lam.cenicco.migracion.entidades.mysql.Rhrelacionlaboralcuentatb;
import java.util.ArrayList;
import java.util.List;
import com.lam.cenicco.migracion.entidades.mysql.Rhposicionsueldohtb;
import com.lam.cenicco.migracion.entidades.mysql.Rhrelacionlaboraltb;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class RhrelacionlaboraltbJpaController implements Serializable {

    public RhrelacionlaboraltbJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rhrelacionlaboraltb rhrelacionlaboraltb) {
        if (rhrelacionlaboraltb.getRhrelacionlaboralcuentatbList() == null) {
            rhrelacionlaboraltb.setRhrelacionlaboralcuentatbList(new ArrayList<Rhrelacionlaboralcuentatb>());
        }
        if (rhrelacionlaboraltb.getRhposicionsueldohtbList() == null) {
            rhrelacionlaboraltb.setRhposicionsueldohtbList(new ArrayList<Rhposicionsueldohtb>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rhtiporelacionlaboraltb idtiporellab = rhrelacionlaboraltb.getIdtiporellab();
            if (idtiporellab != null) {
                idtiporellab = em.getReference(idtiporellab.getClass(), idtiporellab.getIdtiporelacionlaboral());
                rhrelacionlaboraltb.setIdtiporellab(idtiporellab);
            }
            Rhrelacionlaboralposiciontb idrelacionlaboralposicion = rhrelacionlaboraltb.getIdrelacionlaboralposicion();
            if (idrelacionlaboralposicion != null) {
                idrelacionlaboralposicion = em.getReference(idrelacionlaboralposicion.getClass(), idrelacionlaboralposicion.getIdrelacionlaboralposicion());
                rhrelacionlaboraltb.setIdrelacionlaboralposicion(idrelacionlaboralposicion);
            }
            Admregistropatronaltb idregistropatronal = rhrelacionlaboraltb.getIdregistropatronal();
            if (idregistropatronal != null) {
                idregistropatronal = em.getReference(idregistropatronal.getClass(), idregistropatronal.getIdregistropatronal());
                rhrelacionlaboraltb.setIdregistropatronal(idregistropatronal);
            }
            Rhposicionsueldotb idposicionsueldo = rhrelacionlaboraltb.getIdposicionsueldo();
            if (idposicionsueldo != null) {
                idposicionsueldo = em.getReference(idposicionsueldo.getClass(), idposicionsueldo.getIdposicionsueldo());
                rhrelacionlaboraltb.setIdposicionsueldo(idposicionsueldo);
            }
            Nomgrupopagotb idgrupopago = rhrelacionlaboraltb.getIdgrupopago();
            if (idgrupopago != null) {
                idgrupopago = em.getReference(idgrupopago.getClass(), idgrupopago.getIdgrupopago());
                rhrelacionlaboraltb.setIdgrupopago(idgrupopago);
            }
            Rhempleadotb idempleado = rhrelacionlaboraltb.getIdempleado();
            if (idempleado != null) {
                idempleado = em.getReference(idempleado.getClass(), idempleado.getIdempleado());
                rhrelacionlaboraltb.setIdempleado(idempleado);
            }
            Admcompaniatb idcompania = rhrelacionlaboraltb.getIdcompania();
            if (idcompania != null) {
                idcompania = em.getReference(idcompania.getClass(), idcompania.getIdcompania());
                rhrelacionlaboraltb.setIdcompania(idcompania);
            }
            List<Rhrelacionlaboralcuentatb> attachedRhrelacionlaboralcuentatbList = new ArrayList<Rhrelacionlaboralcuentatb>();
            for (Rhrelacionlaboralcuentatb rhrelacionlaboralcuentatbListRhrelacionlaboralcuentatbToAttach : rhrelacionlaboraltb.getRhrelacionlaboralcuentatbList()) {
                rhrelacionlaboralcuentatbListRhrelacionlaboralcuentatbToAttach = em.getReference(rhrelacionlaboralcuentatbListRhrelacionlaboralcuentatbToAttach.getClass(), rhrelacionlaboralcuentatbListRhrelacionlaboralcuentatbToAttach.getIdcuenta());
                attachedRhrelacionlaboralcuentatbList.add(rhrelacionlaboralcuentatbListRhrelacionlaboralcuentatbToAttach);
            }
            rhrelacionlaboraltb.setRhrelacionlaboralcuentatbList(attachedRhrelacionlaboralcuentatbList);
            List<Rhposicionsueldohtb> attachedRhposicionsueldohtbList = new ArrayList<Rhposicionsueldohtb>();
            for (Rhposicionsueldohtb rhposicionsueldohtbListRhposicionsueldohtbToAttach : rhrelacionlaboraltb.getRhposicionsueldohtbList()) {
                rhposicionsueldohtbListRhposicionsueldohtbToAttach = em.getReference(rhposicionsueldohtbListRhposicionsueldohtbToAttach.getClass(), rhposicionsueldohtbListRhposicionsueldohtbToAttach.getIdposicionsueldo());
                attachedRhposicionsueldohtbList.add(rhposicionsueldohtbListRhposicionsueldohtbToAttach);
            }
            rhrelacionlaboraltb.setRhposicionsueldohtbList(attachedRhposicionsueldohtbList);
            em.persist(rhrelacionlaboraltb);
            if (idtiporellab != null) {
                idtiporellab.getRhrelacionlaboraltbList().add(rhrelacionlaboraltb);
                idtiporellab = em.merge(idtiporellab);
            }
            if (idrelacionlaboralposicion != null) {
                idrelacionlaboralposicion.getRhrelacionlaboraltbList().add(rhrelacionlaboraltb);
                idrelacionlaboralposicion = em.merge(idrelacionlaboralposicion);
            }
            if (idregistropatronal != null) {
                idregistropatronal.getRhrelacionlaboraltbList().add(rhrelacionlaboraltb);
                idregistropatronal = em.merge(idregistropatronal);
            }
            if (idposicionsueldo != null) {
                idposicionsueldo.getRhrelacionlaboraltbList().add(rhrelacionlaboraltb);
                idposicionsueldo = em.merge(idposicionsueldo);
            }
            if (idgrupopago != null) {
                idgrupopago.getRhrelacionlaboraltbList().add(rhrelacionlaboraltb);
                idgrupopago = em.merge(idgrupopago);
            }
            if (idempleado != null) {
                idempleado.getRhrelacionlaboraltbList().add(rhrelacionlaboraltb);
                idempleado = em.merge(idempleado);
            }
            if (idcompania != null) {
                idcompania.getRhrelacionlaboraltbList().add(rhrelacionlaboraltb);
                idcompania = em.merge(idcompania);
            }
            for (Rhrelacionlaboralcuentatb rhrelacionlaboralcuentatbListRhrelacionlaboralcuentatb : rhrelacionlaboraltb.getRhrelacionlaboralcuentatbList()) {
                Rhrelacionlaboraltb oldIdrellabOfRhrelacionlaboralcuentatbListRhrelacionlaboralcuentatb = rhrelacionlaboralcuentatbListRhrelacionlaboralcuentatb.getIdrellab();
                rhrelacionlaboralcuentatbListRhrelacionlaboralcuentatb.setIdrellab(rhrelacionlaboraltb);
                rhrelacionlaboralcuentatbListRhrelacionlaboralcuentatb = em.merge(rhrelacionlaboralcuentatbListRhrelacionlaboralcuentatb);
                if (oldIdrellabOfRhrelacionlaboralcuentatbListRhrelacionlaboralcuentatb != null) {
                    oldIdrellabOfRhrelacionlaboralcuentatbListRhrelacionlaboralcuentatb.getRhrelacionlaboralcuentatbList().remove(rhrelacionlaboralcuentatbListRhrelacionlaboralcuentatb);
                    oldIdrellabOfRhrelacionlaboralcuentatbListRhrelacionlaboralcuentatb = em.merge(oldIdrellabOfRhrelacionlaboralcuentatbListRhrelacionlaboralcuentatb);
                }
            }
            for (Rhposicionsueldohtb rhposicionsueldohtbListRhposicionsueldohtb : rhrelacionlaboraltb.getRhposicionsueldohtbList()) {
                Rhrelacionlaboraltb oldIdrellabOfRhposicionsueldohtbListRhposicionsueldohtb = rhposicionsueldohtbListRhposicionsueldohtb.getIdrellab();
                rhposicionsueldohtbListRhposicionsueldohtb.setIdrellab(rhrelacionlaboraltb);
                rhposicionsueldohtbListRhposicionsueldohtb = em.merge(rhposicionsueldohtbListRhposicionsueldohtb);
                if (oldIdrellabOfRhposicionsueldohtbListRhposicionsueldohtb != null) {
                    oldIdrellabOfRhposicionsueldohtbListRhposicionsueldohtb.getRhposicionsueldohtbList().remove(rhposicionsueldohtbListRhposicionsueldohtb);
                    oldIdrellabOfRhposicionsueldohtbListRhposicionsueldohtb = em.merge(oldIdrellabOfRhposicionsueldohtbListRhposicionsueldohtb);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rhrelacionlaboraltb rhrelacionlaboraltb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rhrelacionlaboraltb persistentRhrelacionlaboraltb = em.find(Rhrelacionlaboraltb.class, rhrelacionlaboraltb.getIdrellab());
            Rhtiporelacionlaboraltb idtiporellabOld = persistentRhrelacionlaboraltb.getIdtiporellab();
            Rhtiporelacionlaboraltb idtiporellabNew = rhrelacionlaboraltb.getIdtiporellab();
            Rhrelacionlaboralposiciontb idrelacionlaboralposicionOld = persistentRhrelacionlaboraltb.getIdrelacionlaboralposicion();
            Rhrelacionlaboralposiciontb idrelacionlaboralposicionNew = rhrelacionlaboraltb.getIdrelacionlaboralposicion();
            Admregistropatronaltb idregistropatronalOld = persistentRhrelacionlaboraltb.getIdregistropatronal();
            Admregistropatronaltb idregistropatronalNew = rhrelacionlaboraltb.getIdregistropatronal();
            Rhposicionsueldotb idposicionsueldoOld = persistentRhrelacionlaboraltb.getIdposicionsueldo();
            Rhposicionsueldotb idposicionsueldoNew = rhrelacionlaboraltb.getIdposicionsueldo();
            Nomgrupopagotb idgrupopagoOld = persistentRhrelacionlaboraltb.getIdgrupopago();
            Nomgrupopagotb idgrupopagoNew = rhrelacionlaboraltb.getIdgrupopago();
            Rhempleadotb idempleadoOld = persistentRhrelacionlaboraltb.getIdempleado();
            Rhempleadotb idempleadoNew = rhrelacionlaboraltb.getIdempleado();
            Admcompaniatb idcompaniaOld = persistentRhrelacionlaboraltb.getIdcompania();
            Admcompaniatb idcompaniaNew = rhrelacionlaboraltb.getIdcompania();
            List<Rhrelacionlaboralcuentatb> rhrelacionlaboralcuentatbListOld = persistentRhrelacionlaboraltb.getRhrelacionlaboralcuentatbList();
            List<Rhrelacionlaboralcuentatb> rhrelacionlaboralcuentatbListNew = rhrelacionlaboraltb.getRhrelacionlaboralcuentatbList();
            List<Rhposicionsueldohtb> rhposicionsueldohtbListOld = persistentRhrelacionlaboraltb.getRhposicionsueldohtbList();
            List<Rhposicionsueldohtb> rhposicionsueldohtbListNew = rhrelacionlaboraltb.getRhposicionsueldohtbList();
            if (idtiporellabNew != null) {
                idtiporellabNew = em.getReference(idtiporellabNew.getClass(), idtiporellabNew.getIdtiporelacionlaboral());
                rhrelacionlaboraltb.setIdtiporellab(idtiporellabNew);
            }
            if (idrelacionlaboralposicionNew != null) {
                idrelacionlaboralposicionNew = em.getReference(idrelacionlaboralposicionNew.getClass(), idrelacionlaboralposicionNew.getIdrelacionlaboralposicion());
                rhrelacionlaboraltb.setIdrelacionlaboralposicion(idrelacionlaboralposicionNew);
            }
            if (idregistropatronalNew != null) {
                idregistropatronalNew = em.getReference(idregistropatronalNew.getClass(), idregistropatronalNew.getIdregistropatronal());
                rhrelacionlaboraltb.setIdregistropatronal(idregistropatronalNew);
            }
            if (idposicionsueldoNew != null) {
                idposicionsueldoNew = em.getReference(idposicionsueldoNew.getClass(), idposicionsueldoNew.getIdposicionsueldo());
                rhrelacionlaboraltb.setIdposicionsueldo(idposicionsueldoNew);
            }
            if (idgrupopagoNew != null) {
                idgrupopagoNew = em.getReference(idgrupopagoNew.getClass(), idgrupopagoNew.getIdgrupopago());
                rhrelacionlaboraltb.setIdgrupopago(idgrupopagoNew);
            }
            if (idempleadoNew != null) {
                idempleadoNew = em.getReference(idempleadoNew.getClass(), idempleadoNew.getIdempleado());
                rhrelacionlaboraltb.setIdempleado(idempleadoNew);
            }
            if (idcompaniaNew != null) {
                idcompaniaNew = em.getReference(idcompaniaNew.getClass(), idcompaniaNew.getIdcompania());
                rhrelacionlaboraltb.setIdcompania(idcompaniaNew);
            }
            List<Rhrelacionlaboralcuentatb> attachedRhrelacionlaboralcuentatbListNew = new ArrayList<Rhrelacionlaboralcuentatb>();
            for (Rhrelacionlaboralcuentatb rhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatbToAttach : rhrelacionlaboralcuentatbListNew) {
                rhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatbToAttach = em.getReference(rhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatbToAttach.getClass(), rhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatbToAttach.getIdcuenta());
                attachedRhrelacionlaboralcuentatbListNew.add(rhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatbToAttach);
            }
            rhrelacionlaboralcuentatbListNew = attachedRhrelacionlaboralcuentatbListNew;
            rhrelacionlaboraltb.setRhrelacionlaboralcuentatbList(rhrelacionlaboralcuentatbListNew);
            List<Rhposicionsueldohtb> attachedRhposicionsueldohtbListNew = new ArrayList<Rhposicionsueldohtb>();
            for (Rhposicionsueldohtb rhposicionsueldohtbListNewRhposicionsueldohtbToAttach : rhposicionsueldohtbListNew) {
                rhposicionsueldohtbListNewRhposicionsueldohtbToAttach = em.getReference(rhposicionsueldohtbListNewRhposicionsueldohtbToAttach.getClass(), rhposicionsueldohtbListNewRhposicionsueldohtbToAttach.getIdposicionsueldo());
                attachedRhposicionsueldohtbListNew.add(rhposicionsueldohtbListNewRhposicionsueldohtbToAttach);
            }
            rhposicionsueldohtbListNew = attachedRhposicionsueldohtbListNew;
            rhrelacionlaboraltb.setRhposicionsueldohtbList(rhposicionsueldohtbListNew);
            rhrelacionlaboraltb = em.merge(rhrelacionlaboraltb);
            if (idtiporellabOld != null && !idtiporellabOld.equals(idtiporellabNew)) {
                idtiporellabOld.getRhrelacionlaboraltbList().remove(rhrelacionlaboraltb);
                idtiporellabOld = em.merge(idtiporellabOld);
            }
            if (idtiporellabNew != null && !idtiporellabNew.equals(idtiporellabOld)) {
                idtiporellabNew.getRhrelacionlaboraltbList().add(rhrelacionlaboraltb);
                idtiporellabNew = em.merge(idtiporellabNew);
            }
            if (idrelacionlaboralposicionOld != null && !idrelacionlaboralposicionOld.equals(idrelacionlaboralposicionNew)) {
                idrelacionlaboralposicionOld.getRhrelacionlaboraltbList().remove(rhrelacionlaboraltb);
                idrelacionlaboralposicionOld = em.merge(idrelacionlaboralposicionOld);
            }
            if (idrelacionlaboralposicionNew != null && !idrelacionlaboralposicionNew.equals(idrelacionlaboralposicionOld)) {
                idrelacionlaboralposicionNew.getRhrelacionlaboraltbList().add(rhrelacionlaboraltb);
                idrelacionlaboralposicionNew = em.merge(idrelacionlaboralposicionNew);
            }
            if (idregistropatronalOld != null && !idregistropatronalOld.equals(idregistropatronalNew)) {
                idregistropatronalOld.getRhrelacionlaboraltbList().remove(rhrelacionlaboraltb);
                idregistropatronalOld = em.merge(idregistropatronalOld);
            }
            if (idregistropatronalNew != null && !idregistropatronalNew.equals(idregistropatronalOld)) {
                idregistropatronalNew.getRhrelacionlaboraltbList().add(rhrelacionlaboraltb);
                idregistropatronalNew = em.merge(idregistropatronalNew);
            }
            if (idposicionsueldoOld != null && !idposicionsueldoOld.equals(idposicionsueldoNew)) {
                idposicionsueldoOld.getRhrelacionlaboraltbList().remove(rhrelacionlaboraltb);
                idposicionsueldoOld = em.merge(idposicionsueldoOld);
            }
            if (idposicionsueldoNew != null && !idposicionsueldoNew.equals(idposicionsueldoOld)) {
                idposicionsueldoNew.getRhrelacionlaboraltbList().add(rhrelacionlaboraltb);
                idposicionsueldoNew = em.merge(idposicionsueldoNew);
            }
            if (idgrupopagoOld != null && !idgrupopagoOld.equals(idgrupopagoNew)) {
                idgrupopagoOld.getRhrelacionlaboraltbList().remove(rhrelacionlaboraltb);
                idgrupopagoOld = em.merge(idgrupopagoOld);
            }
            if (idgrupopagoNew != null && !idgrupopagoNew.equals(idgrupopagoOld)) {
                idgrupopagoNew.getRhrelacionlaboraltbList().add(rhrelacionlaboraltb);
                idgrupopagoNew = em.merge(idgrupopagoNew);
            }
            if (idempleadoOld != null && !idempleadoOld.equals(idempleadoNew)) {
                idempleadoOld.getRhrelacionlaboraltbList().remove(rhrelacionlaboraltb);
                idempleadoOld = em.merge(idempleadoOld);
            }
            if (idempleadoNew != null && !idempleadoNew.equals(idempleadoOld)) {
                idempleadoNew.getRhrelacionlaboraltbList().add(rhrelacionlaboraltb);
                idempleadoNew = em.merge(idempleadoNew);
            }
            if (idcompaniaOld != null && !idcompaniaOld.equals(idcompaniaNew)) {
                idcompaniaOld.getRhrelacionlaboraltbList().remove(rhrelacionlaboraltb);
                idcompaniaOld = em.merge(idcompaniaOld);
            }
            if (idcompaniaNew != null && !idcompaniaNew.equals(idcompaniaOld)) {
                idcompaniaNew.getRhrelacionlaboraltbList().add(rhrelacionlaboraltb);
                idcompaniaNew = em.merge(idcompaniaNew);
            }
            for (Rhrelacionlaboralcuentatb rhrelacionlaboralcuentatbListOldRhrelacionlaboralcuentatb : rhrelacionlaboralcuentatbListOld) {
                if (!rhrelacionlaboralcuentatbListNew.contains(rhrelacionlaboralcuentatbListOldRhrelacionlaboralcuentatb)) {
                    rhrelacionlaboralcuentatbListOldRhrelacionlaboralcuentatb.setIdrellab(null);
                    rhrelacionlaboralcuentatbListOldRhrelacionlaboralcuentatb = em.merge(rhrelacionlaboralcuentatbListOldRhrelacionlaboralcuentatb);
                }
            }
            for (Rhrelacionlaboralcuentatb rhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatb : rhrelacionlaboralcuentatbListNew) {
                if (!rhrelacionlaboralcuentatbListOld.contains(rhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatb)) {
                    Rhrelacionlaboraltb oldIdrellabOfRhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatb = rhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatb.getIdrellab();
                    rhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatb.setIdrellab(rhrelacionlaboraltb);
                    rhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatb = em.merge(rhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatb);
                    if (oldIdrellabOfRhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatb != null && !oldIdrellabOfRhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatb.equals(rhrelacionlaboraltb)) {
                        oldIdrellabOfRhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatb.getRhrelacionlaboralcuentatbList().remove(rhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatb);
                        oldIdrellabOfRhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatb = em.merge(oldIdrellabOfRhrelacionlaboralcuentatbListNewRhrelacionlaboralcuentatb);
                    }
                }
            }
            for (Rhposicionsueldohtb rhposicionsueldohtbListOldRhposicionsueldohtb : rhposicionsueldohtbListOld) {
                if (!rhposicionsueldohtbListNew.contains(rhposicionsueldohtbListOldRhposicionsueldohtb)) {
                    rhposicionsueldohtbListOldRhposicionsueldohtb.setIdrellab(null);
                    rhposicionsueldohtbListOldRhposicionsueldohtb = em.merge(rhposicionsueldohtbListOldRhposicionsueldohtb);
                }
            }
            for (Rhposicionsueldohtb rhposicionsueldohtbListNewRhposicionsueldohtb : rhposicionsueldohtbListNew) {
                if (!rhposicionsueldohtbListOld.contains(rhposicionsueldohtbListNewRhposicionsueldohtb)) {
                    Rhrelacionlaboraltb oldIdrellabOfRhposicionsueldohtbListNewRhposicionsueldohtb = rhposicionsueldohtbListNewRhposicionsueldohtb.getIdrellab();
                    rhposicionsueldohtbListNewRhposicionsueldohtb.setIdrellab(rhrelacionlaboraltb);
                    rhposicionsueldohtbListNewRhposicionsueldohtb = em.merge(rhposicionsueldohtbListNewRhposicionsueldohtb);
                    if (oldIdrellabOfRhposicionsueldohtbListNewRhposicionsueldohtb != null && !oldIdrellabOfRhposicionsueldohtbListNewRhposicionsueldohtb.equals(rhrelacionlaboraltb)) {
                        oldIdrellabOfRhposicionsueldohtbListNewRhposicionsueldohtb.getRhposicionsueldohtbList().remove(rhposicionsueldohtbListNewRhposicionsueldohtb);
                        oldIdrellabOfRhposicionsueldohtbListNewRhposicionsueldohtb = em.merge(oldIdrellabOfRhposicionsueldohtbListNewRhposicionsueldohtb);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rhrelacionlaboraltb.getIdrellab();
                if (findRhrelacionlaboraltb(id) == null) {
                    throw new NonexistentEntityException("The rhrelacionlaboraltb with id " + id + " no longer exists.");
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
            Rhrelacionlaboraltb rhrelacionlaboraltb;
            try {
                rhrelacionlaboraltb = em.getReference(Rhrelacionlaboraltb.class, id);
                rhrelacionlaboraltb.getIdrellab();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rhrelacionlaboraltb with id " + id + " no longer exists.", enfe);
            }
            Rhtiporelacionlaboraltb idtiporellab = rhrelacionlaboraltb.getIdtiporellab();
            if (idtiporellab != null) {
                idtiporellab.getRhrelacionlaboraltbList().remove(rhrelacionlaboraltb);
                idtiporellab = em.merge(idtiporellab);
            }
            Rhrelacionlaboralposiciontb idrelacionlaboralposicion = rhrelacionlaboraltb.getIdrelacionlaboralposicion();
            if (idrelacionlaboralposicion != null) {
                idrelacionlaboralposicion.getRhrelacionlaboraltbList().remove(rhrelacionlaboraltb);
                idrelacionlaboralposicion = em.merge(idrelacionlaboralposicion);
            }
            Admregistropatronaltb idregistropatronal = rhrelacionlaboraltb.getIdregistropatronal();
            if (idregistropatronal != null) {
                idregistropatronal.getRhrelacionlaboraltbList().remove(rhrelacionlaboraltb);
                idregistropatronal = em.merge(idregistropatronal);
            }
            Rhposicionsueldotb idposicionsueldo = rhrelacionlaboraltb.getIdposicionsueldo();
            if (idposicionsueldo != null) {
                idposicionsueldo.getRhrelacionlaboraltbList().remove(rhrelacionlaboraltb);
                idposicionsueldo = em.merge(idposicionsueldo);
            }
            Nomgrupopagotb idgrupopago = rhrelacionlaboraltb.getIdgrupopago();
            if (idgrupopago != null) {
                idgrupopago.getRhrelacionlaboraltbList().remove(rhrelacionlaboraltb);
                idgrupopago = em.merge(idgrupopago);
            }
            Rhempleadotb idempleado = rhrelacionlaboraltb.getIdempleado();
            if (idempleado != null) {
                idempleado.getRhrelacionlaboraltbList().remove(rhrelacionlaboraltb);
                idempleado = em.merge(idempleado);
            }
            Admcompaniatb idcompania = rhrelacionlaboraltb.getIdcompania();
            if (idcompania != null) {
                idcompania.getRhrelacionlaboraltbList().remove(rhrelacionlaboraltb);
                idcompania = em.merge(idcompania);
            }
            List<Rhrelacionlaboralcuentatb> rhrelacionlaboralcuentatbList = rhrelacionlaboraltb.getRhrelacionlaboralcuentatbList();
            for (Rhrelacionlaboralcuentatb rhrelacionlaboralcuentatbListRhrelacionlaboralcuentatb : rhrelacionlaboralcuentatbList) {
                rhrelacionlaboralcuentatbListRhrelacionlaboralcuentatb.setIdrellab(null);
                rhrelacionlaboralcuentatbListRhrelacionlaboralcuentatb = em.merge(rhrelacionlaboralcuentatbListRhrelacionlaboralcuentatb);
            }
            List<Rhposicionsueldohtb> rhposicionsueldohtbList = rhrelacionlaboraltb.getRhposicionsueldohtbList();
            for (Rhposicionsueldohtb rhposicionsueldohtbListRhposicionsueldohtb : rhposicionsueldohtbList) {
                rhposicionsueldohtbListRhposicionsueldohtb.setIdrellab(null);
                rhposicionsueldohtbListRhposicionsueldohtb = em.merge(rhposicionsueldohtbListRhposicionsueldohtb);
            }
            em.remove(rhrelacionlaboraltb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rhrelacionlaboraltb> findRhrelacionlaboraltbEntities() {
        return findRhrelacionlaboraltbEntities(true, -1, -1);
    }

    public List<Rhrelacionlaboraltb> findRhrelacionlaboraltbEntities(int maxResults, int firstResult) {
        return findRhrelacionlaboraltbEntities(false, maxResults, firstResult);
    }

    private List<Rhrelacionlaboraltb> findRhrelacionlaboraltbEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rhrelacionlaboraltb.class));
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

    public Rhrelacionlaboraltb findRhrelacionlaboraltb(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rhrelacionlaboraltb.class, id);
        } finally {
            em.close();
        }
    }

    public int getRhrelacionlaboraltbCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rhrelacionlaboraltb> rt = cq.from(Rhrelacionlaboraltb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
