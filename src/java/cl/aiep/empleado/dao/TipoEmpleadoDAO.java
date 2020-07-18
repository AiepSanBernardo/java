/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.aiep.empleado.dao;

import cl.aiep.empleado.modelo.TipoEmpleadoModel;
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
public class TipoEmpleadoDAO extends DAOBase {
    public List<TipoEmpleadoModel> getTiposEmpleado() {

        List<TipoEmpleadoModel> tiposEmpleado = new ArrayList<TipoEmpleadoModel>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            StringBuilder query = getQuerySelect();
            pst = conn.prepareStatement(query.toString());
            rs = pst.executeQuery();

            while (rs.next()) {
                TipoEmpleadoModel model = toModel( rs );
                tiposEmpleado.add(model);
            }
        } catch (SQLException e) {
            writeErrorConsole(e);
        } finally {
            closeConnection(conn, pst, rs);
        }
        return tiposEmpleado;
    }
     
     public TipoEmpleadoModel getTipoEmpleado( int id) {

        TipoEmpleadoModel tipoEmpleado =  new TipoEmpleadoModel();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            StringBuilder query = getQuerySelect();
            query.append( " WHERE tipoempleadoId = ? ");                    
            pst = conn.prepareStatement(query.toString());
            pst.setInt( 1, id );
            rs = pst.executeQuery();

            while (rs.next()) {
                tipoEmpleado = toModel( rs );                
            }
        } catch (SQLException e) {
            writeErrorConsole(e);
        } finally {
            closeConnection(conn, pst, rs);
        }
        return tipoEmpleado;
    }
    
    private StringBuilder getQuerySelect() {
        StringBuilder query = new StringBuilder();

        query.append(" SELECT ");
        query.append("  tipoEmpleadoId ");
        query.append(" ,descripcion");        
        query.append(" FROM tipoempleado ");

        return query;
    }
    
    private TipoEmpleadoModel toModel(ResultSet rs) {
        TipoEmpleadoModel model = new TipoEmpleadoModel();
        
        try{
            model.setTipoEmpleadoId(rs.getInt("tipoEmpleadoId"));
            model.setDescripcion(rs.getString("descripcion"));            
        }
        catch( SQLException ex ){
            writeErrorConsole( ex );
        }
        
        return model ;
    }
     
    
}
