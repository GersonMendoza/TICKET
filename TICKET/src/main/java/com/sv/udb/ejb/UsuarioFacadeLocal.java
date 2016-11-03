/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Adonay
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);
    
    Usuario findByAcce(Object acce);
    
    boolean findPermByAcceAndDire(Object acce, Object path);

    List<Usuario> findAll();
    
    List<Usuario> findTecn();

    List<Usuario> findRange(int[] range);

    int count();
    
}
