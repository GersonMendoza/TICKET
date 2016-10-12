/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.CorrelativoMantenimientos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gersonfrancisco
 */
@Local
public interface CorrelativoMantenimientosFacadeLocal {

    void create(CorrelativoMantenimientos correlativoMantenimientos);

    void edit(CorrelativoMantenimientos correlativoMantenimientos);

    void remove(CorrelativoMantenimientos correlativoMantenimientos);

    CorrelativoMantenimientos find(Object id);

    List<CorrelativoMantenimientos> findAll();

    List<CorrelativoMantenimientos> findRange(int[] range);

    int count();
    
}
