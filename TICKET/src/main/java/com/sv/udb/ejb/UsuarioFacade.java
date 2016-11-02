/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Adonay
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "PILETPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    @Override
    public Usuario findByAcce(Object acce) {
        Query q = getEntityManager().createQuery("SELECT u FROM Usuario u WHERE u.acceUsua = :acceUsua AND u.estaUsua = :estaUsua", Usuario.class);        
        q.setParameter("acceUsua", acce);
        q.setParameter("estaUsua", 1);
        List resu = q.getResultList();
        return resu.isEmpty() ? null : (Usuario)resu.get(0);
    }
    
    @Override
    public boolean findPermByAcceAndDire(Object acce, Object role) {
        String query = "SELECT CASE COUNT(*) WHEN 0 THEN 'false' ELSE 'true' END AS permiso FROM (" +
                       "SELECT u.acce_usua, GET_URL_PATH(p.codi_perm) AS path FROM permiso p INNER JOIN permiso_rol pr ON p.codi_perm = pr.codi_perm " +
                       "INNER JOIN rol r ON pr.codi_role = r.codi_role INNER JOIN usuario_rol ur ON r.codi_role = ur.codi_role "
                     + "INNER JOIN usuario u ON ur.codi_usua = u.codi_usua \n" +
                       "WHERE p.esta_perm = 1 AND pr.esta_perm_role = 1 AND r.esta_role = 1 AND ur.esta_usua_role = 1 AND u.esta_usua = 1) T \n" +
                       "WHERE T.acce_usua = ?1 AND T.path = ?2";
        Query q = getEntityManager().createNativeQuery(query);
        q.setParameter(1, acce);
        q.setParameter(2, role);
        return ((String)q.getSingleResult()).equals("true");
    }
    
}
