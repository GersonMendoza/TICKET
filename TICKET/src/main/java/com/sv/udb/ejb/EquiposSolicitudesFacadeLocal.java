/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.Equipos;
import com.sv.udb.modelo.EquiposSolicitudes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gersonfrancisco
 */
@Local
public interface EquiposSolicitudesFacadeLocal {

    void create(EquiposSolicitudes equiposSolicitudes);

    void edit(EquiposSolicitudes equiposSolicitudes);

    void remove(EquiposSolicitudes equiposSolicitudes);

    EquiposSolicitudes find(Object id);

    List<EquiposSolicitudes> findAll();
    
    List<Equipos> findTodo(int codi);
    
    String findDesc(int codiEqui, int codiSoli);
    
    Long cantEqui(int codi);

    List<EquiposSolicitudes> findRange(int[] range);

    int count();
    
}
