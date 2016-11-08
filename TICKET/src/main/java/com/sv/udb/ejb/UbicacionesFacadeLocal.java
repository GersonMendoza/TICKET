/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.Ubicaciones;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gersonfrancisco
 */
@Local
public interface UbicacionesFacadeLocal {

    void create(Ubicaciones ubicaciones);

    void edit(Ubicaciones ubicaciones);

    void remove(Ubicaciones ubicaciones);

    Ubicaciones find(Object id);

    List<Ubicaciones> findAll();
    
    List<Ubicaciones> findRange(int[] range);

    int count();
    
}
