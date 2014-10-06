/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cenicco.migracion.proceso;

import com.lam.cenicco.migracion.controladores.mysql.CatasentamientotbJpaController;
import com.lam.cenicco.migracion.controladores.mysql.CatciudadtbJpaController;
import com.lam.cenicco.migracion.controladores.mysql.CattipoasentamientotbJpaController;
import com.lam.cenicco.migracion.controladores.oracle.AsentamientoOracleJpaController;
import com.lam.cenicco.migracion.controladores.oracle.CatalogoGeneralOracleJpaController;
import com.lam.cenicco.migracion.entidades.mysql.Catasentamientotb;
import com.lam.cenicco.migracion.entidades.mysql.Catciudadtb;
import com.lam.cenicco.migracion.entidades.mysql.Cattipoasentamientotb;
import com.lam.cenicco.migracion.entidades.oracle.AsentamientoOracle;
import com.lam.cenicco.migracion.entidades.oracle.CatalogoGeneralOracle;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Antonio
 */
public class MigracionCenicco {

    public static void main(String[] args) {
        String valorCaracter = "A";
        int valorEntero = 2;
//        
//        int sumaEntero = Integer.parseInt(valorCaracter) + valorEntero;
        String sumaCaracter = valorCaracter + valorEntero;
//        
//        System.out.println("SumaEntero= " + sumaEntero);
        System.out.println("SumaCaracter= " + sumaCaracter);
//        
//        migrarPaises();
//        migrarEstados();
//        migrarMunicipios();
//        migrarCompania();
//        migrarPuesto();
//        migrarCiudades();
//        migrarCatalogoGeneral();
//        migrarEmpleados();
//        migrarRelacionLaboral();
//        migrarPosicionSueldos();
//        migrarTipoAsentamientos();
//        migrarAsentamientos();
    }

