/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.Equipos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gersonfrancisco
 */
@Local
public interface EquiposFacadeLocal {

    void create(Equipos equipos);

    void edit(Equipos equipos);

    void remove(Equipos equipos);

    Equipos find(Object id);

    List<Equipos> findAll();

    List<Equipos> findRange(int[] range);

    int count();
    
}
