/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.UbicacionesMantenimiento;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gersonfrancisco
 */
@Local
public interface UbicacionesMantenimientoFacadeLocal {

    void create(UbicacionesMantenimiento ubicacionesMantenimiento);

    void edit(UbicacionesMantenimiento ubicacionesMantenimiento);

    void remove(UbicacionesMantenimiento ubicacionesMantenimiento);

    UbicacionesMantenimiento find(Object id);

    List<UbicacionesMantenimiento> findAll();

    List<UbicacionesMantenimiento> findRange(int[] range);

    int count();
    
}
