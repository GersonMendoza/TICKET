/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.TipoMantenimientos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gersonfrancisco
 */
@Local
public interface TipoMantenimientosFacadeLocal {

    void create(TipoMantenimientos tipoMantenimientos);

    void edit(TipoMantenimientos tipoMantenimientos);

    void remove(TipoMantenimientos tipoMantenimientos);

    TipoMantenimientos find(Object id);

    List<TipoMantenimientos> findAll();

    List<TipoMantenimientos> findRange(int[] range);

    int count();
    
}
