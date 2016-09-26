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
 * @author oscar
 */
@Local
public interface SolicitudesFacadeLocal {

    void create(Solicitudes solicitudes);

    void edit(Solicitudes solicitudes);

    void remove(Solicitudes solicitudes);

    Solicitudes find(Object id);

    List<Solicitudes> findAll();

    List<Solicitudes> findRange(int[] range);

    int count();
    
}
