/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.controlador.LoginBean;
import com.sv.udb.modelo.Solicitudes;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author gersonfrancisco
 */
@Stateless
public class SolicitudesFacade extends AbstractFacade<Solicitudes> implements SolicitudesFacadeLocal {
    @PersistenceContext(unitName = "PILETPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitudesFacade() {
        super(Solicitudes.class);
    }
    @Override
    public List<Solicitudes> findTodo() {
        LoginBean login = new LoginBean();
        System.out.print("\n"+login.getObjeUsua().getCodiUsua());
        Query q = getEntityManager().createQuery("SELECT u FROM Solicitudes u WHERE u.codiUsua ="+login.getObjeUsua().getCodiUsua(), Solicitudes.class);
        List resu = q.getResultList();
        return resu;
    }
    
    @Override
    public List<Solicitudes> findEncargado() {
        Query q = getEntityManager().createQuery("SELECT u FROM Solicitudes u WHERE u.estaSoli = 1", Solicitudes.class);
        List resu = q.getResultList();
        return resu;
    }
    
    @Override
    public List<Solicitudes> findTecnico() {
        Query q = getEntityManager().createQuery("SELECT u FROM Solicitudes u WHERE u.estaSoli = 2", Solicitudes.class);
        List resu = q.getResultList();
        return resu;
    }
    
    @Override
    public List<Solicitudes> findVaci() {
        Query q = getEntityManager().createQuery("SELECT u FROM Solicitudes u WHERE u.codiEnca IS NULL", Solicitudes.class);
        List resu = q.getResultList();
        return resu;
    }
    
    @Override
    public List<Solicitudes> findAllAsig() {
        Query q = getEntityManager().createQuery("SELECT u FROM Solicitudes u WHERE u.estaSoli = 2", Solicitudes.class);        
        List resu = q.getResultList();
        return resu;
    }
    
    @Override
    public List<Solicitudes> findAsig() {
        Query q = getEntityManager().createQuery("SELECT u FROM Solicitudes u WHERE u.codiEnca = :codiEnca AND u.estaSoli = 2", Solicitudes.class);
        LoginBean login = new LoginBean();
        q.setParameter("codiEnca", login.getObjeUsua().getCodiUsua());
        List resu = q.getResultList();
        return resu;
    }
    
    @Override
    public void asig(int codiSoli, int codiUsua) {
        Query q = getEntityManager().createQuery("UPDATE Solicitudes s SET s.codiEnca = :codiUsua, s.estaSoli = 2 WHERE s.codiSoli = :codiSoli", Solicitudes.class);
        q.setParameter("codiUsua", codiUsua);
        q.setParameter("codiSoli", codiSoli);
        q.executeUpdate();
    }
}
