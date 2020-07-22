/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.aiep.empleado.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Daniel
 */
public class DAOBase {
    
    private Connection conn = null;
    //private final String dbName = "jdbc:mysql://localhost:3306/dbempleado?serverTimezone=UTC";
    
    private final String dbName = "jdbc:mysql://node55288-env-7674042.jl.serv.net.mx:3306/dbempleado";
    
    
    
    
    //private final String dbName = "jdbc:mysql://node55279-env-0463442.jl.serv.net.mx:3306/dbempleado";
    private final String driver = "com.mysql.jdbc.Driver";
    //private final String user = "Test";        
    //private final String pass = "1234_abcd_OK";
    //private final String user = "root";        
    //private final String pass = "ZNTgso48570";
    
    private final String user = "root";        
    private final String pass = "QHOfok15446";
    
    
    
    protected Connection getConnection(){
        try{            
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(dbName, user, pass);
        }
        catch( SQLException e){
            writeErrorConsole(e);
        }
        catch( Exception e){
            writeErrorConsole(e);
        }
    
        return conn;
    }
    
    protected void closeConnection( Connection conn, PreparedStatement pst, ResultSet rs){
    
        if( conn != null){
            try{
                conn.close();
            }
            catch( SQLException e){
                writeErrorConsole(e);    
            }
        }
        
        if( pst != null){
            try{
                pst.close();
            }
            catch( SQLException e){
                writeErrorConsole(e);
            }
        }
        
        if( rs != null){
            try{
                rs.close();
            }
            catch( SQLException e){
                writeErrorConsole(e);                
            }
        }
    }
    
    protected void closeConnection( Connection conn, PreparedStatement pst){        
        closeConnection( conn, pst, null);    
    }
    
    
    protected void writeErrorConsole( SQLException e){
        System.out.println( e );
        String mensaje = e.getMessage();
        int codigo = e.getErrorCode();
        String estado = e.getSQLState();
        System.out.println( "Inicio Error SQL " );
        System.out.println( mensaje + " " + codigo + " " +  estado ); 
        System.out.println( "Fin Error SQL " );
    }
    
    protected void writeErrorConsole( Exception e){
        System.out.println( e );
        String mensaje = e.getMessage();        
        System.out.println( "Inicio Error generico " );
        System.out.println( mensaje ); 
        System.out.println( "Fin Error generico " );
    }
    
    
    
    
    
    
}
