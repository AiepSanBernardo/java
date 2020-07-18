/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.aiep.empleado.dao;


import cl.aiep.empleado.modelo.TurnoModel;
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
public class TurnoDAO extends DAOBase {
    
    
    public List<TurnoModel> getTurnos( int idTipoEmpleado ) {

        List<TurnoModel> turnos = new ArrayList<TurnoModel>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            StringBuilder query = getQuerySelect();
            query.append( " WHERE tipoempleadoId = ? ");
            
            pst = conn.prepareStatement(query.toString());
            pst.setInt(1, idTipoEmpleado);
            rs = pst.executeQuery();

            while (rs.next()) {
                TurnoModel model = toModel( rs );
                turnos.add(model);
            }
        } catch (SQLException e) {
            writeErrorConsole(e);
        } finally {
            closeConnection(conn, pst, rs);
        }
        return turnos;
    }
    
    public List<TurnoModel> getTurnos() {

        List<TurnoModel> turnos = new ArrayList<TurnoModel>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            StringBuilder query = getQuerySelect();
            pst = conn.prepareStatement(query.toString());
            rs = pst.executeQuery();

            while (rs.next()) {
                TurnoModel model = toModel( rs );
                turnos.add(model);
            }
        } catch (SQLException e) {
            writeErrorConsole(e);
        } finally {
            closeConnection(conn, pst, rs);
        }
        return turnos;
    }
     
    private StringBuilder getQuerySelect() {
        StringBuilder query = new StringBuilder();

        query.append(" SELECT ");
        query.append("  turnoId ");
        query.append(" ,tipoempleadoId ");
        query.append(" ,descripcion");        
        query.append(" FROM turno ");

        return query;
    }
    
    private TurnoModel toModel(ResultSet rs) {
        TurnoModel model = new TurnoModel();
        
        try{
            model.setTurnoId(rs.getInt("turnoId"));
            model.setTipoEmpleadoId(rs.getInt("tipoempleadoId"));
            model.setDescripcion(rs.getString("descripcion"));            
        }
        catch( SQLException ex ){
            writeErrorConsole( ex );
        }
        
        return model ;
    }
    
}
