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
     * @return the tipoEmpleado
     */
    public TipoEmpleadoModel getTipoEmpleado() {
        return tipoEmpleado;
    }

    /**
     * @param tipoEmpleado the tipoEmpleado to set
     */
    public void setTipoEmpleado(TipoEmpleadoModel tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

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
    private TipoEmpleadoModel tipoEmpleado;
    private String descripcion;
}
