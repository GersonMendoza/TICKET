/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.Solicitudes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gersonfrancisco
 */
@Local
public interface SolicitudesFacadeLocal {

    void create(Solicitudes solicitudes);

    void edit(Solicitudes solicitudes);

    void remove(Solicitudes solicitudes);

    Solicitudes find(Object id);
    
    List<Solicitudes> findTodo();
    
    List<Solicitudes> findEncargado();
    
    List<Solicitudes> findTecnico();

    List<Solicitudes> findVaci();
    
    List<Solicitudes> findAsig();

    List<Solicitudes> findAll();

    List<Solicitudes> findRange(int[] range);
    
    void asig(int codiSoli, int codiUsua);

    int count();
    
}
