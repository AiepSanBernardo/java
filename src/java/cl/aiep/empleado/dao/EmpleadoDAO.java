/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.aiep.empleado.dao;

import cl.aiep.empleado.modelo.EmpleadoModel;
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
public class EmpleadoDAO extends DAOBase {

    public boolean insert(EmpleadoModel model) {
        PreparedStatement pst = null;
        Connection conn = null;
        boolean status = false;

        try {
            conn = getConnection();
            pst = conn.prepareStatement(getQueryInsert().toString());
            pst.setString(1, model.getNombre());
            pst.setString(2, model.getApellidoPaterno());
            pst.setString(3, model.getApellidoMaterno());
            pst.setString(4, model.getMail());
            pst.setString(5, model.getTelefono());
            pst.setInt(6, model.getTipoEmpleado().getTipoEmpleadoId());
            pst.setInt(7, model.getTurno().getTurnoId());

            int afectados = pst.executeUpdate();
            status = (afectados > 0);

        } catch (SQLException e) {
            writeErrorConsole(e);
        } finally {
            closeConnection(conn, pst);
        }

        return status;
    }

    private StringBuilder getQueryInsert() {

        StringBuilder query = new StringBuilder();

        query.append(" INSERT INTO empleado ");
        query.append(" ( ");
        query.append("   nombre");
        query.append("  ,apellidoPaterno ");
        query.append("  ,apellidoMaterno ");
        query.append("  ,mail");
        query.append("  ,telefono");
        query.append("  ,tipoEmpleadoId");
        query.append("  ,turnoId");
        query.append("  ,fechaRegistro");
        query.append(" ) ");
        query.append(" VALUES ");
        query.append(" ( ");
        query.append("   ?");
        query.append("  ,?");
        query.append("  ,?");
        query.append("  ,?");
        query.append("  ,?");
        query.append("  ,?");
        query.append("  ,?");
        query.append("  ,sysdate()");
        query.append(" ) ");

        return query;
    }

    public boolean update(EmpleadoModel model) {
        PreparedStatement pst = null;
        Connection conn = null;
        boolean status = false;

        try {
            conn = getConnection();
            pst = conn.prepareStatement(getQueryUpdate().toString());
            pst.setString(1, model.getNombre());
            pst.setString(2, model.getApellidoPaterno());
            pst.setString(3, model.getApellidoMaterno());
            pst.setString(4, model.getMail());
            pst.setString(5, model.getTelefono());            
            //TipoEmpleadoModel tipoEmpleado = model.getTipoEmpleado();            
            //pst.setInt(6,  tipoEmpleado.getTipoEmpleadoId());            
            pst.setInt(6,  model.getTipoEmpleado().getTipoEmpleadoId());
            pst.setInt(7,  model.getTurno().getTurnoId());
            //pst.setInt(6,   model.get  .getTipoEmpleadoId());
            pst.setInt(8, model.getEmpleadoId());

            int afectados = pst.executeUpdate();
            status = (afectados > 0);

        } catch (SQLException e) {
            writeErrorConsole(e);
        } finally {
            closeConnection(conn, pst);
        }

        return status;
    }

    private StringBuilder getQueryUpdate() {

        StringBuilder query = new StringBuilder();

        query.append(" UPDATE empleado ");
        query.append(" SET ");
        query.append("   nombre = ?");
        query.append("  ,apellidoPaterno = ? ");
        query.append("  ,apellidoMaterno = ? ");
        query.append("  ,mail = ?");
        query.append("  ,telefono = ?");
        query.append("  ,tipoEmpleadoId = ?");
        query.append("  ,turnoId = ?");
        query.append(" WHERE ");
        query.append("    empleadoId = ? ");

        return query;
    }

    public List<EmpleadoModel> getEmpleados() {

        List<EmpleadoModel> empleados = new ArrayList<EmpleadoModel>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            StringBuilder query = getQuerySelect();
            pst = conn.prepareStatement(query.toString());
            rs = pst.executeQuery();

            while (rs.next()) {
                EmpleadoModel model = toModel( rs );
                empleados.add(model);
            }
        } catch (SQLException e) {
            writeErrorConsole(e);
        } finally {
            closeConnection(conn, pst, rs);
        }
        return empleados;
    }

    public EmpleadoModel getEmpleado(int id) {

        EmpleadoModel empleado = new EmpleadoModel();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            StringBuilder query = getQuerySelect();
            query.append(" WHERE empleadoId = ? ");
            pst = conn.prepareStatement(query.toString());
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {
                empleado = toModel( rs );
            }
        } catch (SQLException e) {
            writeErrorConsole(e);
        } finally {
            closeConnection(conn, pst, rs);
        }
        return empleado;
    }

    private EmpleadoModel toModel(ResultSet rs) {
        EmpleadoModel empleado = new EmpleadoModel();
        TipoEmpleadoModel tipoEmpleado = new TipoEmpleadoModel();
        TurnoModel turno = new TurnoModel(); 
        
        try{
            empleado.setEmpleadoId(rs.getInt("empleadoId"));
            empleado.setNombre(rs.getString("nombre"));
            empleado.setApellidoPaterno(rs.getString("apellidoPaterno"));
            empleado.setApellidoMaterno(rs.getString("apellidoMaterno"));
            empleado.setMail(rs.getString("mail"));
            empleado.setTelefono(rs.getString("telefono"));
            
            tipoEmpleado.setTipoEmpleadoId(rs.getInt("tipoEmpleadoId"));
            tipoEmpleado.setDescripcion(rs.getString("te.descripcion"));
            
            empleado.setTipoEmpleado( tipoEmpleado  );
            
            turno.setTurnoId(rs.getInt("turnoId"));
            turno.setDescripcion( rs.getString("t.descripcion"));
            turno.setTipoEmpleado(tipoEmpleado);            
            
            empleado.setTurno( turno);
            
        }
        catch( SQLException ex ){
            writeErrorConsole( ex );
        }
        
        return empleado ;
    }

    private StringBuilder getQuerySelect() {
        StringBuilder query = new StringBuilder();

        query.append(" SELECT ");
        query.append("  e.empleadoId ");
        query.append(" ,e.nombre");
        query.append(" ,e.apellidoPaterno ");
        query.append(" ,e.apellidoMaterno ");
        query.append(" ,e.mail ");
        query.append(" ,e.telefono ");
        query.append(" ,e.tipoEmpleadoId ");
        query.append(" ,te.descripcion ");
        query.append(" ,t.turnoId  ");
        query.append(" ,t.descripcion ");
        query.append(" FROM empleado e ");        
        query.append("  INNER JOIN tipoempleado	te ON te.tipoempleadoId = e.tipoEmpleadoId");
        query.append("  INNER JOIN turno	t  ON t.turnoId = e.turnoId");

        return query;
    }

    public boolean delEmpleados(int id) {
        Connection conn = null;
        PreparedStatement pst = null;
        boolean status = false;

        try {
            conn = getConnection();
            StringBuilder query = getQueryDelete();
            query.append(" WHERE empleadoId = ? ");
            pst = conn.prepareStatement(query.toString());
            pst.setInt(1, id);

            int afectados = pst.executeUpdate();
            status = (afectados > 0);

        } catch (SQLException e) {
            writeErrorConsole(e);
        } finally {
            closeConnection(conn, pst);
        }

        return status;
    }

    private StringBuilder getQueryDelete() {
        StringBuilder query = new StringBuilder();

        query.append(" DELETE ");
        query.append(" FROM empleado ");

        return query;
    }

}
