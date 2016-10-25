/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.TiposUsuarios;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gersonfrancisco
 */
@Local
public interface TiposUsuariosFacadeLocal {

    void create(TiposUsuarios tiposUsuarios);

    void edit(TiposUsuarios tiposUsuarios);

    void remove(TiposUsuarios tiposUsuarios);

    TiposUsuarios find(Object id);

    List<TiposUsuarios> findAll();

    List<TiposUsuarios> findRange(int[] range);

    int count();
    
}
