/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.aiep.empleado.dao;


import cl.aiep.empleado.modelo.TipoEmpleadoModel;
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
            query.append( " WHERE t.tipoempleadoId = ? ");
            
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
        query.append("  t.turnoId ");
        query.append(" ,t.tipoempleadoId ");
        query.append(" ,t.descripcion");                
        query.append(" ,te.descripcion ");        
        query.append(" FROM turno t ");        
        query.append("    INNER JOIN tipoempleado te ON  te.tipoEmpleadoId = t.tipoEmpleadoId ");

        return query;
    }
    
    private TurnoModel toModel(ResultSet rs) {
        TurnoModel turnoModel = new TurnoModel();
        TipoEmpleadoModel tipoEmpleadoModel = new TipoEmpleadoModel();
        
        try{
            turnoModel.setTurnoId(rs.getInt("turnoId"));
            turnoModel.setDescripcion(rs.getString("t.descripcion"));            
            tipoEmpleadoModel.setTipoEmpleadoId( rs.getInt("tipoempleadoId"));
            tipoEmpleadoModel.setDescripcion(rs.getString("te.descripcion"));            
            turnoModel.setTipoEmpleado( tipoEmpleadoModel);
            
        }
        catch( SQLException ex ){
            writeErrorConsole( ex );
        }
        
        return turnoModel ;
    }
    
}