    private static void migrarAsentamientos() {
        CatasentamientotbJpaController controlMysql = new CatasentamientotbJpaController();
        AsentamientoOracleJpaController controlOracle = new AsentamientoOracleJpaController();
//        
        CattipoasentamientotbJpaController controlTipoMysql = new CattipoasentamientotbJpaController();
        CatciudadtbJpaController controlCiudadMysql = new CatciudadtbJpaController();
//        
        CatalogoGeneralOracleJpaController controlTipoOracle = new CatalogoGeneralOracleJpaController();

//        
        List<AsentamientoOracle> asentamientos = controlOracle.findAsentamientoOracleEntities();
//        
        Map<String, String> mapaTipoAsentamientosOracle = controlTipoOracle.findByAsentamientoNombre();
        Map<String, Cattipoasentamientotb> mapaTipoAsentamientosMyMsql = controlTipoMysql.findByNombre();
//
        int cont = 1;
//        
        for (AsentamientoOracle a : asentamientos) {
            Catasentamientotb am = new Catasentamientotb();
            String nombre = mapaTipoAsentamientosOracle.get(a.getIdtipoasentamiento());
            System.out.println(a.getCiudadOracle().getNombre() + " | " + a.getAsentamiento() + " | " + a.getNombre() + " | " + cont);
            Cattipoasentamientotb ta = mapaTipoAsentamientosMyMsql.get(nombre);
//            
            Catciudadtb ciudad = controlCiudadMysql.findByCiudadMunicipioEstado(a.getCiudadOracle().getCiudad());
//            
            am.setIdciudad(ciudad);
            am.setIdtipoasentamiento(ta);
            am.setAsentamiento(a.getNombre());
            am.setCodigopostal("" + a.getAsentamientoOraclePK().getCodigopostal());
            controlMysql.create(am);
            cont++;
        }
    }

//    private static void migrarPaises() {
//        PaisMysqlJpaController controlMysql = new PaisMysqlJpaController();
//        PaisOracleJpaController controlOracle = new PaisOracleJpaController();
//        List<PaisOracle> paises = controlOracle.findPaisOracleEntities();
//        for (PaisOracle p : paises) {
//            PaisMysql pm = new PaisMysql();
//            pm.setNombre(p.getNombre());
//            pm.setPais(p.getPais());
//            controlMysql.create(pm);
//        }
//    }
//
//    private static void migrarEstados() {
//        EstadoMysqlJpaController controlMysql = new EstadoMysqlJpaController();
//        EstadoOracleJpaController controlOracle = new EstadoOracleJpaController();
//        PaisMysqlJpaController controlPaisMysql = new PaisMysqlJpaController();
//        List<EstadoOracle> estados = controlOracle.findEstadoOracleEntities();
//        for (EstadoOracle e : estados) {
//            EstadoMysql em = new EstadoMysql();
//            em.setIdpais(controlPaisMysql.findPaisMysql(1));
//            em.setEstado(e.getEstado());
//            em.setNombre(e.getNombre());
//            controlMysql.create(em);
//        }
//    }
//
//    private static void migrarMunicipios() {
//        MunicipioMysqlJpaController controlMysql = new MunicipioMysqlJpaController();
//        MunicipioOracleJpaController controlOracle = new MunicipioOracleJpaController();
//        EstadoMysqlJpaController controlMysqlEstado = new EstadoMysqlJpaController();
//        List<MunicipioOracle> municipios = controlOracle.findMunicipioOracleEntities();
//        for (MunicipioOracle m : municipios) {
//            MunicipioMysql mm = new MunicipioMysql();
//            EstadoMysql em = controlMysqlEstado.findEstadoByNombre(m.getEstadoOracle().getNombre());
//            mm.setIdestado(em);
//            mm.setMunicipio(m.getMunicipio());
//            mm.setNombre(m.getNombre());
//            controlMysql.create(mm);
//        }
//    }
//
//    private static void migrarCompania() {
//        CompaniaMysqlJpaController controlMysql = new CompaniaMysqlJpaController();
//        CompaniaOracleJpaController controlOracle = new CompaniaOracleJpaController();
//        List<CompaniaOracle> companias = controlOracle.findCompaniaOracleEntities();
//        for (CompaniaOracle c : companias) {
//            CompaniaMysql cm = new CompaniaMysql();
//            cm.setEstatus(c.getEstatus());
//            cm.setCompania(c.getCompania());
//            cm.setNombre(c.getNombre());
//            cm.setRfc(c.getRfc());
//            controlMysql.create(cm);
//        }
//    }
//
//    private static void migrarPuesto() {
//        PuestoMysqlJpaController controlMysql = new PuestoMysqlJpaController();
//        PuestoOracleJpaController controlOracle = new PuestoOracleJpaController();
//        CompaniaMysqlJpaController controlCompaniaMysql = new CompaniaMysqlJpaController();
//        List<PuestoOracle> puestos = controlOracle.findPuestoOracleEntities();
//        for (PuestoOracle p : puestos) {
//            PuestoMysql pm = new PuestoMysql();
//            CompaniaMysql cm = controlCompaniaMysql.findCompaniaMysql(1);
//            pm.setIdcompania(cm);
//            pm.setNombre(p.getNombre());
//            pm.setPlazas(p.getPlazas());
//            pm.setPlazasdisponibles(p.getPlazasdisponibles());
//            pm.setPlazasocupadas(p.getPlazasocupadas());
//            pm.setPlazasreservadas(p.getPlazasreservadas());
//            pm.setPuesto(p.getPuesto());
//            controlMysql.create(pm);
//        }
//    }
//
//    private static void migrarCiudades() {
//        CiudadMysqlJpaController controlMysql = new CiudadMysqlJpaController();
//        CiudadOracleJpaController controlOracle = new CiudadOracleJpaController();
//        MunicipioMysqlJpaController controlMysqlMunicipio = new MunicipioMysqlJpaController();
//        List<CiudadOracle> ciudades = controlOracle.findCiudadOracleEntities();
//        for (CiudadOracle c : ciudades) {
//            CiudadMysql cm = new CiudadMysql();
//            MunicipioMysql mm = controlMysqlMunicipio.findByNombre(c.getMunicipioOracle().getNombre(),
//                    c.getMunicipioOracle().getEstadoOracle().getNombre());
//            cm.setCiudad(c.getCiudad());
//            cm.setNombre(c.getNombre());
//            cm.setIdmunicipio(mm);
//            controlMysql.create(cm);
//        }
//
//    }
//
//    private static void migrarCatalogoGeneral() {
//        CatalogoGeneralMysqlJpaController controlMysql = new CatalogoGeneralMysqlJpaController();
//        CatalogoGeneralOracleJpaController controlOracle = new CatalogoGeneralOracleJpaController();
//        List<CatalogoGeneralOracle> catalogos = controlOracle.findCatalogoGeneralOracleEntities();
//        for (CatalogoGeneralOracle c : catalogos) {
//            CatalogoGeneralMysql cm = new CatalogoGeneralMysql();
//            cm.setColumna(c.getCatalogoGeneralOraclePK().getColumna());
//            cm.setNombre(c.getNombre());
//            cm.setTabla(c.getCatalogoGeneralOraclePK().getTabla());
//            cm.setValor(c.getCatalogoGeneralOraclePK().getValor());
//            controlMysql.create(cm);
//        }
//    }
//
//    private static void migrarEmpleados() {
//        EmpleadoMysqlJpaController controlMysql = new EmpleadoMysqlJpaController();
//        EmpleadoOracleJpaController controlOracle = new EmpleadoOracleJpaController();
//        EstadoMysqlJpaController controlEstadoMysql = new EstadoMysqlJpaController();
//        List<EmpleadoOracle> empleados = controlOracle.findEmpleadoOracleEntities();
//        for (EmpleadoOracle e : empleados) {
//            EmpleadoMysql em = new EmpleadoMysql();
//            EstadoMysql edom = controlEstadoMysql.findEstadoByNombre(e.getEstadoOracle().getNombre());
//            em.setAfiliacion(e.getAfiliacion());
//            em.setApellidomaterno(e.getApellidomaterno());
//            em.setApellidopaterno(e.getApellidopaterno());
//            em.setCurp(e.getCurp());
//            em.setEstadocivil(e.getEstadocivil());
//            em.setEstatus(e.getEstatus());
//            em.setExpediente(e.getExpediente());
//            em.setFechanacimiento(e.getFechanacimiento());
//            em.setIdestadonacimiento(edom);
//            em.setLugarnacimiento(e.getLugarnacimiento());
//            em.setNombre(e.getNombre());
//            em.setPathfirma(e.getPathfirma());
//            em.setPathfoto(e.getPathfoto());
//            em.setRfc(e.getRfc());
//            em.setSexo("" + e.getSexo());
//            controlMysql.create(em);
//        }
//    }
//
//    private static void migrarRelacionLaboral() {
//        RelacionLaboralMysqlJpaController controlMysql = new RelacionLaboralMysqlJpaController();
//        RelacionLaboralOracleJpaController controlOracle = new RelacionLaboralOracleJpaController();
////        
//        CompaniaMysqlJpaController controlCompania = new CompaniaMysqlJpaController();
//        EmpleadoMysqlJpaController controlEmpleado = new EmpleadoMysqlJpaController();
//        GrupoPagoMysqlJpaController controlGrupoPago = new GrupoPagoMysqlJpaController();
//        RegistroPatronalMysqlJpaController controlRegistro = new RegistroPatronalMysqlJpaController();
//        TIpoRelacionLaboralMysqlJpaController controlTipoRelLab = new TIpoRelacionLaboralMysqlJpaController();
////        
//        List<RelacionLaboralOracle> relaciones = controlOracle.findRelacionLaboralOracleEntities();
//        for (RelacionLaboralOracle r : relaciones) {
//            CompaniaMysql cm = controlCompania.findCompaniaMysql(1);
//            EmpleadoMysql em = controlEmpleado.findByExpediente(r.getIdempleado().getExpediente());
//            GrupoPagoMysql gm = controlGrupoPago.findGrupoPagoMysql(r.getIdgrupopago().getIdgrupopago().intValue());
//            RegistroPatronalMysql rm = controlRegistro.findRegistroPatronalMysql(1);
//            TIpoRelacionLaboralMysql trm = controlTipoRelLab.findTIpoRelacionLaboralMysql(Integer.parseInt(r.getTiporellab().getTiporellab()));
//            RelacionLaboralMysql relMy = new RelacionLaboralMysql();
////            
//            relMy.setCausabaja(r.getCausabaja());
//            relMy.setCausabajaimss(r.getCausabajaimss());
//            relMy.setDuracion(r.getDuracion());
//            relMy.setEstatus(r.getEstatus());
//            relMy.setFechaantiguedad1(r.getFechaantiguedad1());
//            relMy.setFechaantiguedad2(r.getFechaantiguedad2());
//            relMy.setFechaantiguedad3(r.getFechaantiguedad3());
//            relMy.setFechabaja(r.getFechabaja());
//            relMy.setFechaeventocontrato(r.getFechaventocontrato());
//            relMy.setFechaingreso(r.getFechaingreso());
//            relMy.setFechainiciocontrato(r.getFechainiciocontrato());
//            relMy.setIdcompania(cm);
//            relMy.setIdempleado(em);
//            relMy.setIdgrupopago(gm);
//            relMy.setIdregistropatronal(rm);
//            relMy.setIdtiporellab(trm);
//            relMy.setNumeroempleado(r.getEmpleado());
//            relMy.setSistemaantiguedad(r.getSistemaantiguedad());
//            relMy.setTipocontrato(r.getTipocontrato());
//            controlMysql.create(relMy);
//        }
//    }
//
//    private static void migrarPosicionSueldos() {
//        PosicionSueldoHistoricoMysqlJpaController controlMysql = new PosicionSueldoHistoricoMysqlJpaController();
//        PosicionSueldoOracleJpaController controlOracle = new PosicionSueldoOracleJpaController();
////        
//        RelacionLaboralMysqlJpaController controlRelLab = new RelacionLaboralMysqlJpaController();
//        TipoSueldoMsyqlJpaController controlTipoSueldo = new TipoSueldoMsyqlJpaController();
////        
//        RelacionLaboralOracleJpaController controlRelLabOracle = new RelacionLaboralOracleJpaController();
////        
//        List<PosicionSueldoOracle> posiciones = controlOracle.findPosicionSueldoOracleEntities();
//        for (PosicionSueldoOracle p : posiciones) {
//            PosicionSueldoHistoricoMysql pm = new PosicionSueldoHistoricoMysql();
//            RelacionLaboralOracle rlo = controlRelLabOracle.findRelacionLaboralOracle(p.getPosicionSueldoOraclePK().getIdrellab());
//            RelacionLaboralMysql rlm = controlRelLab.findByExpediente(rlo.getIdempleado().getExpediente());
//            TipoSueldoMsyql tsm = controlTipoSueldo.findByTipoSueldo(p.getPosicionSueldoOraclePK().getTiposueldo());
//            pm.setIdrellab(rlm);
//            pm.setIdtiposueldo(tsm);
//            pm.setFechainicio(p.getPosicionSueldoOraclePK().getFecha());
//            pm.setFechafin(p.getPosicionSueldoOraclePK().getFecha());
//            pm.setSueldo(p.getSueldo().doubleValue());
//            controlMysql.create(pm);
//        }
//    }
    private static void migrarPosicionSueldos() {
        //        PosicionSueldoHistoricoMysqlJpaController controlMysql = new PosicionSueldoHistoricoMysqlJpaController();
//        PosicionSueldoOracleJpaController controlOracle = new PosicionSueldoOracleJpaController();
////        
//        RelacionLaboralMysqlJpaController controlRelLab = new RelacionLaboralMysqlJpaController();
//        TipoSueldoMsyqlJpaController controlTipoSueldo = new TipoSueldoMsyqlJpaController();
////        
//        RelacionLaboralOracleJpaController controlRelLabOracle = new RelacionLaboralOracleJpaController();
////        
//        List<PosicionSueldoOracle> posiciones = controlOracle.findPosicionSueldoOracleEntities();
//        for (PosicionSueldoOracle p : posiciones) {
//            PosicionSueldoHistoricoMysql pm = new PosicionSueldoHistoricoMysql();
//            RelacionLaboralOracle rlo = controlRelLabOracle.findRelacionLaboralOracle(p.getPosicionSueldoOraclePK().getIdrellab());
//            RelacionLaboralMysql rlm = controlRelLab.findByExpediente(rlo.getIdempleado().getExpediente());
//            TipoSueldoMsyql tsm = controlTipoSueldo.findByTipoSueldo(p.getPosicionSueldoOraclePK().getTiposueldo());
//            pm.setIdrellab(rlm);
//            pm.setIdtiposueldo(tsm);
//            pm.setFechainicio(p.getPosicionSueldoOraclePK().getFecha());
//            pm.setFechafin(p.getPosicionSueldoOraclePK().getFecha());
//            pm.setSueldo(p.getSueldo().doubleValue());
//            controlMysql.create(pm);
//        }
    }

    private static void migrarTipoAsentamientos() {
        CattipoasentamientotbJpaController controlMysql = new CattipoasentamientotbJpaController();
        CatalogoGeneralOracleJpaController controladorOracle = new CatalogoGeneralOracleJpaController();
        List<CatalogoGeneralOracle> tipoAsentamientos = controladorOracle.findByTipoAsentamientos();
        for (CatalogoGeneralOracle c : tipoAsentamientos) {
            Cattipoasentamientotb am = new Cattipoasentamientotb();
            am.setNombre(c.getNombre());
            controlMysql.create(am);
        }
    }
}
