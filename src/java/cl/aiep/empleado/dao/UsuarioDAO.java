/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.aiep.empleado.dao;

import cl.aiep.empleado.modelo.UsuarioModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class UsuarioDAO extends DAOBase {
    
    public UsuarioModel getUsuario( String nick  ) {

        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        UsuarioModel model = new UsuarioModel();
        try {
            conn = getConnection();
            StringBuilder query = getQuerySelect();
            query.append(" WHERE nick = ? ");
            
            pst = conn.prepareStatement(query.toString());
            pst.setString(1, nick);
            
            rs = pst.executeQuery();

            while (rs.next()) {
                model = toModel( rs );
                
            }
        } catch (SQLException e) {
            writeErrorConsole(e);
        } finally {
            closeConnection(conn, pst, rs);
        }
        return model;
    }
     
    private StringBuilder getQuerySelect() {
        StringBuilder query = new StringBuilder();

        query.append(" SELECT ");
        query.append("  usuarioId ");
        query.append(" ,nick ");
        query.append(" ,password ");        
        query.append(" FROM usuario ");

        return query;
    }
    
    private UsuarioModel toModel(ResultSet rs) {
        UsuarioModel model = new UsuarioModel();
        
        try{
            model.setUsuarioId(rs.getInt("usuarioId"));
            model.setNick(rs.getString("nick"));
            model.setPassword( rs.getString("password"));            
        }
        catch( SQLException ex ){
            writeErrorConsole( ex );
        }
        
        return model ;
    }
    
}
