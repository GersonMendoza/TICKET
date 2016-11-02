/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.utils;

import com.sv.udb.controlador.GlobalAppBean;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sv.udb.controlador.LoginBean;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Roberto Cerón
 */
public class FilterManager implements Filter {
    @Inject
    private LoginBean logiBean; //Bean de session
    
    @Inject
    private GlobalAppBean globalAppBean; //Bean de Aplicación

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("************************************************");
        System.out.println("***** Iniciando Despegue De la Aplicación ******");
        System.out.println("************************************************");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServRequ = (HttpServletRequest) request;
        String page = httpServRequ.getRequestURI(); //Página actual
        HttpServletResponse httpServResp = (HttpServletResponse) response;
        //Nombre del Contexto
        String nombCtxt = String.format("%s%s/", httpServRequ.getContextPath(), httpServRequ.getServletPath());
        List<String> initPage = new ArrayList<>(); //Páginas iniciales
        initPage.add(httpServRequ.getContextPath() + "/");
        initPage.add(nombCtxt + "login.xhtml");
        List<String> ignoPageFilt = new ArrayList<>(); //Páginas ignoradas por el filtro sin login
        ignoPageFilt.addAll(initPage);
        ignoPageFilt.add(nombCtxt + "otra.xhtml");
        List<String> ignoPageFiltLogi = new ArrayList<>(); //Páginas ignoradas por el filtro con login
        ignoPageFiltLogi.add(nombCtxt + "index.xhtml");
        ignoPageFiltLogi.add(nombCtxt + "error.xhtml");
        this.logiBean = this.logiBean != null ? this.logiBean : new LoginBean();
        try
        {
             // Si esta en pagina inicial y está logeado
            if(initPage.contains(page) && this.logiBean.isLoge())
            {
                httpServResp.sendRedirect(nombCtxt + "index.xhtml"); //Se va para el index
            }
            if(ignoPageFilt.contains(page)) //Si está en las páginas ignoradas, deja pasar
            {
                chain.doFilter(request, response);
            }
            else
            {
                if(logiBean.isLoge()) //Si está logeado
                {
                    if(globalAppBean.getEstaPermByPage(logiBean.getObjeUsua().getAcceUsua(), page)
                            || ignoPageFiltLogi.contains(page)) // Tiene permiso para esta página? o está en las ignoradas por el filtro, deja pasar
                    {
                        chain.doFilter(request, response);
                    }
                    else //Sino tiene permiso, se va a la página de error
                    {
                        httpServResp.sendRedirect(nombCtxt + "error.xhtml");
                    }
                }
                else // Sino enviarlo al login
                {
                    httpServResp.sendRedirect(nombCtxt + "login.xhtml");
                }
            }
        }
        catch (Exception e) {
        }
    }

    @Override
    public void destroy() {
        System.out.println("**************************************************");
        System.out.println("***** Finalizando Despliegue De la Aplicación ******");
        System.out.println("**************************************************");
    }

}
