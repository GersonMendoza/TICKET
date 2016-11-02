/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.utils.LOG4J;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.ServletContext;

/**
 *
 * @author Carlos
 */
@Named(value = "backupBean")
@ViewScoped
@ManagedBean
public class BackupBean implements Serializable{
    private LOG4J log;
    
    /**
     * Creates a new instance of BackupBean
     */
    public BackupBean() {
    }
    
    /**
    * Método que se ejecuta después de haber construido la clase e inicializa las variables
    */
    @PostConstruct
    public void init()
    {
        log = new LOG4J();
        log.debug("Se está creando una copia de respaldo.");
    }
    
    public void creaBackUp()
    {
        try 
        {
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String dumpExePath = "C:\\xampp\\mysql\\bin\\mysqldump.exe";
            //"C:\\wamp\\bin\\mysql\\mysql5.5.16\\bin\\mysqldump.exe"
            String host = "localhost";
            String port = "3306";
            String user = "root";
            String password="123456";
            String database = "sistemas_pilet";
            String basePath = ctx.getRealPath("/");
            //String backupPath = basePath + "BackupsMySQLdb//";
            String backupPath = "C:\\xampp\\mysql\\";
            Process p = null;
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();
            String filepath = database + "-" + host + "-(" + dateFormat.format(date) + ").sql";
            String batchCommand = "";
            if (password != "") {
                batchCommand = dumpExePath + " -h " + host + " --port " + port + " -u " + user + " --password=" + password + " --add-drop-database -B " + database + " -r \"" + backupPath + "" + filepath + "\"";
            } else {
                batchCommand = dumpExePath + " -h " + host + " --port " + port + " -u " + user + " --add-drop-database -B " + database + " -r \"" + backupPath + "" + filepath + "\"";
            }
            Runtime runtime = Runtime.getRuntime();
            p = runtime.exec(batchCommand);
            int processComplete = p.waitFor();
            if (processComplete == 0) {
                log.info("Backup created successfully for with DB " + database + " in " + host + ":" + port);
            } else {
                log.info("Could not create the backup for with DB " + database + " in " + host + ":" + port);
            }
 
        } catch (IOException ioe) {
            log.error("ERROR AL CREAR BACKUP");
        } catch (Exception e) {
            log.error("ERROR AL CREAR BACKUP");
        }
    }
}
