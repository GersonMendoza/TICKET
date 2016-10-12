/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.ProcesoMantenimientos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gersonfrancisco
 */
@Local
public interface ProcesoMantenimientosFacadeLocal {

    void create(ProcesoMantenimientos procesoMantenimientos);

    void edit(ProcesoMantenimientos procesoMantenimientos);

    void remove(ProcesoMantenimientos procesoMantenimientos);

    ProcesoMantenimientos find(Object id);

    List<ProcesoMantenimientos> findAll();

    List<ProcesoMantenimientos> findRange(int[] range);

    int count();
    
}
