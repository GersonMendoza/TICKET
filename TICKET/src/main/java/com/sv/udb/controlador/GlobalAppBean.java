package com.sv.udb.controlador;

import com.sv.udb.ejb.UsuarioFacadeLocal;
import com.sv.udb.modelo.Notificacion;
import com.sv.udb.modelo.Usuario;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 * Esta clase contiene métodos que son utilizados en todo el sistema
 * @author: Adonay Santos
 * 
 * @version: Prototipo 1
 */
@Named(value = "globalAppBean")
@ApplicationScoped
public class GlobalAppBean {

    @EJB
    private UsuarioFacadeLocal FCDEUsua;   
    @Inject
    private LoginBean logiBean; //Bean de session
    
    /**
     * Constructor de la clase
     */
    public GlobalAppBean() {
    }
    
    /**
     * Método que devuelve la ruta completa de una página dentro sistema
     * @param page el nombre de la página que se quiere encontrar
     * @return la ruta de la ubicación de la página
     */
    public String getUrl(String page)
    {
        String resp;
        FacesContext facsCtxt = FacesContext.getCurrentInstance();
        String prefix = facsCtxt.getExternalContext().getInitParameter("prefix");
        resp = String.format("%s/%s/%s", facsCtxt.getExternalContext().getRequestContextPath(), prefix, page);
        return resp;
    }
    
    /**
     * Método que devuelve la ruta completa de un archivo dentro del servidor
     * @param file el nombre del archivo que se quiere encontrar
     * @return la ruta completa de la ubicación del archivo
     */
    public String getResourcePath(String file)
    {
        String resp;      
        FacesContext facsCtxt = FacesContext.getCurrentInstance();
        String resoPath = facsCtxt.getExternalContext().getInitParameter("javax.faces.WEBAPP_RESOURCES_DIRECTORY");
        resp = String.format("%s/%s", resoPath, file);
        resp = facsCtxt.getExternalContext().getRealPath(resp);
        return resp;
    }
    
    public StreamedContent getImage(String imag) throws IOException {
        FacesContext facsCtxt = FacesContext.getCurrentInstance();
        if (facsCtxt.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            return new DefaultStreamedContent(new FileInputStream(getResourcePath(imag)));
        }
    }
    
    public boolean getEstaPermByPage(String usua, String page)
    {
        /*
        * page tiene que ser enviada en el siguiente formato: /Plantilla/poo/modulo/pagina.xhtml
        */
        try
        {
            return FCDEUsua.findPermByAcceAndDire(usua, page);
        }
        catch(Exception ex)
        {
            return false;
        }
    }
    
    public void setNotificacion(Usuario codi, String mens, String modu, String path)
    {
        try {
            
            NotificacionBean obje = new NotificacionBean();
            obje.guar(codi, mens, modu, path);
        } catch (Exception e) {
            
        }
    }
    
    public boolean getEstaPermByName(String role)
    {
        this.logiBean = this.logiBean != null ? this.logiBean : new LoginBean();
        if(logiBean.isLoge())
        {
            return getEstaPermByName(logiBean.getObjeUsua().getAcceUsua(), role);
        }
        else
        {
            return false;
        }
    }
    
    public boolean getEstaPermByName(String usua, String role)
    {
        /*
        * role tiene que ser enviado según el dire_role de la tabla roles
        */
        try
        {
            HttpServletRequest requ = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String page = String.format("%s%s%s", requ.getContextPath(), requ.getServletPath(), role);
            System.out.println(page);
            return FCDEUsua.findPermByAcceAndDire(usua, page);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
    
    public String getAppName(String page)
    {
        String resp = "";
        try
        {
            HttpServletRequest requ = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            resp = String.format("%s%s%s", requ.getContextPath(), requ.getServletPath(), page); //Retorna /Plantilla/poo/modulo/pagina.xhtml
        }
        catch(Exception ex)
        {
            System.err.println("Error: " + ex.getMessage());
        }
        return resp;
    }
}
