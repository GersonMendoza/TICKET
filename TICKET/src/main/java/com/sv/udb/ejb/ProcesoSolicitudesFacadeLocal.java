/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.ProcesoSolicitudes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gersonfrancisco
 */
@Local
public interface ProcesoSolicitudesFacadeLocal {

    void create(ProcesoSolicitudes procesoSolicitudes);

    void edit(ProcesoSolicitudes procesoSolicitudes);

    void remove(ProcesoSolicitudes procesoSolicitudes);

    ProcesoSolicitudes find(Object id);

    List<ProcesoSolicitudes> findAll();

    List<ProcesoSolicitudes> findRange(int[] range);

    int count();
    
}
