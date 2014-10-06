/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cenicco.migracion.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Antonio
 */
public class JpaUtil {

    private JpaUtil() {
    }

    public static JpaUtil getInstance() {
        return JpaUtilHolder.INSTANCE;
    }

    private static class JpaUtilHolder {

        private static final JpaUtil INSTANCE = new JpaUtil();
//        
        private static final EntityManagerFactory EMFORacle = Persistence.createEntityManagerFactory("MigracionCeniccoOracle");
//        
        private static final EntityManagerFactory EMFMysql = Persistence.createEntityManagerFactory("MigracionCeniccoMysql");
    }

    public EntityManager createEntityManagerCeniccoOracle() {
        return JpaUtilHolder.EMFORacle.createEntityManager();
    }

    public EntityManager createEntityManagerCeniccoMysql() {
        return JpaUtilHolder.EMFMysql.createEntityManager();
    }

    public void closeEntityManager(EntityManager em) {
        if (em != null) {
            em.close();
        }
    }

    public void rollbackEntityManager(EntityManager em) {
        if (em != null) {
            em.getTransaction().rollback();
        }
    }
}
