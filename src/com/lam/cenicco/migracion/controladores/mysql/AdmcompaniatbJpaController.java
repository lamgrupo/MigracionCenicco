/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lam.cenicco.migracion.controladores.mysql;

import com.lam.cenicco.migracion.controladores.mysql.exceptions.NonexistentEntityException;
import com.lam.cenicco.migracion.entidades.mysql.Admcompaniatb;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.lam.cenicco.migracion.entidades.mysql.Admregistropatronaltb;
import java.util.ArrayList;
import java.util.List;
import com.lam.cenicco.migracion.entidades.mysql.Admdepartamentotb;
import com.lam.cenicco.migracion.entidades.mysql.Nomgrupopagotb;
import com.lam.cenicco.migracion.entidades.mysql.Rhrelacionlaboraltb;
import com.lam.cenicco.migracion.entidades.mysql.Admpuestotb;
import com.lam.cenicco.migracion.entidades.mysql.Taturnotb;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author JoséAntonio
 */
public class AdmcompaniatbJpaController implements Serializable {

    public AdmcompaniatbJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Admcompaniatb admcompaniatb) {
        if (admcompaniatb.getAdmregistropatronaltbList() == null) {
            admcompaniatb.setAdmregistropatronaltbList(new ArrayList<Admregistropatronaltb>());
        }
        if (admcompaniatb.getAdmdepartamentotbList() == null) {
            admcompaniatb.setAdmdepartamentotbList(new ArrayList<Admdepartamentotb>());
        }
        if (admcompaniatb.getNomgrupopagotbList() == null) {
            admcompaniatb.setNomgrupopagotbList(new ArrayList<Nomgrupopagotb>());
        }
        if (admcompaniatb.getRhrelacionlaboraltbList() == null) {
            admcompaniatb.setRhrelacionlaboraltbList(new ArrayList<Rhrelacionlaboraltb>());
        }
        if (admcompaniatb.getAdmpuestotbList() == null) {
            admcompaniatb.setAdmpuestotbList(new ArrayList<Admpuestotb>());
        }
        if (admcompaniatb.getTaturnotbList() == null) {
            admcompaniatb.setTaturnotbList(new ArrayList<Taturnotb>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Admregistropatronaltb> attachedAdmregistropatronaltbList = new ArrayList<Admregistropatronaltb>();
            for (Admregistropatronaltb admregistropatronaltbListAdmregistropatronaltbToAttach : admcompaniatb.getAdmregistropatronaltbList()) {
                admregistropatronaltbListAdmregistropatronaltbToAttach = em.getReference(admregistropatronaltbListAdmregistropatronaltbToAttach.getClass(), admregistropatronaltbListAdmregistropatronaltbToAttach.getIdregistropatronal());
                attachedAdmregistropatronaltbList.add(admregistropatronaltbListAdmregistropatronaltbToAttach);
            }
            admcompaniatb.setAdmregistropatronaltbList(attachedAdmregistropatronaltbList);
            List<Admdepartamentotb> attachedAdmdepartamentotbList = new ArrayList<Admdepartamentotb>();
            for (Admdepartamentotb admdepartamentotbListAdmdepartamentotbToAttach : admcompaniatb.getAdmdepartamentotbList()) {
                admdepartamentotbListAdmdepartamentotbToAttach = em.getReference(admdepartamentotbListAdmdepartamentotbToAttach.getClass(), admdepartamentotbListAdmdepartamentotbToAttach.getIddepartamento());
                attachedAdmdepartamentotbList.add(admdepartamentotbListAdmdepartamentotbToAttach);
            }
            admcompaniatb.setAdmdepartamentotbList(attachedAdmdepartamentotbList);
            List<Nomgrupopagotb> attachedNomgrupopagotbList = new ArrayList<Nomgrupopagotb>();
            for (Nomgrupopagotb nomgrupopagotbListNomgrupopagotbToAttach : admcompaniatb.getNomgrupopagotbList()) {
                nomgrupopagotbListNomgrupopagotbToAttach = em.getReference(nomgrupopagotbListNomgrupopagotbToAttach.getClass(), nomgrupopagotbListNomgrupopagotbToAttach.getIdgrupopago());
                attachedNomgrupopagotbList.add(nomgrupopagotbListNomgrupopagotbToAttach);
            }
            admcompaniatb.setNomgrupopagotbList(attachedNomgrupopagotbList);
            List<Rhrelacionlaboraltb> attachedRhrelacionlaboraltbList = new ArrayList<Rhrelacionlaboraltb>();
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListRhrelacionlaboraltbToAttach : admcompaniatb.getRhrelacionlaboraltbList()) {
                rhrelacionlaboraltbListRhrelacionlaboraltbToAttach = em.getReference(rhrelacionlaboraltbListRhrelacionlaboraltbToAttach.getClass(), rhrelacionlaboraltbListRhrelacionlaboraltbToAttach.getIdrellab());
                attachedRhrelacionlaboraltbList.add(rhrelacionlaboraltbListRhrelacionlaboraltbToAttach);
            }
            admcompaniatb.setRhrelacionlaboraltbList(attachedRhrelacionlaboraltbList);
            List<Admpuestotb> attachedAdmpuestotbList = new ArrayList<Admpuestotb>();
            for (Admpuestotb admpuestotbListAdmpuestotbToAttach : admcompaniatb.getAdmpuestotbList()) {
                admpuestotbListAdmpuestotbToAttach = em.getReference(admpuestotbListAdmpuestotbToAttach.getClass(), admpuestotbListAdmpuestotbToAttach.getIdpuesto());
                attachedAdmpuestotbList.add(admpuestotbListAdmpuestotbToAttach);
            }
            admcompaniatb.setAdmpuestotbList(attachedAdmpuestotbList);
            List<Taturnotb> attachedTaturnotbList = new ArrayList<Taturnotb>();
            for (Taturnotb taturnotbListTaturnotbToAttach : admcompaniatb.getTaturnotbList()) {
                taturnotbListTaturnotbToAttach = em.getReference(taturnotbListTaturnotbToAttach.getClass(), taturnotbListTaturnotbToAttach.getIdturno());
                attachedTaturnotbList.add(taturnotbListTaturnotbToAttach);
            }
            admcompaniatb.setTaturnotbList(attachedTaturnotbList);
            em.persist(admcompaniatb);
            for (Admregistropatronaltb admregistropatronaltbListAdmregistropatronaltb : admcompaniatb.getAdmregistropatronaltbList()) {
                Admcompaniatb oldIdcompaniaOfAdmregistropatronaltbListAdmregistropatronaltb = admregistropatronaltbListAdmregistropatronaltb.getIdcompania();
                admregistropatronaltbListAdmregistropatronaltb.setIdcompania(admcompaniatb);
                admregistropatronaltbListAdmregistropatronaltb = em.merge(admregistropatronaltbListAdmregistropatronaltb);
                if (oldIdcompaniaOfAdmregistropatronaltbListAdmregistropatronaltb != null) {
                    oldIdcompaniaOfAdmregistropatronaltbListAdmregistropatronaltb.getAdmregistropatronaltbList().remove(admregistropatronaltbListAdmregistropatronaltb);
                    oldIdcompaniaOfAdmregistropatronaltbListAdmregistropatronaltb = em.merge(oldIdcompaniaOfAdmregistropatronaltbListAdmregistropatronaltb);
                }
            }
            for (Admdepartamentotb admdepartamentotbListAdmdepartamentotb : admcompaniatb.getAdmdepartamentotbList()) {
                Admcompaniatb oldIdcompaniaOfAdmdepartamentotbListAdmdepartamentotb = admdepartamentotbListAdmdepartamentotb.getIdcompania();
                admdepartamentotbListAdmdepartamentotb.setIdcompania(admcompaniatb);
                admdepartamentotbListAdmdepartamentotb = em.merge(admdepartamentotbListAdmdepartamentotb);
                if (oldIdcompaniaOfAdmdepartamentotbListAdmdepartamentotb != null) {
                    oldIdcompaniaOfAdmdepartamentotbListAdmdepartamentotb.getAdmdepartamentotbList().remove(admdepartamentotbListAdmdepartamentotb);
                    oldIdcompaniaOfAdmdepartamentotbListAdmdepartamentotb = em.merge(oldIdcompaniaOfAdmdepartamentotbListAdmdepartamentotb);
                }
            }
            for (Nomgrupopagotb nomgrupopagotbListNomgrupopagotb : admcompaniatb.getNomgrupopagotbList()) {
                Admcompaniatb oldIdcompaniaOfNomgrupopagotbListNomgrupopagotb = nomgrupopagotbListNomgrupopagotb.getIdcompania();
                nomgrupopagotbListNomgrupopagotb.setIdcompania(admcompaniatb);
                nomgrupopagotbListNomgrupopagotb = em.merge(nomgrupopagotbListNomgrupopagotb);
                if (oldIdcompaniaOfNomgrupopagotbListNomgrupopagotb != null) {
                    oldIdcompaniaOfNomgrupopagotbListNomgrupopagotb.getNomgrupopagotbList().remove(nomgrupopagotbListNomgrupopagotb);
                    oldIdcompaniaOfNomgrupopagotbListNomgrupopagotb = em.merge(oldIdcompaniaOfNomgrupopagotbListNomgrupopagotb);
                }
            }
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListRhrelacionlaboraltb : admcompaniatb.getRhrelacionlaboraltbList()) {
                Admcompaniatb oldIdcompaniaOfRhrelacionlaboraltbListRhrelacionlaboraltb = rhrelacionlaboraltbListRhrelacionlaboraltb.getIdcompania();
                rhrelacionlaboraltbListRhrelacionlaboraltb.setIdcompania(admcompaniatb);
                rhrelacionlaboraltbListRhrelacionlaboraltb = em.merge(rhrelacionlaboraltbListRhrelacionlaboraltb);
                if (oldIdcompaniaOfRhrelacionlaboraltbListRhrelacionlaboraltb != null) {
                    oldIdcompaniaOfRhrelacionlaboraltbListRhrelacionlaboraltb.getRhrelacionlaboraltbList().remove(rhrelacionlaboraltbListRhrelacionlaboraltb);
                    oldIdcompaniaOfRhrelacionlaboraltbListRhrelacionlaboraltb = em.merge(oldIdcompaniaOfRhrelacionlaboraltbListRhrelacionlaboraltb);
                }
            }
            for (Admpuestotb admpuestotbListAdmpuestotb : admcompaniatb.getAdmpuestotbList()) {
                Admcompaniatb oldIdcompaniaOfAdmpuestotbListAdmpuestotb = admpuestotbListAdmpuestotb.getIdcompania();
                admpuestotbListAdmpuestotb.setIdcompania(admcompaniatb);
                admpuestotbListAdmpuestotb = em.merge(admpuestotbListAdmpuestotb);
                if (oldIdcompaniaOfAdmpuestotbListAdmpuestotb != null) {
                    oldIdcompaniaOfAdmpuestotbListAdmpuestotb.getAdmpuestotbList().remove(admpuestotbListAdmpuestotb);
                    oldIdcompaniaOfAdmpuestotbListAdmpuestotb = em.merge(oldIdcompaniaOfAdmpuestotbListAdmpuestotb);
                }
            }
            for (Taturnotb taturnotbListTaturnotb : admcompaniatb.getTaturnotbList()) {
                Admcompaniatb oldIdcompaniaOfTaturnotbListTaturnotb = taturnotbListTaturnotb.getIdcompania();
                taturnotbListTaturnotb.setIdcompania(admcompaniatb);
                taturnotbListTaturnotb = em.merge(taturnotbListTaturnotb);
                if (oldIdcompaniaOfTaturnotbListTaturnotb != null) {
                    oldIdcompaniaOfTaturnotbListTaturnotb.getTaturnotbList().remove(taturnotbListTaturnotb);
                    oldIdcompaniaOfTaturnotbListTaturnotb = em.merge(oldIdcompaniaOfTaturnotbListTaturnotb);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Admcompaniatb admcompaniatb) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Admcompaniatb persistentAdmcompaniatb = em.find(Admcompaniatb.class, admcompaniatb.getIdcompania());
            List<Admregistropatronaltb> admregistropatronaltbListOld = persistentAdmcompaniatb.getAdmregistropatronaltbList();
            List<Admregistropatronaltb> admregistropatronaltbListNew = admcompaniatb.getAdmregistropatronaltbList();
            List<Admdepartamentotb> admdepartamentotbListOld = persistentAdmcompaniatb.getAdmdepartamentotbList();
            List<Admdepartamentotb> admdepartamentotbListNew = admcompaniatb.getAdmdepartamentotbList();
            List<Nomgrupopagotb> nomgrupopagotbListOld = persistentAdmcompaniatb.getNomgrupopagotbList();
            List<Nomgrupopagotb> nomgrupopagotbListNew = admcompaniatb.getNomgrupopagotbList();
            List<Rhrelacionlaboraltb> rhrelacionlaboraltbListOld = persistentAdmcompaniatb.getRhrelacionlaboraltbList();
            List<Rhrelacionlaboraltb> rhrelacionlaboraltbListNew = admcompaniatb.getRhrelacionlaboraltbList();
            List<Admpuestotb> admpuestotbListOld = persistentAdmcompaniatb.getAdmpuestotbList();
            List<Admpuestotb> admpuestotbListNew = admcompaniatb.getAdmpuestotbList();
            List<Taturnotb> taturnotbListOld = persistentAdmcompaniatb.getTaturnotbList();
            List<Taturnotb> taturnotbListNew = admcompaniatb.getTaturnotbList();
            List<Admregistropatronaltb> attachedAdmregistropatronaltbListNew = new ArrayList<Admregistropatronaltb>();
            for (Admregistropatronaltb admregistropatronaltbListNewAdmregistropatronaltbToAttach : admregistropatronaltbListNew) {
                admregistropatronaltbListNewAdmregistropatronaltbToAttach = em.getReference(admregistropatronaltbListNewAdmregistropatronaltbToAttach.getClass(), admregistropatronaltbListNewAdmregistropatronaltbToAttach.getIdregistropatronal());
                attachedAdmregistropatronaltbListNew.add(admregistropatronaltbListNewAdmregistropatronaltbToAttach);
            }
            admregistropatronaltbListNew = attachedAdmregistropatronaltbListNew;
            admcompaniatb.setAdmregistropatronaltbList(admregistropatronaltbListNew);
            List<Admdepartamentotb> attachedAdmdepartamentotbListNew = new ArrayList<Admdepartamentotb>();
            for (Admdepartamentotb admdepartamentotbListNewAdmdepartamentotbToAttach : admdepartamentotbListNew) {
                admdepartamentotbListNewAdmdepartamentotbToAttach = em.getReference(admdepartamentotbListNewAdmdepartamentotbToAttach.getClass(), admdepartamentotbListNewAdmdepartamentotbToAttach.getIddepartamento());
                attachedAdmdepartamentotbListNew.add(admdepartamentotbListNewAdmdepartamentotbToAttach);
            }
            admdepartamentotbListNew = attachedAdmdepartamentotbListNew;
            admcompaniatb.setAdmdepartamentotbList(admdepartamentotbListNew);
            List<Nomgrupopagotb> attachedNomgrupopagotbListNew = new ArrayList<Nomgrupopagotb>();
            for (Nomgrupopagotb nomgrupopagotbListNewNomgrupopagotbToAttach : nomgrupopagotbListNew) {
                nomgrupopagotbListNewNomgrupopagotbToAttach = em.getReference(nomgrupopagotbListNewNomgrupopagotbToAttach.getClass(), nomgrupopagotbListNewNomgrupopagotbToAttach.getIdgrupopago());
                attachedNomgrupopagotbListNew.add(nomgrupopagotbListNewNomgrupopagotbToAttach);
            }
            nomgrupopagotbListNew = attachedNomgrupopagotbListNew;
            admcompaniatb.setNomgrupopagotbList(nomgrupopagotbListNew);
            List<Rhrelacionlaboraltb> attachedRhrelacionlaboraltbListNew = new ArrayList<Rhrelacionlaboraltb>();
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach : rhrelacionlaboraltbListNew) {
                rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach = em.getReference(rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach.getClass(), rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach.getIdrellab());
                attachedRhrelacionlaboraltbListNew.add(rhrelacionlaboraltbListNewRhrelacionlaboraltbToAttach);
            }
            rhrelacionlaboraltbListNew = attachedRhrelacionlaboraltbListNew;
            admcompaniatb.setRhrelacionlaboraltbList(rhrelacionlaboraltbListNew);
            List<Admpuestotb> attachedAdmpuestotbListNew = new ArrayList<Admpuestotb>();
            for (Admpuestotb admpuestotbListNewAdmpuestotbToAttach : admpuestotbListNew) {
                admpuestotbListNewAdmpuestotbToAttach = em.getReference(admpuestotbListNewAdmpuestotbToAttach.getClass(), admpuestotbListNewAdmpuestotbToAttach.getIdpuesto());
                attachedAdmpuestotbListNew.add(admpuestotbListNewAdmpuestotbToAttach);
            }
            admpuestotbListNew = attachedAdmpuestotbListNew;
            admcompaniatb.setAdmpuestotbList(admpuestotbListNew);
            List<Taturnotb> attachedTaturnotbListNew = new ArrayList<Taturnotb>();
            for (Taturnotb taturnotbListNewTaturnotbToAttach : taturnotbListNew) {
                taturnotbListNewTaturnotbToAttach = em.getReference(taturnotbListNewTaturnotbToAttach.getClass(), taturnotbListNewTaturnotbToAttach.getIdturno());
                attachedTaturnotbListNew.add(taturnotbListNewTaturnotbToAttach);
            }
            taturnotbListNew = attachedTaturnotbListNew;
            admcompaniatb.setTaturnotbList(taturnotbListNew);
            admcompaniatb = em.merge(admcompaniatb);
            for (Admregistropatronaltb admregistropatronaltbListOldAdmregistropatronaltb : admregistropatronaltbListOld) {
                if (!admregistropatronaltbListNew.contains(admregistropatronaltbListOldAdmregistropatronaltb)) {
                    admregistropatronaltbListOldAdmregistropatronaltb.setIdcompania(null);
                    admregistropatronaltbListOldAdmregistropatronaltb = em.merge(admregistropatronaltbListOldAdmregistropatronaltb);
                }
            }
            for (Admregistropatronaltb admregistropatronaltbListNewAdmregistropatronaltb : admregistropatronaltbListNew) {
                if (!admregistropatronaltbListOld.contains(admregistropatronaltbListNewAdmregistropatronaltb)) {
                    Admcompaniatb oldIdcompaniaOfAdmregistropatronaltbListNewAdmregistropatronaltb = admregistropatronaltbListNewAdmregistropatronaltb.getIdcompania();
                    admregistropatronaltbListNewAdmregistropatronaltb.setIdcompania(admcompaniatb);
                    admregistropatronaltbListNewAdmregistropatronaltb = em.merge(admregistropatronaltbListNewAdmregistropatronaltb);
                    if (oldIdcompaniaOfAdmregistropatronaltbListNewAdmregistropatronaltb != null && !oldIdcompaniaOfAdmregistropatronaltbListNewAdmregistropatronaltb.equals(admcompaniatb)) {
                        oldIdcompaniaOfAdmregistropatronaltbListNewAdmregistropatronaltb.getAdmregistropatronaltbList().remove(admregistropatronaltbListNewAdmregistropatronaltb);
                        oldIdcompaniaOfAdmregistropatronaltbListNewAdmregistropatronaltb = em.merge(oldIdcompaniaOfAdmregistropatronaltbListNewAdmregistropatronaltb);
                    }
                }
            }
            for (Admdepartamentotb admdepartamentotbListOldAdmdepartamentotb : admdepartamentotbListOld) {
                if (!admdepartamentotbListNew.contains(admdepartamentotbListOldAdmdepartamentotb)) {
                    admdepartamentotbListOldAdmdepartamentotb.setIdcompania(null);
                    admdepartamentotbListOldAdmdepartamentotb = em.merge(admdepartamentotbListOldAdmdepartamentotb);
                }
            }
            for (Admdepartamentotb admdepartamentotbListNewAdmdepartamentotb : admdepartamentotbListNew) {
                if (!admdepartamentotbListOld.contains(admdepartamentotbListNewAdmdepartamentotb)) {
                    Admcompaniatb oldIdcompaniaOfAdmdepartamentotbListNewAdmdepartamentotb = admdepartamentotbListNewAdmdepartamentotb.getIdcompania();
                    admdepartamentotbListNewAdmdepartamentotb.setIdcompania(admcompaniatb);
                    admdepartamentotbListNewAdmdepartamentotb = em.merge(admdepartamentotbListNewAdmdepartamentotb);
                    if (oldIdcompaniaOfAdmdepartamentotbListNewAdmdepartamentotb != null && !oldIdcompaniaOfAdmdepartamentotbListNewAdmdepartamentotb.equals(admcompaniatb)) {
                        oldIdcompaniaOfAdmdepartamentotbListNewAdmdepartamentotb.getAdmdepartamentotbList().remove(admdepartamentotbListNewAdmdepartamentotb);
                        oldIdcompaniaOfAdmdepartamentotbListNewAdmdepartamentotb = em.merge(oldIdcompaniaOfAdmdepartamentotbListNewAdmdepartamentotb);
                    }
                }
            }
            for (Nomgrupopagotb nomgrupopagotbListOldNomgrupopagotb : nomgrupopagotbListOld) {
                if (!nomgrupopagotbListNew.contains(nomgrupopagotbListOldNomgrupopagotb)) {
                    nomgrupopagotbListOldNomgrupopagotb.setIdcompania(null);
                    nomgrupopagotbListOldNomgrupopagotb = em.merge(nomgrupopagotbListOldNomgrupopagotb);
                }
            }
            for (Nomgrupopagotb nomgrupopagotbListNewNomgrupopagotb : nomgrupopagotbListNew) {
                if (!nomgrupopagotbListOld.contains(nomgrupopagotbListNewNomgrupopagotb)) {
                    Admcompaniatb oldIdcompaniaOfNomgrupopagotbListNewNomgrupopagotb = nomgrupopagotbListNewNomgrupopagotb.getIdcompania();
                    nomgrupopagotbListNewNomgrupopagotb.setIdcompania(admcompaniatb);
                    nomgrupopagotbListNewNomgrupopagotb = em.merge(nomgrupopagotbListNewNomgrupopagotb);
                    if (oldIdcompaniaOfNomgrupopagotbListNewNomgrupopagotb != null && !oldIdcompaniaOfNomgrupopagotbListNewNomgrupopagotb.equals(admcompaniatb)) {
                        oldIdcompaniaOfNomgrupopagotbListNewNomgrupopagotb.getNomgrupopagotbList().remove(nomgrupopagotbListNewNomgrupopagotb);
                        oldIdcompaniaOfNomgrupopagotbListNewNomgrupopagotb = em.merge(oldIdcompaniaOfNomgrupopagotbListNewNomgrupopagotb);
                    }
                }
            }
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListOldRhrelacionlaboraltb : rhrelacionlaboraltbListOld) {
                if (!rhrelacionlaboraltbListNew.contains(rhrelacionlaboraltbListOldRhrelacionlaboraltb)) {
                    rhrelacionlaboraltbListOldRhrelacionlaboraltb.setIdcompania(null);
                    rhrelacionlaboraltbListOldRhrelacionlaboraltb = em.merge(rhrelacionlaboraltbListOldRhrelacionlaboraltb);
                }
            }
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListNewRhrelacionlaboraltb : rhrelacionlaboraltbListNew) {
                if (!rhrelacionlaboraltbListOld.contains(rhrelacionlaboraltbListNewRhrelacionlaboraltb)) {
                    Admcompaniatb oldIdcompaniaOfRhrelacionlaboraltbListNewRhrelacionlaboraltb = rhrelacionlaboraltbListNewRhrelacionlaboraltb.getIdcompania();
                    rhrelacionlaboraltbListNewRhrelacionlaboraltb.setIdcompania(admcompaniatb);
                    rhrelacionlaboraltbListNewRhrelacionlaboraltb = em.merge(rhrelacionlaboraltbListNewRhrelacionlaboraltb);
                    if (oldIdcompaniaOfRhrelacionlaboraltbListNewRhrelacionlaboraltb != null && !oldIdcompaniaOfRhrelacionlaboraltbListNewRhrelacionlaboraltb.equals(admcompaniatb)) {
                        oldIdcompaniaOfRhrelacionlaboraltbListNewRhrelacionlaboraltb.getRhrelacionlaboraltbList().remove(rhrelacionlaboraltbListNewRhrelacionlaboraltb);
                        oldIdcompaniaOfRhrelacionlaboraltbListNewRhrelacionlaboraltb = em.merge(oldIdcompaniaOfRhrelacionlaboraltbListNewRhrelacionlaboraltb);
                    }
                }
            }
            for (Admpuestotb admpuestotbListOldAdmpuestotb : admpuestotbListOld) {
                if (!admpuestotbListNew.contains(admpuestotbListOldAdmpuestotb)) {
                    admpuestotbListOldAdmpuestotb.setIdcompania(null);
                    admpuestotbListOldAdmpuestotb = em.merge(admpuestotbListOldAdmpuestotb);
                }
            }
            for (Admpuestotb admpuestotbListNewAdmpuestotb : admpuestotbListNew) {
                if (!admpuestotbListOld.contains(admpuestotbListNewAdmpuestotb)) {
                    Admcompaniatb oldIdcompaniaOfAdmpuestotbListNewAdmpuestotb = admpuestotbListNewAdmpuestotb.getIdcompania();
                    admpuestotbListNewAdmpuestotb.setIdcompania(admcompaniatb);
                    admpuestotbListNewAdmpuestotb = em.merge(admpuestotbListNewAdmpuestotb);
                    if (oldIdcompaniaOfAdmpuestotbListNewAdmpuestotb != null && !oldIdcompaniaOfAdmpuestotbListNewAdmpuestotb.equals(admcompaniatb)) {
                        oldIdcompaniaOfAdmpuestotbListNewAdmpuestotb.getAdmpuestotbList().remove(admpuestotbListNewAdmpuestotb);
                        oldIdcompaniaOfAdmpuestotbListNewAdmpuestotb = em.merge(oldIdcompaniaOfAdmpuestotbListNewAdmpuestotb);
                    }
                }
            }
            for (Taturnotb taturnotbListOldTaturnotb : taturnotbListOld) {
                if (!taturnotbListNew.contains(taturnotbListOldTaturnotb)) {
                    taturnotbListOldTaturnotb.setIdcompania(null);
                    taturnotbListOldTaturnotb = em.merge(taturnotbListOldTaturnotb);
                }
            }
            for (Taturnotb taturnotbListNewTaturnotb : taturnotbListNew) {
                if (!taturnotbListOld.contains(taturnotbListNewTaturnotb)) {
                    Admcompaniatb oldIdcompaniaOfTaturnotbListNewTaturnotb = taturnotbListNewTaturnotb.getIdcompania();
                    taturnotbListNewTaturnotb.setIdcompania(admcompaniatb);
                    taturnotbListNewTaturnotb = em.merge(taturnotbListNewTaturnotb);
                    if (oldIdcompaniaOfTaturnotbListNewTaturnotb != null && !oldIdcompaniaOfTaturnotbListNewTaturnotb.equals(admcompaniatb)) {
                        oldIdcompaniaOfTaturnotbListNewTaturnotb.getTaturnotbList().remove(taturnotbListNewTaturnotb);
                        oldIdcompaniaOfTaturnotbListNewTaturnotb = em.merge(oldIdcompaniaOfTaturnotbListNewTaturnotb);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = admcompaniatb.getIdcompania();
                if (findAdmcompaniatb(id) == null) {
                    throw new NonexistentEntityException("The admcompaniatb with id " + id + " no longer exists.");
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
            Admcompaniatb admcompaniatb;
            try {
                admcompaniatb = em.getReference(Admcompaniatb.class, id);
                admcompaniatb.getIdcompania();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The admcompaniatb with id " + id + " no longer exists.", enfe);
            }
            List<Admregistropatronaltb> admregistropatronaltbList = admcompaniatb.getAdmregistropatronaltbList();
            for (Admregistropatronaltb admregistropatronaltbListAdmregistropatronaltb : admregistropatronaltbList) {
                admregistropatronaltbListAdmregistropatronaltb.setIdcompania(null);
                admregistropatronaltbListAdmregistropatronaltb = em.merge(admregistropatronaltbListAdmregistropatronaltb);
            }
            List<Admdepartamentotb> admdepartamentotbList = admcompaniatb.getAdmdepartamentotbList();
            for (Admdepartamentotb admdepartamentotbListAdmdepartamentotb : admdepartamentotbList) {
                admdepartamentotbListAdmdepartamentotb.setIdcompania(null);
                admdepartamentotbListAdmdepartamentotb = em.merge(admdepartamentotbListAdmdepartamentotb);
            }
            List<Nomgrupopagotb> nomgrupopagotbList = admcompaniatb.getNomgrupopagotbList();
            for (Nomgrupopagotb nomgrupopagotbListNomgrupopagotb : nomgrupopagotbList) {
                nomgrupopagotbListNomgrupopagotb.setIdcompania(null);
                nomgrupopagotbListNomgrupopagotb = em.merge(nomgrupopagotbListNomgrupopagotb);
            }
            List<Rhrelacionlaboraltb> rhrelacionlaboraltbList = admcompaniatb.getRhrelacionlaboraltbList();
            for (Rhrelacionlaboraltb rhrelacionlaboraltbListRhrelacionlaboraltb : rhrelacionlaboraltbList) {
                rhrelacionlaboraltbListRhrelacionlaboraltb.setIdcompania(null);
                rhrelacionlaboraltbListRhrelacionlaboraltb = em.merge(rhrelacionlaboraltbListRhrelacionlaboraltb);
            }
            List<Admpuestotb> admpuestotbList = admcompaniatb.getAdmpuestotbList();
            for (Admpuestotb admpuestotbListAdmpuestotb : admpuestotbList) {
                admpuestotbListAdmpuestotb.setIdcompania(null);
                admpuestotbListAdmpuestotb = em.merge(admpuestotbListAdmpuestotb);
            }
            List<Taturnotb> taturnotbList = admcompaniatb.getTaturnotbList();
            for (Taturnotb taturnotbListTaturnotb : taturnotbList) {
                taturnotbListTaturnotb.setIdcompania(null);
                taturnotbListTaturnotb = em.merge(taturnotbListTaturnotb);
            }
            em.remove(admcompaniatb);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Admcompaniatb> findAdmcompaniatbEntities() {
        return findAdmcompaniatbEntities(true, -1, -1);
    }

    public List<Admcompaniatb> findAdmcompaniatbEntities(int maxResults, int firstResult) {
        return findAdmcompaniatbEntities(false, maxResults, firstResult);
    }

    private List<Admcompaniatb> findAdmcompaniatbEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Admcompaniatb.class));
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

    public Admcompaniatb findAdmcompaniatb(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Admcompaniatb.class, id);
        } finally {
            em.close();
        }
    }

    public int getAdmcompaniatbCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Admcompaniatb> rt = cq.from(Admcompaniatb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
