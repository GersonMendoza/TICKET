/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.ResolucionSolicitudes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gersonfrancisco
 */
@Local
public interface ResolucionSolicitudesFacadeLocal {

    void create(ResolucionSolicitudes resolucionSolicitudes);

    void edit(ResolucionSolicitudes resolucionSolicitudes);

    void remove(ResolucionSolicitudes resolucionSolicitudes);

    ResolucionSolicitudes find(Object id);

    List<ResolucionSolicitudes> findAll();

    List<ResolucionSolicitudes> findRange(int[] range);

    int count();
    
}
