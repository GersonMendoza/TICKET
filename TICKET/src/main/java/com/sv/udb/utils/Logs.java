/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.utils;

import static javax.ws.rs.client.Entity.entity;
import org.apache.log4j.Logger;

/**
 *
 * @author oscar
 */
public abstract class Logs<T> {
    private Class<T> entityClass;
    private Logger log;
    
     public Logs(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.log = Logger.getLogger(entityClass.getName());
    } 

    public Logger getLog() {
        return log;
    }
     
     
}
