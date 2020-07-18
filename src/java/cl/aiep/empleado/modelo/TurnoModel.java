/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.aiep.empleado.modelo;

/**
 *
 * @author Daniel
 */
public class TurnoModel {

    /**
     * @return the turnoId
     */
    public int getTurnoId() {
        return turnoId;
    }

    /**
     * @param turnoId the turnoId to set
     */
    public void setTurnoId(int turnoId) {
        this.turnoId = turnoId;
    }

    /**
     * @return the tipoEmpleadoId
     */
    public int getTipoEmpleadoId() {
        return tipoEmpleadoId;
    }

    /**
     * @param tipoEmpleadoId the tipoEmpleadoId to set
     */
    public void setTipoEmpleadoId(int tipoEmpleadoId) {
        this.tipoEmpleadoId = tipoEmpleadoId;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    private int turnoId;
    private int tipoEmpleadoId;
    private String descripcion;
}
