/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.utils;

import org.apache.log4j.*;

/**
 * La clase LOG4J se encarga de realizar bitácora de acciones realizadas en el sistema
 * @author AGAV Team
 * @version Prototipo 1
 */

public class LOG4J {
    //Propiedad de tipo Logger que invocará los distintos métodos de Log4J
    private static Logger log = Logger.getLogger(LOG4J.class);
    
    /**
    * Método público donde se invocará el archivo de propiedades de Log4J
    */
    public LOG4J() {
        try{
            PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("/log4j.properties").getPath());;
            
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e.getMessage());
        }
        
    }
    
    /**
    * Método encargado de realizar el filtrado y redirigir a los usuarios a las páginas que le competen
    * @param mens mensaje que se guardará en la tabla applog de la base de datos
    */
    public void trace(String mens){
        try{
            log.trace(mens);
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e.getMessage());
        }
        
    }
    
    /**
    * Método que utiliza para mensajes de información detallada que son útiles para debugear una aplicación
    * @param mens mensaje que se guardará en la tabla applog de la base de datos
    */
    public void debug(String mens){
        try{
            log.debug(mens);
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e.getMessage());
        }
        
    }
    
    /**
    * Método que se utiliza para mensajes de información que resaltan el progreso de la aplicación de una forma general
    * @param mens mensaje que se guardará en la tabla applog de la base de datos
    */
    public void info(String mens){
        try{
            log.info(mens);
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    /**
    * Método que se utiliza para situaciones que podrían ser potencialmente dañinas
    * @param mens mensaje que se guardará en la tabla applog de la base de datos
    */
    public void warn(String mens){
        try{
            log.warn(mens);
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    /**
    * Método que se utiliza para eventos de error que podrían permitir que la aplicación continúe ejecutándose
    * @param mens mensaje que se guardará en la tabla applog de la base de datos
    */
    public void error(String mens){
        try{
            log.error(mens);
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e.getMessage());
        }
    }
   
    /**
    * Método que se utiliza para errores muy graves, que podrían hacer que la aplicación dejara de funcionar
    * @param mens mensaje que se guardará en la tabla applog de la base de datos
    */
    public void fatal(String mens){
        try{
            log.fatal(mens);
        }
        catch(Exception e)
        {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
}
