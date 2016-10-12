/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.EvaluacionResoluciones;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gersonfrancisco
 */
@Local
public interface EvaluacionResolucionesFacadeLocal {

    void create(EvaluacionResoluciones evaluacionResoluciones);

    void edit(EvaluacionResoluciones evaluacionResoluciones);

    void remove(EvaluacionResoluciones evaluacionResoluciones);

    EvaluacionResoluciones find(Object id);

    List<EvaluacionResoluciones> findAll();

    List<EvaluacionResoluciones> findRange(int[] range);

    int count();
    
}
